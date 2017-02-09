
public class Contact implements Comparable<Contact> {
	
	private String firstName,lastName,address;
	private int number;
	
	public int compareTo(Contact name) {
		return this.firstName.compareTo(name.getFName());
	}
	public Contact(String f,String l, String a, int n) {
		firstName = f;
		lastName = l;
		address = a;
		number = n;
	}
	
	public String getFName() {
		return firstName;
	}
	public String getLName() {
		return lastName;
	}
	public String getAddress() {
		return address;
	}
	public int getNumber() {
		return number;
	}
	public void setFName(String f) {
		firstName = f;
	}
	public void setLName(String l) {
		lastName = l;
	}
	public void setAddress(String a) {
		address = a;
	}
	public void setNumber(int n) {
		number = n;
	}
}
