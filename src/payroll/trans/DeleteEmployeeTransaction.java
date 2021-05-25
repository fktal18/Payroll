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
		//首先在数据库中查找改雇员
		Employee employee = PayrollDatabase.getEmployee(empId);
		//若找到，则删除
		if(employee != null){
			PayrollDatabase.deleteEmloyee(empId);
		}
		//否则，抛出异常（雇员不存在）
		else{
			throw new NoSuchEmployeeException("No such empoyee empId = " + empId);
		}
	}

}
