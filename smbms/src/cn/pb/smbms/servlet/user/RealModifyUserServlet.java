package cn.pb.smbms.servlet.user;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.pb.smbms.pojo.User;
import cn.pb.smbms.service.user.UserService;
import cn.pb.smbms.service.user.UserServiceImpl;
import cn.pb.smbms.util.Constants;

/**
 * 真正的修改的servlet
 * 
 * @author Administrator
 * 
 */
@WebServlet("/realModify")
public class RealModifyUserServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 获取前台数据
		String id = request.getParameter("userid");
		System.out.println("要修改的用户id===>" + id);
		String userName = request.getParameter("userName");
		String gender = request.getParameter("gender");
		String birthday = request.getParameter("birthday");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		String userType = request.getParameter("userType");
		// 创建对象
		User user = new User();
		// 给对象赋值
		user.setId(Integer.valueOf(id));
		user.setUserName(userName);
		user.setGender(Integer.valueOf(gender));
		try {
			user.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse(birthday));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		user.setPhone(phone);
		user.setAddress(address);
		user.setUserType(Integer.valueOf(userType));

		user.setModifyBy(((User) request.getSession().getAttribute(
				Constants.USER_SESSION)).getId());
		user.setModifyDate(new Date());
		System.out.println("被修改者id========》"
				+ ((User) request.getSession().getAttribute(
						Constants.USER_SESSION)).getId());
		// 调用service层方法
		UserService service = new UserServiceImpl();
		if (service.modifyById(user)) {
			// 成功！跳转到显示页面
			request.getRequestDispatcher("user.do").forward(request, response);
		} else {
			request.getRequestDispatcher("jsp/userModify.jsp").forward(request,
					response);
		}
		;
	}

}
