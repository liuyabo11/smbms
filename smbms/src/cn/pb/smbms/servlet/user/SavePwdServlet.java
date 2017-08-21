package cn.pb.smbms.servlet.user;

/**
 * 保存修改之后密码的servlet
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

import com.mysql.jdbc.StringUtils;

@WebServlet("/savepwd")
public class SavePwdServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 根据session中的user对象 获取id
		Object o = request.getSession().getAttribute(Constants.USER_SESSION);
		// 获取前台输入的 新密码
		String newpassword = request.getParameter("newpassword");
		boolean flag = false;

		if (null != o && !StringUtils.isNullOrEmpty(newpassword)) {
			UserService service = new UserServiceImpl();
			flag = service.updatePwd(((User) o).getId(), newpassword);
			if (flag) {
				request.setAttribute(Constants.SYS_MESSAGE, "修改密码成功！");
			} else {
				request.setAttribute(Constants.SYS_MESSAGE, "修改密码失败！");
			}
		} else {
			request.setAttribute(Constants.SYS_MESSAGE, "修改密码失败！");
		}

		request.getRequestDispatcher("jsp/password.jsp").forward(request,
				response);
	}

}
