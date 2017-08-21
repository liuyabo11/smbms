package cn.pb.smbms.servlet.provider;

/**
 * 供应商列表的servlet
 */
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.pb.smbms.pojo.Provider;
import cn.pb.smbms.service.provider.ProviderService;
import cn.pb.smbms.service.provider.ProviderServiceImpl;

import com.mysql.jdbc.StringUtils;

@WebServlet("/provider.do")
public class ListProviderServlet extends HttpServlet {

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
		List<Provider> list = new ArrayList<Provider>();
		// 调用service层方法
		ProviderService service = new ProviderServiceImpl();
		if (StringUtils.isNullOrEmpty(searchName)) {
			searchName = "";
		}
		list = service.getProvidersList(searchName);
		// 将list放到作用域中
		request.setAttribute("providerList", list);
		request.setAttribute("searchName", searchName);
		// 转发到providerList.jsp页面
		request.getRequestDispatcher("jsp/providerList.jsp").forward(request,
				response);
	}

}
