package cn.pb.smbms.servlet.bill;

/**
 * 修改账单的servlet 先根据id获得这个账单对象 然后回显数据进行修改
 */
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.pb.smbms.pojo.Bill;
import cn.pb.smbms.service.bill.BillService;
import cn.pb.smbms.service.bill.BillServiceImpl;

import com.mysql.jdbc.StringUtils;

@WebServlet("/modifyBill")
public class ModifyBillServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 获取从billlist.js中传过来的参数billid
		String billid = request.getParameter("billid");
		if (!StringUtils.isNullOrEmpty(billid)) {
			// 调用service层方法
			BillService service = new BillServiceImpl();
			Bill bill = service.getBillById(billid);
			// 将返回的bill对象放到作用域中
			request.setAttribute("bill", bill);
			// 转发到修改页面billModify.jsp
			request.getRequestDispatcher("jsp/billModify.jsp").forward(request,
					response);
		}
	}

}
