import java.util.Iterator;

public class BMGEmployeeVisitor
{
	double getTotalSalary(BMGHourlyEmployee employee)
	{
		return employee.getHours() * employee.getPay();
	}
	
	double getTotalSalary(BMGCommissionEmployee employee)
	{
		return employee.getSales() * employee.getPay()/100;
	}
	
	double getTotalSalary(BMGWeeklyEmployee employee)
	{
		return employee.getPay();
	}
	
	double getSupervisorsSalary(BMGSupervisorEmployee employee)
	{
		double salary = employee.acceptSalaryVisitor(this);
		
		for (Iterator<BMGIEmployee> iterator = employee.getSubordinates().iterator(); iterator.hasNext();)
		{
			BMGIEmployee nextEmployee = iterator.next();
			if (nextEmployee instanceof BMGSupervisorEmployee)
				salary += nextEmployee.acceptSalaryVisitor(this);
		}
		
		System.out.println("Total weekly pay of all the supervisors: $" + salary);
		return salary;
	}

	double getNonSupervisorsSalaries(BMGSupervisorEmployee employee)
	{
		double salary = 0;
		double nextSalary;
		BMGIEmployee nextEmployee;
		
		System.out.println("Salaries for employees working under " + employee.getName() + ":");
		for (Iterator<BMGIEmployee> iterator = employee.getSubordinates().iterator(); iterator.hasNext();)
		{
			nextEmployee = iterator.next();
			if (!(nextEmployee instanceof BMGSupervisorEmployee))
			{
				nextSalary = nextEmployee.acceptSalaryVisitor(this);
				salary += nextSalary;
				System.out.println("    " + nextEmployee.getName() + " : $" + nextSalary);
			}
		}
		
		System.out.println("    Total : $" + salary);
		return salary;
	}

	double getSalesResults(BMGSupervisorEmployee employee)
	{
		double sales = 0;
		double salary = 0;
		double nextSalary;
		double nextSales;
		BMGIEmployee nextEmployee;
		
		System.out.println("Sales for employees working under " + employee.getName() + ":");
		
		for (Iterator<BMGIEmployee> iterator = employee.getSubordinates().iterator(); iterator.hasNext();)
		{
			nextEmployee = iterator.next();
			if (nextEmployee instanceof BMGCommissionEmployee)
			{
				nextSales = ((BMGCommissionEmployee) nextEmployee).getSales();
				nextSalary = nextEmployee.acceptSalaryVisitor(this);
				
				System.out.println("    " + nextEmployee.getName() + " : Sold $" + nextSales + ", Was Paid $" + nextSalary);
				
				sales += nextSales;
				salary += nextSalary;
			}
		}
		
		System.out.println("    Total : Sold $" + sales + ", Paid Out $" + salary);
		
		return sales;
	}
}
