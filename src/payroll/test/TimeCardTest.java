package payroll.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import payroll.Employee;
import payroll.PaymentClassification;
import payroll.PayrollDatabase;
import payroll.Transaction;
import payroll.classification.HourlyClassification;
import payroll.trans.AddHourlyEmployeeTransaction;
import payroll.trans.TimeCardTransaction;

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
		Employee employee = PayrollDatabase.getEmployee(empId);
		PaymentClassification pc = employee.getPaymentClassification();	
		assertTrue(pc instanceof HourlyClassification); //应为钟点工
		HourlyClassification hc = (HourlyClassification) pc;
		TimeCard tc = hc.getTimeCardOfDate(date);  // 取出当日时间卡
		assertNotNull(tc);	//时间卡非空
		assertEquals(date, tc.getDate()); // 日期正确
		assertEquals(hours, tc.getHours(), 0.01);//工作小时数正确

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
