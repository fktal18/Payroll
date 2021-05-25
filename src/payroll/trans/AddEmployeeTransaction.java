package payroll.trans;

import payroll.Employee;
import payroll.PaymentClassification;
import payroll.PayrollDatabase;
import payroll.Transaction;
import payroll.method.HoldMethod;

public abstract class AddEmployeeTransaction implements Transaction {

	private int empId;
	private String name;
	private String address;

	public AddEmployeeTransaction(int empId, String name, String address) {
		super();
		this.empId = empId;
		this.name = name;
		this.address = address;
	}
	protected abstract PaymentClassification getPaymentClassification();
	
	public AddEmployeeTransaction() {
		super();
	}

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

}