package cn.pb.smbms.dao.user;

import java.sql.Connection;
import java.util.List;

import cn.pb.smbms.pojo.User;

public interface UserDao {
	/**
	 * 增加用户的方法
	 * 
	 * @param connection
	 * @param user
	 * @return
	 * @throws Exception
	 */
	int add(Connection connection, User user) throws Exception;

	/**
	 * 用户登录的方法
	 * 
	 * @param connection
	 *            传过来连接
	 * @param userCode
	 *            用户编码
	 * @return
	 */
	User getLoginUser(Connection connection, String userCode) throws Exception;

	/**
	 * 模糊查询用户列表
	 * 
	 * @param connection
	 *            连接
	 * @param userName
	 *            查询条件
	 * @return 用户列表集合
	 * @throws Exception
	 */
	List<User> getUserList(Connection connection, String userName)
			throws Exception;

	/**
	 * 通过id删除用户
	 * 
	 * @param delId
	 *            用户id
	 * @return
	 */
	int delUserById(Connection connection, Integer delId) throws Exception;

	/**
	 * 根据用户id 查询用户
	 * 
	 * @param id
	 * @return
	 */
	User getUserById(Connection connection, String id) throws Exception;

	/**
	 * 修改用户信息
	 * 
	 * @param user
	 * @return
	 */
	int modifyById(Connection connection, User user) throws Exception;

	/**
	 * 修改密码的方法
	 * 
	 * @param id
	 * @param newpassword
	 * @return
	 */
	int updatePwd(Connection connection, Integer id, String newpassword)
			throws Exception;
}
