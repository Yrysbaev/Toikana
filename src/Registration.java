import java.util.Scanner;

public class Registration extends Users{
    static String username;
    static String userpassword;
    public Registration(){

    }
    public static void input(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Please Log in(If you Client press c)\nUsername:");
        username = scan.nextLine();
        if(username.equals("c")){
            return;
        }
        System.out.println("Password:");
        userpassword = scan.nextLine();
        checking();
    }
    public static void checking(){
        for (int i = 0; i < Adminusernames.length; i++) {
            if(Adminusernames[i].equals(username) && Adminpasswords[i].equals(userpassword)){
                Admin admin = new Admin();
                admin.welcome();
            }else if(Managerusernames[i].equals(username) && Managerusernames[i].equals(userpassword)){
                return;
            }
        }
    }
}
