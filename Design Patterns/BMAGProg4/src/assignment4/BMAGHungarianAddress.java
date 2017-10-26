package assignment4;

import java.util.Scanner; //allow for user input

public class BMAGHungarianAddress implements IAddress {
	
	//variable declaration
	private String lastName = "";
	private String firstName = "";
	private String title = "";

	private String fourDigitCode = "";
	private String city = "";
	
	private String streetName = "";
	private String streetNumber = "";
	
	private String apartmentFloor = "";
	private String apartmentNumber = "";
	
	//accepts user input
	Scanner scanner = new Scanner(System.in);
	
	@Override
	public void inputAddress() {
		
		//prompt for and receive input for the address' last name
		System.out.println("Enter a last name <-- (imagine this is in hungarian):");
		editLastName();
		
		//prompt for and receive input for the address' first name
		System.out.println("Enter a first name <-- (imagine this is in hungarian):");
		editFirstName();
		
		//prompt for and receive input for the address' title
		System.out.println("Enter a title <-- (imagine this is in hungarian):");
		editTitle();
		
		//prompt for and receive input for the address' four digit code
		System.out.println("Enter a four digit code  <-- (imagine this is in hungarian):");
		editFourDigitCode();
		
		//prompt for and receive input for the address' city
		System.out.println("Enter a city name  <-- (imagine this is in hungarian):");
		editCity();
		
		//prompt for and receive input for the address' street name
		System.out.println("Enter a street name  <-- (imagine this is in hungarian):");
		editStreetName();
		
		//prompt for and receive input for the address' street number
		System.out.println("Enter a street number  <-- (imagine this is in hungarian):");
		editStreetNumber();
		
		//prompt for and receive input for the address' apartment floor
		System.out.println("Enter a apartment floor (or enter \"null\" if not applicable)  <-- (imagine this is in hungarian):");
		editApartmentFloor();
		
		//ensure the user did not enter null for the apartment floor
		if (!(apartmentFloor.contains("null"))) {
			//prompt for and receive input for the address' apartment number
			System.out.println("Enter a apartment number  <-- (imagine this is in hungarian):");
			editApartmentNumber();
		}
	}
	
	@Override
	public void printAddress() {
		//output the first line of the address
		System.out.println(lastName + "   " + firstName + "   " + title);
		
		//output the second line of the address
		System.out.println(fourDigitCode + "   " + city);
		
		//output the third line of the address
		System.out.print(streetName + "   " + streetNumber);
		
		//check if the user specified apartment info for the address
		if (!(apartmentFloor.contains("null")) && apartmentFloor != "")
			System.out.println("   " + apartmentFloor + "  u" + apartmentNumber); //output address the third line
		else
			System.out.println(""); //end the third line
	}
	
	//functions to edit the private variables with user input from scanner
	
	@Override
	public void editLastName() {
		lastName = scanner.nextLine();
	}

	@Override
	public void editFirstName() {
		firstName = scanner.nextLine();
	}

	@Override
	public void editTitle() {
		title = scanner.nextLine();
	}
		
	@Override
	public void editFourDigitCode() {
		fourDigitCode = ""; //reset the four digit code
		
		//loop until the user enters a four digit code in the proper format
		do {
			
			//check if the user has entered anything yet
			if (fourDigitCode != "")
				System.out.println("Incorrect format for code <-- (imagine this is in hungarian).");
			
			//receive the user input
			fourDigitCode = scanner.nextLine();
			
		} while (fourDigitCode.length() != 4); //check if the user input is formatted correctly
	}

	@Override
	public void editCity() {
		city = scanner.nextLine();
	}

	@Override
	public void editStreetName() {
		streetName = scanner.nextLine();		
	}

	@Override
	public void editStreetNumber() {
		streetNumber = scanner.nextLine();
	}

	@Override
	public void editApartmentFloor() {
		apartmentFloor = scanner.nextLine();
	}

	@Override
	public void editApartmentNumber() {
		apartmentNumber = scanner.nextLine();
	}
	
	//setters and getters for each private variable
	
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getFourDigitCode() {
		return fourDigitCode;
	}

	public void setFourDigitCode(String fourDigitCode) {
		this.fourDigitCode = fourDigitCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}

	public String getApartmentFloor() {
		return apartmentFloor;
	}

	public void setApartmentFloor(String apartmentFloor) {
		this.apartmentFloor = apartmentFloor;
	}

	public String getApartmentNumber() {
		return apartmentNumber;
	}

	public void setApartmentNumber(String apartmentNumber) {
		this.apartmentNumber = apartmentNumber;
	}
}
