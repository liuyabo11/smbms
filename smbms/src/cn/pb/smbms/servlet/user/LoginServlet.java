package cn.pb.smbms.servlet.user;

/**
 * 登录的servlet
 */
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.pb.smbms.pojo.User;
import cn.pb.smbms.service.user.UserService;
import cn.pb.smbms.service.user.UserServiceImpl;
import cn.pb.smbms.util.Constants;

@WebServlet("/login.do")
public class LoginServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 获取用户名和密码
		String userCode = request.getParameter("userCode");
		String userPassword = request.getParameter("userPassword");
		// 调用service层方法
		UserService service = new UserServiceImpl();
		User user = service.login(userCode, userPassword);

		if (null != user) {// 登陆成功
			// 将user放入作用域
			request.getSession().setAttribute(Constants.USER_SESSION, user);
			// 重定向到welcome.jsp页面
			response.sendRedirect("jsp/welcome.jsp");
		} else {
			// 转发到login.jsp 并带出错去提示
			request.setAttribute("erro", "用户名或密码不正确！");
			request.getRequestDispatcher("login.jsp")
					.forward(request, response);
		}

	}
}
