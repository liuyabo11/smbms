package cn.pb.smbms.servlet.bill;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.pb.smbms.pojo.Bill;
import cn.pb.smbms.service.bill.BillService;
import cn.pb.smbms.service.bill.BillServiceImpl;

import com.alibaba.fastjson.JSONArray;
import com.mysql.jdbc.StringUtils;

@WebServlet("/delBill")
public class DeleteBillServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 获取ajax传过来的参数
		String billid = request.getParameter("billid");
		// 创建集合存放标识
		HashMap<String, String> resultMap = new HashMap<String, String>();
		if (StringUtils.isNullOrEmpty(billid)) {
			resultMap.put("result", "notexist");
		} else {
			Bill bill = new Bill();
			bill.setId(Integer.parseInt(billid));
			// 调用service层方法
			BillService service = new BillServiceImpl();
			if (service.deleteBillById(bill)) {
				resultMap.put("result", "true");
			} else {
				resultMap.put("result", "false");
			}
		}

		// 把resultMap转换成json对象 输出
		response.setContentType("application/json");
		PrintWriter writer = response.getWriter();
		writer.write(JSONArray.toJSONString(resultMap));
		writer.flush();
		writer.close();

	}

}
