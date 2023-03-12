

import java.util.Scanner;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;

public class RegisterAccount {

    public static void main(String[] args) {
        createAccount();

    }

    public static void createAccount(){

        Scanner input = new Scanner(System.in);

        String username = "";
        String password = "";
        String type = "";

        boolean isValidUsername = false;
        while (!isValidUsername) {

            System.out.print("Enter username: ");
            username = input.nextLine().trim();

            if (username.length() == 0) {
                System.out.println("Username cannot be empty. Please choose another.");
            } else if (isUsernameTaken(username)) {
                System.out.println("Username already taken. Please choose another.");
            } else {
                isValidUsername = true;
            }
        }
        boolean isValidPassword = false;
        while (!isValidPassword) {

            System.out.print("Enter password: ");
            password = input.nextLine().trim();

            if (password.length() < 4) {
                System.out.println("Password too short. Must be at least 4 characters long.");
            } else {
                isValidPassword = true;
            }
        }

        boolean isValidType = false;
        while (!isValidType) {

            System.out.print("Enter type (student or admin): ");
            type = input.nextLine().trim();

            if (type.equalsIgnoreCase("student") || type.equalsIgnoreCase("admin")) {
                isValidType = true;
            } else {
                System.out.println("Invalid type. Must be student or admin only.");
            }
        }

        saveAccountDetails(username, password, type);
    }

    public static boolean isUsernameTaken(String username) {

        try {
            File file = new File("account_details.txt");
            if (file.createNewFile()) {
                return false;
            }
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(",");
                if (details[0].equals(username)) {
                    reader.close();
                    return true;
                }
            }
            reader.close();
            return false;
        } catch (IOException e) {
            System.out.println("Error checking username: " + e.getMessage());
            return false;
        }
    }

    public static void saveAccountDetails(String username, String password, String type) {

        try {
            File file = new File("account_details.txt");
            FileWriter filewriter = new FileWriter(file, true);
            BufferedWriter bufferedwriter = new BufferedWriter(filewriter);
            PrintWriter printwriter = new PrintWriter(bufferedwriter);
            printwriter.println(username+","+password+","+type);
            printwriter.flush();
            printwriter.close();

            System.out.println("Record saved." + "\n");
            returnToLoginOrRegister();

        }
        catch (Exception E) {
            System.out.println("Record not saved.");
        }
    }

    public static void returnToLoginOrRegister() {
        LoginOrRegister.main(new String[0]);
    }
}
