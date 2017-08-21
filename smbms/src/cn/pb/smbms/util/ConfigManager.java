package cn.pb.smbms.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {
	// 饿汉模式（线程安全）
	private static ConfigManager configManager = new ConfigManager();
	// 懒汉模式（线程不安全） 1.方法体同步2.双重锁
	// private static ConfigManager configManager;
	private static Properties properties;

	// 私有化构造 保证只有一个实例
	private ConfigManager() {
		String path = "database.properties";
		properties = new Properties();
		InputStream is = ConfigManager.class.getClassLoader()
				.getResourceAsStream(path);
		try {
			properties.load(is);
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 饿汉模式 线程安全
	 * 
	 * @return
	 */
	// 获取ConfigManager的实例
	public static ConfigManager getInstance() {
		return configManager;
	}

	/**
	 * 饿汉模式 线程不安全
	 * 
	 * @return
	 * 
	 *         public static synchronized ConfigManager getInstance() { if
	 *         (configManager == null) { configManager = new ConfigManager(); }
	 *         return configManager; }
	 */
	// 通过配置文件的键取值
	public static String getString(String key) {
		return properties.getProperty(key);
	}
}
