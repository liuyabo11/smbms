package cn.pb.smbms.dao.provider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import cn.pb.smbms.pojo.Provider;
import cn.pb.smbms.util.BaseDao;

import com.mysql.jdbc.StringUtils;

/**
 * 供应商dao层实现类
 * 
 * @author Administrator
 * 
 */
public class ProviderDaoImpl implements ProviderDao {
	/**
	 * 新增供应商的方法
	 */
	@Override
	public boolean addProvider(Connection connection, Provider provider)
			throws Exception {
		boolean flag = false;
		PreparedStatement pst = null;
		if (connection != null) {
			String sql = "insert into smbms_provider (proCode,proName,proContact,proPhone,proAddress,proFax,proDesc,createdBy,creationDate) values(?,?,?,?,?,?,?,?,?)";
			Object[] params = { provider.getProCode(), provider.getProName(),
					provider.getProContact(), provider.getProPhone(),
					provider.getProAddress(), provider.getProFax(),
					provider.getProDesc(), provider.getCreatedBy(),
					provider.getCreationDate() };
			if (BaseDao.execute(connection, pst, sql, params) > 0) {
				// 新增成功
				flag = true;
			}
		}

		return flag;
	}

	/**
	 * 模糊查询供应商列表的方法
	 */
	@Override
	public List<Provider> getProvidersList(Connection connection,
			String providerName) throws Exception {
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<Provider> list = new ArrayList<Provider>();
		Provider provider = null;
		if (connection != null) {
			String sql = "select * from smbms_provider where proName like ?";
			Object[] params = { "%" + providerName + "%" };
			rs = BaseDao.execute(connection, pst, rs, sql, params);
			while (rs.next()) {
				// 创建对象
				provider = new Provider();

				provider.setId(rs.getInt("id"));
				provider.setProCode(rs.getString("proCode"));
				provider.setProName(rs.getString("proName"));
				provider.setProContact(rs.getString("proContact"));
				provider.setProDesc(rs.getString("proDesc"));
				provider.setProPhone(rs.getString("proPhone"));
				provider.setProAddress(rs.getString("proAddress"));
				provider.setProFax(rs.getString("proFax"));
				provider.setCreatedBy(rs.getInt("createdBy"));
				provider.setCreationDate(rs.getDate("creationDate"));
				provider.setModifyBy(rs.getInt("modifyBy"));
				provider.setModifyDate(rs.getDate("modifyDate"));

				list.add(provider);
			}
		}
		return list;
	}

	/**
	 * 根据id查询供应商
	 */
	@Override
	public Provider getProviderById(Connection connection, String id)
			throws Exception {
		PreparedStatement pst = null;
		ResultSet rs = null;
		Provider provider = null;
		if (connection != null) {
			String sql = "select * from smbms_provider where id=?";
			Object[] params = { id };
			rs = BaseDao.execute(connection, pst, rs, sql, params);
			if (rs.next()) {
				provider = new Provider();

				provider.setId(rs.getInt("id"));
				provider.setProCode(rs.getString("proCode"));
				provider.setProName(rs.getString("proName"));
				provider.setProContact(rs.getString("proContact"));
				provider.setProDesc(rs.getString("proDesc"));
				provider.setProPhone(rs.getString("proPhone"));
				provider.setProAddress(rs.getString("proAddress"));
				provider.setProFax(rs.getString("proFax"));
				provider.setCreatedBy(rs.getInt("createdBy"));
				provider.setCreationDate(rs.getDate("creationDate"));
				provider.setModifyBy(rs.getInt("modifyBy"));
				provider.setModifyDate(rs.getDate("modifyDate"));
			}
		}
		return provider;
	}

	/**
	 * 删除供应商的方法
	 */
	@Override
	public int delProvider(Connection connection, Provider provider)
			throws Exception {
		int num = 0;
		PreparedStatement pst = null;
		if (connection != null) {
			// 创建集合 用来存放 参数
			List<Object> list = new ArrayList<Object>();
			StringBuffer sql = new StringBuffer();
			sql.append("delete from smbms_provider ");
			if (!StringUtils.isNullOrEmpty(provider.getId().toString())) {
				sql.append(" where id=?");
				list.add(provider.getId());
			} else if (!StringUtils.isNullOrEmpty(provider.getProCode())) {
				sql.append(" where proCode=?");
				list.add(provider.getProCode());
			}
			// 将list集合 转换为Object[] 数组
			Object[] params = list.toArray();
			num = BaseDao.execute(connection, pst, sql.toString(), params);

		}
		return num;
	}

	/**
	 * 修改供应商
	 */
	@Override
	public boolean modifyProvider(Connection connection, Provider provider)
			throws Exception {
		PreparedStatement pst = null;
		boolean flag = false;
		if (connection != null) {
			String sql = "update smbms_provider set proCode=?,proName=?,proContact=?,proPhone=?,proAddress=?,proFax=?,proDesc=?,modifyBy=?,modifyDate=? where id=?";
			Object[] params = { provider.getProCode(), provider.getProName(),
					provider.getProContact(), provider.getProPhone(),
					provider.getProAddress(), provider.getProFax(),
					provider.getProDesc(), provider.getModifyBy(),
					provider.getModifyDate(), provider.getId() };

			if (BaseDao.execute(connection, pst, sql, params) > 0) {
				flag = true;
			}
		}
		return flag;
	}

}
