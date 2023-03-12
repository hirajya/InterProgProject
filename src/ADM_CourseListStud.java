import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class ADM_CourseListStud {

    static Scanner sc = new Scanner(System.in);

    public static void viewListCourseStud() {
        System.out.println();
        System.out.println("View List of Students...");
        String inputClassCode = readClassCode();
        System.out.println("\nViewing List of Students under " + inputClassCode + "...");
        String fileName = inputClassCode + ".txt";
        BufferedReader objReader = null;
        String maxEnrollees = "";

        try {

            String strCurrentLine;
            objReader = new BufferedReader(new FileReader(fileName));

            String[] headData = getHead(inputClassCode);
            System.out.println("Course Code: " + headData[0]);
            System.out.println("Course Name: " + headData[1]);
            System.out.println("Course Teacher: " + headData[2] +"\n");
            maxEnrollees = headData[4];
            System.out.printf(" %-50s | %-30s | %n", "STUDENT NAME", "STUDENT NUMBER");
            System.out.println("======================================================================================");

            objReader.readLine();

            int studentCnt = 0;
            while ((strCurrentLine = objReader.readLine()) != null) {
                studentCnt++;
                String[] studData = strCurrentLine.split(",");
                System.out.printf(" %-50s | %-30s | %n", studData[0], studData[1]);
            }

            System.out.println("\n\sTotal of Enrollees: " + studentCnt + " / " + maxEnrollees);
        } catch (Exception e) {
            System.out.println("INPUT ERROR: Given class code doesn't exist.");
        }
    }

    private static String[] getHead(String inputClassCode) {
        ADM_ViewCourses.getCourseFiles();

        for (String courseFile : ADM_ViewCourses.courseFiles) {
            String[] headData = ADM_ViewCourses.courseHeadingData(courseFile);
            if (inputClassCode.equals(headData[0])) {
                return headData;
            }
        }
        return new String[5];
    }

    private static String readClassCode() {
        System.out.println("Enter Class Code: ");
        String classCode = sc.next();
        while (classCode.length() != 6) {
            System.out.println("Class Code must be 6 characters \n");
            System.out.println("Enter Class Code: ");
            classCode = sc.next();
        }
        return classCode;
    }

    public static void main(String[] args) {
        viewListCourseStud();
    }
}
