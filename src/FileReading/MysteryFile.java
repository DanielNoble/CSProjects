package FileReading;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MysteryFile {
    public static void main(String[] args) {
        try {
            File file = new File("file.txt");
            System.out.println(file.canRead());
            Scanner in = new Scanner(System.in); // Begins scanner for keyword
            System.out.print("What keyword would you like to search for? ");
            String key = in.next(); // stores keyword input as string
            Scanner console = new Scanner(file); // scanner opens for file
            int count = 0;
            System.out.println(console.hasNext());
            while (console.hasNext()) { // iterates line by line
                if (console.nextLine().contains(key)) // if line contains key, increment count
                    count++;
            }
            System.out.println(key);
            System.out.println(count + " lines contain \"" + key + "\"");
        } catch (FileNotFoundException ex) {

        }
    }
}

