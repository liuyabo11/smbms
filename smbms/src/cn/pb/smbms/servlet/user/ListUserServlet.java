package cn.pb.smbms.servlet.user;

/**
 * 查询用户列表的servlet
 */
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
/**
 * 查询用户列表的servlet
 */
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.pb.smbms.pojo.User;
import cn.pb.smbms.service.user.UserService;
import cn.pb.smbms.service.user.UserServiceImpl;

@WebServlet("/user.do")
public class ListUserServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 获取前台输入的模糊查询条件
		String searchName = request.getParameter("searchName");
		// 创建集合接收数据
		List<User> list = new ArrayList<User>();
		// 调用service层方法
		UserService service = new UserServiceImpl();
		if (searchName == null) {
			searchName = "";
		}
		list = service.getUserList(searchName);

		// 将list放到作用域中
		request.setAttribute("userList", list);
		request.setAttribute("searchName", searchName);
		// 转发到userList.jsp页面
		request.getRequestDispatcher("jsp/userList.jsp").forward(request,
				response);

	}

}
