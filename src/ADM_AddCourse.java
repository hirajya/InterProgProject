import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

// Admin_AddCourse
public class ADM_AddCourse {

    static Scanner sc = new Scanner(System.in);

    public static void addCourse() {
        System.out.println();
        System.out.println("Add Course.");

        // Reading CourseName(CN), ClassCode(CCC), CourseTeacherName(CTN), CourseMaxStudentCount(CMSC) for adding new course
        System.out.println("Enter new Course Name: ");
        String inputCN = sc.nextLine();

        System.out.println("Enter Course Teacher Name: ");
        String inputCTN = sc.nextLine();

        String inputCCC = readCourseCode();
        if(ccExist(inputCCC)) {
            System.out.println("Input Error: Class code already exists.");
            ADM_Main.ADM_MainInterfaceBody();
        }

        String inputCMSC = String.valueOf(readMaxCount());
        sc.nextLine();

        String[] courseInfo= {inputCN, inputCTN, inputCCC, inputCMSC};
        createCourseTextFile(courseInfo);
        System.out.println("Adding Course...");
        System.out.println("Course Added.");

    }

    private static int readMaxCount() {
        int inputInt = 0;
        boolean done = false;

        while (!done) {
            try {
                while (true) {
                    System.out.println("Enter Course Max Student count: ");
                    inputInt = sc.nextInt();
                    if(inputInt > 0) {
                        done = true;
                        break;
                    }
                    System.out.println("\nInput Error: Maximum Number of Students can't be negative.");
                }
            } catch (InputMismatchException ime) {
                String temp = sc.nextLine();
                System.out.println("\nInput Error: Input must be an integer.");
            }
        }
        return inputInt;
    }

    private static String readCourseCode() {
        String inputCCC = "";

        while (true) {
            System.out.println("Enter Course Class Code: ");
            inputCCC = sc.next();
            if (inputCCC.length() == 6) {
                break;
            }
            System.out.println("\nClass Code must be 6 elements long.");
        }

        return inputCCC;
    }

    private static void createCourseTextFile(String[] info) {
        String CourseName = info[0];
        String CourseTeacher = info[1];
        String CourseCode = info[2];
        String CourseMaxStud = info[3];
        String filename = CourseCode + ".txt";
        String filenameCourseData = CourseCode + "," + CourseName + "," + CourseTeacher + ",0," + CourseMaxStud;

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
            writer.write(filenameCourseData);
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // get list courses for anti duplicate class code; in add course
    public static String[] getHead() {
        ArrayList<String> courseList = new ArrayList<>();

        File directory = new File(".");

        // Get all the files in the directory
        File[] files = directory.listFiles();

        // Loop through all the files
        for (File file : files) {
            // Check if the file is a text file
            if (file.getName().equals("account_details.txt")) {
                continue;
            }
            if (file.getName().startsWith("Student_")) {
                continue;
            }
            if (file.getName().endsWith(".txt")) {
                // Print the name of the text file
                courseList.add(file.getName());
            }
        }

        for (int i = 0; i < courseList.size(); i++) {
            String element = courseList.get(i);
            element = element.replace(".txt", "");
            courseList.set(i, element);
        }
        String[] arrayCourseList = courseList.toArray(new String[courseList.size()]);

        return arrayCourseList;
    }

    public static boolean ccExist(String targetCC) {
        String[] listCourse = getHead();

        for (String element : listCourse) {
            if (element.equals(targetCC)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        addCourse();
    }

}
