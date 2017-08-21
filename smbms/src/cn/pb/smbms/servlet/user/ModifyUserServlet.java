package cn.pb.smbms.servlet.user;

/**
 * 修改的servlet
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

import com.mysql.jdbc.StringUtils;

@WebServlet("/modify")
public class ModifyUserServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 获取从userlist.js传过来的参数 uid
		String id = request.getParameter("uid");
		System.out.println("需要修改的用户id====>" + id);
		if (!StringUtils.isNullOrEmpty(id)) {// 非空
			// 掉用service层方法
			UserService service = new UserServiceImpl();
			User user = service.getUserById(id);
			// 把返回的user放到作用域中
			request.setAttribute("user", user);
			// 跳转到修改的页面
			request.getRequestDispatcher("jsp/userModify.jsp").forward(request,
					response);
		}

	}

}
