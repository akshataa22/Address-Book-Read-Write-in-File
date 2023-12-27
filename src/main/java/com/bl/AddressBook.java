package com.bl;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class AddressBook implements Serializable {
    private static final long serialVersionUID = 1L;

    private ArrayList<Contact> contacts;

    public AddressBook() {
        this.contacts = new ArrayList<>();
    }

    public void addContact(Contact contact) {
        contacts.add(contact);
        System.out.println("Contact added successfully!");
    }

    public void displayContacts() {
        for (Contact contact : contacts) {
            System.out.println("First Name: " + contact.firstName);
            System.out.println("Last Name: " + contact.lastName);
            System.out.println("Address: " + contact.address);
            System.out.println("City: " + contact.city);
            System.out.println("State: " + contact.state);
            System.out.println("ZIP: " + contact.zip);
            System.out.println("Phone Number: " + contact.phoneNumber);
            System.out.println("Email: " + contact.email);
            System.out.println("-----------------------");
        }
    }

    public void editContact(String inputFirstName) {
        for (Contact contact : contacts) {
            if (contact.firstName.equals(inputFirstName)) {
                Scanner sc = new Scanner(System.in);
                System.out.println("Enter new details for the contact:");
                System.out.print("New First Name: ");
                String newFirstName = sc.nextLine();

                contact.firstName = newFirstName;
                System.out.println("Contact name changed to " + contact.firstName);

                System.out.println("Contact details updated successfully.");
                return;
            }
        }
        System.out.println("Contact not found.");
    }

    public void deleteContact(String firstName) {
        for (Contact contact : contacts) {
            if (contact.firstName.equals(firstName)) {
                contacts.remove(contact);
                System.out.println("Contact deleted successfully.");
                return;
            }
        }
        System.out.println("Contact not found.");
    }

    public void sortContactsByName() {
        List<Contact> sortedContacts = contacts.stream()
                .sorted(Comparator.comparing(Contact::getFullName))
                .collect(Collectors.toList());

        contacts.clear();
        contacts.addAll(sortedContacts);
    }

    public List<Contact> sortContactsByCity() {
        return contacts.stream()
                .sorted(Comparator.comparing(Contact::getCity))
                .collect(Collectors.toList());
    }

    public List<Contact> sortContactsByState() {
        return contacts.stream()
                .sorted(Comparator.comparing(Contact::getState))
                .collect(Collectors.toList());
    }

    public List<Contact> sortContactsByZip() {
        return contacts.stream()
                .sorted(Comparator.comparing(Contact::getZip))
                .collect(Collectors.toList());
    }

    public void displayContacts(List<Contact> contacts) {
        if (contacts.isEmpty()) {
            System.out.println("No contacts to display.");
        } else {
            contacts.forEach(contact -> System.out.println(contact.toString()));
        }
    }

    public void writeToFile(String fileName) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            outputStream.writeObject(contacts);
            System.out.println("Address book written to file successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error writing to file.");
        }
    }

    @SuppressWarnings("unchecked")
    public void readFromFile(String fileName) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName))) {
            contacts = (ArrayList<Contact>) inputStream.readObject();
            System.out.println("Address book read from file successfully.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Error reading from file.");
        }
    }

    public void writeContactsToCSV(String fileName) {
        try (CSVWriter csvWriter = new CSVWriter(new FileWriter(fileName))) {
            String[] headerRecord = {"FirstName", "LastName", "Address", "City", "State", "ZIP", "PhoneNumber", "Email"};
            csvWriter.writeNext(headerRecord);

            for (Contact contact : contacts) {
                String[] data = {contact.firstName, contact.lastName, contact.address, contact.city,
                        contact.state, contact.zip, contact.phoneNumber, contact.email};
                csvWriter.writeNext(data);
            }

            System.out.println("Contacts written to CSV file successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error writing contacts to CSV file.");
        }
    }

    public void readContactsFromCSV(String fileName) {
        try (CSVReader csvReader = new CSVReader(new FileReader(fileName))) {
            String[] nextRecord;

            // Skip header
            csvReader.readNext();

            while ((nextRecord = csvReader.readNext()) != null) {
                Contact contact = new Contact(nextRecord[0], nextRecord[1], nextRecord[2], nextRecord[3],
                        nextRecord[4], nextRecord[5], nextRecord[6], nextRecord[7]);
                contacts.add(contact);
            }

            System.out.println("Contacts read from CSV file successfully.");
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
            System.out.println("Error reading contacts from CSV file.");
        }
    }

    public void writeContactsToJSON(String fileName) {
        try (FileWriter writer = new FileWriter(fileName)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(contacts, writer);
            System.out.println("Contacts written to JSON file successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error writing contacts to JSON file.");
        }
    }

    public void readContactsFromJSON(String fileName) {
        try (FileReader reader = new FileReader(fileName)) {
            Gson gson = new Gson();
            Contact[] contactsArray = gson.fromJson(reader, Contact[].class);
            contacts = new ArrayList<>(Arrays.asList(contactsArray));
            System.out.println("Contacts read from JSON file successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error reading contacts from JSON file.");
        }
    }

    public ArrayList<Contact> getContacts() {
        return contacts;
    }
}
