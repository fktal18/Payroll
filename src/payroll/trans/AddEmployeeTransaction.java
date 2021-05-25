package payroll.trans;

import payroll.Employee;
import payroll.PaymentClassification;
import payroll.PayrollDatabase;
import payroll.Transaction;
import payroll.method.HoldMethod;

public abstract class AddEmployeeTransaction {

	protected int empId;

	protected abstract PaymentClassification getPaymentClassification();

	protected String name;
	protected String address;

	public AddEmployeeTransaction() {
		super();
	}

	@Override
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