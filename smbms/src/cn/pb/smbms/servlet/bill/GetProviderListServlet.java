package cn.pb.smbms.servlet.bill;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.pb.smbms.pojo.Provider;
import cn.pb.smbms.service.provider.ProviderService;
import cn.pb.smbms.service.provider.ProviderServiceImpl;

import com.alibaba.fastjson.JSONArray;

@WebServlet("/getproviderlist")
public class GetProviderListServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<Provider> providerliList = new ArrayList<Provider>();
		ProviderService service = new ProviderServiceImpl();
		// 调用service层方法
		providerliList = service.getProvidersList("");

		// 将providerList转换成json对象输出
		response.setContentType("application/json");
		PrintWriter writer = response.getWriter();
		writer.write(JSONArray.toJSONString(providerliList));
		writer.flush();
		writer.close();
	}

}
