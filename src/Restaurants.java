import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Restaurants {
    public static void AdminWelcome() {

        System.out.println("Welcome to Restaurants!\nWhat are we going to do today?");
        System.out.println("1.Add Restaurants\n2.Delete Restaurants\n3.Change Restaurants\n\n9.Information\n0.Exit");
        Scanner scan = new Scanner(System.in);
        String choice = scan.nextLine();
        if (choice.equals("1") || choice.equals("Add")) {
            System.out.println("This part is not ready yet!");
            AdminWelcome();
        } else if (choice.equals("2") || choice.equals("Delete")) {
            System.out.println("This part is not ready yet!");
            AdminWelcome();
        } else if (choice.equals("3") || choice.equals("Change")) {
            System.out.println("This part is not ready yet!");
            AdminWelcome();
        } else if (choice.equals("9") || choice.equals("Information")){
            System.out.println("This part is not ready yet!");
            AdminWelcome();
        } else if (choice.equals("0") || choice.equals("Exit")){
            Admin.adminwelcome();
        }
    }
    public static void ManagerWelcome() {
        System.out.println("Welcome to Restaurants!\nWhat are we going to do today?");
        System.out.println("1.Add Restaurants\n2.Delete Restaurants\n3.Change Restaurants\n\n9.Information\n0.Exit");
        Scanner scan = new Scanner(System.in);
        String choice = scan.nextLine();
        if (choice.equals("1") || choice.equals("Add")) {
            System.out.println("This part is not ready yet!");
            ManagerWelcome();
        } else if (choice.equals("2") || choice.equals("Delete")) {
            System.out.println("This part is not ready yet!");
            ManagerWelcome();
        } else if (choice.equals("3") || choice.equals("Change")) {
            System.out.println("This part is not ready yet!");
            ManagerWelcome();
        } else if (choice.equals("9") || choice.equals("Information")){
            System.out.println("This part is not ready yet!");
            ManagerWelcome();
        } else if (choice.equals("0") || choice.equals("Exit")){
            Admin.managerwelcome();
        }
    }
}
