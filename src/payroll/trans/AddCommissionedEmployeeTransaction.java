package payroll.trans;

import payroll.PaymentClassification;

public class AddCommissionedEmployeeTransaction  extends AddEmployeeTransaction{

	@Override
	protected PaymentClassification getPaymentClassification() {
		return null;
	}

}
