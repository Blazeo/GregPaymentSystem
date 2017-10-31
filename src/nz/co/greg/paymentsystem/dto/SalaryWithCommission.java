package nz.co.greg.paymentsystem.dto;

public class SalaryWithCommission extends Salary {
	private int commission;
	public SalaryWithCommission(int salary, int commission) {
		super(salary);
		this.commission=commission;
		
		
	}

	@Override
	public int calculatePayment(int hours) throws Exception {

		return super.calculatePayment(hours)+commission;
	}
}