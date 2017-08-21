package cn.pb.smbms.service.bill;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import cn.pb.smbms.pojo.Bill;

public class BillServiceImplTest {
	private BillService billService;

	/**
	 * 加载类的时候 先执行这个方法
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		billService = new BillServiceImpl();
	}

	@Test
	public void testAdd() {
		Bill bill = new Bill();

		bill.setBillCode("222");
		bill.setProductName("大米");
		bill.setProductDesc("大米很不错！");
		bill.setProductUnit("斤");
		BigDecimal bigDecimal = new BigDecimal(10.25);
		bill.setProductCount(bigDecimal);
		BigDecimal bigDecimal1 = new BigDecimal(1025);

		bill.setTotalPrice(bigDecimal1);
		bill.setIsPayment(1);
		bill.setProviderId(2);
		bill.setProviderName("beidaqingniao");
		bill.setCreatedBy(1);
		bill.setCreationDate(new Date());

		System.out.println(billService.addBill(bill));
	}

	@Test
	public void testModifyBill() {
		Bill bill = new Bill();

		bill.setBillCode("222");
		bill.setProductName("大米111");
		bill.setProductDesc("大米很不错！1111");
		bill.setProductUnit("斤1111");
		BigDecimal bigDecimal = new BigDecimal(10.25);
		bill.setProductCount(bigDecimal);
		BigDecimal bigDecimal1 = new BigDecimal(1025);

		bill.setTotalPrice(bigDecimal1);
		bill.setIsPayment(1);
		bill.setProviderId(1);
		bill.setProviderName("beidaqingniao111111");
		bill.setModifyBy(1);
		bill.setModifyDate(new Date());
		bill.setId(3);

		billService.modifyBill(bill);
	}

	@Test
	public void testDeleteBill() {
		Bill bill = new Bill();

		bill.setId(2);

		System.out.println(billService.deleteBillById(bill));
	}

	@Test
	public void testGetBill() {

		System.out.println((billService.getBillById("4")).getProviderId());
		System.out.println((billService.getBillById("4")).getProviderName());
		System.out.println((billService.getBillById("4")).getCreatedBy());
	}

	@Test
	public void testGetBills() {
		List<Bill> list = new ArrayList<Bill>();

		Bill bill1 = new Bill();
		bill1.setProviderId(0);
		bill1.setIsPayment(0);
		list = billService.getBillList(bill1);

		if (list == null) {
			System.out.println("list为null");
		} else {
			for (Bill bill : list) {
				System.out.println("billName" + bill.getProductName());

			}
		}

	}

	@Test
	public void testInteger() {
		String a = "0";

		System.out.println(Integer.parseInt(a));
	}
}
