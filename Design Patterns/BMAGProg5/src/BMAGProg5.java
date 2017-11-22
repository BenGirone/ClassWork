
public class BMAGProg5
{
	public static void main(String[] args)
	{
		SupervisorEmployee billSmith = new SupervisorEmployee(new WeeklyEmployee("Bill Smith", "CEO", 2000));
		SupervisorEmployee janetJones = new SupervisorEmployee(new WeeklyEmployee("Janet Jones", "CFO", 1800));
		SupervisorEmployee willJames = new SupervisorEmployee(new WeeklyEmployee("Will James", "VP of Human Resources", 1200));
		SupervisorEmployee jillJohn = new SupervisorEmployee(new WeeklyEmployee("Jill John", "VP of Marketing", 1500));
		SupervisorEmployee andrewNagy = new SupervisorEmployee(new WeeklyEmployee("Andrew Nagy", "VP of Manufacturing", 1600));
		SupervisorEmployee janeJanet = new SupervisorEmployee(new WeeklyEmployee("Jane Janet", "Foreman", 1000));
		
		HourlyEmployee bettyBill = new HourlyEmployee("Betty Bill", "Secretary of CEO", 15, 40);
		HourlyEmployee philFine = new HourlyEmployee("Phil Fine", "Head Accountant", 18, 35);
		HourlyEmployee jamesOverworked = new HourlyEmployee("James Overworked", "Assistant Accountant", 10, 10);
		HourlyEmployee joanArc = new HourlyEmployee("Joan Arc", "Secretary of VP of Human Resources", 19, 45);
		HourlyEmployee helgaHelper = new HourlyEmployee("Helga Helper", "Floor Worker", 10, 40);
		HourlyEmployee henryHelper = new HourlyEmployee("Henry Helper", "Floor Worker", 9, 40);
		HourlyEmployee littleBilly = new HourlyEmployee("Little Billy", "Floor Worker", 5, 20);
		
		CommissionEmployee imaCheater = new CommissionEmployee(" Ima Cheeter", "SalesPerson", 2, 20000);
		CommissionEmployee stanfordNuthin = new CommissionEmployee("Ima Cheeter", "SalesPerson", 4, 10000);
		
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
		
		System.out.println("done");
	}
}
