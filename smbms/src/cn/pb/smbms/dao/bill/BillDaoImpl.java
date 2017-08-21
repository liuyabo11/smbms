package cn.pb.smbms.dao.bill;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import cn.pb.smbms.pojo.Bill;
import cn.pb.smbms.pojo.Provider;
import cn.pb.smbms.util.BaseDao;

import com.mysql.jdbc.StringUtils;

/**
 * 账单dao层接口实现类
 * 
 * @author Administrator
 * 
 */
public class BillDaoImpl implements BillDao {
	/**
	 * 增加账单
	 */
	@Override
	public boolean addBill(Connection connection, Bill bill) throws Exception {
		PreparedStatement pst = null;
		boolean flag = false;
		if (connection != null) {
			String sql = "insert into smbms_bill (billCode,productName,productUnit,productCount,totalPrice,providerId,isPayment,createdBy,creationDate) values(?,?,?,?,?,?,?,?,?)";
			Object[] params = { bill.getBillCode(), bill.getProductName(),
					bill.getProductUnit(), bill.getProductCount(),
					bill.getTotalPrice(), bill.getProviderId(),
					bill.getIsPayment(), bill.getCreatedBy(),
					bill.getCreationDate() };
			if (BaseDao.execute(connection, pst, sql, params) > 0) {
				flag = true;
			}
		}
		return flag;
	}

	/**
	 * 修改账单
	 */
	@Override
	public boolean modifyBill(Connection connection, Bill bill)
			throws Exception {
		PreparedStatement pst = null;
		boolean flag = false;
		if (connection != null) {
			String sql = "update smbms_bill set billCode=?,productName=?,productUnit=?,productCount=?,totalPrice=?,providerId=?,isPayment=?,modifyBy=?,modifyDate=? where id=?";
			Object[] params = { bill.getBillCode(), bill.getProductName(),
					bill.getProductUnit(), bill.getProductCount(),
					bill.getTotalPrice(), bill.getProviderId(),
					bill.getIsPayment(), bill.getModifyBy(),
					bill.getModifyDate(), bill.getId() };
			if (BaseDao.execute(connection, pst, sql, params) > 0) {
				flag = true;
			}
		}
		return flag;
	}

	/**
	 * 删除账单
	 */
	@Override
	public boolean deleteBillById(Connection connection, Bill bill)
			throws Exception {
		PreparedStatement pst = null;
		boolean flag = false;
		if (connection != null) {
			// 创建集合 用来存放 参数
			List<Object> list = new ArrayList<Object>();
			StringBuffer sql = new StringBuffer();
			sql.append("delete from smbms_bill ");
			if (!StringUtils.isNullOrEmpty(bill.getId().toString())) {
				sql.append(" where id=?");
				list.add(bill.getId());
			} else if (!StringUtils.isNullOrEmpty(bill.getBillCode())) {
				sql.append(" where billCode=?");
				list.add(bill.getBillCode());
			}
			// 将list集合 转换为Object[] 数组
			Object[] params = list.toArray();
			if (BaseDao.execute(connection, pst, sql.toString(), params) > 0) {
				flag = true;
			}
		}
		return flag;
	}

	/**
	 * 根据id查询账单
	 */
	@Override
	public Bill getBillById(Connection connection, String id) throws Exception {
		Bill bill = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		if (connection != null) {
			String sql = "select b.id,b.billCode,b.productName,b.productDesc,b.productUnit,b.productCount,b.totalPrice,b.isPayment,b.providerId,b.createdBy,b.creationDate,b.modifyBy,b.modifyDate,p.proName,u.userName from smbms_bill b,smbms_provider p,smbms_user u where b.id=? and b.providerId=p.id and b.createdBy=u.id ";
			Object[] params = { id };
			rs = BaseDao.execute(connection, pst, rs, sql, params);
			if (rs.next()) {
				bill = new Bill();

				bill.setId(rs.getInt("id"));
				bill.setBillCode(rs.getString("billCode"));
				bill.setProductName(rs.getString("productName"));
				bill.setProductDesc(rs.getString("productDesc"));
				bill.setProductUnit(rs.getString("productUnit"));
				bill.setProductCount(rs.getBigDecimal("productCount"));
				bill.setTotalPrice(rs.getBigDecimal("totalPrice"));
				bill.setIsPayment(rs.getInt("isPayment"));
				bill.setProviderId(rs.getInt("providerId"));

				bill.setProviderName(rs.getString("proName"));

				bill.setCreatedBy(rs.getInt("createdBy"));
				bill.setCreationDate(rs.getDate("creationDate"));
				bill.setModifyBy(rs.getInt("modifyBy"));
				bill.setModifyDate(rs.getDate("modifyDate"));

				// bill.setUserName(rs.getString("userName"));
			}
		}
		return bill;
	}

	/**
	 * 根据用户输入条件 查询账单
	 */
	@Override
	public List<Bill> getBillList(Connection connection, Bill bill)
			throws Exception {
		List<Bill> billList = new ArrayList<Bill>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		if (connection != null) {
			// 拼接sql语句
			StringBuffer sql = new StringBuffer();
			sql.append("select b.id,b.billCode,b.productName,b.productDesc,b.productUnit,b.productCount,b.totalPrice,b.isPayment,b.providerId,b.createdBy,b.creationDate,b.modifyBy,b.modifyDate,p.proName from smbms_bill b,smbms_provider p where b.providerId=p.id ");
			// 创建集合 存放参数
			List<Object> list = new ArrayList<Object>();
			if (!StringUtils.isNullOrEmpty(bill.getProductName())) {
				sql.append(" and productName like ?");
				list.add("%" + bill.getProductName() + "%");
			}
			if (bill.getProviderId() > 0) {
				sql.append(" and providerId = ? ");
				list.add(bill.getProviderId());
			}
			if (bill.getIsPayment() > 0) {
				sql.append(" and isPayment = ? ");
				list.add(bill.getIsPayment());
			}

			Object[] params = list.toArray();
			rs = BaseDao.execute(connection, pst, rs, sql.toString(), params);
			while (rs.next()) {
				bill = new Bill();

				bill.setId(rs.getInt("id"));
				bill.setBillCode(rs.getString("billCode"));
				bill.setProductName(rs.getString("productName"));
				bill.setProductDesc(rs.getString("productDesc"));
				bill.setProductUnit(rs.getString("productUnit"));
				bill.setProductCount(rs.getBigDecimal("productCount"));
				bill.setTotalPrice(rs.getBigDecimal("totalPrice"));
				bill.setIsPayment(rs.getInt("isPayment"));
				bill.setProviderId(rs.getInt("providerId"));

				bill.setProviderName(rs.getString("proName"));

				bill.setCreatedBy(rs.getInt("createdBy"));
				bill.setCreationDate(rs.getDate("creationDate"));
				bill.setModifyBy(rs.getInt("modifyBy"));
				bill.setModifyDate(rs.getDate("modifyDate"));

				billList.add(bill);
			}
		}
		return billList;
	}

	/**
	 * 根据供应商Id 查询该供应商对应的账单
	 */
	@Override
	public int getBillCountByProviderId(Connection connection, Provider provider)
			throws Exception {
		PreparedStatement pst = null;
		ResultSet rs = null;
		int count = 0;
		if (connection != null) {
			String sql = "select count(1) as count from smbms_bill where providerId=?";
			Object[] params = { provider.getId() };

			rs = BaseDao.execute(connection, pst, rs, sql, params);
			if (rs.next()) {
				count = rs.getInt("count");
			}
		}

		return count;
	}

}
