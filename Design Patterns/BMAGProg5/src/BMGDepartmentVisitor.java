import java.util.Iterator;

public class BMGDepartmentVisitor
{
	public void displayDepartmentInfo(BMGSupervisorEmployee employee)
	{
		double departmentSalary = employee.acceptSalaryVisitor(new BMGSalaryVisitor());
		double nextSalary = departmentSalary;
		System.out.println(employee.getName() + " : " + employee.getTitle() + ", is the department head and is paid: $" + departmentSalary + " per week.");
		
		//iterate over the employee hierarchy
		for (Iterator<BMGIEmployee> iterator = employee.getSubordinates().iterator(); iterator.hasNext();)
		{
			BMGIEmployee nextEmployee = iterator.next();
			nextSalary = nextEmployee.acceptSalaryVisitor(new BMGSalaryVisitor());
			
			System.out.print("    ");
			System.out.println(nextEmployee.getName() + " : " + nextEmployee.getTitle() + ", is paid: $" + nextSalary + " per week.");
		
			departmentSalary += nextSalary;
		}
		
		System.out.print("    ");
		System.out.println("Total department salary is $" + departmentSalary + " per week.");
	}
}
