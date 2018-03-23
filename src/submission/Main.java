
package submission;

import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Natnael Getahun
 * */

public class Main{
    public static void main(String[]args){

        final String usage="Commands:\n"+
            "help - Display this message\n"+
            "add - Add new person to the address book"+
            "search - Search the address book\n"+
            "edit - Edit the address directory\n"+
            "delete - Delete the address directory\n"+
            "import - Load address book from a file"+
            "export - save address book to a file"+
            "exit - Exit the main program\n";

        final String prompt="Address Book>";

        System.out.printf("%s",prompt);
        Scanner in = new Scanner(System.in);
        AddressBook myAddressBook = new AddressBook();

        // main application loop
        while(true){
            System.out.printf("%s", prompt);

            String input = in.nextLine();

            switch (input.toLowerCase()){
                case "help":
                    System.out.print(usage);
                    break;
                case "search":{
                    myAddressBook.search();
                    break;
                }
                case "edit":{
                    myAddressBook.editEntry();
                    break;
                }
                case "delete":{
                    myAddressBook.deleteEntry();
                    break;
                }
                case "import": {
                    try {
                        myAddressBook.readFromFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                }
                case "export": {
                    try {
                        myAddressBook.saveToFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                }
                case "exit": {
                    System.out.print("Exiting gracefully.");
                    return;
                }
                default:
                    System.out.print(usage);
                    break;
            }
        }
    }
}