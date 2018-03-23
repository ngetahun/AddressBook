
package submission;

import javax.swing.*;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.TreeMap;


/**
 * AddressBook class
 * @author Natnael Getahun
 * Matriculation number: 30000384
 * */
public class AddressBook {
    private TreeMap<Long, Person> addressBook;
    private final Path outputFileName = Paths.get("addressBook.txt");

    public AddressBook(){
        addressBook = new TreeMap();
    }

    /**
     * Add a person object to the address book
     * @param p
     * @return boolean value indicating success
     */
    public boolean addPerson(Person p){
        System.out.print("Preparing to add a person.");

        // TODO: make sure to hash the entries
        Scanner input = new Scanner(System.in);

        // create a person
        System.out.print("Person's name: ");
        String personName = input.nextLine();

        System.out.print("Address: ");
        String address = input.nextLine();

        System.out.print("Email: ");
        String email = input.nextLine();

        System.out.print("Phone number: ");
        long phoneNo = input.nextLong();

        System.out.print("Age: ");
        int age = input.nextInt();

        System.out.print("website (optional): ");
        String website = input.nextLine();

        Person tempPerson;
        tempPerson = new Person(personName, address, email, phoneNo, website, age);
        long personHash = Person.hashString(personName);
        try{
            addressBook.put(personHash, tempPerson);
        } catch (NullPointerException e){
            e.printStackTrace();
            return false;
        }

        return true;
    }

    /**
     * Saves addressBook object to text file
     * @return boolean indicating success
     * */

    public boolean saveToFile() throws IOException {
        System.out.print("Saving file...");

        FileOutputStream fop = null;
        File m = new File(String.valueOf(outputFileName));
        JFileChooser saveFile = new JFileChooser();
        int sf = 0;
        String filename;

        if (! m.exists()) {
            // show dialog
            filename = JOptionPane.showInputDialog("Name this file");
            saveFile.setSelectedFile(new File(filename));
            saveFile.showSaveDialog(saveFile);
            sf = saveFile.showSaveDialog(null);

            if (sf == JFileChooser.APPROVE_OPTION) {
                try {
                    fop = new FileOutputStream(new File(filename));
                    ObjectOutputStream oos = new ObjectOutputStream(fop);
                    oos.writeObject(addressBook);
                    oos.close();
                    System.out.print("Completed saving file!");
                    return true;
                } catch (IOException e) {
                    System.out.println("Saving failed!");
                    return false;
                }
            }
        }
        else {
            try {
                fop = new FileOutputStream(m);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            ObjectOutputStream oos = new ObjectOutputStream(fop);
            oos.writeObject(addressBook);
            oos.close();
            System.out.print("Completed saving file!");
            return true;
        }
        System.out.print("Failed to save!");
        return false;
    }

    /**
     * Read from the file
     * @return boolean indicating success
     * */
    public boolean readFromFile() throws IOException {
        System.out.print("Preparing to import a file...");

        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Load which file?");
        int result = chooser.showOpenDialog(null);
        File file;
        String fileName = "";
        if (result == JFileChooser.APPROVE_OPTION) {
            file = chooser.getSelectedFile();
            try {
                fileName = file.getCanonicalPath();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                FileInputStream fis = new FileInputStream(fileName);
                ObjectInputStream ois = new ObjectInputStream(fis);
                addressBook = (TreeMap<Long, Person>) ois.readObject();
                System.out.print("Completed importing file!");
                ois.close();
            }
            catch (ClassNotFoundException e){
                System.out.println("Not a valid Address Book.");
            }
        }
        else{
            System.out.print("Please select a valid file!");
            return false;
        }

        return true;
    }

    /**
     * Print persons
     * @param people
     * */
    public void printEntries(Person[] people){
        int i = 1;
        for (Person p: people){
            System.out.print(i + ". " + p);
            i += 1;
        }
    }

    /**
     * Performs search on the address book
     * @param
     * */
    public void search(){
        String name;
        Scanner stdin = new Scanner(System.in);

        System.out.print("Enter a name to search:");
        name = stdin.nextLine();
        System.out.print("Searching directory...");
        long hash = Person.hashString(name);

        if(addressBook.containsKey(hash)){
            System.out.print("Found a person. Printing now...");
            printEntries(new Person[]{addressBook.get(hash)});
        }
        else{
            System.out.print("Couldn't find the person. You should add them to the address book.");
        }

    }

    /**
     * Edit an entry to the address book
     * @param
     * @return boolean indicating success
     * */
    public boolean editEntry(){
        Scanner in = new Scanner(System.in);

        System.out.print("Edit an entry...");
        System.out.print("Search for an entry");

        String needleName = in.nextLine();
        long hashKey = Person.hashString(needleName);
        if (addressBook.containsKey(hashKey)){
            System.out.print("Found people matching your criteria. Put 'exit' to exit updating.");
            Person haystackVal = addressBook.get(needleName);
            printEntries(new Person[]{haystackVal});

            // remove old value
            addressBook.remove(needleName);

            // get update inputs from the user
            Scanner _input = new Scanner(System.in);
            String userInput = _input.nextLine();
            String name = "", address = "", web = "", email = "";
            long phoneNumber = 0;
            int age = 0;
            while (!userInput.equalsIgnoreCase("exit")){
                // get name first
                System.out.print("Enter name (Hit Enter to leave unchanged):");
                name = _input.nextLine();

                // address
                System.out.print("Enter address (Hit Enter to leave unchanged):");
                address = _input.nextLine();

                // email address
                System.out.print("Enter email (Hit Enter to leave unchanged):");
                email = _input.nextLine();

                // age
                System.out.print("Enter Age (Hit Enter to leave unchanged):");
                age = _input.nextInt();

                // phone number
                System.out.print("Enter phone number (Hit Enter to leave unchanged):");
                phoneNumber = _input.nextLong();

                // web address
                System.out.print("Enter website (Hit Enter to leave unchanged):");
                web = _input.nextLine();
            }

            haystackVal.update(name, address, email, phoneNumber, web, age);
            addPerson(haystackVal);
            System.out.print("Entry updated");
        }
        else {
            System.out.print("Entry not found");
            return false;
        }

        return true;
    }

    /**
     * Delete an entry from an address book
     * @param
     * @return boolean indicating success
     * */
    public boolean deleteEntry(){
        Scanner input = new Scanner(System.in);
        String needleName = "", option = "";

        System.out.print("Please input the name of the person you want to remove" +
                "from the address book.");

        needleName = input.nextLine();
        long needleKey = Person.hashString(needleName);
        if (addressBook.containsKey(needleKey)){
            System.out.print("Found person: " + needleName);
            System.out.print("Initiating delete");
            System.out.printf("Are you sure you want to delete %s?", needleName);
            System.out.print("[yes/no/cancel]: ");
            option = input.nextLine();

            if (option.equalsIgnoreCase("yes")){
                    System.out.print("Deleting entry.");
                    addressBook.remove(needleKey);
                    System.out.print("Successfully removed entry. Exiting...");
                    return true;
            }
            else if (option.equalsIgnoreCase("no")) {
                System.out.print("Aborting operation.");
                return false;
            }
            else if(option.equalsIgnoreCase("cancel")){
                System.out.print("Aborting operation.");
                return false;
            }
            else {
                return false;
            }
        }


        return false;
    }


}