//Ben Girone CSC 352 11/29/17
//This file contains a class to define employees who supervise other employees.
//Supervisors can have any pay type. This class wraps an employee of another pay type.

import java.util.ArrayList; //ArrayList<BMGIEmployee>
import java.util.Iterator; //Iterator<BMGIEmployee>

public class BMGSupervisorEmployee implements BMGIEmployee
{	
	//data members
	private BMGIEmployee employee;
	
	private ArrayList<BMGIEmployee> subordinates = new ArrayList<BMGIEmployee>();
	
	//constructor
	public BMGSupervisorEmployee(BMGIEmployee e)
	{
		employee = e;
	}
	
	/** getSubordinates
	 * Returns a list of all employees that are subordinates of this employee.
	 * @return subordinates
	 */
	public ArrayList<BMGIEmployee> getSubordinates()
	{
		return subordinates;
	}

	@Override
	public String getName()
	{
		return employee.getName();
	}

	@Override
	public void setName(String name)
	{
		employee.setName(name);
	}

	@Override
	public String getTitle()
	{
		return employee.getTitle();
	}

	@Override
	public void setTitle(String title)
	{
		employee.setTitle(title);
	}

	@Override
	public double getPay()
	{
		return employee.getPay();
	}

	@Override
	public void setPay(double pay)
	{
		employee.setPay(pay);
	}

	public void addSubordinate(BMGIEmployee employee)
	{
		subordinates.add(employee);
	}
	
	public void removeSubordinate(BMGIEmployee employee)
	{
		if (subordinates.contains(employee))
			subordinates.remove(employee);
	}

	@Override
	public void display()
	{
		//display the info on the employee wrapped by this object
		employee.display();
		
		//iterate over the employee hierarchy
		for (Iterator<BMGIEmployee> iterator = subordinates.iterator(); iterator.hasNext();)
		{
			//indent
			System.out.print("    ");
			
			//display the next employee
			iterator.next().display();
		}
	}

	@Override
	public void displayCustomIterator()
	{
		//display the info on the employee wrapped by this object
		employee.displayCustomIterator();
		
		//iterate over the employee hierarchy
		for (BMGEmployeeIterator iterator = new BMGEmployeeIterator(this); iterator.hasNext();)
		{
			//indent
			System.out.print("    ");
			
			//display the next employee
			iterator.next().displayCustomIterator();
		}
	}
	
	@Override
	public double acceptSalaryVisitor(BMGSalaryVisitor visitor)
	{
		return employee.acceptSalaryVisitor(visitor);
	}
	
	public double acceptSupervisorVisitor(BMGSupervisorSalaryVisitor visitor)
	{
		return visitor.getSupervisorsSalary(this);
	}
	
	public void acceptNonSupervisorVisitor(BMGNonSupervisorSalaryVisitor visitor)
	{
		visitor.getNonSupervisorsSalaries(this);
	}
	
	public void acceptSalesVisitor(BMGSalesResultVisitor visitor)
	{
		visitor.getSalesResults(this);
	}
	
}
