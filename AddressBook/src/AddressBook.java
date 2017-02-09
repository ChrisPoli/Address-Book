import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class AddressBook {

	Scanner scan = new Scanner(System.in);
	public final int MAX_ENTRIES = 10;

	private ArrayList<Contact> contacts;

	public AddressBook() {
		contacts = new ArrayList<Contact>(MAX_ENTRIES);

		displayOptions();
	}

	public static void main(String[] args) {
		new AddressBook();
	}

	public void displayOptions() {
		System.out.println("\nAddressBook");
		if (contacts.size() == 1)
			System.out.println("\nThere is " + contacts.size() + " contact in the Address Book.");
		else
			System.out.println("\nThere are " + contacts.size() + " contacts in the Address Book.");
		System.out.println("\n1) Load contact information from file");
		System.out.println("2) Save contact information to file");
		System.out.println("3) Add book entry");
		System.out.println("4) Remove book entry");
		System.out.println("5) Edit book entry");
		System.out.println("6) Sort book ");
		System.out.println("7) Search book");
		System.out.println("8) View entry");
		System.out.println("9) Quit");
		System.out.println("\nPlease choose what you want to do.");

		int choice = scan.nextInt();
		makeChoice(choice);

	}

	public void makeChoice(int choice) {
		if (choice == 1) {
			loadFile();
		}
		if (choice == 2) {
			saveFile();
		}
		if (choice == 3) {
			addEntry();
		}
		if (choice == 4) {
			removeEntry();
		}
		if (choice == 5) {
			editEntry();
		}
		if (choice == 6) {
			sortBook();
		}
		if (choice == 7) {
			searchEntry();
		}
		if (choice == 8) {
			viewAllEntries();
		}
		if (choice == 9) {
			System.exit(0);
		} else {
			displayOptions();
		}
	}

	public void loadFile() {
		Scanner kb = new Scanner(System.in);
		System.out.println("Which contact would you like to load into the addressbook? Enter first name.");
		String c = kb.nextLine() + ".txt";
		try {
			BufferedReader reader = new BufferedReader(new FileReader(c));
			String[] tokens = new String[4];
			tokens = reader.readLine().split("/");
			System.out.println("Loaded  " + tokens[0] + " " + tokens[1] + " , " + tokens[2] + " , " + tokens[3]);
			contacts.add(new Contact(tokens[0], tokens[1], tokens[2], (Integer.parseInt(tokens[3]))));
			System.out.println(
					"\nSaved " + contacts.get(0).getFName() + " as contact " + contacts.size() + " in addressbook.");
			displayOptions();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public void saveFile() {
		System.out.println("Which # contact do you want to save to a file?");
		int choice = scan.nextInt() - 1;
		System.out.println("Contact will be saved as first name as entered.");

		try {
			FileWriter writer = new FileWriter(new File(contacts.get(choice).getFName() + ".txt"));
			writer.write(contacts.get(choice).getFName() + "/" + contacts.get(choice).getLName() + "/"
					+ contacts.get(choice).getAddress() + "/" + contacts.get(choice).getNumber());
			writer.close();
			System.out.println("Saved");
			displayOptions();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void addEntry() {
		Scanner kb = new Scanner(System.in);
		System.out.println("Please enter first name," + " last name, address and number with /" + " after each.");
		String entry = kb.nextLine();
		String[] tokens = new String[4];
		tokens = entry.split("/");
		System.out.println(
				"Saving contact details as " + tokens[0] + " " + tokens[1] + " " + tokens[2] + " " + tokens[3]);

		contacts.add(new Contact(tokens[0], tokens[1], tokens[2], (Integer.parseInt(tokens[3]))));
		System.out
				.println("Saved " + contacts.get(0).getFName() + " as contact " + contacts.size() + " in addressbook.");

		displayOptions();
	}

	public void removeEntry() {
		System.out.println("\nWhich entry number would you like to remove from addressbook?");
		int choice = scan.nextInt();
		contacts.remove(choice - 1);
		System.out.println("Entry " + choice + " removed.");
		displayOptions();

	}

	public void editEntry() {
		System.out.println("\nWhich entry would you like to edit?");
		int choice = scan.nextInt();
		System.out.println("Which component of " + contacts.get(choice - 1).getFName() + " would you like to edit?");
		String comp = scan.next();

		if (comp.equals("first name") || comp.equals("firstname") || comp.equals("First Name")
				|| comp.equals("First name")) {
			System.out.println("Change first name to?");
			String f = scan.next();
			contacts.get(choice - 1).setFName(f);
			System.out.println("Successfully changed first name to " + contacts.get(choice - 1).getFName() + ".");
		} else if (comp.equals("last name") || comp.equals("lastname") || comp.equals("Last Name")
				|| comp.equals("Last name")) {
			System.out.println("Change last name to?");
			String l = scan.next();
			contacts.get(choice - 1).setLName(l);
			System.out.println("Successfully changed first name to " + contacts.get(choice - 1).getLName() + ".");
		} else if (comp.equals("address") || comp.equals("Address")) {
			System.out.println("Change address to?");
			String a = scan.next();
			contacts.get(choice - 1).setAddress(a);
			System.out.println("Successfully changed address to " + contacts.get(choice - 1).getAddress() + ".");
		} else if (comp.equals("number") || comp.equals("Number")) {
			System.out.println("Change number to?");
			int n = scan.nextInt();
			contacts.get(choice - 1).setNumber(n);
			System.out.println("Successfully changed number to " + contacts.get(choice - 1).getNumber() + ".");
		} else {
			System.out.println("Option not found.");
		}
		displayOptions();

	}

	public void sortBook() {
		System.out.println("Comparing by first name");
		// CompareContacts contactCompare = new CompareContacts();

		Collections.sort(contacts);
		System.out.println(contacts);
	}

	public void searchEntry() {
		Scanner kb = new Scanner(System.in);
		System.out.println("Enter what you want to search for?");
		String choice = kb.nextLine();

		for (Contact c : contacts) {
			if (c.getFName().equals(choice)) {
				System.out.println("Found first name match!");
				System.out.println("Contact : " + c.getFName() + " " + c.getLName() + " , " + c.getAddress() + " , "
						+ c.getNumber());
				displayOptions();
			}
			if (c.getLName().equals(choice)) {
				System.out.println("Found last name match!");
				System.out.println("Contact : " + c.getFName() + " " + c.getLName() + " , " + c.getAddress() + " , "
						+ c.getNumber());
				displayOptions();
			}
			if (c.getAddress().equals(choice)) {
				System.out.println("Found address match!");
				System.out.println("Contact : " + c.getFName() + " " + c.getLName() + " , " + c.getAddress() + " , "
						+ c.getNumber());
				displayOptions();
			}
			if (Integer.toString(c.getNumber()).equals(choice)) {
				System.out.println("Found number match!");
				System.out.println("Contact : " + c.getFName() + " " + c.getLName() + " , " + c.getAddress() + " , "
						+ c.getNumber());
				displayOptions();
			}
		}

		System.out.println("No results found for : \"" + choice + "\".");
		System.out.print("\nSearch again? Y or N:");
		String yOrNo = kb.nextLine();
		if (yOrNo.equals("Y")) {
			searchEntry();
		} else {
			displayOptions();
		}

	}

	public void viewAllEntries() {
		System.out.println("\nCurrent contacts in book:");
		int contactNum = 1;
		if (contacts.size() > 0) {
			for (int i = 0; i < contacts.size(); i++) {
				System.out.println(contactNum + ": " + contacts.get(i).getFName() + " , " + contacts.get(i).getLName()
						+ " , " + contacts.get(i).getAddress() + " , " + contacts.get(i).getNumber());
				contactNum++;
			}
		} else {
			System.out.println("No contacts in AddressBook!");
		}
		displayOptions();
	}

}
