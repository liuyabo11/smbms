package cn.pb.smbms.service.bill;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import cn.pb.smbms.dao.bill.BillDao;
import cn.pb.smbms.dao.bill.BillDaoImpl;
import cn.pb.smbms.pojo.Bill;
import cn.pb.smbms.util.BaseDao;

/**
 * 账单的service层接口实现类
 * 
 * @author Administrator
 * 
 */
public class BillServiceImpl implements BillService {
	private BillDao billDao;

	public BillServiceImpl() {
		billDao = new BillDaoImpl();
	}

	/**
	 * 增加账单
	 */
	@Override
	public boolean addBill(Bill bill) {
		Connection connection = null;
		boolean flag = false;
		try {
			connection = BaseDao.getConnection();
			flag = billDao.addBill(connection, bill);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			BaseDao.closeResource(connection, null, null);
		}
		return flag;
	}

	/**
	 * 修改账单
	 */
	@Override
	public boolean modifyBill(Bill bill) {
		Connection connection = null;
		boolean flag = false;
		try {
			connection = BaseDao.getConnection();
			flag = billDao.modifyBill(connection, bill);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			BaseDao.closeResource(connection, null, null);
		}
		return flag;
	}

	/**
	 * 删除账单
	 */
	@Override
	public boolean deleteBillById(Bill bill) {
		Connection connection = null;
		boolean flag = false;
		try {
			connection = BaseDao.getConnection();
			flag = billDao.deleteBillById(connection, bill);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			BaseDao.closeResource(connection, null, null);
		}
		return flag;
	}

	/**
	 * 根据id查询账单
	 */
	@Override
	public Bill getBillById(String id) {
		Connection connection = null;
		Bill bill = null;
		try {
			connection = BaseDao.getConnection();
			bill = billDao.getBillById(connection, id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			BaseDao.closeResource(connection, null, null);
		}
		return bill;
	}

	/**
	 * 根据用户输入条件 查询账单
	 */
	@Override
	public List<Bill> getBillList(Bill bill) {
		Connection connection = null;
		List<Bill> list = new ArrayList<Bill>();
		try {
			connection = BaseDao.getConnection();
			list = billDao.getBillList(connection, bill);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			BaseDao.closeResource(connection, null, null);
		}
		return list;
	}

}
