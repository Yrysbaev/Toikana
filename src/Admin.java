import java.util.Scanner;

public class Admin {
    public void welcome(){
        System.out.println("Welcome Admin!\nWhat are we going to do today?");
        System.out.println("1.Users\n2.Menu\n3.Event");
        Scanner scan = new Scanner(System.in);
        String choice = scan.nextLine();
        if(choice.equals("1")) {
            Users user = new Users();
            Users.WelcomeforAdmin();
        }else if(choice.equals("2")){
            return;
        }
    }

}
