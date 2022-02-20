import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
    static final Scanner scanner = new Scanner(System.in);
    private static int getCorrectInteger(int max) {
        boolean correctNumberEntered = false;
        int input = -1;
        do{
            try{
                input = Integer.parseUnsignedInt(scanner.nextLine());
            } catch(NumberFormatException e){
                System.out.println("Invalid entry, please enter a number from 0 to " + max + " .");
            }
            if(input <= max && input >= 0){
                correctNumberEntered = true;
            } else {
                System.out.println("Number should be from  0 to " + max + "  .");
            }
        }while(!correctNumberEntered);
        return input;
    }

    static void printList(ArrayList<?> list){
        for(Object p : list) {
            System.out.println(p.toString());
        }
    }

    public static void main(String[] args) {
        ArrayList<Person> persons = new ArrayList<Person>();
        System.out.println("Enter number of persons:");
        int numberOfPersons = getCorrectInteger(10000);
        for (int i = 0; i < numberOfPersons; i++) {
            System.out.println("Enter person's lastname:");
            String lastname = scanner.nextLine();
            System.out.println("Enter person's name:");
            String name = scanner.nextLine();
            System.out.println("Enter person's age:");
            Byte age = (byte)getCorrectInteger(127);
            persons.add(new Person(lastname, name, age));
        }
        System.out.println("List before sorting:");
        printList(persons);
        persons.sort(Comparator.comparing(Person::getLastname).thenComparing(Person::getFirstname).thenComparing(Person::getNegativeAge));
        System.out.println("List after sorting:");
        printList(persons);
    }
}
