package package5;
//Ben Girone CSC 352 11/29/17
//This program uses the Composite pattern to create an employee hierarchy for a fictional company.
//The Visitor and Iterator patterns are used to perform several operations on the hierarchy.
//Both extra credit opportunities have been fulfilled.

//NOTE: Documenting comments are written at the highest level definition of each member/function.

public class BMGMain5
{
	//begin main function
	public static void main(String[] args)
	{
		//declare the supervisor employees
		BMGSupervisorEmployee billSmith = new BMGSupervisorEmployee(new BMGWeeklyEmployee("Bill Smith", "CEO", 2000));
		BMGSupervisorEmployee janetJones = new BMGSupervisorEmployee(new BMGWeeklyEmployee("Janet Jones", "CFO", 1800));
		BMGSupervisorEmployee willJames = new BMGSupervisorEmployee(new BMGWeeklyEmployee("Will James", "VP of Human Resources", 1200));
		BMGSupervisorEmployee jillJohn = new BMGSupervisorEmployee(new BMGWeeklyEmployee("Jill John", "VP of Marketing", 1500));
		BMGSupervisorEmployee andrewNagy = new BMGSupervisorEmployee(new BMGWeeklyEmployee("Andrew Nagy", "VP of Manufacturing", 1600));
		BMGSupervisorEmployee janeJanet = new BMGSupervisorEmployee(new BMGWeeklyEmployee("Jane Janet", "Foreman", 1000));
		
		//declare the hourly employees
		BMGHourlyEmployee bettyBill = new BMGHourlyEmployee("Betty Bill", "Secretary of CEO", 15, 40);
		BMGHourlyEmployee philFine = new BMGHourlyEmployee("Phil Fine", "Head Accountant", 18, 35);
		BMGHourlyEmployee jamesOverworked = new BMGHourlyEmployee("James Overworked", "Assistant Accountant", 10, 10);
		BMGHourlyEmployee joanArc = new BMGHourlyEmployee("Joan Arc", "Secretary of VP of Human Resources", 19, 45);
		BMGHourlyEmployee helgaHelper = new BMGHourlyEmployee("Helga Helper", "Floor Worker", 10, 40);
		BMGHourlyEmployee henryHelper = new BMGHourlyEmployee("Henry Helper", "Floor Worker", 9, 40);
		BMGHourlyEmployee littleBilly = new BMGHourlyEmployee("Little Billy", "Floor Worker", 5, 20);
		
		//declare the sales employees
		BMGCommissionEmployee imaCheater = new BMGCommissionEmployee("Ima Cheeter", "SalesPerson", 2, 20000);
		BMGCommissionEmployee stanfordNuthin = new BMGCommissionEmployee("Stanford Nuthin", "SalesPerson", 4, 10000);
		
		//set employee(s) under Jane Janet
		janeJanet.addSubordinate(helgaHelper);
		janeJanet.addSubordinate(henryHelper);
		janeJanet.addSubordinate(littleBilly);
		
		//set employee(s) under Andrew Nagey
		andrewNagy.addSubordinate(janeJanet);
		
		//set employee(s) under Jill John
		jillJohn.addSubordinate(imaCheater);
		jillJohn.addSubordinate(stanfordNuthin);
		
		//set employee(s) under Will James
		willJames.addSubordinate(joanArc);
		
		//set employee(s) under Janet Jones
		janetJones.addSubordinate(philFine);
		janetJones.addSubordinate(jamesOverworked);
		
		//set employee(s) under Bill Smith
		billSmith.addSubordinate(janetJones);
		billSmith.addSubordinate(willJames);
		billSmith.addSubordinate(jillJohn);
		billSmith.addSubordinate(andrewNagy);
		billSmith.addSubordinate(bettyBill);
		
		//display all the employees working under Bill Smith
		billSmith.display();
		System.out.println("\n");
		
		//display all the employees working under Bill Smith using a custom iterator (BMGEmployeeIterator)
		billSmith.displayCustomIterator();
		System.out.println("\n");
		
		//display the weekly salaries of all non-supervisor employees working under Bill Smith
		billSmith.acceptNonSupervisorVisitor(new BMGNonSupervisorSalaryVisitor());
		System.out.println("\n");
		
		//display the total weekly salary paid to all supervisors working under Bill Smith
		System.out.println("Total weekly pay of all the supervisors under "
							+ billSmith.getName()
							+ ": $"
							+ billSmith.acceptSupervisorVisitor(new BMGSupervisorSalaryVisitor()));
		System.out.println("\n");
		
		//display the sales results for all salesman working under Bill Smith
		billSmith.acceptSalesVisitor(new BMGSalesResultVisitor());
		System.out.println("\n");
		
		//display the department info for the employees directly under Bill Smith
		billSmith.acceptDepartmentVisitor(new BMGDepartmentVisitor());
		
	} //end main function
}