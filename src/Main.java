import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String[] contacts = new String[3];
        int nextEmpty = 0;
        int command = 4;
       do {
           System.out.println("1.Create Contact\n2.Search Contact\n3.Delete Contact\n4.List All Contacts\n5.Exit");
           command = scanner.nextInt();
           switch (command) {
               case 1:
                   System.out.println("Create Contact");
                   System.out.println("Name:");
                   String name = scanner.next();
                   System.out.println("Surname");
                   String surname = scanner.next();
                   System.out.println("Phone");
                   String phone = scanner.next();
                   if (nextEmpty != contacts.length) {
                       contacts[nextEmpty] = name + " " + surname + " " + phone;
                       nextEmpty++;
                   } else {
                       System.out.println("Memory Full");
                   }
                   break;
               case 2:
                   System.out.println("Search Contact by phone");
                   System.out.println("Enter phone to search: ");
                   String searchPhone = scanner.next();

                   boolean contactFound = false;

                   for (String contact: contacts) {
                       if (contact != null) {
                           String[] contactSplitted = contact.split(" ");
                           if (contactSplitted[2].startsWith(searchPhone)) {
                               System.out.println("Contact Found: "+contact);
                               contactFound = true;
                           }
                       }
                   }

                   if (!contactFound) {
                       System.out.println("No contact with such phone number!");
                   }

                   break;
               case 3:
                   System.out.println("Delete Contact by Phone");
                   System.out.println("Enter phone to delete: ");
                   String deletePhone = scanner.next();

                   boolean contactFoundToDelete = false;

                   for (int i = 0; i < contacts.length; i++) {
                       String contact = contacts[i];
                       if (contact != null) {
                           String[] contactSplitted = contact.split(" ");
                           if (contactSplitted[2].equals(deletePhone)) {
                               System.out.println("Contact Found: "+contact);
                               contacts[i] = null;
                               contactFoundToDelete = true;
                               break;
                           }
                       }
                   }

                   if (!contactFoundToDelete) {
                       System.out.println("No contact with such phone number!");
                   }

                   break;
               case 4:
                   for (int i = 0; i < contacts.length; i++) {
                       System.out.println(contacts[i]);
                   }
                   break;
               default:
                   System.err.println("Invalid command, Command should be in range 1,2,3,4");
                   break;
           }
       } while (command != 5);

    }
}