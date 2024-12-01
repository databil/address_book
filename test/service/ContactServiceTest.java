package service;


import model.Contact;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import repository.FileRepository;

import java.io.File;
import java.util.InputMismatchException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class ContactServiceTest {

    //save
    //update
    //delete
    //find

    @TempDir
    File tempDir;

    ContactService contactService;
    FileRepository fileRepository;

    @BeforeEach
    void setUp() {
        File tempfile = new File(tempDir, "contacts.txt");
        fileRepository = new FileRepository(tempfile.getPath());
        contactService = new ContactService(tempfile.getPath());
    }

    @Test
    @DisplayName("Tests save contact method, adds new Contact")
    public void contactSaveTest() {

        //Given
        Contact contact1 = new Contact("John", "Smith", "+996 555 223 222");
        Contact contactPhoneIncorrect = new Contact("Will", "Smith", "123");

        //When
        contactService.save(contact1);
        assertThrows(InputMismatchException.class, () -> {
            contactService.save(contactPhoneIncorrect);
        });

        Contact contact = contactService.findByPhone("+996 555 223 222");

        //Then
        assertEquals(contact1.getPhone(), contact.getPhone());
        assertEquals(contact1.getName(), contact.getName());
        assertEquals(contact1.getSurname(), contact.getSurname());

        //file assertions
        List<Contact> contactsFromFile = fileRepository.readContacts();
        assertEquals(1, contactsFromFile.size());


    }

    @Test
    @DisplayName("Tests update Contact, should update existing coontact")
    public void contactUpdateTest() {
        //Given
        Contact contact1 = new Contact("Wi", "Sm", "+996555223222");
        contactService.save(contact1);

        //When
        Contact updateContact = new Contact("William", "Smith", "+996555223222");
        contactService.update(updateContact);

        Contact updatedContact = contactService.findByPhone("+996555223222");

        //Then
        assertNotEquals("Wi", updatedContact.getName());
        assertEquals("William", updatedContact.getName());

        assertNotEquals("Sm", updatedContact.getSurname());
        assertEquals("Smith", updatedContact.getSurname());

        //file assertions
        List<Contact> contactsFromFile = fileRepository.readContacts();
        assertEquals(1, contactsFromFile.size());
        assertEquals("Smith", contactsFromFile.get(0).getSurname());

    }

    @Test
    @DisplayName("Tests delete Contact, should remove contact")
    public void contactDeleteTest(){
        //Given
        Contact contact1 = new Contact("John", "Smith", "+996 555 223 222");
        contactService.save(contact1);

        Contact contact2 = new Contact("William", "Smith", "+996 555 223 221");
        contactService.save(contact2);

        assertEquals(2, contactService.getContactSize());

        //When
        contactService.delete("+996 555 223 222");

        //Then

        Contact deletedContact = contactService.findByPhone("+996 555 223 222");
        assertNull(deletedContact);
        assertEquals(1, contactService.getContactSize());

    }

    @Test
    @DisplayName("Tests findContacts by phonePrefix, should return all contacts which starts with same phone prefix")
    public void findContactsByPhonePrefixTest() {
        //Given
        Contact contact1 = new Contact("John", "Smith", "+996 551 223 222");
        contactService.save(contact1);

        Contact contact2 = new Contact("William", "Smith", "+996 551 223 221");
        contactService.save(contact2);

        Contact contact3 = new Contact("Donald", "Trump", "+996 555 223 223");
        contactService.save(contact3);

        assertEquals(3, contactService.getContactSize());

        //When
        List<Contact> foundContacts = contactService.findByPhonePrefix("+996 551");

        //Then
        assertEquals(2, foundContacts.size());
        assertNotEquals(3, foundContacts.size());

        assertFalse(foundContacts.contains(contact3));
        assertTrue(foundContacts.contains(contact2));
        assertTrue(foundContacts.contains(contact1));

    }

    //Test Validation

}
