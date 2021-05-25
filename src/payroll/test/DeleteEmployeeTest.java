package payroll.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import payroll.Employee;
import payroll.PayrollDatabase;
import payroll.Transaction;
import payroll.trans.AddHourlyEmployeeTransaction;

class DeleteEmployeeTest {

	//����2  ɾ����Ա��
	//ʹ��DelEmp������ɾ����Ա��������ʽ��
	//DelEmp EmpId
	//��ִ�иò���ʱ����ɾ����Ӧ�Ĺ�Ա��¼��
	//�쳣���1����Ч����δ֪��EmpId��
	//���EmpId�ֶβ�������ȷ�Ľṹ��������û�����õ�һ����Ч�Ĺ�Ա��¼�����ӡһ������
	//��Ϣ����������������

	@Test
	public void DeleteEmployeeTest() {
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
	}
}
