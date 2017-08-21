package cn.pb.smbms.servlet.provider;

/**
 * 增加供应商的servlet
 */
import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.pb.smbms.pojo.Provider;
import cn.pb.smbms.pojo.User;
import cn.pb.smbms.service.provider.ProviderService;
import cn.pb.smbms.service.provider.ProviderServiceImpl;
import cn.pb.smbms.util.Constants;

@WebServlet("/provideradd.do")
public class AddProviderServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 获取前台表单数据
		String proCode = request.getParameter("proCode");
		String proName = request.getParameter("proName");
		String proContact = request.getParameter("proContact");
		String proPhone = request.getParameter("proPhone");
		String proAddress = request.getParameter("proAddress");
		String proFax = request.getParameter("proFax");
		String proDesc = request.getParameter("proDesc");
		// 创建对象
		Provider provider = new Provider();
		// 给对象赋值
		provider.setProCode(proCode);
		provider.setProName(proName);
		provider.setProContact(proContact);
		provider.setProPhone(proPhone);
		provider.setProAddress(proAddress);
		provider.setProFax(proFax);
		provider.setProDesc(proDesc);
		// 被创建者
		provider.setCreatedBy(((User) request.getSession().getAttribute(
				Constants.USER_SESSION)).getId());
		System.out.println("创建者id===>"
				+ ((User) request.getSession().getAttribute(
						Constants.USER_SESSION)).getId());
		provider.setCreationDate(new Date());

		// 调用service层方法
		ProviderService service = new ProviderServiceImpl();
		boolean flag = service.addProvider(provider);
		if (flag) {
			// 重定向到provider.do 数据回显
			response.sendRedirect("provider.do");
		} else {
			// 转发到providerAdd.jsp页面
			request.getRequestDispatcher("jsp/providerAdd.jsp").forward(
					request, response);
		}
	}

}
