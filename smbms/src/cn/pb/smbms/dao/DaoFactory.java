package cn.pb.smbms.dao;

/**
 * Dao层的工厂 因为只需创建一个 使用单例
 * 
 * @author Administrator
 * 
 */
public class DaoFactory {
	// 私有化本对象
	private static DaoFactory daoFactory;

	// 私有化构造
	private DaoFactory() {

	}

	/**
	 * 静态代码块 创建dao工厂实例
	 */
	static {
		if (daoFactory == null) {
			synchronized (DaoFactory.class) {
				if (daoFactory == null) {
					daoFactory = new DaoFactory();
				}
			}
		}
	}

	/**
	 * 提供接口供外部访问 工厂模式 根据service层传过来的参数，获取相应daoImpl对象
	 */
	public static CommentDao getDaoImpl(String daoImpl) {
		// 实例化commentDao
		CommentDao commentDao = null;
		if ("userDaoImpl".equals(daoImpl)) {
			// commentDao=new UserDaoImpl();
		} else if ("billDaoImpl".equals(daoImpl)) {
			// commentDao=new BillDaoImpl();
		} else if ("providerDaoImpl".equals(daoImpl)) {
			// commentDao=new ProviderDaoImpl();
		}

		return commentDao;
	}

}
