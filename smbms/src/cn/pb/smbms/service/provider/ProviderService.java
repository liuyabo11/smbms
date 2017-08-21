package cn.pb.smbms.service.provider;

import java.util.List;

import cn.pb.smbms.pojo.Provider;

/**
 * 供应商的service层接口
 * 
 * @author Administrator
 * 
 */
public interface ProviderService {
	/**
	 * 新增供应商的方法
	 * 
	 * @param provider
	 * @return
	 */
	public boolean addProvider(Provider provider);

	/**
	 * 模糊查询供应商列表的方法
	 * 
	 * @param providerName
	 * @return
	 */
	public List<Provider> getProvidersList(String providerName);

	/**
	 * 根据id查询供应商
	 * 
	 * @param id
	 * @return
	 */
	public Provider getProviderById(String id);

	/**
	 * 根据id删除供应商
	 * 
	 * @param id
	 * @return
	 */
	public int delProviderById(Provider provider);

	/**
	 * 修改供应商
	 * 
	 * @param provider
	 * @return
	 */
	public boolean modifyProvider(Provider provider);
}
