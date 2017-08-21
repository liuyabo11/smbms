package cn.pb.smbms.servlet.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
/**
 * 后台验证ajax的servlet
 * 判断用户帐号是否存在
 */
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.pb.smbms.pojo.User;
import cn.pb.smbms.service.user.UserService;
import cn.pb.smbms.service.user.UserServiceImpl;

import com.alibaba.fastjson.JSONArray;
import com.mysql.jdbc.StringUtils;

@WebServlet("/ucexist")
public class UcexistServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 获取ajax传过来的参数
		String userCode = request.getParameter("userCode");
		// 存放标志exist 判断是否存在userCode
		HashMap<String, String> resultMap = new HashMap<String, String>();
		// 判断userCode是否为Null或Empty
		if (StringUtils.isNullOrEmpty(userCode)) {
			resultMap.put("userCode", "exist");
		} else {
			UserService service = new UserServiceImpl();
			User user = service.selectUserCodeExist(userCode);

			if (null != user) {// 说明账号已存在
				resultMap.put("userCode", "exist");
			} else {// 账号不存在 可以使用
				resultMap.put("userCode", "notexist");
			}
		}

		/**
		 * 把resultMap转为json字符串 以json的形式输出
		 */
		// 01.配置上下文的输出类型
		response.setContentType("application/json");
		// 02.从response对象中获取往外输出的writer的对象
		PrintWriter writer = response.getWriter();
		// 03.把resultMap转为json字符串 输出
		writer.write(JSONArray.toJSONString(resultMap));
		writer.flush();
		writer.close();
	}

}
