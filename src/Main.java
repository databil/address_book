import java.util.Scanner;

public class Main {
    static final String DELIMITER = ";";
    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);
        String[] contacts = new String[3];
        int nextEmpty = 0;
        int command;

       do {
           System.out.println("1.Create Contact\n2.Search Contact\n3.Update\n4.Delete Contact\n5.List All Contacts\n6.Exit");
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
                       contacts[nextEmpty] = name + DELIMITER + surname + DELIMITER + phone;
                       nextEmpty++;
                   } else {
                       System.out.println("Memory Full");
                   }
                   break;

               case 2:
                   System.out.println("Search Contact by phone");
                   System.out.println("Enter phone to search: ");
                   String searchPhone = scanner.next();

                   int foundIndex = findContactIndexByPhone(contacts, searchPhone);
                   if (foundIndex != -1) {
                       System.out.println("Found contact: "+contacts[foundIndex]);
                   } else {
                       System.out.println("No contact with such phone number!");
                   }

                   break;
               case 3:
                   System.out.println("Update Contact");
                   System.out.println("Enter phone to search: ");
                   String updatePhone = scanner.next();

                   int updateIndex = findContactIndexByPhone(contacts, updatePhone);
                   System.out.println("Name:");
                   String newName = scanner.next();
                   System.out.println("Surname");
                   String newSurname = scanner.next();
                   System.out.println("Phone");
                   String newPhone = scanner.next();

                   contacts[updateIndex] = newName + DELIMITER + newSurname + DELIMITER + newPhone;
                   System.out.println("Contact Updated!");
                   break;

               case 4:
                   System.out.println("Delete Contact by Phone");
                   System.out.println("Enter phone to delete: ");
                   String deletePhone = scanner.next();

                   int deleteIndex = findContactIndexByPhone(contacts, deletePhone);

                   if (deleteIndex != -1) {
                       System.out.println("Contact Found: "+contacts[deleteIndex]);
                       for (int j = deleteIndex; j < contacts.length - 1; j++) {
                           contacts[j] = contacts[j+1];
                       }
                       contacts[contacts.length - 1] = null;
                       nextEmpty--;
                   } else {
                       System.out.println("No such contact!");
                   }

                   break;
               case 5:
                   for (String contact : contacts) {
                       System.out.println(contact);
                   }
                   break;
               default:
                   System.err.println("Invalid command, Command should be in range 1,2,3,4");
                   break;
           }
       } while (command != 6);

    }

    public static int findContactIndexByPhone(String[] contacts, String searchString) {
        int foundIndex = -1;

        for (int i = 0; i < contacts.length; i++) {
            String contact = contacts[i];
            if (contact != null) {
                String[] contactSplitted = contact.split(DELIMITER);
                if (contactSplitted[2].equals(searchString)) {
                    foundIndex = i;
                    break;
                }
            }
        }

        return foundIndex;
    }
}