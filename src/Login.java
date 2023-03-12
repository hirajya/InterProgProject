

import java.util.Scanner;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;

public class Login {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (isValidCredentials(username, password)) {
            System.out.println("Login successful!" + "\n");

            // Redirect to appropriate interface based on user role
            String[] userDetails = getUserDetails(username);
            String type = userDetails[2];

            if (type.equals("admin")) {
                ADM_Main.ADM_MainInterface(username); //pa-call na lang ng method
            } else if (type.equals("student")) {
                STD_Main.STD_MainInterface(username); // pacall ng method std saket
            } else {
                System.out.println("Invalid type.");
            }


        } else {
            System.out.println("Account doesn't exist.");
            System.out.println("Please Register Account." + "\n");
            LoginOrRegister.main(new String[0]);
        }
    }

    private static String[] getUserDetails(String username) {
        try {
            File file = new File("account_details.txt");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(",");
                if (details[0].equals(username)) {
                    reader.close();
                    return details;
                }
            }
            reader.close();
            // Return null if user details are not found
            return null;
        } catch (Exception e) {
            System.out.println("Error reading user details: " + e.getMessage());
            return null;
        }
    }


    public static boolean isValidCredentials(String username, String password) {

        try {
            File file = new File("account_details.txt");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(",");
                if (details[0].equals(username) && details[1].equals(password)) {
                    reader.close();
                    return true;
                }
            }
            reader.close();
            // Return false if username and password combination is not found
            return false;
        } catch (Exception e) {
            System.out.println("Account doesn't exist.");
            System.out.println("Please Register Account." + "\n");
            LoginOrRegister.main(new String[0]);
            return false;

        }
    }
}
