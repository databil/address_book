package service;
import model.Contact;

import java.util.ArrayList;
import java.util.List;

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

    public List<Contact> findByPhonePrefix(String phonePrefix) {

        List<Contact> filteredList = contacts.stream().filter(c -> c.getPhone().startsWith(phonePrefix)).toList();

      /*  ArrayList<Contact> foundContacts = new ArrayList<>();

        for (Contact contact : contacts) {
            if (contact.getPhone().startsWith(phonePrefix)) {
                foundContacts.add(contact);
            }
        }
*/
        return filteredList;
    }

    public String printContacts() {
        return contacts.toString();
    }

    public int getContactSize() {
        return contacts.size();
    }

}
