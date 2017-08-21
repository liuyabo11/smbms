package cn.pb.smbms.servlet.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
/**
 * 删除用户的servlet
 */
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.pb.smbms.service.user.UserService;
import cn.pb.smbms.service.user.UserServiceImpl;

import com.alibaba.fastjson.JSONArray;

@WebServlet("/delete")
public class DeleteUserServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 获取ajax传过来的参数
		String id = request.getParameter("uid");
		Integer delId = 0;
		try {
			delId = Integer.parseInt(id);
		} catch (Exception e) {
			delId = 0;
		}
		// hashMap集合 用于存放标记
		HashMap<String, String> resultMap = new HashMap<String, String>();
		if (delId <= 0) {
			resultMap.put("delResult", "notexist");
		} else {
			UserService service = new UserServiceImpl();
			if (service.deleteUserById(delId)) {
				resultMap.put("delResult", "true");
			} else {
				resultMap.put("delResult", "false");
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
