import java.util.*;
import java.util.Map.Entry;
public class Users{
    static List<String> Waiterusernames = new ArrayList<String>();
    static List<String> Waiterpasswords = new ArrayList<String>();

    static List<String> Managerusernames = new ArrayList<String>();
    static List<String> Managerpasswords = new ArrayList<String>();

    static List<String> Adminusernames = new ArrayList<String>();
    static List<String> Adminpasswords = new ArrayList<String>();

    public static void auto(){
        Adminusernames.add("Yrysbaev");
        Adminpasswords.add("1234");
    }
    public static void WelcomeforAdmin(){
        System.out.println("Welcome Admin");
        System.out.println("You are in User's menu");
        System.out.println("What are we going to do today?");
        System.out.println("1.Add users\n2.Delete users\n3.Change Users\n\n9.Information\n0.Exit");
        Scanner scan = new Scanner(System.in);
        String choice = scan.nextLine();
        if(choice.equals("1")) {
            addUsers();
        }else if(choice.equals("2")){
            deleteUsers();
        }else if(choice.equals("3")){
            changeUsers();
        }else if(choice.equals("9")){
            information();
        }else if(choice.equals("0")){
            Admin.welcome();
        }
    }
    private static void addUsers(){
        System.out.println("Create username:");
        Scanner scan = new Scanner(System.in);
        String newUser = scan.nextLine();
        System.out.println("Create password:");
        String newUserpassword = scan.nextLine();
        System.out.println("What is the role of this user");
        System.out.println("1.Admin\n2.Manager\n3.Waiter");
        String usersrole = scan.nextLine();
        if(usersrole.equals("1")){
            Adminusernames.add(newUser);
            Adminpasswords.add(newUserpassword);
        }else if(usersrole.equals("2")){
            Managerusernames.add(newUser);
            Managerpasswords.add(newUserpassword);
        }else if(usersrole.equals("3")){
            Waiterusernames.add(newUser);
            Waiterpasswords.add(newUserpassword);
        }else{
            System.out.println("You didn't added\nPlease press m to back main menu\npress any character to return");
            String back = scan.nextLine();
            if(back.equals("m")){
                WelcomeforAdmin();
            }else{
                addUsers();
            }
        }
        System.out.println("You added correctly\nPlease press m to back main menu");
        String back = scan.nextLine();
        if(back.equals("m")){
            WelcomeforAdmin();
        }
    }
    private static void deleteUsers(){
        System.out.println("Write user name: ");
        Scanner scan = new Scanner(System.in);
        String oldUser = scan.nextLine();
        System.out.println("To continue press y if you want to change name press s");
        String choice = scan.nextLine();
        if(choice.equals("y")){
            if(Adminusernames.contains(oldUser)){
                System.out.println("This is a Admin?\nPress y(Yes) or n(No)");
                String choice1 = scan.nextLine();
                if(choice1.equals("y")){
                    Adminpasswords.remove(Adminusernames.indexOf(oldUser));
                    Adminusernames.remove(oldUser);
                }else{
                    return;
                }
            }else if(Managerusernames.contains(oldUser)){
                System.out.println("This is a Manager?\nPress y(Yes) or n(No)");
                String choice1 = scan.nextLine();
                if(choice1.equals("y")){
                    Managerpasswords.remove(Managerusernames.indexOf(oldUser));
                    Managerusernames.remove(oldUser);
                }else{
                    return;
                }
            } else if (Waiterusernames.contains(oldUser)) {
                System.out.println("This is a Waiter?\nPress y(Yes) or n(No)");
                String choice1 = scan.nextLine();
                if(choice1.equals("y")){
                    Waiterpasswords.remove(Waiterusernames.indexOf(oldUser));
                    Waiterusernames.remove(oldUser);
                }else{
                    return;
                }
            }else{
                System.out.println("There is no User like that!\nPlease check User name");
                System.out.println("Please press m to back main menu");
                String back = scan.nextLine();
                if(back.equals("m")){
                    WelcomeforAdmin();
                } else {
                    System.out.println("Sorry we didn't have like that command");
                    Admin.welcome();
                }
                return;
            }
        }
        System.out.println("User is deleted!");
        System.out.println("Please press m to back main menu");
        String back = scan.nextLine();
        if(back.equals("m")){
            WelcomeforAdmin();
        }
    }
    private static void changeUsers(){
        System.out.println("Sorry this part isn't ready!");
        Scanner scan = new Scanner(System.in);
        System.out.println("Please press m to back main menu");
        String back = scan.nextLine();
        if(back.equals("m")){
            WelcomeforAdmin();
        }else {
            System.out.println("Sorry we didn't have like that command");
            Admin.welcome();
        }

        }


    private static void information(){
        System.out.println("You are in information menu!");
        System.out.println("1.Admin\n2.Manager\n3.Waiter");
        Scanner scan = new Scanner(System.in);
        String choice = scan.nextLine();
        if(choice.equals("1")){
            for (int i = 0; i < Adminusernames.size(); i++) {
                System.out.println(i+1);
                System.out.println("Usernames: "+": "+Adminusernames.get(i));
                System.out.println("Password: "+": "+Adminpasswords.get(i));
            }
            System.out.println("Please press m to back main menu");
            String back = scan.nextLine();
            if(back.equals("m")){
                WelcomeforAdmin();
            }
        }
    }

}
