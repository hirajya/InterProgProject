import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ADM_ViewCourses {

    static ArrayList<String> courseFiles = new ArrayList<>();

    public static void viewCourses() {
        System.out.println();
        getCourseFiles();
        System.out.println("Viewing Courses...\n");
        System.out.printf(" %-13s | %-50s | %-40s | %-10s  %n", "COURSE CODE", "COURSE NAME", "TEACHER", "ENROLLED / TOTAL");
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------");
        for (String file : courseFiles) {
            String[] headData = courseHeadingData(file);
            String courseCode = headData[0];
            String courseName = headData[1];
            String courseTeacher = headData[2];
            String studentOverTotal = headData[3] + "/" + headData[4];
            System.out.printf(" %-13s | %-50s | %-40s | %-10s  %n", courseCode, courseName, courseTeacher, studentOverTotal);
        }

    }

    public static void getCourseFiles() {

        courseFiles.removeAll(courseFiles);
        // Get the current working directory
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
                courseFiles.add(file.getName());
            }
        }
    }

    public static String[] courseHeadingData(String filename) {
        File file = new File(filename);
        String[] values = new String[5];

        try {
            Scanner sc = new Scanner(file);
            String firstLine = sc.nextLine();

            values = firstLine.split(",");

        } catch (FileNotFoundException e) {
            System.out.println("FileNotFound Error: File can't found.");
        }
        return values;
    }

    public static void main(String[] args) {
        viewCourses();
    }



}
