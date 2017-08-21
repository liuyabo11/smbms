package cn.pb.smbms.service.bill;

import java.util.List;

import cn.pb.smbms.pojo.Bill;

/**
 * 账单的service层接口
 * 
 * @author Administrator
 * 
 */
public interface BillService {
	/**
	 * 增加账单
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean addBill(Bill bill);

	/**
	 * 修改账单
	 * 
	 * @param connection
	 * @param bill
	 * @return
	 * @throws Exception
	 */
	public boolean modifyBill(Bill bill);

	/**
	 * 删除账单
	 * 
	 * @param connection
	 * @param bill
	 * @return
	 * @throws Exception
	 */
	public boolean deleteBillById(Bill bill);

	/**
	 * 根据id查询账单
	 * 
	 * @param connection
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Bill getBillById(String id);

	/**
	 * 根据用户输入条件 查询账单
	 * 
	 * @param connection
	 * @param bill
	 * @return
	 * @throws Exception
	 */
	public List<Bill> getBillList(Bill bill);
}
