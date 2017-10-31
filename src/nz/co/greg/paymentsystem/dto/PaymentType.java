package nz.co.greg.paymentsystem.dto;

public abstract class PaymentType {

	protected int paymentAmount;
	
	public int calculatePayment(int hours) throws Exception {
		throw new Exception("Please implement this method");
	}
	
	
}
