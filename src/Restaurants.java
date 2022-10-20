import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Restaurants {

    static List<String> Restaurants = new ArrayList<String>();
    static List<String> RestaurantAddress = new ArrayList<String>();
    static List<String> RestaurantManager = new ArrayList<String>();
    static List<String> Restaurantsize = new ArrayList<String>();

    public static void WelcomeRestaurant(){
        System.out.println("Welcome Admin");
        System.out.println("You are in Restaurant's menu");
        System.out.println("What are we going to do today?");
        System.out.println("1.Add Restaurant\n2.Delete Restaurant\n3.Change Restaurants\n\n9.Information\n0.Exit");
        Scanner scan = new Scanner(System.in);
        String choice = scan.nextLine();
        if(choice.equals("1")) {
            Addrestaurant();
        }else if(choice.equals("2")){
            Deleterestaurant();
        }else if(choice.equals("3")){
            Changerestaurant();
        }else if(choice.equals("9")){
            Information();
        }else if(choice.equals("0")){
            Admin.welcome();
        }
    }
    private static void Addrestaurant(){

    }
    private static void Deleterestaurant(){

    }
    private static void Changerestaurant(){

    }
    private static void Information(){

    }
}
