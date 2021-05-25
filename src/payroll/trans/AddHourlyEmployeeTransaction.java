package payroll.trans;

import payroll.Employee;
import payroll.Transaction;
import payroll.classification.HourlyClassification;

public class AddHourlyEmployeeTransaction implements Transaction {

	private int empId;
	private String name;
	private String address;
	private double hourlyRate;

	public AddHourlyEmployeeTransaction(int empId, String name, String address, double hourlyRate) {
		this.empId = empId;
		this.name = name;
		this.address = address;
		this.hourlyRate = hourlyRate;
	}

	@Override
	public void execute() {
		//�½���Ա
		Employee employee = new Employee(empId,name,address);
		//���ù��ʼ��㷽ʽ
		employee.setPaymentClassification(new HourlyClassification(hourlyRate));
		//���ù���֧����ʽ
		//���浽���ݿ�
	}

}
