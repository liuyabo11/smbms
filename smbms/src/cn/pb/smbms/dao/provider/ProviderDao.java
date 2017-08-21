package cn.pb.smbms.dao.provider;

import java.sql.Connection;
import java.util.List;

import cn.pb.smbms.pojo.Provider;

/**
 * 供应商dao层接口
 * 
 * @author Administrator
 * 
 */
public interface ProviderDao {
	/**
	 * 新增供应商的方法
	 * 
	 * @param provider
	 * @return
	 */
	public boolean addProvider(Connection connection, Provider provider)
			throws Exception;

	/**
	 * 模糊查询供应商列表的方法
	 * 
	 * @param providerName
	 * @return
	 */
	public List<Provider> getProvidersList(Connection connection,
			String providerName) throws Exception;

	/**
	 * 根据id查询供应商
	 * 
	 * @param id
	 * @return
	 */
	public Provider getProviderById(Connection connection, String id)
			throws Exception;

	/**
	 * 删除供应商
	 * 
	 * @param id
	 * @return
	 */
	public int delProvider(Connection connection, Provider provider)
			throws Exception;

	/**
	 * 修改供应商
	 * 
	 * @param provider
	 * @return
	 */
	public boolean modifyProvider(Connection connection, Provider provider)
			throws Exception;
}
