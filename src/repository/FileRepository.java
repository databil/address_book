package repository;

import model.Contact;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FileRepository {

    private String FILE_PATH;

    public FileRepository(String filePath) {
        this.FILE_PATH = filePath;
    }

    /*
      open file
      read records from file : name; surname; phone
      parse to Contact object
      add to List
      return list of contacts
     */
    public List<Contact> readContacts() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            //John;Smith;+996555555555
            //William;Smith;+996700700700
            return reader.lines().map(line -> parseContact(line)).collect(Collectors.toList());
        } catch (IOException e) {
           System.out.println("File not found!");
           return new ArrayList<>();
        }
    }

    /*
    open file
    List of Contacts convert to String: name; surname; phone
    write to the file
     */
    public void writeContacts(List<Contact> contacts) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Contact contact : contacts) {
                writer.write(contact.toString());
                writer.newLine();
            }
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public Contact parseContact(String line) {
        String[] splitedLine = line.split(";");
        return new Contact(splitedLine[0], splitedLine[1], splitedLine[2]);
    }
}
