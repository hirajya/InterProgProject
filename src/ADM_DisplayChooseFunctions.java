import java.util.InputMismatchException;
import java.util.Scanner;

// Admin Display Functions, will display and choose all the function that Admin interface can offer
public class ADM_DisplayChooseFunctions {

    static Scanner sc = new Scanner(System.in);
    public static int adminDisplayFunctions() {
        System.out.println("[1] Add Courses");
        System.out.println("[2] Delete Courses");
        System.out.println("[3] View All Courses");
        System.out.println("[4] View All List of Enrollees on a Specific Course ");
        System.out.println("[5] Exit");

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
