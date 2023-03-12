import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.util.Scanner;

// Main interface for ADMIN account
public class ADM_Main {

    static Scanner sc = new Scanner(System.in);

    public static void ADM_MainInterface(String AccountName) {
        System.out.println("Welcome Admin " + AccountName + "...");

        ADM_MainInterfaceBody();
    }

    public static void ADM_MainInterfaceBody() {
        System.out.println();

        int inputIntFunc = ADM_DisplayChooseFunctions.adminDisplayFunctions();

        if (inputIntFunc == 1) {
            ADM_AddCourse.addCourse();
            System.out.println();
            ADM_Main.ADM_MainInterfaceBody();
        } else if (inputIntFunc == 2) {
            ADM_DeleteCourses.deleteCourse();
            System.out.println();
            ADM_Main.ADM_MainInterfaceBody();
        } else if (inputIntFunc == 3) {
            ADM_ViewCourses.viewCourses();
            System.out.println();
            ADM_Main.ADM_MainInterfaceBody();
        } else if (inputIntFunc == 4) {
            ADM_CourseListStud.viewListCourseStud();
            System.out.println();
            ADM_Main.ADM_MainInterfaceBody();
        } else if (inputIntFunc == 5){
            System.out.println();
            LoginOrRegister.main(new String[0]);

        }

    }
}
