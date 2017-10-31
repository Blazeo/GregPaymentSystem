package nz.co.greg.paymentsystem.dto;

import java.io.Serializable;

public class Employee implements Serializable {
	private int employeeId;
	private PaymentType paymentType;
	public Employee(){}
	public Employee(int employeeId, PaymentType paymentType) {
		this.employeeId = employeeId;
		this.paymentType = paymentType;
	}

	public PaymentType getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}

	public int getEmployeeId() {
		return employeeId;
	}	
	
	public int calculatePayment(int hours)
	{
		try {
			return paymentType.calculatePayment(hours);
		} catch (Exception any) {
			System.err.println("ERROR!! "+any.getStackTrace());
			return 0;
		}
	}
	@Override
	public String toString() {
		return "[Employee id:"+employeeId+", payment type:{"+paymentType.toString()+"}]" ;
	}
	
	
}
