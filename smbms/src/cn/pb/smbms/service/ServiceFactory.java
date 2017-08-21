package cn.pb.smbms.service;


/**
 * service层的工厂 只需一个实例 使用单例
 * 
 * @author Administrator
 * 
 */
public class ServiceFactory {
	// 01.属性私有化
	private static ServiceFactory serviceFactory;

	// 02.私有化构造
	private ServiceFactory() {
	}

	/**
	 * 静态代码块+双重校验锁 创建实例
	 */
	static {
		if (serviceFactory == null) {
			synchronized (ServiceFactory.class) {
				if (serviceFactory == null) {
					serviceFactory = new ServiceFactory();
				}
			}
		}
	}

	/**
	 * 提供外部访问的接口 工厂模式 根据service层传过来的参数，获取相应serviceImpl对象
	 */

	public static CommentService getServiceImpl(String serviceImpl) {
		// 实例化 commentService
		CommentService commentService = null;
		if ("userServiceImpl".equals(serviceImpl)) {
			// commentService=new UserServiceImpl();
		} else if ("billServiceImpl".equals(serviceImpl)) {
			// commentService=new BillServiceImpl();
		} else if ("providerServiceImpl".equals(serviceImpl)) {
			// commentService=new ProviderServiceImpl();
		}
		return commentService;
	}
}
