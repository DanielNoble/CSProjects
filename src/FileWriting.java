import java.io.*;
import java.util.Scanner;

public class FileWriting {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner console = new Scanner(System.in);
        System.out.println("How many students are there? ");
        int count = console.nextInt();
        String[] students = new String[count];
        String[] IDs = new String[count];
        int[] grades = new int[count];
        double[] GPAs = new double[count];
        for (int i = 0; i < count; i++) {
            System.out.print("Name: ");
            students[i] = console.next();

            System.out.print("ID: ");
            IDs[i] = console.next();

            System.out.print("Grade: ");
            grades[i] = console.nextInt();

            System.out.print("GPA: ");
            GPAs[i] = console.nextDouble();
            System.out.println("\n");
        }

        PrintStream name = new PrintStream(new File("table.txt"));
        name.println("Student\tID\tGrade\tGPA");

        print(name, students, IDs, grades, GPAs);
    }

    public static void print(PrintStream out, String[] students, String[] IDs, int[] grades, double[] GPAs) {
        for (int i = 0; i < GPAs.length; i++) {
            out.println(students[i] + "\t" + IDs[i] + "\t" + grades[i] + "\t" + GPAs[i]);
        }
    }

}
