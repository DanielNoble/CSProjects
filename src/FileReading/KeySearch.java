package FileReading;

import java.io.*;
import java.util.Scanner;

public class KeySearch {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("Word.docx");
        System.out.println(file.canRead());
        Scanner in = new Scanner(System.in); // Begins scanner for keyword
        System.out.print("What keyword would you like to search for?");
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
    }
}
