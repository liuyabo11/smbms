package cn.pb.smbms.service.provider;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import cn.pb.smbms.pojo.Provider;

public class ProviderServiceTest {
	private ProviderService providerService;

	/**
	 * 加载类的时候 先执行这个方法
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		providerService = new ProviderServiceImpl();
	}

	@Test
	public void testAddProvider() {
		Provider provider = new Provider();
		provider.setProCode("zhangsan");
		provider.setProName("张三");
		provider.setProContact("张三小弟");
		provider.setProPhone("6666666666");
		provider.setProAddress("beidaqingniao");
		provider.setProFax("888888888");
		provider.setProDesc("卖鱼的");
		provider.setCreatedBy(1);
		provider.setCreationDate(new Date());

		System.out.println(providerService.addProvider(provider));
	}

	@Test
	public void testGetProvidersList() {
		List<Provider> providers = providerService.getProvidersList("");
		for (Provider provider : providers) {
			System.out.println(provider.getProName());
		}
	}

	@Test
	public void testGetProviderById() {
		Provider provider = providerService.getProviderById("1");
		System.out.println(provider.getProName());
	}

	@Test
	public void testDeleteById() {
		Provider provider = new Provider();
		provider.setId(2);
		System.out.println(providerService.delProviderById(provider));
	}

	@Test
	public void testModifyBuId() {
		Provider provider = new Provider();

		provider.setProCode("zhangsan11");
		provider.setProName("张三11");
		provider.setProContact("张三小弟11");
		provider.setProPhone("666666666611");
		provider.setProAddress("beidaqingniao11");
		provider.setProFax("88888888811");
		provider.setProDesc("卖鱼的11");
		provider.setModifyBy(1);
		provider.setModifyDate(new Date());
		provider.setId(2);
		System.out.println(providerService.modifyProvider(provider));

	}

}
