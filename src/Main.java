import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        String cnt = getNumberOfStud("ITC222");
        System.out.println(cnt);
    }

    public static String getNumberOfStud(String CourseCodes) {
        String studCnt = "";
        try {
            // Open the file for reading
            BufferedReader reader = new BufferedReader(new FileReader( CourseCodes +".txt"));

            String firstLine = reader.readLine();
            String[] HData = firstLine.split(",");
            studCnt = HData[3];

        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

        return studCnt;
    }

    public static void doesExist(String inputFName) {
        File f = new File(inputFName);

        System.out.println(f.exists());
    }

    public static void saveStudentEnrollmentInfo(String StudNum, String CourseCode) {

        // Save the student's enrollment information in a separate file
        String enrolledCoursesFileName = "Student_" + StudNum + ".txt";
        File enrolledCoursesFile = new File(enrolledCoursesFileName);

        try (FileWriter fileWriter2 = new FileWriter(enrolledCoursesFile, true);
             BufferedWriter bufferedWriter2 = new BufferedWriter(fileWriter2)) {
            if (enrolledCoursesFile.exists()) {
                // If the file exists, write the course code to a new line in the file
                bufferedWriter2.newLine();
                bufferedWriter2.write(CourseCode);
            } else {
                // If the file does not exist, create a new file and write the course code to it
                bufferedWriter2.write(CourseCode);
            }
//            // write the course code to a new line in the file
//            bufferedWriter2.write(CourseCode);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void getHead() {
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

        for (String element : arrayCourseList) {
            System.out.println(element);
        }
    }
}
