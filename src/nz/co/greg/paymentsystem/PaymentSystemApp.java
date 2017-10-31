package nz.co.greg.paymentsystem;

import java.util.Scanner;

import nz.co.greg.paymentsystem.core.PaymentSystem;
import nz.co.greg.paymentsystem.dto.HourRate;
import nz.co.greg.paymentsystem.dto.PaymentType;
import nz.co.greg.paymentsystem.dto.Salary;
import nz.co.greg.paymentsystem.dto.SalaryWithCommission;

public class PaymentSystemApp {
	private static final Scanner sc = new Scanner(System.in);

	public static final int ADD_EMPLOYEE = 1;
	public static final int PRINT_REPORT = 2;
	public static final int EXIT = 3;

	public static final int HOUR_RATE = 1;
	public static final int SALARY = 2;
	public static final int SALARY_WITH_COMMISSION = 3;

	public static void main(String[] args) {

		PaymentSystem paymentSystem = new PaymentSystem();
		System.out.println("Initializing PaymentSystem...");
		paymentSystem.init();
		boolean run = true;
		System.out.println("***************************************");
		System.out.println("******Greg's Payment Syetem V1.0*******");
		System.out.println("***************************************");
		run = runApp(paymentSystem, run);
	}

	private static boolean runApp(PaymentSystem paymentSystem, boolean run) {
		while (run) {
			System.out.println("             Menu");
			System.out.println("1. Add employee");
			System.out.println("2. Print payment report");
			System.out.println("3. Exit");
			int selectedMenu = getPositiveNo("Select menu by type number: ");
			switch (selectedMenu) {
			case ADD_EMPLOYEE:
				paymentSystem.addEmployee(getPaymentTypeFromUser());
				break;
			case PRINT_REPORT:
				System.out.println(paymentSystem.createReport());
				break;
			case EXIT:
				run = false;
				System.out.println("Bye Bye..");
				break;
			default:
				System.out.println("!ERROR! Invalid input :" + selectedMenu);
				break;
			}
		}
		return run;
	}

	private static PaymentType getPaymentTypeFromUser() {
		while (true) {
			System.out.println("             Payment Type");
			System.out.println("1. Hour rate");
			System.out.println("2. Salary");
			System.out.println("3. Salary + Commission");
			int selectedPaymentType = getPositiveNo("Select payment type by type number: ");
			switch (selectedPaymentType) {
			case HOUR_RATE:
				int amount = getPositiveNo("Insert hourly rate: ");
				return new HourRate(amount);
			case SALARY:
				int salary = getPositiveNo("Insert Salary : ");
				return new Salary(salary);
			case SALARY_WITH_COMMISSION:
				int salaryAmount = getPositiveNo("Insert Salary : ");
				int commission = getPositiveNo("Insert Commission : ");
				return new SalaryWithCommission(salaryAmount, commission);
			}
			System.out.println("!ERROR! Invalid input :" + selectedPaymentType);
		}
	}

	private static int getPositiveNo(String msg) {
		while (true) {
			System.out.println(msg);
			String selectedPaymentTypeStr = sc.nextLine();
			try {
				int positiveNo = Integer.parseInt(selectedPaymentTypeStr);
				if (positiveNo > 0) {
					return positiveNo;
				} else {
					System.out.println("!ERROR! Input number is negative :"
							+ selectedPaymentTypeStr);
				}
			} catch (Exception any) {
				System.out.println("!ERROR! Invalid input :"
						+ selectedPaymentTypeStr);
			}
		}
	}
}
