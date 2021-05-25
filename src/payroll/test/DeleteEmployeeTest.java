package payroll.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import payroll.Employee;
import payroll.PayrollDatabase;
import payroll.Transaction;
import payroll.exception.NoSuchEmployeeException;
import payroll.trans.AddHourlyEmployeeTransaction;
import payroll.trans.DeleteEmployeeTransaction;

class DeleteEmployeeTest {

	//����2  ɾ����Ա��
	//ʹ��DelEmp������ɾ����Ա��������ʽ��
	//DelEmp EmpId
	//��ִ�иò���ʱ����ɾ����Ӧ�Ĺ�Ա��¼��
	//�쳣���1����Ч����δ֪��EmpId��
	//���EmpId�ֶβ�������ȷ�Ľṹ��������û�����õ�һ����Ч�Ĺ�Ա��¼�����ӡһ������
	//��Ϣ����������������

	@Test
	public void testDeleteEmployeeExisted() {
		//�Ȳ���һ����Ա��Ȼ��ɾ��
		int empId = 2001;
		new AddHourlyEmployeeTransaction(empId, "Bill", "Home", 12.5)
			.execute();
		// ɾ����Ա
		Transaction t = new DeleteEmployeeTransaction(empId);
		t.execute();
		
		// ��֤��Ա��ɾ��
		Employee e = PayrollDatabase.getEmployee(empId);
		assertNull(e); // ��Ա��ɾ��
		//�޸ı������
		//���ԣ�gg
		//�ٴ�ִ�У�����ͨ��
	}
	@Test
	public void testDeleteEmployeeNotExisted() {
		int empId = 2002; //�����ڵĹ�Ա���
		Employee employee = PayrollDatabase.getEmployee(empId);
		assertNull(employee); //���ݿ��в����ڸĹ�Ա
		//ɾ����Ա
		Transaction t = new DeleteEmployeeTransaction(empId);
		try {
			t.execute();
			fail("No such employee.");
		} catch (Exception e) {
			assertTrue(e instanceof NoSuchEmployeeException);
		}
		//��һ�β��ԣ�����ͨ��
	}
}
