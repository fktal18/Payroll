package payroll.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import payroll.Employee;
import payroll.PaymentClassification;
import payroll.PaymentMethod;
import payroll.PayrollDatabase;
import payroll.Transaction;
import payroll.classification.HourlyClassification;
import payroll.method.HoldMethod;
import payroll.trans.AddHourlyEmployeeTransaction;

class AddEmployeeTest {
	//����1�����ӹ�Ա��
		//ʹ��AddEmp����(transaction)�����¹�Ա���ò��������з���Ĺ�Ա�š���Ա�����ּ���
		//��ַ���ò�����3����ʽ��
		//AddEmp EmpId ��name�� ��address�� H hourly-rate
		//AddEmp EmpId ��name" ��address�� S monthly-salary
		//AddEmp EmpId ��name�� ��address�� C monthly-salary commission-rate
		//��Ա��¼�Ǹ��ݶ�Ӧ�ֶε�ֵ�������ġ�
		//�쳣���1�����������Ľṹ�д���
		//������������Ľṹ����ȷ�����ӡһ��������Ϣ���������κδ���

		@Test
		//���Сʱ���ʹ�Ա
		//AddEmp EmpId ��name�� ��address�� H hourly-rate
		public void testAddHourlyEmployeeTransaction() {
			int empId = 1001;
			String name = "Bill";
			String address = "Home";
			double hourlyRate = 12.5;
			
			// �½�����ӵ㹤��������ִ��
			Transaction t = new AddHourlyEmployeeTransaction(empId, name, address, hourlyRate);
			t.execute();
			// ��ִ֤�н��
			Employee e = PayrollDatabase.getEmployee(empId); // ���ݹ�Ա��Ŷ�ȡ��Ա��¼
			assertNotNull(e); // ��Ա��¼����
			assertEquals(empId, e.getEmpId()); // �����ȷ
			assertEquals(name, e.getName()); // ������ȷ
			assertEquals(address, e.getAddress()); // ��ַ��ȷ
			PaymentClassification pc = e.getPaymentClassification();
			assertTrue(pc instanceof HourlyClassification); // �ӵ㹤
			HourlyClassification hc = (HourlyClassification) pc;
			assertEquals(hourlyRate, hc.getHourlyRate(), 0.01); // Сʱ������ȷ
			PaymentMethod pm = e.getPaymentMethod();
			assertTrue(pm instanceof HoldMethod); // ֧����ʽĬ��Ϊ����֧Ʊ
			//��һ�β��ԣ�gg
			//�ٴγ���,gg
		
		}
}
