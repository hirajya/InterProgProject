

import java.util.Scanner;

public class LoginOrRegister {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {


        System.out.println("Welcome to XYZ University!");

        showOptions();
    }

    private static void showOptions() {
        System.out.println("Do you want to login or register an account?");
        System.out.println("[1] Login");
        System.out.println("[2] Register");
        System.out.println("[3] Exit");

        String choice = sc.nextLine().trim();

        switch (choice) {
            case "1":
                Login.main(null);
                break;
            case "2":
                RegisterAccount.createAccount();
                break;
            case "3":
                System.out.println("Thank you, signing off...");
                System.exit(0);
            default:
                System.out.println("Invalid choice. Please enter a number from 1 to 3.");
                showOptions(); // Recursive function call to prompt the user again
        }
    }
}
