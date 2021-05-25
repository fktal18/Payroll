package payroll.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import payroll.PayrollDatabase;
import payroll.Transaction;
import payroll.trans.AddHourlyEmployeeTransaction;

class TimeCardTest {

	//�Ǽ�ʱ�俨��һ��ʱ�俨���ӵ㹤��
	//TimeCard EmpId date hours
	@Test
	public void testOneTimeCardToHourlyEmplouyee() {
		//���һ���ӵ㹤��Ȼ����ӵ㹤�Ǽ�һ��ʱ�俨
		int empId = 3001;
		new AddHourlyEmployeeTransaction(empId, "Bill", "Home", 12.5).execute();
		assertNotNull(PayrollDatabase.getEmployee(empId));
		//���ʱ�俨
		String date = "2013-06-01"; //���ַ��������ڱ�ʾ
		double hours = 8;
		
		Transaction t = new TimeCardTransaction(empId,date,hours);
		t.execute();
		
		//��֤ʱ�俨
	}
	//�Ǽ�ʱ�俨������ʱ�俨���ӵ㹤��
	@Test
	public void testTwoTimeCardToHourlyEmplouyee() {
		
	}
	//�Ǽ�ʱ�俩��һ��ʱ�俨����н��Ա���׳��쳣��
	@Test
	public void testTimeCardToSalariedEmplouyee() {
		
	}
	//�Ǽ�ʱ�俨��һ��ʱ�俨����н����ɹ�Ա���׳��쳣��
	@Test
	public void testTimeCardToCommissionedEmplouyee() {
		
	}
	//�Ǽ�ʱ�俨��һ��ʱ�俨�������ڵĹ�Ա���׳��쳣��
	@Test
	public void testTimeCardToNoSuchEmployee() {
		
	}
}
