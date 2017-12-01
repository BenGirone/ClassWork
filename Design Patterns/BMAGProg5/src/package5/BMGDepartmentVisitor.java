package package5;
//Ben Girone CSC 352 11/29/17
//This class defines a visitor that can display department information.

import java.util.Iterator;

public class BMGDepartmentVisitor
{
	/** displayDepartmentInfo
	 * Prints the department head's info and the info of direct subordinates.
	 * @param employee
	 */
	public void displayDepartmentInfo(BMGSupervisorEmployee employee)
	{
		//variable declaration
		double departmentSalary = employee.acceptSalaryVisitor(new BMGSalaryVisitor()); //set the initial department salary to the supervisors salary
		double nextSalary; 
		BMGIEmployee nextEmployee;
		
		//print the supervisor's info
		System.out.println(employee.getName() + " : " + employee.getTitle() + ", is the department head and is paid: $" + departmentSalary + " per week.");
		
		//iterate over the employee hierarchy
		for (Iterator<BMGIEmployee> iterator = employee.getSubordinates().iterator(); iterator.hasNext();)
		{
			//get the next employee and the next employee's salary
			nextEmployee = iterator.next();
			nextSalary = nextEmployee.acceptSalaryVisitor(new BMGSalaryVisitor());
			
			//print the next employee's info
			System.out.print("    ");
			System.out.println(nextEmployee.getName() + " : " + nextEmployee.getTitle() + ", is paid: $" + nextSalary + " per week.");
		
			//add the next employee's salary to the total department salary
			departmentSalary += nextSalary;
		}
		
		//print the total department salary
		System.out.print("    ");
		System.out.println("Total department salary is $" + departmentSalary + " per week.");
	}
	
	/** displayDepartmentInfoCustom
	 * The same as {@link #displayDepartmentInfo(BMGSupervisorEmployee)}, but with a custom iterator.
	 * @param employee
	 */
	public void displayDepartmentInfoCustom(BMGSupervisorEmployee employee)
	{
		//variable declaration
		double departmentSalary = employee.acceptSalaryVisitor(new BMGSalaryVisitor()); //set the initial department salary to the supervisors salary
		double nextSalary; 
		
		//print the supervisor's info
		System.out.println(employee.getName() + " : " + employee.getTitle() + ", is the department head and is paid: $" + departmentSalary + " per week.");
		
		//iterate over the employee hierarchy
		for (BMGEmployeeIterator iterator = new BMGEmployeeIterator(employee); iterator.hasNext();)
		{
			//get the next employee and the next employee's salary
			BMGIEmployee nextEmployee = iterator.next();
			nextSalary = nextEmployee.acceptSalaryVisitor(new BMGSalaryVisitor());
			
			//print the next employee's info
			System.out.print("    ");
			System.out.println(nextEmployee.getName() + " : " + nextEmployee.getTitle() + ", is paid: $" + nextSalary + " per week.");
		
			//add the next employee's salary to the total department salary
			departmentSalary += nextSalary;
		}
		
		//print the total department salary
		System.out.print("    ");
		System.out.println("Total department salary is $" + departmentSalary + " per week.");
	}
}
