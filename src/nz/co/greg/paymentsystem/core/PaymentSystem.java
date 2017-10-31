package nz.co.greg.paymentsystem.core;

import java.util.ArrayList;
import java.util.List;

import nz.co.greg.paymentsystem.dto.Employee;
import nz.co.greg.paymentsystem.dto.PaymentType;

public class PaymentSystem {
	private int lastId = 0;
	private List<Employee> employees = new ArrayList<>();
	private int getNextId()
	{
		return lastId++;
	}	
	
	public void init() {
		// TODO Auto-generated method stub		
	}

	public void addEmployee(PaymentType type) {
		employees.add(new Employee(getNextId(), type));		
		saveData();
	}
	
	private void saveData()
	{
		
	}

	public String createReport() {
		// TODO Auto-generated method stub
		return null;
	}

}
