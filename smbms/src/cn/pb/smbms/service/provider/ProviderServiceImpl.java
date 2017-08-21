package cn.pb.smbms.service.provider;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import cn.pb.smbms.dao.bill.BillDao;
import cn.pb.smbms.dao.bill.BillDaoImpl;
import cn.pb.smbms.dao.provider.ProviderDao;
import cn.pb.smbms.dao.provider.ProviderDaoImpl;
import cn.pb.smbms.pojo.Provider;
import cn.pb.smbms.util.BaseDao;

/**
 * 供应商service层的实现类
 * 
 * @author Administrator
 * 
 */
public class ProviderServiceImpl implements ProviderService {
	private ProviderDao providerDao;
	private BillDao billDao;

	// 构造方法中实例化 providerDao
	public ProviderServiceImpl() {
		providerDao = new ProviderDaoImpl();
		billDao = new BillDaoImpl();
	}

	/**
	 * 新增供应商的方法
	 */
	@Override
	public boolean addProvider(Provider provider) {
		Connection connection = null;
		boolean flag = false;
		try {
			connection = BaseDao.getConnection();
			flag = providerDao.addProvider(connection, provider);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			BaseDao.closeResource(connection, null, null);
		}
		return flag;
	}

	/**
	 * 模糊查询供应商列表的方法
	 */
	@Override
	public List<Provider> getProvidersList(String providerName) {
		List<Provider> providers = null;
		Connection connection = null;
		try {
			connection = BaseDao.getConnection();
			providers = providerDao.getProvidersList(connection, providerName);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			BaseDao.closeResource(connection, null, null);
		}

		return providers;
	}

	/**
	 * 根据id查询供应商
	 */
	@Override
	public Provider getProviderById(String id) {
		Provider provider = null;
		Connection connection = null;
		try {
			connection = BaseDao.getConnection();
			provider = providerDao.getProviderById(connection, id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			BaseDao.closeResource(connection, null, null);
		}
		return provider;
	}

	/**
	 * 根据id删除供应商:如果供应商下有账单 则不能删除 billCount=0:说明供应商下没有账单 并且成功删除了供应商
	 * billCount=-1：说明供应商下没有账单 但是删除供应商失败 billCount>0:说明供应商下有账单不能删除 供应商
	 */
	@Override
	public int delProviderById(Provider provider) {
		Connection connection = null;
		int billCount = -1;
		try {
			// 获取连接
			connection = BaseDao.getConnection();
			// 开启事务
			connection.setAutoCommit(false);
			// 判断供应商下是否有账单
			billCount = billDao.getBillCountByProviderId(connection, provider);
			if (billCount == 0) {
				providerDao.delProvider(connection, provider);
			}
			// 提交事务
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				billCount = -1;
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			BaseDao.closeResource(connection, null, null);
		}
		return billCount;
	}

	/**
	 * 修改供应商
	 */
	@Override
	public boolean modifyProvider(Provider provider) {
		Connection connection = null;
		boolean flag = false;
		try {
			connection = BaseDao.getConnection();
			flag = providerDao.modifyProvider(connection, provider);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			BaseDao.closeResource(connection, null, null);
		}
		return flag;
	}

}
