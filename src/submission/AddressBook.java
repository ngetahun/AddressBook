
package submission;

import java.util.TreeMap;
import java.lang.String;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.io.*;
import submission.Person;
import javax.swing.JFileChooser;

public class AddressBook {
    private TreeMap<String, String> addressBook;
    private final Path outputFileName = "addressBook.txt";

    public AddressBook(){
        addressBook = new TreeMap();
    }

    /**
     * @param p
     * 
     */
    public boolean addPerson(Person p){
        // addressBook
    }

    /**
     * Saves addressBook object to text file
     *
     * */

    public boolean saveToFile(){
        FileOutputStream fop = null;
        File m = new File(outputFileName);
        JFileChooser saveFile = new JFileChooser();
        int sf = 0;
        String filename;

        if (! m.exists()){
            // show dialog
            filename = JOptionPane.showInputDialog("Name this file");
            savefile.setSelectedFile(new File(filename));
            savefile.showSaveDialog(savefile);
            BufferedWriter writer;
            sf = savefile.showSaveDialog(null);
        }
        if (sf == JFileChooser.APPROVE_OPTION){
            try {
                fop = new FileOutputStream(new File(filename))
                ObjectOutputStream oos = new ObjectOutputStream(fop);
                oos.writeObject(addressBook);
                oos.close();
            }
            catch (IOException e){
                System.out.println("Saving failed!");
                return false;
            }
        }
        else {
            fop = new FileOutputStream(m);
            ObjectOutputStream oos = new ObjectOutputStream(fop);
            oos.writeObject(addressBook);
            oos.close();
        }
    }

    public boolean readFromFile(){
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Load which file?");
        int result = chooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
        }

        String fileName = file.getCanonicalPath();
        // BufferedReader myReader = new BufferedReader(new FileReader(fileName));

        try {
            FileInputStream fis = new FileInputStream(fileName);
            ObjectInputStream ois = new ObjectInputStream(fis);
            addressBook = (TreeMap<String, Person>) ois.readObject();
            ois.close();
        }
        catch (ClassNotFoundException e){
            System.out.println("Not a valid Address Book.");
        }
    }

    public boolean editEntry(Person p, long phonenumber, String email, int age, String website){

    }


}