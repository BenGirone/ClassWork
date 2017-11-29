import java.util.Iterator;

public class BMGDepartmentVisitor
{
	public void displayDepartmentInfo(BMGSupervisorEmployee employee)
	{
		double departmentSalary = employee.acceptSalaryVisitor(new BMGSalaryVisitor());
		double nextSalary;
		System.out.println(employee.getName() + " : " + employee.getTitle() + ", is the department head and is paid: $" + departmentSalary);
		
		//iterate over the employee hierarchy
		for (Iterator<BMGIEmployee> iterator = employee.getSubordinates().iterator(); iterator.hasNext();)
		{
			BMGIEmployee nextEmployee = iterator.next();
			
			nextSalary = nextEmployee.acceptSalaryVisitor(new BMGSalaryVisitor());
			
			if (nextEmployee instanceof BMGSupervisorEmployee)
			{
				System.out.println();
			}
			else
			{
				nextEmployee.display();
			}
			
			departmentSalary += nextSalary;
		}
	}
}
