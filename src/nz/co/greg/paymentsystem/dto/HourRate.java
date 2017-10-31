package nz.co.greg.paymentsystem.dto;

public class HourRate extends PaymentType {
	public HourRate(int hourRate) {
		paymentAmount = hourRate;
	}

	@Override
	public int calculatePayment(int hours) throws Exception {
		return paymentAmount * hours;
	}

	@Override
	public String toString() {
		return "HourRate:"+paymentAmount;
	}

}
