package package5;
//Ben Girone CSC 352 11/29/17
//This file contains a class that defines an operation to calculate supervisor salaries.

import java.util.Iterator; //Iterator<BMGIEmployee>

public class BMGSupervisorSalaryVisitor
{
	/**getSupervisorsSalary
	 * An operation to sum the salaries of supervisors.
	 * @param employee
	 * @return the total salary of all supervisors under and including <strong>employee</strong>.
	 */
	public double getSupervisorsSalary(BMGSupervisorEmployee employee)
	{
		//declare a variable to store sum the salaries and set it to the salary of the first supervisor 
		double salary = employee.acceptSalaryVisitor(new BMGSalaryVisitor());
		
		//declare a pointer to point to each employee the iterator returns
		BMGIEmployee nextEmployee;
		
		//iterate over the employee hierarchy
		for (Iterator<BMGIEmployee> iterator = employee.getSubordinates().iterator(); iterator.hasNext();)
		{
			//get the next employee
			nextEmployee = iterator.next();
			
			//check if the next employee is a supervisor
			if (nextEmployee instanceof BMGSupervisorEmployee)
				//sum the salary of the supervisor and any subordinate supervisors
				salary += ((BMGSupervisorEmployee) nextEmployee).acceptSupervisorVisitor(this);
		}
		
		//return the summation
		return salary;
	}
	
	/**getSupervisorsSalaryCustom
	 * Same as {@link #getSupervisorsSalary(BMGSupervisorEmployee)}, but with a custom iterator.
	 * @param employee
	 * @return the total salary of all supervisors under and including <strong>employee</strong>.
	 */
	public double getSupervisorsSalaryCustom(BMGSupervisorEmployee employee)
	{
		//declare a variable to store sum the salaries and set it to the salary of the first supervisor 
		double salary = employee.acceptSalaryVisitor(new BMGSalaryVisitor());
		
		//iterate over the employee hierarchy
		for (BMGEmployeeIterator iterator = new BMGEmployeeIterator(employee); iterator.hasNext();)
		{
			//get the next employee
			BMGIEmployee nextEmployee = iterator.next();
			
			//check if the next employee is a supervisor
			if (nextEmployee instanceof BMGSupervisorEmployee)
				//sum the salary of the supervisor and any subordinate supervisors
				salary += ((BMGSupervisorEmployee) nextEmployee).acceptSupervisorVisitor(this);
		}
		
		//return the summation
		return salary;
	}
}
