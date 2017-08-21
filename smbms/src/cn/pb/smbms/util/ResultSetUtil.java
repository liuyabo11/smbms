package cn.pb.smbms.util;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ResultSetUtil {
	/**
	 * 给单个对象属性赋值
	 * 
	 * @param rs
	 * @param clazz
	 * @return
	 */
	public static <T> T eachResult(ResultSet rs, Class<T> clazz) {
		// 循环resultSet 01.先获取对象 02.循环属性赋值03.返回对象
		T object = null;
		try {
			if (rs.next()) {
				// 实例化对象
				object = clazz.newInstance();
				// 获取实体类的所有属性
				Field[] fields = clazz.getDeclaredFields();
				// 遍历属性并赋值
				for (Field field : fields) {
					// 设置权限 可以访问私有属性并赋值
					field.setAccessible(true);
					field.set(object, rs.getObject(field.getName()));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return object;
	}

	/**
	 * 给集合中多个对象属性赋值
	 * 
	 * @param rs
	 * @param class1
	 * @return
	 */
	public static <T> List<T> eachResultSet(ResultSet rs, Class<T> class1) {
		List<T> list = new ArrayList<T>();
		// 循环resultSet 01.先获取对象 02.循环属性赋值03.放进集合
		T object = null;
		try {
			while (rs.next()) {
				// 实例化对象
				object = class1.newInstance();
				// 获取实体类的所有属性
				Field[] fields = class1.getDeclaredFields();
				for (Field field : fields) {
					// 可以访问私有属性 并赋值
					field.setAccessible(true);
					field.set(object, rs.getObject(field.getName()));
				}
				// 放进集合
				list.add(object);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return list;
	}
}
