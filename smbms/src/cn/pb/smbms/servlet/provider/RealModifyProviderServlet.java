package cn.pb.smbms.servlet.provider;

/**
 * 真正的修改供应商的servlet
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

@WebServlet("/realModifyProvider")
public class RealModifyProviderServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 获取前台数据
		String id = request.getParameter("proid");
		System.out.println("要修改的供应商的编号==》" + id);
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
		provider.setId(Integer.parseInt(id));
		provider.setProCode(proCode);
		provider.setProName(proName);
		provider.setProContact(proContact);
		provider.setProPhone(proPhone);
		provider.setProAddress(proAddress);
		provider.setProDesc(proDesc);
		provider.setProFax(proFax);
		// 修改时间
		provider.setModifyDate(new Date());
		// 修改者
		provider.setModifyBy(((User) request.getSession().getAttribute(
				Constants.USER_SESSION)).getId());
		System.out.println("修改者编号为====》"
				+ ((User) request.getSession().getAttribute(
						Constants.USER_SESSION)).getId());
		// 调用service层方法
		ProviderService service = new ProviderServiceImpl();
		boolean flag = service.modifyProvider(provider);
		if (flag) {// 成功
			// 重定向到provider.do
			response.sendRedirect("provider.do");
		} else {// 失败
			request.getRequestDispatcher("jsp/providerModify.jsp").forward(
					request, response);
		}
	}

}
