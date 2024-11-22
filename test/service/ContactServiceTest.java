package service;


import model.Contact;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class ContactServiceTest {

    //save
    //update
    //delete
    //find

    ContactService contactService = new ContactService();

    @Test
    @DisplayName("Tests save contact method, adds new Contact")
    public void contactSaveTest() {

        //Given
        Contact contact1 = new Contact("John", "Smith", "12345");

        //When
        contactService.save(contact1);
        Contact contact = contactService.findByPhone("12345");

        //Then
        assertEquals(contact1, contact);

    }

    @Test
    @DisplayName("Tests update Contact, should update existing coontact")
    public void contactUpdateTest() {
        //Given
        Contact contact1 = new Contact("W", "S", "12345");
        contactService.save(contact1);

        //When
        Contact updateContact = new Contact("William", "Smith", "12345");
        contactService.update(updateContact);

        Contact updatedContact = contactService.findByPhone("12345");

        //Then
        assertNotEquals(updatedContact.getName(), "W");
        assertEquals(updatedContact.getName(), "William");

        assertNotEquals(updatedContact.getSurname(), "S");
        assertEquals(updatedContact.getSurname(), "Smith");

    }

    @Test
    @DisplayName("Tests delete Contact, should remove contact")
    public void contactDeleteTest(){
        //Given
        Contact contact1 = new Contact("John", "Smith", "12345");
        contactService.save(contact1);

        Contact contact2 = new Contact("William", "Smith", "123456");
        contactService.save(contact2);

        assertEquals(2, contactService.getContactSize());

        //When
        contactService.delete("12345");

        //Then

        Contact deletedContact = contactService.findByPhone("12345");
        assertNull(deletedContact);
        assertEquals(1, contactService.getContactSize());

    }

    @Test
    @DisplayName("Tests findContacts by phonePrefix, should return all contacts which starts with same phone prefix")
    public void findContactsByPhonePrefixTest() {
        //Given
        Contact contact1 = new Contact("John", "Smith", "12345");
        contactService.save(contact1);

        Contact contact2 = new Contact("William", "Smith", "123456");
        contactService.save(contact2);

        Contact contact3 = new Contact("Donald", "Trump", "45678");
        contactService.save(contact3);

        assertEquals(3, contactService.getContactSize());

        //When
        List<Contact> foundContacts = contactService.findByPhonePrefix("123");

        //Then
        assertEquals(2, foundContacts.size());
        assertNotEquals(3, foundContacts.size());

        assertFalse(foundContacts.contains(contact3));
        assertTrue(foundContacts.contains(contact2));
        assertTrue(foundContacts.contains(contact1));

    }

}
