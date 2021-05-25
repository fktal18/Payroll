package payroll.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

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
		}
}
