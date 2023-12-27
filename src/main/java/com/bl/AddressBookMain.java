package com.bl;

    import java.util.HashMap;
    import java.util.List;
    import java.util.Map;
    import java.util.Scanner;

public class AddressBookMain {
    public static void main(String[] args) {
        System.out.println("Welcome to Address Book Management program.");

        Map<String, AddressBook> addressBooks = new HashMap<>();       // Dictionary to store Address Book Name to Address Book

        Scanner sc = new Scanner(System.in);
        String choice;
            do {
                System.out.println("1. Add a new Address Book");
                System.out.println("2. Create new Contact");
                System.out.println("3. Select Address Book");
                System.out.println("4. Exit");

                System.out.print("Enter your choice: ");
                choice = sc.nextLine();

                switch (choice) {
                    case "1":
                        System.out.print("Enter a unique name for the new Address Book: ");
                        String newAddressBookName = sc.nextLine();

                        if (!addressBooks.containsKey(newAddressBookName)) {
                            AddressBook newAddressBook = new AddressBook();
                            addressBooks.put(newAddressBookName, newAddressBook);
                            System.out.println("Address Book with the name '" + newAddressBookName + "' created successfully!");
                        } else {
                            System.out.println("Address Book with this name already exists. Please choose a different name.");
                        }
                        break;
                    case "2":
                        System.out.println("Enter address book name: ");
                        String addressBookName = sc.nextLine();
                        if (addressBooks.containsKey(addressBookName)) {
                            AddressBook selectedAddressBookName = addressBooks.get(addressBookName);
                            operateOnAddressBook(selectedAddressBookName, sc);
                        } else {
                            System.out.println("Address Book not found. Please create it first.");
                        }
                        break;
                    case "3":
                        System.out.print("Enter the name of the Address Book: ");
                        String selectedAddressBookName = sc.nextLine();

                        // Check if the selected Address Book exists
                        if (addressBooks.containsKey(selectedAddressBookName)) {
                            AddressBook selectedAddressBook = addressBooks.get(selectedAddressBookName);
                            operateOnAddressBook(selectedAddressBook, sc);
                        } else {
                            System.out.println("Address Book not found. Please create it first.");
                        }
                        break;

                    case "4":
                        System.out.println("Exiting..");
                        break;

                    default:
                        System.out.println("Invalid choice! Please try again..");
                        break;
                }
                System.out.println();
            } while (!choice.equals("4"));

        }

        private static void operateOnAddressBook(AddressBook addressBook, Scanner sc) {
            String choice;
            do {
                System.out.println("1. Add a new contact");
                System.out.println("2. Display all contacts");
                System.out.println("3. Edit Name");
                System.out.println("4. Delete Contact");
                System.out.println("5. Sort the entries alphabetically");
                System.out.println("6. Sort the entries by city");
                System.out.println("7. Sort the entries by state");
                System.out.println("8. Sort the entries by zip");
                System.out.println("9. Write address book data in file");
                System.out.println("10. Read address book data in file");
                System.out.println("11. Write contacts to CSV");
                System.out.println("12. Read contacts from CSV");
                System.out.println("13. Write contacts as JSON");
                System.out.println("14. Read contacts from JSON");
                System.out.println("15. Exit");

                System.out.print("Enter your choice: ");
                choice = sc.nextLine();

                switch (choice) {
                    case "1":
                        System.out.println("Enter details for a new contact:");
                        System.out.print("First Name: ");
                        String newFirstName = sc.nextLine();
                        System.out.print("Last Name: ");
                        String newLastName = sc.nextLine();
                        System.out.print("Address: ");
                        String newAddress = sc.nextLine();
                        System.out.print("City: ");
                        String newCity = sc.nextLine();
                        System.out.print("State: ");
                        String newState = sc.nextLine();
                        System.out.print("ZIP: ");
                        String newZip = sc.nextLine();
                        System.out.print("Phone Number: ");
                        String newPhoneNumber = sc.nextLine();
                        System.out.print("Email: ");
                        String newEmail = sc.nextLine();
                        Contact newContact = new Contact(newFirstName, newLastName, newAddress, newCity, newState, newZip, newPhoneNumber, newEmail);
                        addressBook.addContact(newContact);
                        addressBook.writeContactsToCSV("contacts.csv");
                        addressBook.writeContactsToJSON("contacts.json");
                        break;
                    case "2":
                        System.out.println("Contact Details:");
                        addressBook.displayContacts();
                        break;
                    case "3":
                        System.out.println("Enter the first Name of the contact you want to edit:");
                        String editFirstName = sc.nextLine();

                        addressBook.editContact(editFirstName);
                        addressBook.displayContacts();
                        addressBook.writeContactsToCSV("contacts.csv");
                        addressBook.writeContactsToJSON("contacts.json");
                        break;
                    case "4":
                        System.out.println("Enter the First Name of the contact you want to delete:");
                        String deleteFirstName = sc.nextLine();

                        addressBook.deleteContact(deleteFirstName);
                        addressBook.writeContactsToCSV("contacts.csv");
                        addressBook.writeContactsToJSON("contacts.json");
                        break;
                    case "5":
                        addressBook.sortContactsByName();
                        System.out.println("Contacts sorted by name:");
                        addressBook.displayContacts();
                        addressBook.writeContactsToCSV("contacts.csv");
                        addressBook.writeContactsToJSON("contacts.json");
                        break;

                    case "6":
                        List<Contact> contactsSortedByCity = addressBook.sortContactsByCity();
                        System.out.println("Contacts sorted by City:");
                        addressBook.displayContacts(contactsSortedByCity);
                        break;

                    case "7":
                        List<Contact> contactsSortedByState = addressBook.sortContactsByState();
                        System.out.println("Contacts sorted by State:");
                        addressBook.displayContacts(contactsSortedByState);
                        break;
                    case "8":
                        List<Contact> contactsSortedByZip = addressBook.sortContactsByZip();
                        System.out.println("Contacts sorted by Zip:");
                        addressBook.displayContacts(contactsSortedByZip);
                        break;
                    case "9":
                        System.out.print("Enter the file name to write address book data: ");
                        String writeFileName = sc.nextLine();
                        addressBook.writeToFile(writeFileName);
                        break;
                    case "10":
                        System.out.print("Enter the file name to read address book data: ");
                        String readFileName = sc.nextLine();
                        addressBook.readFromFile(readFileName);
                        break;
                    case "11":
                        System.out.print("Enter the file name to write contacts as CSV: ");
                        String writeCSVFileName = sc.nextLine();
                        addressBook.writeContactsToCSV(writeCSVFileName);
                        break;
                    case "12":
                        System.out.print("Enter the file name to read contacts from CSV: ");
                        String readCSVFileName = sc.nextLine();
                        addressBook.readContactsFromCSV(readCSVFileName);
                        break;
                    case "13":
                        System.out.print("Enter the file name to write contacts as JSON: ");
                        String writeJSONFileName = sc.nextLine();
                        addressBook.writeContactsToJSON(writeJSONFileName);
                        System.out.println("Contacts written to JSON file successfully.");
                        break;
                    case "14":
                        System.out.print("Enter the file name to read contacts from JSON: ");
                        String readJSONFileName = sc.nextLine();
                        addressBook.readContactsFromJSON(readJSONFileName);
                        System.out.println("Contacts read from JSON file successfully.");
                        break;
                    case "15":
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid choice! Please try again...");
                        break;
                }
                System.out.println();
            } while (!choice.equals("11"));


            addressBook.displayContacts();
        }
    }
