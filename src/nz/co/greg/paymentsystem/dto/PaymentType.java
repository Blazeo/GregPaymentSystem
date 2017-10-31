package nz.co.greg.paymentsystem.dto;

import java.io.Serializable;

public abstract class PaymentType implements Serializable{
	protected int paymentAmount;
	
	public int calculatePayment(int hours) throws Exception {
		throw new Exception("Please implement this method");
	}	
}
