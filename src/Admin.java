import java.util.Scanner;

public class Admin {
    public  static void adminwelcome(){
        System.out.println("Welcome Admin!\nWhat are we going to do today?");
        System.out.println("1.Users\n2.Restaurants\n3.Serving Table\n4.Menu\n5.Event\n6.Report\n\n0.Exit");
        Scanner scan = new Scanner(System.in);
        String choice = scan.nextLine();
        if(choice.equals("1")) {
            Users.WelcomeforAdmin();
        }else if(choice.equals("2")){
            Restaurants.AdminWelcome();
        }else if(choice.equals("3")){
            Servingtable.AdminWelcome();
        }else if(choice.equals("4")){
            Menu.AdminWelcome();
        }else if(choice.equals("5")){
            Event.AdminWelcome();
        }else if(choice.equals("6")){
            Report.AdminWelcome();
        }else if(choice.equals("0")){
            Registration.input();
        }
    }
    public static void managerwelcome(){
        System.out.println("Welcome Manager!\nWhat are we going to do today?");
        System.out.println("1.Menu\n2.Event\n3.Serving Table");
        Scanner scan = new Scanner(System.in);
        String choice = scan.nextLine();
        if(choice.equals("1")) {
            Menu.ManagerWelcome();
        }else if(choice.equals("2")){
            Event.ManagerWelcome();
        }else if(choice.equals("3")){
            Servingtable.ManagerWelcome();
        }
    }
    public static void waiterwelcom(){
        System.out.println("You don't have acces to the system");
    }

}
