import model.Contact;
import service.ContactService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ContactService contactService = new ContactService();

        int command;

       do {
           System.out.println("1.Create Contact\n2.Search Contact\n3.Update\n4.Delete Contact\n5.List All Contacts\n6.Exit");
           command = scanner.nextInt();
           scanner.nextLine();
           switch (command) {
               case 1:
                   System.out.println("Create Contact");
                   System.out.println("Name:");
                   String name = scanner.next();

                   System.out.println("Surname");
                   String surname = scanner.next();

                   //TODO fix scanner reads Line
                   System.out.println("Phone");
                   String phone = scanner.nextLine();
                   try {
                       contactService.save(new Contact(name, surname, phone));
                   } catch (InputMismatchException e) {
                       System.out.println("Incorrect input, try again! Name/Surname should be A-Z a-z1.phone format +996 xxx xxx xxx");
                   }
                   break;


               case 2:
                   System.out.println("Search Contact by phone");
                   System.out.println("Enter phone to search: ");
                   String searchPhone = scanner.next();

                   Contact foundContact = contactService.findByPhone(searchPhone);

                   if (foundContact != null) {
                       System.out.println("Found contact: " + foundContact);
                   } else {
                       System.out.println("No contact with such phone number!");
                   }

                   break;
               case 3:
                   System.out.println("Update Contact");
                   System.out.println("Enter phone to search: ");
                   String updatePhone = scanner.next();

                   Contact updateContact = contactService.findByPhone(updatePhone);

                   if (updateContact != null) {
                       System.out.println("Name:");
                       String newName = scanner.next();
                       System.out.println("Surname");
                       String newSurname = scanner.next();
                       System.out.println("Phone");
                       String newPhone = scanner.next();

                       contactService.update(new Contact(newName, newSurname, newPhone));

                       System.out.println("Contact Updated!");
                   } else {
                       System.out.println("No contact with such phone number!");
                   }

                   break;

               case 4:
                   System.out.println("Delete Contact by Phone");
                   System.out.println("Enter phone to delete: ");
                   String deletePhone = scanner.next();

                   contactService.delete(deletePhone);

                   break;
               case 5:
                   System.out.println(contactService.printContacts());
                   break;

               case 7:
                   System.out.println(contactService.findByPhonePrefix("123"));
                   break;
               default:
                   System.err.println("Invalid command, Command should be in range 1,2,3,4");
                   break;
           }
       } while (command != 6);

    }
}