package nz.co.greg.paymentsystem.dto;

public class Salary extends PaymentType {
	public Salary(int Salary) {
		paymentAmount = Salary;

	}

	private int getHourRate() {
		return (paymentAmount / 52) / 40;
	}

	@Override
	public int calculatePayment(int hours) throws Exception {

		return getHourRate() * hours;
	}
}