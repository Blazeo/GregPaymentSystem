package nz.co.greg.paymentsystem.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.omg.CORBA.Environment;

import nz.co.greg.paymentsystem.dto.Employee;
import nz.co.greg.paymentsystem.dto.PaymentType;

public class PaymentSystem {
	private static final File SAVE_DIR = new File("c:\\GregPaymentSystem");
	private static final File SAVE_FILE = new File(
			"c:\\GregPaymentSystem\\employees.data");
	private static final int EXCEED_PAYMENT_LIMIT = 1000;

	private int lastId = 0;
	private List<Employee> employees = new ArrayList<>();

	private int getNextId() {
		return lastId++;
	}

	public void init() {
		loadData();
	}

	public void addEmployee(PaymentType type) {
		employees.add(new Employee(getNextId(), type));
		saveData();
	}

	private void loadData() {
		ObjectInputStream ois;
		FileInputStream fis;
		System.out.println("Loading employee records from.. "+SAVE_FILE.toString());
		try {
			if (!SAVE_DIR.exists()) {
				System.out.println("No save folder found...");
				return;
			}
			
			fis = new FileInputStream(SAVE_FILE);
			ois = new ObjectInputStream(fis);
			employees = (List<Employee>) ois.readObject();
			lastId = employees.size()-1;
			System.out.println("Loaded " + employees.size() + " employees...");
			fis.close();
			ois.close();
			
		} catch (Exception any) {
			System.err.println("loadData has failed with :"+any.getCause());
		}
		return;
	}

	private void saveData() {
		System.out.println("Saving employee records to.. "+SAVE_FILE.toString());
		FileOutputStream fout;
		ObjectOutputStream oos;
		try {
			// if the directory does not exist, create it
			if (!SAVE_DIR.exists()) {
				System.out.println("creating directory: " + SAVE_DIR.getName());
				SAVE_DIR.mkdir();
				System.out.println("DIR created");
			}
			fout = new FileOutputStream(SAVE_FILE, false);
			oos = new ObjectOutputStream(fout);
			oos.writeObject(employees);
			System.out
					.println(employees.size() + " employees data has saved..");
			
			fout.close();
			oos.close();
		} catch (Exception any) {
			System.err.println("saveData has failed with :"+any.getCause());
		}
	}

	public String createReport(int hours) {
		StringBuilder sb = new StringBuilder("Payment Reports"+ System.lineSeparator() );
		int totalPayment = 0;
		for (Employee employee : employees) {
			sb.append(employee.toString()+ System.lineSeparator() );
			int paymentAmountForThisWeek = employee.calculatePayment(hours);
			sb.append("Payment for this week (" + hours + " hours):"
					+ paymentAmountForThisWeek+ System.lineSeparator() );
			
			if (paymentAmountForThisWeek > EXCEED_PAYMENT_LIMIT) {
				sb.append("Payment exceed the limit set to max payment :"
						+ EXCEED_PAYMENT_LIMIT+ System.lineSeparator() );
				paymentAmountForThisWeek = EXCEED_PAYMENT_LIMIT;
			}
			
			totalPayment += paymentAmountForThisWeek;
		}
		sb.append("***Total Payment***"+ System.lineSeparator() );
		sb.append(totalPayment + " $ out of " + employees.size() + " employees"+ System.lineSeparator() );
		return sb.toString();
	}

}
