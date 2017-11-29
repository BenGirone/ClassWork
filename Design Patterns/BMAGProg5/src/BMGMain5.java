public class BMGMain5
{
	public static void main(String[] args)
	{
		BMGSupervisorEmployee billSmith = new BMGSupervisorEmployee(new BMGWeeklyEmployee("Bill Smith", "CEO", 2000));
		BMGSupervisorEmployee janetJones = new BMGSupervisorEmployee(new BMGWeeklyEmployee("Janet Jones", "CFO", 1800));
		BMGSupervisorEmployee willJames = new BMGSupervisorEmployee(new BMGWeeklyEmployee("Will James", "VP of Human Resources", 1200));
		BMGSupervisorEmployee jillJohn = new BMGSupervisorEmployee(new BMGWeeklyEmployee("Jill John", "VP of Marketing", 1500));
		BMGSupervisorEmployee andrewNagy = new BMGSupervisorEmployee(new BMGWeeklyEmployee("Andrew Nagy", "VP of Manufacturing", 1600));
		BMGSupervisorEmployee janeJanet = new BMGSupervisorEmployee(new BMGWeeklyEmployee("Jane Janet", "Foreman", 1000));
		
		BMGHourlyEmployee bettyBill = new BMGHourlyEmployee("Betty Bill", "Secretary of CEO", 15, 40);
		BMGHourlyEmployee philFine = new BMGHourlyEmployee("Phil Fine", "Head Accountant", 18, 35);
		BMGHourlyEmployee jamesOverworked = new BMGHourlyEmployee("James Overworked", "Assistant Accountant", 10, 10);
		BMGHourlyEmployee joanArc = new BMGHourlyEmployee("Joan Arc", "Secretary of VP of Human Resources", 19, 45);
		BMGHourlyEmployee helgaHelper = new BMGHourlyEmployee("Helga Helper", "Floor Worker", 10, 40);
		BMGHourlyEmployee henryHelper = new BMGHourlyEmployee("Henry Helper", "Floor Worker", 9, 40);
		BMGHourlyEmployee littleBilly = new BMGHourlyEmployee("Little Billy", "Floor Worker", 5, 20);
		
		BMGCommissionEmployee imaCheater = new BMGCommissionEmployee("Ima Cheeter", "SalesPerson", 2, 20000);
		BMGCommissionEmployee stanfordNuthin = new BMGCommissionEmployee("Stanford Nuthin", "SalesPerson", 4, 10000);
		
		janeJanet.AddSubordinate(helgaHelper);
		janeJanet.AddSubordinate(henryHelper);
		janeJanet.AddSubordinate(littleBilly);
		
		andrewNagy.AddSubordinate(janeJanet);
		
		jillJohn.AddSubordinate(imaCheater);
		jillJohn.AddSubordinate(stanfordNuthin);
		
		willJames.AddSubordinate(joanArc);
		
		janetJones.AddSubordinate(philFine);
		janetJones.AddSubordinate(jamesOverworked);
		
		billSmith.AddSubordinate(janetJones);
		billSmith.AddSubordinate(willJames);
		billSmith.AddSubordinate(jillJohn);
		billSmith.AddSubordinate(andrewNagy);
		billSmith.AddSubordinate(bettyBill);
		
		billSmith.display();
		System.out.println("\n");
		
		billSmith.displayCustomIterator();
		System.out.println("\n");
		
		billSmith.acceptNonSupervisorVisitor(new BMGEmployeeVisitor());
		System.out.println("\n");
		
		billSmith.acceptSupervisorVisitor(new BMGEmployeeVisitor());
		System.out.println("\n");
		
		billSmith.acceptSalesVisitor(new BMGEmployeeVisitor());
	}
}