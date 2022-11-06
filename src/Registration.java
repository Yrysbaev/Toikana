import java.util.Scanner;

public class Registration extends Users {
    static String username;
    static String userpassword;

    public Registration() {

    }

    public static void input() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please Log in(If you Client press c)\nUsername:");
        username = scan.nextLine();
        if (username.equals("c")) {
            Admin.waiterwelcom();
            input();
        }
        System.out.println("Password:");
        userpassword = scan.nextLine();
        checking();
    }

    public static void checking() {
        for (int i = 0; i < Adminusernames.size(); i++) {
            if (Adminusernames.get(i).equals(username) && Adminpasswords.get(i).equals(userpassword)) {
                Admin.welcome();
                return;
            }
        }
        for (int i = 0; i < Managerusernames.size(); i++) {
            if (Managerusernames.get(i).equals(username) && Managerpasswords.get(i).equals(userpassword)) {
                Admin.managerwelcome();
                return;
            }
        }
        for (int i = 0; i < Waiterusernames.size(); i++) {
            if (Waiterusernames.get(i).equals(username) && Waiterpasswords.get(i).equals(userpassword)) {
                Admin.waiterwelcom();
                return;
            }
        }
        System.out.println("Incorrect Please try again");
        input();
    }
}
