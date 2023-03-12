import java.util.InputMismatchException;
import java.util.Scanner;

// Student Display Functions, will display and choose all the function that Student interface can offer
public class STD_DisplayChooseFunctions {

    static Scanner sc = new Scanner(System.in);
    public static int studentDisplayFunctions() {
        System.out.println("[1] View and Enroll Courses");
        System.out.println("[2] View Enrolled Courses");
        System.out.println("[3] Unenroll Courses");
        System.out.println("[4] Exit");

        int inputInt = 0;
        boolean done = false;

        while (!done) {
            try {
                while (true) {
                    System.out.println("Enter Procedural Number: ");
                    inputInt = sc.nextInt();
                    if(inputInt > 0 && inputInt <= 5) {
                        done = true;
                        break;
                    }
                    System.out.println("\nInput Error: Inputted Number operation doesn't exist.");
                }
            } catch (InputMismatchException ime) {
                String temp = sc.nextLine();
                System.out.println("\nInput Error: Input must be an integer.");
            }
        }
        return inputInt;
    }


}
