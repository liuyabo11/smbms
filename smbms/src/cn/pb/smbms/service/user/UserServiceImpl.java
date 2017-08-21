package cn.pb.smbms.service.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import cn.pb.smbms.dao.user.UserDao;
import cn.pb.smbms.dao.user.UserDaoImpl;
import cn.pb.smbms.pojo.User;
import cn.pb.smbms.util.BaseDao;

public class UserServiceImpl implements UserService {
	private UserDao userDao;

	public UserServiceImpl() {
		userDao = new UserDaoImpl();
	}

	/**
	 * 新增用户
	 */
	@Override
	public boolean add(User user) {
		boolean flag = false;
		Connection connection = null;
		PreparedStatement pst = null;

		try {
			connection = BaseDao.getConnection();
			// 开启JDBC事务管理
			connection.setAutoCommit(false);
			int i = userDao.add(connection, user);

			// 提交事务
			connection.commit();
			if (i > 0) {
				flag = true;
				System.out.println("add success");
			} else {
				System.out.println("add failed");
			}
		} catch (Exception e) {

			e.printStackTrace();

			System.out.println("rollback=====>");
			// 事务回滚
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		} finally {
			// 在service层关闭connection对象
			BaseDao.closeResource(connection, null, null);
		}
		return flag;
	}

	/**
	 * 用户登录
	 */
	@Override
	public User login(String userCode, String userPassword) {
		Connection connection = null;
		User user = null;
		try {
			// 获取连接
			connection = BaseDao.getConnection();
			user = userDao.getLoginUser(connection, userCode);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭流
			BaseDao.closeResource(connection, null, null);
		}
		// 匹配密码
		if (user != null) {
			if (!user.getUserPassword().equals(userPassword)) {
				user = null;
			}
		}
		return user;
	}

	/**
	 * 模糊查询用户列表
	 */
	@Override
	public List<User> getUserList(String userName) {
		Connection connection = null;
		List<User> list = null;
		System.out.println("模糊查询条件userName===》" + userName);

		try {
			// 获取连接
			connection = BaseDao.getConnection();
			list = userDao.getUserList(connection, userName);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			BaseDao.closeResource(connection, null, null);
		}

		return list;
	}

	/**
	 * 根据userCode 查询出user
	 */
	@Override
	public User selectUserCodeExist(String userCode) {
		User user = null;
		Connection connection = null;
		try {
			connection = BaseDao.getConnection();
			user = userDao.getLoginUser(connection, userCode);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			BaseDao.closeResource(connection, null, null);
		}
		return user;
	}

	/**
	 * 通过id删除用户
	 */
	@Override
	public boolean deleteUserById(Integer delId) {
		boolean flag = false;
		Connection connection = null;
		int num = 0;
		try {
			connection = BaseDao.getConnection();
			num = userDao.delUserById(connection, delId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			BaseDao.closeResource(connection, null, null);
		}
		if (num > 0) {
			flag = true;
			System.out.println("delete success");
		} else {
			System.out.println("delete failed");
		}

		return flag;
	}

	/**
	 * 根据用户id 查询用户
	 */
	@Override
	public User getUserById(String id) {
		Connection connection = null;
		User user = null;
		try {
			connection = BaseDao.getConnection();
			user = userDao.getUserById(connection, id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			BaseDao.closeResource(connection, null, null);
		}

		return user;
	}

	/**
	 * 修改用户信息
	 */
	@Override
	public boolean modifyById(User user) {
		Connection connection = null;
		boolean flag = false;
		BaseDao.getConnection();
		int num = 0;
		try {
			connection = BaseDao.getConnection();
			num = userDao.modifyById(connection, user);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			BaseDao.closeResource(connection, null, null);
		}

		if (num > 0) {
			flag = true;
			System.out.println("modify success");
		} else {
			System.out.println("modify failed");
		}
		return flag;
	}

	/**
	 * 修改密码的方法
	 */
	@Override
	public boolean updatePwd(Integer id, String newpassword) {
		Connection connection = null;
		boolean flag = false;
		int num = 0;
		try {
			connection = BaseDao.getConnection();
			num = userDao.updatePwd(connection, id, newpassword);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			BaseDao.closeResource(connection, null, null);
		}

		if (num > 0) {
			flag = true;
			System.out.println("修改密码成功！");
		} else {
			System.out.println("修改密码失败！");
		}
		return flag;
	}

}
