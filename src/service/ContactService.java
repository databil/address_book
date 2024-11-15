package service;
import model.Contact;

import java.util.ArrayList;

public class ContactService {

    ArrayList<Contact> contacts = new ArrayList<>();

    //save
    //update
    //delete
    //find

    public void save(Contact contact) {
        contacts.add(contact);
    }

    public void update(Contact newContact) {
        Contact oldContact = findByPhone(newContact.getPhone());
        contacts.remove(oldContact);
        contacts.add(newContact);
    }

    public void delete(String phone) {
        Contact contact = findByPhone(phone);
        contacts.remove(contact);
    }

    public Contact findByPhone(String phone) {

        for (Contact contact : contacts) {
            if (contact.getPhone().equals(phone)) {
                return contact;
            }
        }

        return null;
    }

    public String printContacts() {
        return contacts.toString();
    }

}
