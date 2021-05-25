package payroll.classification;

import payroll.PaymentClassification;

public class CommissionedClassification extends PaymentClassification {

	public double getSalary() {
		return 0;
	}

	public double getCommissionRate() {
		return 0;
	}

}
