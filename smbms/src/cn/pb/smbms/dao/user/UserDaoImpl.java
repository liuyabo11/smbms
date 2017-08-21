package cn.pb.smbms.dao.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import cn.pb.smbms.pojo.User;
import cn.pb.smbms.util.BaseDao;
import cn.pb.smbms.util.ResultSetUtil;

public class UserDaoImpl implements UserDao {
	/**
	 * 增加用户的方法
	 */
	@Override
	public int add(Connection connection, User user) throws Exception {
		PreparedStatement pst = null;
		int updateRows = 0;
		if (connection != null) {
			String sql = "insert into smbms_user (userCode,userName,userPassword,gender,birthday,phone,address,userType,createdBy,createDate) values(?,?,?,?,?,?,?,?,?,?)";
			Object[] params = { user.getUserCode(), user.getUserName(),
					user.getUserPassword(), user.getGender(),
					user.getBirthday(), user.getPhone(), user.getAddress(),
					user.getUserType(), user.getCreatedBy(),
					user.getCreateDate() };

			updateRows = BaseDao.execute(connection, pst, sql, params);
			BaseDao.closeResource(null, pst, null);
		}

		return updateRows;
	}

	/**
	 * 用户登录的方法
	 * 
	 * @return
	 */
	@Override
	public User getLoginUser(Connection connection, String userCode)
			throws Exception {
		PreparedStatement pst = null;
		ResultSet rs = null;
		User user = null;
		if (connection != null) {
			String sql = "select * from smbms_user where userCode=?";
			Object[] params = { userCode };
			// 调用baseDao查询的方法
			rs = BaseDao.execute(connection, pst, rs, sql, params);
			// 遍历rs集合 并给属性赋值
			user = ResultSetUtil.eachResult(rs, User.class);
			BaseDao.closeResource(null, pst, rs);
		}

		return user;

	}

	/**
	 * 模糊查询用户列表
	 */
	@Override
	public List<User> getUserList(Connection connection, String userName)
			throws Exception {
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<User> list = null;
		if (connection != null) {

			String sql = "select * from smbms_user where userName like ?";
			Object[] params = { "%" + userName + "%" };
			// 调用baseDao查询的方法
			rs = BaseDao.execute(connection, pst, rs, sql, params);
			// 遍历rs集合 并给属性赋值
			list = ResultSetUtil.eachResultSet(rs, User.class);
			// 关闭流
			BaseDao.closeResource(null, pst, rs);
		}
		return list;
	}

	/**
	 * 通过id删除用户
	 */
	@Override
	public int delUserById(Connection connection, Integer delId)
			throws Exception {
		int i = 0;
		PreparedStatement pst = null;
		if (connection != null) {
			String sql = "delete from smbms_user where id=?";
			Object[] params = { delId };
			// 调用baseDao方法
			i = BaseDao.execute(connection, pst, sql, params);
			// 关闭流
			BaseDao.closeResource(null, pst, null);
		}
		return i;
	}

	/**
	 * 根据用户id 查询用户
	 * 
	 * @return
	 */
	@Override
	public User getUserById(Connection connection, String id) throws Exception {
		PreparedStatement pst = null;
		ResultSet rs = null;
		User user = null;
		if (connection != null) {
			String sql = "select * from smbms_user where id=?";
			Object[] params = { id };
			// 调用baseDao查询的方法
			rs = BaseDao.execute(connection, pst, rs, sql, params);
			// 遍历rs集合 并给属性赋值
			user = ResultSetUtil.eachResult(rs, User.class);
			BaseDao.closeResource(null, pst, rs);
		}

		return user;
	}

	/**
	 * 修改用户信息
	 */
	@Override
	public int modifyById(Connection connection, User user) throws Exception {
		PreparedStatement pst = null;
		int i = 0;
		if (connection != null) {
			String sql = "update smbms_user set userName=?,gender=?,birthday=?,phone=?,address=?,userType=?,modifyBy=?,modifyDate=? where id=?";
			Object[] params = { user.getUserName(), user.getGender(),
					user.getBirthday(), user.getPhone(), user.getAddress(),
					user.getUserType(), user.getModifyBy(),
					user.getModifyDate(), user.getId() };
			// 调用baseDao方法
			i = BaseDao.execute(connection, pst, sql, params);
			// 关闭流
			BaseDao.closeResource(null, pst, null);
		}
		return i;
	}

	/**
	 * 修改密码的方法
	 */
	@Override
	public int updatePwd(Connection connection, Integer id, String newpassword)
			throws Exception {
		PreparedStatement pst = null;
		int i = 0;
		if (connection != null) {
			String sql = "update smbms_user set userPassword=? where id=?";
			Object[] params = { newpassword, id };
			i = BaseDao.execute(connection, pst, sql, params);
			// 关闭流
			BaseDao.closeResource(null, pst, null);
		}
		return i;
	}

}
