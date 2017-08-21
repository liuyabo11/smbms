package cn.pb.smbms.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 操作数据库的基类--静态类 因为每个线程都需要单独的connection，而不是大家共用一个connection 静态非全局唯一 单例全局唯一
 * 
 * @author Administrator
 * 
 */
public class BaseDao {
	/**
	 * 获取数据库连接
	 * 
	 * @return connection连接
	 */
	public static Connection getConnection() {
		String driver = ConfigManager.getInstance().getString("driver");
		String url = ConfigManager.getInstance().getString("url");
		String user = ConfigManager.getInstance().getString("user");
		String password = ConfigManager.getInstance().getString("password");

		Connection connection = null;

		try {
			// 加载驱动
			Class.forName(driver);
			// 获取数据库连接
			connection = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return connection;
	}

	/**
	 * 查询操作
	 * 
	 * @param connection
	 * @param pst
	 * @param rs
	 * @param sql
	 * @param params
	 * @return rs结果集
	 */
	public static ResultSet execute(Connection connection,
			PreparedStatement pst, ResultSet rs, String sql, Object[] params)
			throws Exception {

		pst = connection.prepareStatement(sql);
		for (int i = 0; i < params.length; i++) {
			pst.setObject(i + 1, params[i]);
		}
		rs = pst.executeQuery();

		return rs;
	}

	/**
	 * 增删改的操作 方法重载 体现面向对象多态的思想
	 * 
	 * @param connection
	 * @param pst
	 * @param sql
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public static int execute(Connection connection, PreparedStatement pst,
			String sql, Object[] params) throws Exception {
		int updateRows = 0;
		pst = connection.prepareStatement(sql);
		for (int i = 0; i < params.length; i++) {
			pst.setObject(i + 1, params[i]);
		}
		updateRows = pst.executeUpdate();
		return updateRows;

	}

	/**
	 * 关闭资源的方法
	 * 
	 * @param connection
	 *            数据库连接
	 * @param pst
	 *            preparedStatement对象
	 * @return 是否成功关闭
	 */
	public static boolean closeResource(Connection connection,
			PreparedStatement pst, ResultSet rs) {
		boolean flag = true;
		if (rs != null) {
			try {
				rs.close();
				// GC回收
				rs = null;
			} catch (SQLException e) {
				e.printStackTrace();
				flag = false;
			}
		}

		if (pst != null) {
			try {
				pst.close();
				// GC回收
				pst = null;
			} catch (SQLException e) {
				e.printStackTrace();
				flag = false;
			}
		}

		if (connection != null) {
			try {
				connection.close();
				// GC回收
				connection = null;
			} catch (SQLException e) {
				e.printStackTrace();
				flag = false;
			}
		}
		return flag;
	}

}
