package cn.pb.smbms.servlet.bill;

import java.io.IOException;
import java.util.List;

/**
 * 账单列表的servlet
 */
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.pb.smbms.pojo.Bill;
import cn.pb.smbms.pojo.Provider;
import cn.pb.smbms.service.bill.BillService;
import cn.pb.smbms.service.bill.BillServiceImpl;
import cn.pb.smbms.service.provider.ProviderService;
import cn.pb.smbms.service.provider.ProviderServiceImpl;

import com.mysql.jdbc.StringUtils;

@WebServlet("/bill.do")
public class ListBillServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 获取前台输入的模糊查询条件
		String searchName = request.getParameter("searchName");
		String providerID = request.getParameter("providerID");
		String isPayment = request.getParameter("isPayment");
		// 创建对象 将条件封装到对象
		if (StringUtils.isNullOrEmpty(searchName)) {
			searchName = "";
		}
		if (StringUtils.isNullOrEmpty(providerID)) {
			providerID = "0";
		}
		if (StringUtils.isNullOrEmpty(isPayment)) {
			isPayment = "0";
		}
		Bill bill = new Bill();
		bill.setProductName(searchName);
		bill.setProviderId(Integer.parseInt(providerID));
		bill.setIsPayment(Integer.parseInt(isPayment));
		// 查询供应商编号对应的供应商的名称
		ProviderService pService = new ProviderServiceImpl();
		List<Provider> pList = pService.getProvidersList("");

		// 调用service层方法
		BillService service = new BillServiceImpl();
		List<Bill> list = service.getBillList(bill);
		// 将返回的集合 放到作用域
		request.setAttribute("providerList", pList);
		request.setAttribute("billList", list);
		request.setAttribute("searchName", searchName);
		request.setAttribute("providerID", providerID);
		request.setAttribute("isPayment", isPayment);
		request.getRequestDispatcher("jsp/billList.jsp").forward(request,
				response);

	}

}
