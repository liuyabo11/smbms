package cn.pb.smbms.servlet.user;

/**
 * 退出系统的servlet
 */
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.pb.smbms.util.Constants;

@WebServlet("/logout.do")
public class LogoutServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// 清除session
		req.getSession().removeAttribute(Constants.USER_SESSION);
		// 重定向到login.jsp
		resp.sendRedirect("login.jsp");
	}

}
