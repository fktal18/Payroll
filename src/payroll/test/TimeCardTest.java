package payroll.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import payroll.PayrollDatabase;
import payroll.Transaction;
import payroll.trans.AddHourlyEmployeeTransaction;

class TimeCardTest {

	//登记时间卡（一个时间卡，钟点工）
	//TimeCard EmpId date hours
	@Test
	public void testOneTimeCardToHourlyEmplouyee() {
		//添加一个钟点工，然后给钟点工登记一个时间卡
		int empId = 3001;
		new AddHourlyEmployeeTransaction(empId, "Bill", "Home", 12.5).execute();
		assertNotNull(PayrollDatabase.getEmployee(empId));
		//添加时间卡
		String date = "2013-06-01"; //用字符串简化日期表示
		double hours = 8;
		
		Transaction t = new TimeCardTransaction(empId,date,hours);
		t.execute();
		
		//验证时间卡
	}
	//登记时间卡（两个时间卡，钟点工）
	@Test
	public void testTwoTimeCardToHourlyEmplouyee() {
		
	}
	//登记时间咯（一个时间卡，月薪雇员，抛出异常）
	@Test
	public void testTimeCardToSalariedEmplouyee() {
		
	}
	//登记时间卡（一个时间卡，月薪加提成雇员，抛出异常）
	@Test
	public void testTimeCardToCommissionedEmplouyee() {
		
	}
	//登记时间卡（一个时间卡，不存在的雇员，抛出异常）
	@Test
	public void testTimeCardToNoSuchEmployee() {
		
	}
}
