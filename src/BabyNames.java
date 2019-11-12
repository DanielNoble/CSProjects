import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class BabyNames {

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("baby names.txt");
        Scanner console = new Scanner(file);
        console.nextLine(); // skips past the blank line and ranking numbers in the file
        console.nextLine();
        ArrayList<String> girlNames = new ArrayList<String>();
        ArrayList<String> boyNames = new ArrayList<String>();
        ArrayList<Integer> girlPoints = new ArrayList<>();
        ArrayList<Integer> boyPoints = new ArrayList<>();
        String currentName;

        while (console.hasNextLine()) { // iterates through the entire file
            console.next(); // skips the year
            for (int i = 1; i <= 5; i++) { // iterates through 5 girls
                currentName = console.next();
                if (!girlNames.contains(currentName)) {
                    girlNames.add(currentName); // creates arrayList of every unique girl name
                    girlPoints.add(6 - i); // point system: 1st is 5, 2nd is 4 ... 5th is 1
                } else {
                    for (int j = 0; j < girlNames.size(); j++) { // if current girlName not unique, find it's location and add to its point value in the point array
                        if (currentName.equals(girlNames.get(j))) {
                            girlPoints.set(j, girlPoints.get(j) + 6 - i);
                        }
                    }
                }
            }

            for (int i = 1; i <= 5; i++) { // same process as girls with boys
                currentName = console.next();
                if (!boyNames.contains(currentName)) {
                    boyNames.add(currentName);
                    boyPoints.add(6 - i);
                } else {
                    for (int j = 0; j < boyNames.size(); j++) {
                        if (currentName.equals(boyNames.get(j))) {
                            boyPoints.set(j, boyPoints.get(j) + 6 - i);
                        }
                    }
                }
            }
        }
        ArrayList<Person> girls = new ArrayList<>();
        for (int i = 0; i < girlNames.size(); i++) { // creates new arrayList of person objects, matching name and points for easy sorting and later access
            girls.add(new Person(girlNames.get(i),girlPoints.get(i)));
        }
        girls.sort(Person::compareTo); // sorts person objects by their scores

        ArrayList<Person> boys = new ArrayList<>();
        for (int i = 0; i < boyNames.size(); i++) { // same process as girls with boys
            boys.add(new Person(boyNames.get(i),boyPoints.get(i)));
        }
        boys.sort(Person::compareTo);

        PrintStream girlRanks = new PrintStream(new File("Girl Ranks.txt"));

        girlRanks.println("The top girl names are: ");
        for (int i = 0; i < girls.size(); i++) { // prints out the top five girl names
            girlRanks.println(i+1 + ": " + girls.get(i).getName() + " " + girls.get(i).getScore());
        }

        PrintStream boyRanks = new PrintStream(new File("Boy Ranks.txt"));
        boyRanks.println("The top boy names are: ");
        for (int i = 0; i < boys.size(); i++) { // prints out the top five boy names
            boyRanks.println(i+1 + ": " + boys.get(i).getName() + " " + boys.get(i).getScore());
        }
    }

    private static class Person implements Comparable{ // custom private class to create person objects for easy sorting
        private final String name;
        public final int score;
        private Person(String name, int score) {
            this.name = name;
            this.score = score;
        }

        @Override
        public int compareTo(Object o) { // compares two persons by score
            Person person = (Person) o;
            return Integer.compare(person.score, score);
        }

        public String getName() {
            return this.name;
        }

        public int getScore() {
            return this.score;
        }
    }
}
