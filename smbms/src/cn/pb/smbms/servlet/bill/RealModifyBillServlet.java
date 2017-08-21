package cn.pb.smbms.servlet.bill;

/**
 * 真正的修改
 */
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.pb.smbms.pojo.Bill;
import cn.pb.smbms.pojo.User;
import cn.pb.smbms.service.bill.BillService;
import cn.pb.smbms.service.bill.BillServiceImpl;
import cn.pb.smbms.util.Constants;

@WebServlet("/realModifyBill")
public class RealModifyBillServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 获取前台表单数据
		String billid = request.getParameter("billid");
		String billCode = request.getParameter("billCode");
		String productName = request.getParameter("productName");
		String productUnit = request.getParameter("productUnit");
		String productCount = request.getParameter("productCount");
		String totalPrice = request.getParameter("totalPrice");
		String providerId = request.getParameter("providerId");
		String isPayment = request.getParameter("isPayment");
		// 创建对象
		Bill bill = new Bill();
		// 给对象赋值
		bill.setId(Integer.parseInt(billid));
		bill.setBillCode(billCode);
		bill.setProductName(productName);
		bill.setProductUnit(productUnit);

		bill.setProductCount(new BigDecimal(productCount).setScale(2,
				BigDecimal.ROUND_DOWN));
		bill.setTotalPrice(new BigDecimal(totalPrice).setScale(2,
				BigDecimal.ROUND_DOWN));
		bill.setIsPayment(Integer.parseInt(isPayment));
		bill.setProviderId(Integer.parseInt(providerId));
		bill.setModifyDate(new Date());

		bill.setModifyBy(((User) request.getSession().getAttribute(
				Constants.USER_SESSION)).getId());
		// 调用service层方法
		BillService service = new BillServiceImpl();
		if (service.addBill(bill)) {
			// 重定向到bill.do 数据回显
			response.sendRedirect("bill.do");
		} else {
			// 转发到billAdd.jsp页面
			request.getRequestDispatcher("jsp/billModify.jsp").forward(request,
					response);
		}
	}
}
