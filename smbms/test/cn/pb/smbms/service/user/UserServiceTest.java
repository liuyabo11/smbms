package cn.pb.smbms.service.user;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import cn.pb.smbms.pojo.User;

public class UserServiceTest {
	private UserService userService;

	/**
	 * 加载类的时候 先执行这个方法
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		userService = new UserServiceImpl();
	}

	@Test
	public void testAdd() {
		User user = new User();
		user.setUserCode("111");
		user.setUserName("222");
		boolean result = userService.add(user);
		// result = false;
		// 断言
		Assert.assertTrue("增加失败", result);
	}

	@Test
	public void testLogin() {
		User user = userService.login("admin", "admin");
		System.out.println("用户编码：" + user.getUserCode() + "\t\t" + "密码："
				+ user.getUserPassword());
	}

	@Test
	public void testGetUserList() {
		List<User> list = new ArrayList<User>();
		list = userService.getUserList("a");
		// 断言
		Assert.assertEquals(1, list.size());
	}

	/*
	 * public static void main(String[] args) { UserService service = new
	 * UserServiceImpl(); service.getUserList("a"); }
	 */
	@Test
	public void testDeleteUserById() {
		boolean flag = userService.deleteUserById(16);
		System.out.println(flag);
	}

	@Test
	public void testGetUserById() {
		User user = userService.getUserById("10");
		System.out.println("=====>" + user.getUserName());
	}

	@Test
	public void testModifyById() {
		User user = new User();
		user.setId(18);
		user.setUserName("shabi");
		System.out.println(userService.modifyById(user));
	}

	@Test
	public void testUpdatePwd() {
		System.out.println(userService.updatePwd(23, "88888888"));
	}
}
