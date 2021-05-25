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

	//用例2  删除雇员。
	//使用DelEmp操作来删除雇员。操作形式：
	//DelEmp EmpId
	//当执行该操作时，会删除对应的雇员记录。
	//异常情况1：无效或者未知的EmpId。
	//如果EmpId字段不具有正确的结构，或者它没有引用到一条有效的雇员记录，会打印一条错误
	//消息，不进行其他处理。

	@Test
	public void testDeleteEmployeeExisted() {
		//先插入一个雇员，然后删除
		int empId = 2001;
		new AddHourlyEmployeeTransaction(empId, "Bill", "Home", 12.5)
			.execute();
		// 删除雇员
		Transaction t = new DeleteEmployeeTransaction(empId);
		t.execute();
		
		// 验证雇员已删除
		Employee e = PayrollDatabase.getEmployee(empId);
		assertNull(e); // 雇员已删除
		//修改编译错误
		//测试，gg
		//再次执行，测试通过
	}
	@Test
	public void testDeleteEmployeeNotExisted() {
		int empId = 2002; //不存在的雇员编号
		Employee employee = PayrollDatabase.getEmployee(empId);
		assertNull(employee); //数据库中不存在改雇员
		//删除雇员
		Transaction t = new DeleteEmployeeTransaction(empId);
		try {
			t.execute();
			fail("No such employee.");
		} catch (Exception e) {
			assertTrue(e instanceof NoSuchEmployeeException);
		}
		//第一次测试，并不通过
	}
}
