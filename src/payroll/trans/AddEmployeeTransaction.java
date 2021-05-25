package payroll.trans;

import payroll.Employee;
import payroll.PaymentClassification;
import payroll.PayrollDatabase;
import payroll.Transaction;
import payroll.method.HoldMethod;

public abstract class AddEmployeeTransaction implements Transaction {

	protected int empId;
	protected String name;
	protected String address;

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
		//�½���Ա
		Employee employee = new Employee(empId,name,address);
		//���ù��ʼ��㷽ʽ
		employee.setPaymentClassification(getPaymentClassification());
		//���ù���֧����ʽ
		employee.setPaymentMethod(new HoldMethod());
		//���浽���ݿ�
		PayrollDatabase.save(employee);
	}

}