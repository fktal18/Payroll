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
	//用例1：增加雇员。
		//使用AddEmp操作(transaction)增加新雇员。该操作包含有分配的雇员号、雇员的名字及其
		//地址。该操作有3种形式：
		//AddEmp EmpId “name” “address” H hourly-rate
		//AddEmp EmpId “name" “address” S monthly-salary
		//AddEmp EmpId “name” “address” C monthly-salary commission-rate
		//雇员记录是根据对应字段的值来创建的。
		//异常情况1：描述操作的结构有错误。
		//如果描述操作的结构不正确，会打印一条错误消息，不进行任何处理。

		@Test
		//添加小时工资雇员
		//AddEmp EmpId “name” “address” H hourly-rate
		public void testAddHourlyEmployeeTransaction() {
			int empId = 1001;
			String name = "Bill";
			String address = "Home";
			double hourlyRate = 12.5;
			
			// 新建添加钟点工操作，并执行
			Transaction t = new AddHourlyEmployeeTransaction(empId, name, address, hourlyRate);
			t.execute();
			// 验证执行结果
			Employee e = PayrollDatabase.getEmployee(empId); // 根据雇员编号读取雇员记录
			assertNotNull(e); // 雇员记录存在
			assertEquals(empId, e.getEmpId()); // 编号正确
			assertEquals(name, e.getName()); // 名字正确
			assertEquals(address, e.getAddress()); // 地址正确
			PaymentClassification pc = e.getPaymentClassification();
			assertTrue(pc instanceof HourlyClassification); // 钟点工
			HourlyClassification hc = (HourlyClassification) pc;
			assertEquals(hourlyRate, hc.getHourlyRate(), 0.01); // 小时工资正确
			PaymentMethod pm = e.getPaymentMethod();
			assertTrue(pm instanceof HoldMethod); // 支付方式默认为保存支票
			//第一次测试，gg
			//再次尝试,gg
			//运行，成功
		}
		//添加月薪雇员
		//AddEmp EmpId “name" “address” S monthly-salary
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
			assertTrue(pc instanceof SalariedClassification); // 月薪方式
			SalariedClassification sc = (SalariedClassification) pc;
			assertEquals(salary, sc.getSalary(), 0.01); // 月薪正确
			PaymentMethod pm = e.getPaymentMethod();
			assertTrue(pm instanceof HoldMethod);
			//修改编译问题
			//尝试运行，失败
			//再次尝试，ok
			//有重复代码，抽取超类
			//抽取完成，测试成功
		}
		//添加月薪加销售提成的雇员
		//AddEmp EmpId “name” “address” C monthly-salary commission-rate
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
