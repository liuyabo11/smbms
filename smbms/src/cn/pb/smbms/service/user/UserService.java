package cn.pb.smbms.service.user;

import java.util.List;

import cn.pb.smbms.pojo.User;

public interface UserService {
	/**
	 * 添加用户的方法
	 * 
	 * @param user
	 * @return
	 */
	public boolean add(User user);

	/**
	 * 用户登陆的方法
	 * 
	 * @param userCode
	 *            用户编码
	 * @param userPassword
	 *            用户密码
	 * @return
	 */
	public User login(String userCode, String userPassword);

	/**
	 * 模糊查询用户列表
	 * 
	 * @param userName
	 *            查询条件
	 * @return
	 */
	List<User> getUserList(String userName);

	/**
	 * 根据userCode 查询出user
	 * 
	 * @param userCode
	 *            用户编码
	 * @return
	 */
	public User selectUserCodeExist(String userCode);

	/**
	 * 通过id删除用户
	 * 
	 * @param delId
	 *            用户id
	 * @return
	 */
	public boolean deleteUserById(Integer delId);

	/**
	 * 根据用户id 查询用户
	 * 
	 * @param id
	 * @return
	 */
	public User getUserById(String id);

	/**
	 * 修改用户信息
	 * 
	 * @param user
	 * @return
	 */
	public boolean modifyById(User user);

	/**
	 * 修改密码的方法
	 * 
	 * @param id
	 * @param newpassword
	 * @return
	 */
	public boolean updatePwd(Integer id, String newpassword);
}
