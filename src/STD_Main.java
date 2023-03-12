import java.util.Scanner;

public class STD_Main {

    static Scanner sc = new Scanner(System.in);

    public static void STD_MainInterface(String username) {

        System.out.println("Welcome " + username + "...");
        STD_MainInterfaceBody();
    }

    public static void STD_MainInterfaceBody() {
        System.out.println();

        int inputIntFunc = STD_DisplayChooseFunctions.studentDisplayFunctions();

        if (inputIntFunc == 1) {
            STD_ViewEnrollCourse.main();
            STD_MainInterfaceBody();
        } else if (inputIntFunc == 2) {
            STD_ViewEnrolledCourses.ViewEnrolledCourses();
            STD_MainInterfaceBody();
        }else if (inputIntFunc == 3) {
            STD_UnenrollCourse.UnenrollCourse();
            STD_MainInterfaceBody();
        } else if (inputIntFunc == 4) {
            LoginOrRegister.main(null);
        }
    }
}