package cn.pb.smbms.dao.bill;

import java.sql.Connection;
import java.util.List;

import cn.pb.smbms.pojo.Bill;
import cn.pb.smbms.pojo.Provider;

/**
 * 账单dao层接口
 * 
 * @author Administrator
 * 
 */
public interface BillDao {
	/**
	 * 增加账单
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean addBill(Connection connection, Bill bill) throws Exception;

	/**
	 * 修改账单
	 * 
	 * @param connection
	 * @param bill
	 * @return
	 * @throws Exception
	 */
	public boolean modifyBill(Connection connection, Bill bill)
			throws Exception;

	/**
	 * 删除账单
	 * 
	 * @param connection
	 * @param bill
	 * @return
	 * @throws Exception
	 */
	public boolean deleteBillById(Connection connection, Bill bill)
			throws Exception;

	/**
	 * 根据id查询账单
	 * 
	 * @param connection
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Bill getBillById(Connection connection, String id) throws Exception;

	/**
	 * 根据用户输入条件 查询账单
	 * 
	 * @param connection
	 * @param bill
	 * @return
	 * @throws Exception
	 */
	public List<Bill> getBillList(Connection connection, Bill bill)
			throws Exception;

	/**
	 * 根据供应商Id 查询该供应商对应的账单
	 * 
	 * @param connection
	 * @param provider
	 * @return
	 */
	public int getBillCountByProviderId(Connection connection, Provider provider)
			throws Exception;
}
