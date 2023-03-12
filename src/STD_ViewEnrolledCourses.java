import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class STD_ViewEnrolledCourses {

    public static void ViewEnrolledCourses() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter student number: ");
        String StudNum = scanner.nextLine();

        System.out.println("Viewing enrolled courses for Student_" + StudNum);

        String enrolledCoursesFileName = "Student_" + StudNum + ".txt";
        File enrolledCoursesFile = new File(enrolledCoursesFileName);

        if (enrolledCoursesFile.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(enrolledCoursesFile))) {
                System.out.println("Enrolled Courses:");
                String courseCode;
                while ((courseCode = br.readLine()) != null) {
                    System.out.println(courseCode);
                }
                STD_Main.STD_MainInterfaceBody();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No enrolled courses found for Student_" + StudNum);
        }
    }

}