package cn.pb.smbms.servlet.bill;

/**
 * 查看账单的servlet
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

@WebServlet("/viewBill")
public class ViewBillServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 获取从billlist.js传过来的参数 billid
		String billid = request.getParameter("billid");
		System.out.println("需要查看的账单id====>" + billid);
		// 调用service层方法
		BillService service = new BillServiceImpl();
		Bill bill = service.getBillById(billid);

		request.setAttribute("bill", bill);
		// 跳转到查看的页面
		request.getRequestDispatcher("jsp/billView.jsp").forward(request,
				response);
	}

}
