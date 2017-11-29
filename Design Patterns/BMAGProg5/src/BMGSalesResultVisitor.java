//Ben Girone CSC 352 11/29/17
//This file contains a class that defines an operation to calculate sales results.

import java.util.Iterator; //Iterator<BMGIEmployee>

public class BMGSalesResultVisitor
{
	/** getSalesResults
	 * Prints sales results for all commission employees under <param>employee</param>
	 * @param employee
	 */
	public void getSalesResults(BMGSupervisorEmployee employee)
	{
		//variable declaration
		double nextSalary;
		double nextSales;
		BMGIEmployee nextEmployee;
		
		//iterate over the employee hierarchy
		for (Iterator<BMGIEmployee> iterator = employee.getSubordinates().iterator(); iterator.hasNext();)
		{
			//get the next employee
			nextEmployee = iterator.next();
			
			//check if the next employee is a commission employee
			if (nextEmployee instanceof BMGCommissionEmployee)
			{
				//get the sales and salary of the employee
				nextSales = ((BMGCommissionEmployee) nextEmployee).getSales();
				nextSalary = nextEmployee.acceptSalaryVisitor(new BMGSalaryVisitor());
				
				//print the sales results of the employee
				System.out.println(nextEmployee.getName() + " : Sold $" + nextSales + ", Was Paid $" + nextSalary);
			}
			else //next employee is not paid by commission
			{
				//check if the employee is a supervisor
				if (nextEmployee instanceof BMGSupervisorEmployee)
				{
					//get the sales results for all commission employees under nextEmployee
					((BMGSupervisorEmployee) nextEmployee).acceptSalesVisitor(new BMGSalesResultVisitor());
				}
			}
		}
	}
}
