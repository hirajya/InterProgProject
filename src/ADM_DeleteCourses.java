import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class ADM_DeleteCourses {
    static Scanner sc = new Scanner(System.in);

    public static void deleteCourse() {
        System.out.println();
        System.out.println("Delete Course.");
        System.out.println("Enter Course Class Code: ");
        String CCC = sc.nextLine().trim(); //CCV222
        String filename = CCC + ".txt"; //CCV222.txt
        Path xpath = Paths.get(filename); // path CCV222.txt

        try {
            Files.delete(xpath);
            System.out.println("Class Course Code: " + CCC + "\nSuccessfully deleted");
            String[] allStudentFile = getAllStud();
            for (String studFile : allStudentFile) { // [Student_102323.txt,.....]
                removeCourStud(studFile, CCC);
            }
        } catch (IOException e) {
            System.out.println("Class can't found: Class Course Code doesn't exist. ");
        }
    }

    public static String[] getAllStud() {
        ArrayList<String> allStuds = new ArrayList<>();
        File directory = new File(".");

        // Get all the files in the directory
        File[] files = directory.listFiles();

        // Loop through all the files
        for (File file : files) {
            // Check if the file is a text file

            if (file.getName().startsWith("Student_")) {
                // Print the name of the text file
                allStuds.add(file.getName());
            }
        }
        String[] allStudArray = allStuds.toArray(new String[allStuds.size()]);
        return allStudArray;
    }

    public static void removeCourStud(String studFile, String course) {
        String enrolledCoursesFileName = studFile;
        File enrolledCoursesFile = new File(enrolledCoursesFileName);

        try (Scanner fileScanner = new Scanner(enrolledCoursesFile)) {
            List<String> courses = new ArrayList<>();
            while (fileScanner.hasNextLine()) {
                String courseCode = fileScanner.nextLine();
                if (!(Objects.equals(course, courseCode))) {
                    courses.add(courseCode);
                }
            }
            try (PrintWriter writer = new PrintWriter(enrolledCoursesFileName)) {
                for (String courseList : courses) {
                    writer.println(courseList);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not Found");
        }
    }

    public static void main(String[] args) {
        deleteCourse();
//        getAllStud();
    }
}
