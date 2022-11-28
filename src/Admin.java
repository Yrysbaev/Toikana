import java.util.Scanner;

public class Admin {
    public  static void welcome(){
        System.out.println("Welcome Admin!\nWhat are we going to do today?");
        System.out.println("1.Users\n2.Restaurants\n3.Serving Table\n4.Menu\n5.Event\n6.Report\n\n0.Exit");
        Scanner scan = new Scanner(System.in);
        String choice = scan.nextLine();
        if(choice.equals("1")) {
            Users.WelcomeforAdmin();
        }else if(choice.equals("2")){
            Restaurants.WelcomeRestaurant();
        }else if(choice.equals("3")){
            Event.WelcomeEvent();
        }else if(choice.equals("4")){
            Registration.input();
        }else if(choice.equals("5")){

        }else if(choice.equals("6")){

        }else if(choice.equals("0")){

        }
    }
    public static void managerwelcome(){
        System.out.println("Welcome Manager!\nWhat are we going to do today?");
        System.out.println("1.Menu\n2.Event\n3.Serving Table");
        Scanner scan = new Scanner(System.in);
        String choice = scan.nextLine();
        if(choice.equals("1")) {
            Users.WelcomeforAdmin();
        }else if(choice.equals("2")){
            Menu.Welcome();
        }else if(choice.equals("3")){
            Event.WelcomeEvent();
        }
    }
    public static void waiterwelcom(){
        System.out.println("You don't have acces to the system");
    }

}
