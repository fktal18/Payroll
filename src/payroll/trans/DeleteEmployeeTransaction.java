package payroll.trans;

import payroll.Employee;
import payroll.PayrollDatabase;
import payroll.Transaction;

public class DeleteEmployeeTransaction implements Transaction {

	private int empId;

	public DeleteEmployeeTransaction(int empId) {
		this.empId = empId;
	}

	@Override
	public void execute() {
		//���������ݿ��в��ҸĹ�Ա
		Employee employee = PayrollDatabase.getEmployee(empId);
		//���ҵ�����ɾ��
		if(employee != null){
			PayrollDatabase.deleteEmloyee(empId);
		}
		//�����׳��쳣����Ա�����ڣ�
		else{
			throw new NoSuchEmployeeException("No such empoyee empId = " + empId);
		}
	}

}
