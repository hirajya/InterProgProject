import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class STD_UnenrollCourse {

    public static void main(String[] args) {
        UnenrollCourse();
    }



    public static void UnenrollCourse() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter student number: ");
        String StudNum = scanner.nextLine().trim();

        System.out.println("Viewing enrolled courses for Student_" + StudNum + "...");

        String enrolledCoursesFileName = "Student_" + StudNum + ".txt";
        File enrolledCoursesFile = new File(enrolledCoursesFileName);

        if (enrolledCoursesFile.exists()) {
            try (Scanner fileScanner = new Scanner(enrolledCoursesFile)) {
                List<String> courses = new ArrayList<>();
                while (fileScanner.hasNextLine()) {
                    String courseCode = fileScanner.nextLine();
                    courses.add(courseCode);
                }

                if (courses.isEmpty()) {
                    System.out.println("No enrolled courses found for Student_" + StudNum);
                    STD_Main.STD_MainInterfaceBody();
                }

                System.out.println("Enrolled Courses:");
                for (int i = 0; i < courses.size(); i++) {
                    System.out.println((i + 1) + ". " + courses.get(i));
                }

                System.out.print("Enter the number of the course to unenroll: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // consume the newline character

                if (choice < 1 || choice > courses.size()) {
                    System.out.println("Invalid choice. Please try again.");
                    return;
                }

                String courseToUnenroll = courses.get(choice - 1);
                courses.remove(choice - 1);

                try (PrintWriter writer = new PrintWriter(enrolledCoursesFileName)) {
                    for (String course : courses) {
                        writer.println(course);
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                System.out.println("Successfully unenrolled from " + courseToUnenroll);

                updateHeadMinus(courseToUnenroll, StudNum);

                // remove the student from the corresponding course code file
                String CourseCode = courseToUnenroll + ".txt";
                File courseCodeFile = new File(CourseCode);

//                if (courseCodeFile.exists()) {
//                    try (Scanner fileScanner1 = new Scanner(courseCodeFile)) {
//                        List<String> students = new ArrayList<>();
//                        while (fileScanner1.hasNextLine()) {
//                            String studentData = fileScanner1.nextLine();
//                            if (!studentData.contains(StudNum)) {
//                                students.add(studentData);
//                            }
//                        }
//
//                        try (PrintWriter writer = new PrintWriter(CourseCode)) {
//                            for (String student : students) {
//                                writer.println(student);
//                            }
//                        } catch (FileNotFoundException e) {
//                            e.printStackTrace();
//                        }
//                    } catch (FileNotFoundException e) {
//                        e.printStackTrace();
//                    }
//                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Input Error: No enrolled courses found for Student_" + StudNum);
        } STD_Main.STD_MainInterfaceBody();
    }

    public static void updateHeadMinus(String course, String studId) {
        String fileName = course + ".txt";

        String[] listStud = studList(fileName, studId);

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String firstLine = reader.readLine();
            String[] headData = firstLine.split(",");

            // Replacing an element in the array
            int currentClassCntInt = Integer.parseInt(headData[3]) - 1;
            String currentClassCntStr = String.valueOf(currentClassCntInt);
            headData[3] = currentClassCntStr;

            // Writing the modified array back to the file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
                writer.write(String.join(",", headData));
                for (String registered : listStud) {
                    writer.newLine();
                    writer.write(registered);
                }

            } catch (IOException e) {
                System.out.println("An error occurred while writing to the file.");
                e.printStackTrace();
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
            e.printStackTrace();
        }
    }

    private static String[] studList(String courseFile, String excepted) {
        ArrayList<String> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(courseFile))) {

            // Read the first line and discard it
            reader.readLine();

            // Read the remaining lines and add them to the ArrayList
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                if (!(currentLine.contains(excepted))) {
                    list.add(currentLine); // [,....]
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] studlistArr = list.toArray(new String[list.size()]);

        return studlistArr;
    }
}
