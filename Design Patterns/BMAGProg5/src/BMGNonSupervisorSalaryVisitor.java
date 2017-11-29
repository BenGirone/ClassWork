//Ben Girone CSC 352 11/29/17
//This file contains a class that defines an operation to calculate non-supervisor salaries.

import java.util.Iterator; //Iterator<BMGIEmployee>

public class BMGNonSupervisorSalaryVisitor
{
	/** getNonSupervisorsSalaries
	 * Prints the weekly salaries of each non-supervisor employee under <param>employee</param>, regardless of pay type
	 * @param employee
	 */
	public void getNonSupervisorsSalaries(BMGSupervisorEmployee employee)
	{
		//variable declaration
		double nextSalary;
		BMGIEmployee nextEmployee;
		
		//iterate over the employee hierarchy
		for (Iterator<BMGIEmployee> iterator = employee.getSubordinates().iterator(); iterator.hasNext();)
		{
			//get the next employee
			nextEmployee = iterator.next();
			
			//check if the employee is a non-supervisor
			if (!(nextEmployee instanceof BMGSupervisorEmployee))
			{
				//print the salary of the employee
				nextSalary = nextEmployee.acceptSalaryVisitor(new BMGSalaryVisitor());
				System.out.println(nextEmployee.getName() + " : $" + nextSalary);
			}
			else //employee is supervisor
			{
				//print non-supervisor salaries of employees under nextEmployee
				((BMGSupervisorEmployee) nextEmployee).acceptNonSupervisorVisitor(this);
			}
		}
	}
}
