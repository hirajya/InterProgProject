
import java.io.*;
import java.util.Objects;
import java.util.Scanner;

public class STD_ViewEnrollCourse {

    static Scanner sc = new Scanner(System.in);

    public static void main() {
        ADM_ViewCourses.viewCourses();
        Enroll();
    }


    public static void Enroll() {
        GetCredentials();
    }


    public static String getClassCode() {
        System.out.println("Please select your preferred Course code: ");
        String fileName = sc.next().trim();
        String ccc = CheckClass(fileName);
        return ccc;

    }

    private static String CheckClass(String classCode) {

        File file = new File(classCode + ".txt");

        while (true) {
            if (file.exists()) {
                return classCode;

            } else {
                System.out.println("The Course code you entered " + classCode + " does not exist");
                Enroll();
            }
            break;
        }
        return "";
    }

    public static void GetCredentials() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your full name: ");
        String Stud_name = sc.nextLine();

        String Stud_num = CheckStudNumber();

        String[] Stud_info = {Stud_name, Stud_num};
        AddStudent(Stud_info);

    }
    private static String getStudNumber(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your student number: ");
        String snums = sc.nextLine().trim();
        return snums;

    }

    public static String CheckStudNumber() {
        int number = 0;
        Scanner scanner = new Scanner(System.in);
        boolean validInput = false;

        while (!validInput) {
            String input = getStudNumber();
            try {
                if (input.length() < 6) {
                    System.out.println("Student Number too short. Must be at least 6 characters long.");
                } else {
                    number = Integer.parseInt(input);
                    validInput = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
        String s = String.valueOf(number);

        return s;
    }

    public static void AddStudent(String[] Stud_info) {

        String StudName = Stud_info[0];
        String StudNum = Stud_info[1];
        String CourseCode = getClassCode();
        String OldStudNum = getNumberOfStud(CourseCode);
        boolean isFull = CheckClassNum(CourseCode, OldStudNum);


        boolean haveDup = haveDuplicateStudId(StudNum, CourseCode); // true or false
        if (haveDup) { // true
            System.out.println("Student Number is already enrolled.");
            STD_Main.STD_MainInterfaceBody();
        }
        if(isFull){
            System.out.println(CourseCode + " is Full.");
            STD_Main.STD_MainInterfaceBody();
        } else {
            String filename = CourseCode + ".txt";
            String filenameCourseData = StudName + "," + StudNum;

            // Save the student's enrollment information in "CourseCode.txt"
            try (FileWriter courseFileWriter = new FileWriter(CourseCode + ".txt", true);
                 BufferedWriter courseBufferedWriter = new BufferedWriter(courseFileWriter)) {


                courseBufferedWriter.newLine();
                courseBufferedWriter.write(filenameCourseData);
                System.out.println("You are now successfully enrolled in " + CourseCode);
                String StudCtr = getNumberOfStud(CourseCode);
                UpdateHeadData(CourseCode, StudCtr);

                saveStudentEnrollmentInfo(StudNum, CourseCode);

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }


    public static void saveStudentEnrollmentInfo(String StudNum, String CourseCode) {

        // Save the student's enrollment information in a separate file
        String enrolledCoursesFileName = "Student_" + StudNum + ".txt";
        File enrolledCoursesFile = new File(enrolledCoursesFileName);

        try (FileWriter fileWriter2 = new FileWriter(enrolledCoursesFile, true);
             BufferedWriter bufferedWriter2 = new BufferedWriter(fileWriter2)) {
            if (enrolledCoursesFile.exists()) {
                // If the file exists, write the course code to a new line in the file
                bufferedWriter2.write(CourseCode);
                bufferedWriter2.newLine();
            } else {
                // If the file does not exist, create a new file and write the course code to it
                bufferedWriter2.write(CourseCode);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void UpdateHeadData(String CCode, String StudCount){
        try {
            // Open the file for reading and writing
            RandomAccessFile file = new RandomAccessFile(CCode + ".txt", "rw");
            BufferedReader reader = new BufferedReader(new FileReader(file.getFD()));

            // Read the first line

            String firstLine = reader.readLine();
            String[] HeadData = firstLine.split(",");
            for(String data: HeadData);


            // Rewrite the first line with the new content
            int studCntInt = Integer.parseInt(StudCount) + 1;
            String studCntStr = String.valueOf(studCntInt);
            HeadData[3] = studCntStr;
            String newFirstLine = HeadData[0] + "," + HeadData[1] + "," + HeadData[2] +"," + HeadData[3]+ "," + HeadData[4];
            file.seek(0);
            file.write(newFirstLine.getBytes());

            // Close the file
            reader.close();
            file.close();
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }

    }

    public static boolean haveDuplicateStudId(String studId, String studCourse) {
        boolean haveDuplicate = false;
        String fileName = studCourse + ".txt";
        try {
            String strCurrentLine;

            BufferedReader objReader = new BufferedReader(new FileReader(fileName));
            objReader.readLine();
            while (((strCurrentLine = objReader.readLine()) != null)) {
                String[] stringArray = strCurrentLine.split(",");
                if (Objects.equals(stringArray[1], studId)) {
                    haveDuplicate = true;
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Course class code doesn't exist");
        }
        return haveDuplicate;
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

    public static boolean CheckClassNum(String ClassCode, String CurrStudNum) {
        boolean isFull = false;

        try {
            // Open the file for reading and writing
            RandomAccessFile file = new RandomAccessFile(ClassCode + ".txt", "rw");
            BufferedReader reader = new BufferedReader(new FileReader(file.getFD()));

            // Read the first line

            String firstLine = reader.readLine();
            String[] DataC = firstLine.split(",");
            int total = Integer.parseInt(DataC[4]);
            int CurrNum = Integer.parseInt(CurrStudNum);

            if (CurrNum >= total){
                isFull = true;
            }


        } catch (IOException e) {
            System.out.println("Error");

        }
        return isFull;
    }
}


