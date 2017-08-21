package cn.pb.smbms.servlet.provider;

/**
 * 修改供应商的servlet 先根据id获得这个供应商对象 然后回显数据进行修改
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

@WebServlet("/modifyProvider")
public class ModifyProviderServlet extends HttpServlet {

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
		if (!StringUtils.isNullOrEmpty(proid)) {
			// 调用service层方法
			ProviderService service = new ProviderServiceImpl();
			Provider provider = service.getProviderById(proid);
			// 将provider放到作用域中
			request.setAttribute("provider", provider);
			// 转发到修改页面 providerModify.jsp
			request.getRequestDispatcher("jsp/providerModify.jsp").forward(
					request, response);
		}
	}

}
