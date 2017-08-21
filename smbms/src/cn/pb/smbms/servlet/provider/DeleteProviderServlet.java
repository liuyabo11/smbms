package cn.pb.smbms.servlet.provider;

/**
 * 删除供应商的servlet
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.pb.smbms.pojo.Provider;
import cn.pb.smbms.service.provider.ProviderService;
import cn.pb.smbms.service.provider.ProviderServiceImpl;

import com.alibaba.fastjson.JSONArray;
import com.mysql.jdbc.StringUtils;

@WebServlet("/delProvider")
public class DeleteProviderServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 获取ajax传过来的参数
		String id = request.getParameter("proid");
		// 创建集合存放标识
		HashMap<String, String> resultMap = new HashMap<String, String>();
		if (StringUtils.isNullOrEmpty(id)) {
			// 供应商不存在
			resultMap.put("result", "notexist");
		} else {
			ProviderService service = new ProviderServiceImpl();
			Provider provider = new Provider();
			provider.setId(Integer.parseInt(id));
			int num = service.delProviderById(provider);
			if (num == 0) {
				// 成功删除
				resultMap.put("result", "delsuccess");
			} else if (num == -1) {
				// 删除失败
				resultMap.put("result", "delfailed");
			} else if (num > 0) {
				// 该供应商下有账单 不能删除
				resultMap.put("result", "notdel");
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
