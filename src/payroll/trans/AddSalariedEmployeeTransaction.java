package payroll.trans;

import payroll.Employee;
import payroll.PayrollDatabase;
import payroll.Transaction;
import payroll.classification.SalariedClassification;
import payroll.method.HoldMethod;

public class AddSalariedEmployeeTransaction implements Transaction {

	private int empId;
	private String name;
	private String address;
	private double salary;

	public AddSalariedEmployeeTransaction(int empId, String name, String address, double salary) {
		this.empId = empId;
		this.name = name;
		this.address = address;
		this.salary = salary;
	}

	@Override
	public void execute() {
		//新建雇员
		Employee employee = new Employee(empId,name,address);
		//设置工资计算方式
		employee.setPaymentClassification(getPaymentClassification());
		//设置工资支付方式
		employee.setPaymentMethod(new HoldMethod());
		//保存到数据库
		PayrollDatabase.save(employee);
	}

	private SalariedClassification getPaymentClassification() {
		return new SalariedClassification(salary);
	}

}
