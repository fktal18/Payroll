package payroll.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import payroll.Employee;
import payroll.PaymentClassification;
import payroll.PaymentMethod;
import payroll.PayrollDatabase;
import payroll.Transaction;
import payroll.classification.HourlyClassification;
import payroll.classification.SalariedClassification;
import payroll.method.HoldMethod;
import payroll.trans.AddHourlyEmployeeTransaction;
import payroll.trans.AddSalariedEmployeeTransaction;

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
			//���У��ɹ�
		}
		//�����н��Ա
		//AddEmp EmpId ��name" ��address�� S monthly-salary
		@Test
		public  void testAddSalariedEmployee() {
			int empId = 1002;
			String name = "Bill";
			String address = "Home";
			double salary = 2410.0;
			
			Transaction t = new AddSalariedEmployeeTransaction(empId, name, address, salary);
			t.execute();
			Employee e = PayrollDatabase.getEmployee(empId);
			assertNotNull(e);
			assertEquals(name, e.getName());
			assertEquals(address, e.getAddress());
			PaymentClassification pc = e.getPaymentClassification();
			assertTrue(pc instanceof SalariedClassification); // ��н��ʽ
			SalariedClassification sc = (SalariedClassification) pc;
			assertEquals(salary, sc.getSalary(), 0.01); // ��н��ȷ
			PaymentMethod pm = e.getPaymentMethod();
			assertTrue(pm instanceof HoldMethod);
			//�޸ı�������
			//�������У�ʧ��
			//�ٴγ��ԣ�ok
			//���ظ����룬��ȡ����
			//��ȡ��ɣ����Գɹ�
		}
		//�����н��������ɵĹ�Ա
		//AddEmp EmpId ��name�� ��address�� C monthly-salary commission-rate
		@Test
		public void testAddCommissionedEmployee() {
			int empId = 1002;
			String name = "Bill";
			String address = "Home";
			double salary = 2410.0;
			double commissionRate = 0.02;
			
			Transaction t = new AddCommissionedEmployeeTransaction(empId, name, address,
					salary, commissionRate);
			t.execute();
			
			Employee e = PayrollDatabase.getEmployee(empId);
			assertNotNull(e);
			assertEquals(name, e.getName());
			assertEquals(address, e.getAddress());
			PaymentClassification pc = e.getPaymentClassification();
			assertTrue(pc instanceof CommissionedClassification);
			CommissionedClassification sc = (CommissionedClassification) pc;
			assertEquals(salary, sc.getSalary(), 0.01);
			assertEquals(commissionRate, sc.getCommissionRate(), 0.0001);
		}
		
		
}
