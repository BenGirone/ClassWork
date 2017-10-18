//This program gives an example of the adapter design pattern.
//The class BMAGAmericanAddress acts as the adapter and the class BMAGHungarianAddress acts as the adaptee
//IAddress serves as the common interface that both adapter and adaptee implement

package assignment4;

public class BMAGProg4 {

	public static void main(String[] args) {
		
		//create an American and Hungarian address object
		BMAGHungarianAddress myaddress = new BMAGHungarianAddress();
		BMAGAmericanAddress myaddressAmerican = new BMAGAmericanAddress();
		
		//output empty addresses
		myaddress.printAddress();
		myaddressAmerican.printAddress();
		
		//get user input for myaddress
		myaddress.inputAddress();
		
		System.out.println(""); //output a blank line
		
		//output myaddress
		myaddress.printAddress(); 
		
		System.out.println(""); //output a blank line
		
		//test if the adapter can take a raw Hungarian address.
		myaddressAmerican = new BMAGAmericanAddress(myaddress);
		
		System.out.println(""); //output a blank line
		
		//output myaddressAmerican
		myaddressAmerican.printAddress();
		
		System.out.println(""); //output a blank line
		
		//get user input for myaddressAmerican
		myaddressAmerican.inputAddress();
		
		System.out.println(""); //output a blank line
		
		//output myaddressAmerican
		myaddressAmerican.printAddress();
		
		//additional proof that the classes are compatible
		IAddress addresses[] = {myaddress, myaddressAmerican};
	}
}
