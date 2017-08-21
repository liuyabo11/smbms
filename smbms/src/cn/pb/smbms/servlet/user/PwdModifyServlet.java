package cn.pb.smbms.servlet.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
/**
 * 修改密码的servlet
 */
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.pb.smbms.pojo.User;
import cn.pb.smbms.util.Constants;

import com.alibaba.fastjson.JSONArray;
import com.mysql.jdbc.StringUtils;

@WebServlet("/pwdmodify")
public class PwdModifyServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 获取session中的user对象
		Object o = request.getSession().getAttribute(Constants.USER_SESSION);
		// 获取前台输入的旧密码
		String oldpassword = request.getParameter("oldpassword");

		// 创建hashMap保存标记
		HashMap<String, String> resultMap = new HashMap<String, String>();

		if (null != o && !StringUtils.isNullOrEmpty(oldpassword)) {
			// 获取session中user对象中的密码 与旧密码进行对比
			String sessionPwd = ((User) o).getUserPassword();
			if (oldpassword.equals(sessionPwd)) {
				resultMap.put("result", "true");
			} else {
				resultMap.put("result", "false");
			}
		} else {
			resultMap.put("result", "error");
		}
		// 将resultMap 以json形式输出
		response.setContentType("application/json");
		PrintWriter writer = response.getWriter();
		writer.write(JSONArray.toJSONString(resultMap));
		writer.flush();
		writer.close();
	}

}
