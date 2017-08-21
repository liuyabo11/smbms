package cn.pb.smbms.servlet.user;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
/**
 * 增加用户的servlet
 */
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.pb.smbms.pojo.User;
import cn.pb.smbms.service.user.UserService;
import cn.pb.smbms.service.user.UserServiceImpl;
import cn.pb.smbms.util.Constants;

@WebServlet("/useradd.do")
public class AddUserServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 获取前台表单数据
		String userCode = request.getParameter("userCode");
		String userName = request.getParameter("userName");
		String userPassword = request.getParameter("userPassword");
		String gender = request.getParameter("gender");
		String birthday = request.getParameter("birthday");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		String userType = request.getParameter("userType");
		// 创建对象
		User user = new User();
		user.setUserCode(userCode);
		user.setUserName(userName);
		user.setUserPassword(userPassword);
		user.setAddress(address);
		try {
			user.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse(birthday));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		user.setGender(Integer.valueOf(gender));
		user.setPhone(phone);
		user.setUserType(Integer.valueOf(userType));
		user.setCreateDate(new Date());
		user.setCreatedBy(((User) request.getSession().getAttribute(
				Constants.USER_SESSION)).getId());
		System.out.println("创建者编号====》"
				+ ((User) request.getSession().getAttribute(
						Constants.USER_SESSION)).getId());
		UserService service = new UserServiceImpl();
		if (service.add(user)) {// 登录成功
			// 重定向到user.do这个servlet 数据回显
			response.sendRedirect("user.do");
			// request.getRequestDispatcher("user.do").forward(request,
			// response);
		} else {
			// 转发到userAdd.jsp页面
			request.getRequestDispatcher("jsp/userAdd.jsp").forward(request,
					response);
		}
	}

}
