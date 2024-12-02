package repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Contact;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileRepository {

    private final String FILE_PATH;
    private final ObjectMapper objectMapper = new ObjectMapper();

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
    public ArrayList<Contact> readContacts() {
       try {
           File file = new File(FILE_PATH);
           return objectMapper.readValue(file, ArrayList.class);
       } catch (Exception e) {
           return new ArrayList<>();
       }
    }

    /*
    open file
    List of Contacts convert to String: name; surname; phone
    write to the file
     */
    public void writeContacts(List<Contact> contacts) {
        try {
           objectMapper.writeValue(new File(FILE_PATH), contacts);
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
