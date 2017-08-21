package cn.pb.smbms.servlet.provider;

/**
 * 查看供应商的servlet
 */
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.pb.smbms.pojo.Provider;
import cn.pb.smbms.service.provider.ProviderService;
import cn.pb.smbms.service.provider.ProviderServiceImpl;

import com.mysql.jdbc.StringUtils;

@WebServlet("/viewProvider")
public class ViewProviderServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 获取从providerlist.js传过来的参数 proid
		String proid = request.getParameter("proid");
		System.out.println("需要查看的供应商id====>" + proid);
		if (!StringUtils.isNullOrEmpty(proid)) {
			// 掉用service层方法
			ProviderService service = new ProviderServiceImpl();
			// 根据id查到供应商
			Provider provider = service.getProviderById(proid);
			// 把返回的provider放到作用域
			request.setAttribute("provider", provider);
			// 跳转到查看的页面
			request.getRequestDispatcher("jsp/providerView.jsp").forward(
					request, response);
		}
	}

}
