package assignment4;

import java.util.Scanner; //allow for user input

public class BMAGAmericanAddress implements IAddress {

	//variable declaration
	private BMAGHungarianAddress hAddress; //adaptee
	
	//accepts user input
	private Scanner scanner = new Scanner(System.in);
	
	//a constructor that adapts an existing Hungarian address to an American address
	//I have no idea why this would be useful for this adapter pattern, but it can be useful in other implementations of the pattern (Wrappers)
	public BMAGAmericanAddress(BMAGHungarianAddress h) {
		hAddress = h;
		
		//check if the zip code follows the standard American zip or zip+4 standards
		if (hAddress.getFourDigitCode().length() != 5 || hAddress.getFourDigitCode().length() != 10) {
			//inform the user and receive the properly formatted input
			System.out.println("Incorrect zip or zip+4 format. You must enter a different zip code: ");
			editFourDigitCode();
		}
		
		//check if the address contains a city and state separated by a comma
		if (!(hAddress.getCity().contains(","))) {
			//inform the user and receive properly formatted input
			System.out.println("City and state field must be of the format \"City, State\"");
			editCity();
		}
	}
	
	//a basic constructor
	public BMAGAmericanAddress() {
		//initialize the adaptee
		hAddress = new BMAGHungarianAddress();
	}
	
	@Override
	public void inputAddress() {
		//prompt for and receive input for the address' first name
		System.out.println("Enter a title:");
		editTitle();
		
		//prompt for and receive input for the address' first name
		System.out.println("Enter a first name:");
		editFirstName();
		
		//prompt for and receive input for the address' last name
		System.out.println("Enter a last name:");
		editLastName();
		
		//prompt for and receive input for the address' apartment floor
		System.out.println("Enter a apartment floor (or enter \"null\" if not applicable):");
		editApartmentFloor();
		
		//check if the user entered "null" for the apartment floor variable in hAddress
		if (!(hAddress.getApartmentFloor().contains("null"))) {
			//prompt for and receive input for the address' apartment number
			System.out.println("Enter a apartment number:");
			editApartmentNumber();
		}

		//prompt for and receive input for the address' street number
		System.out.println("Enter a street number:");
		editStreetNumber();
		
		//prompt for and receive input for the address' street name
		System.out.println("Enter a street name:");
		editStreetName();
		
		//prompt for and receive input for the address' city, state
		System.out.println("Enter city, state names e.g. (Steubenville, OH):");
		editCity();
		
		//prompt for and receive input for the address' zip code
		System.out.println("Enter a zip code:");
		editFourDigitCode();
		
	}

	@Override
	public void printAddress() {
		//output the first line of the address
		System.out.println(hAddress.getTitle() + " " + hAddress.getFirstName() + " " + hAddress.getLastName());
		
		//check if the user specified apartment info for the address
		if (!(hAddress.getApartmentFloor().contains("null")) && hAddress.getApartmentFloor() != "")
			//out put the apartment info
			System.out.println("FL " + hAddress.getApartmentFloor() + " APT " + hAddress.getApartmentNumber());
		
		//output the street info of the address
		System.out.println(hAddress.getStreetNumber() + " " + hAddress.getStreetName());
		
		//output the city and state info of the address
		System.out.println(hAddress.getCity() + " " + hAddress.getFourDigitCode());
	}

	//functions to edit the private variables with user input 
	
	@Override
	public void editLastName() {
		hAddress.editLastName();
	}

	@Override
	public void editFirstName() {
		hAddress.editFirstName();
	}

	@Override
	public void editTitle() {
		hAddress.editTitle();
	}
	
	@Override
	public void editFourDigitCode() {
		String zip = ""; //temporary variable
		
		//loop until the user inputs a zip in the proper format
		do {
			
			//check if the user entered anything yet
			if (!(zip == ""))
				System.out.println("Incorrect zip or zip+4 format");
			
			//receive the user input into the temporary variable
			zip = scanner.nextLine();
			
		} while (zip.length() != 5 && zip.length() != 10); //check if the input is formatted correctly
		
		//set the four digit code variable in hAddress to the properly formatted info
		hAddress.setFourDigitCode(zip);
			
	}

	@Override
	public void editCity() {
		String cityState = ""; //temporary variable
		
		//loop until the user inputs a "city, state" in the proper format 
		do {
			//check if the user has entered anything yet
			if (!(cityState == ""))
				System.out.println("Must be of the format \"City, State\"");
			
			//receive the user input into the temporary variable
			cityState = scanner.nextLine();
			
		} while (!(cityState.contains(","))); //check if the input is formatted correctly
		
		//set the city variable in hAddress to the properly formatted info
		hAddress.setCity(cityState);
	}

	@Override
	public void editStreetName() {
		hAddress.editStreetName();
	}

	@Override
	public void editStreetNumber() {
		hAddress.editStreetNumber();
	}

	@Override
	public void editApartmentFloor() {
		hAddress.editApartmentFloor();
	}

	@Override
	public void editApartmentNumber() {
		hAddress.editApartmentNumber();
	}
}
