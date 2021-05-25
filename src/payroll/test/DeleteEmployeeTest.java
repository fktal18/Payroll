package payroll.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import payroll.Employee;
import payroll.PayrollDatabase;
import payroll.Transaction;
import payroll.trans.AddHourlyEmployeeTransaction;

class DeleteEmployeeTest {

	//用例2  删除雇员。
	//使用DelEmp操作来删除雇员。操作形式：
	//DelEmp EmpId
	//当执行该操作时，会删除对应的雇员记录。
	//异常情况1：无效或者未知的EmpId。
	//如果EmpId字段不具有正确的结构，或者它没有引用到一条有效的雇员记录，会打印一条错误
	//消息，不进行其他处理。

	@Test
	public void DeleteEmployeeTest() {
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
	}
}
