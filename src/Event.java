import java.sql.*;
import java.util.Scanner;

public class Event {
    public static String adddate;
    public static String resid;
    public static String typeid;
    public static int howmany;
    public static String menuid;
    public static int totalprice;
    public static int menuprice;
    public static void ManagerWelcome() {
        System.out.println("Welcome to Events! Manager!\nWhat are we going to do today?");
        System.out.println("1.Add Event\n2.Delete Event\n3.Change Event\n\n9.Information\n0.Exit");
        Scanner scan = new Scanner(System.in);
        String choice = scan.nextLine();
        if (choice.equals("1") || choice.equals("Add")) {
            Addevent();
        } else if (choice.equals("2") || choice.equals("Delete")) {
            delevent();
        } else if (choice.equals("3") || choice.equals("Change")) {
            System.out.println("This part is not ready yet!");
            ManagerWelcome();
        } else if (choice.equals("9") || choice.equals("Information")){
            informationaboutevent1();
        } else if (choice.equals("0") || choice.equals("Exit")){
            Admin.managerwelcome();
        }else{
            ManagerWelcome();
        }
    }
    public static void AdminWelcome() {

        System.out.println("Welcome to Events! Admin!\nWhat are we going to do today?");
        System.out.println("1.Add Event\n2.Delete Event\n3.Change Event\n\n9.Information\n0.Exit");
        Scanner scan = new Scanner(System.in);
        String choice = scan.nextLine();
        if (choice.equals("1") || choice.equals("Add")) {
            Addevent();
        } else if (choice.equals("2") || choice.equals("Delete")) {
            delevent();
        } else if (choice.equals("3") || choice.equals("Change")) {
            System.out.println("This part is not ready yet!");
            AdminWelcome();
        } else if (choice.equals("9") || choice.equals("Information")){
            informationaboutevent1();
        } else if (choice.equals("0") || choice.equals("Exit")){
            Admin.adminwelcome();
        }else{
            AdminWelcome();
        }
    }

    private static void informationaboutevent1() {
        informationaboutevent();
        System.out.println("Please press m to back! ");
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();
        if(choice.equals("m")){
            if(Registration.role.equals("admin")){
                AdminWelcome();
            }else {
                ManagerWelcome();
            }
        }else{
            System.out.println("Error");
            if(Registration.role.equals("admin")){
                AdminWelcome();
            }else {
                ManagerWelcome();
            }
        }
    }
    private static void delevent(){
        informationaboutevent();
        System.out.println("There are all retaurants that have at the moment!");
        System.out.println("Please write id that you want to delete!");
        Scanner scanner = new Scanner(System.in);
        String idfordelete = scanner.nextLine();

        String jdbcUrl =  "jdbc:postgresql://localhost:5430/postgres";
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl);
            String sql = "SELECT  * from event";
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                String idfromtable = result.getString("id");
                if (idfordelete.equals(idfromtable)) {
                    System.out.println("Please press 'y' to delete!");
                    String choicefordelete = scanner.nextLine();
                    if (choicefordelete.equals("y")){
                        sql = String.format("DELETE FROM event WHERE id = '%s';",idfordelete);
                        Statement statement2 = connection.createStatement();
                        statement2.executeUpdate(sql);
                        System.out.println("The Event has been deleted!");
                        if(Registration.role.equals("admin")){
                            AdminWelcome();
                        }else{
                            ManagerWelcome();
                        }
                    }else{
                        if(Registration.role.equals("admin")){
                            AdminWelcome();
                        }else {
                            ManagerWelcome();
                        }
                    }
                }
            }
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void Addevent(){
        fordate();
        forrestaurants();
        fortype();
        forper();
        formenu();
        forprice();
        System.out.println("Menu: "+menuprice*howmany);
        double service = totalprice * 0.15;
        System.out.println("Service: "+service);
        double total = totalprice + service;
        System.out.println("Total price: "+total);

        String jdbcUrl =  "jdbc:postgresql://localhost:5430/postgres";

        try {
            Connection connection = DriverManager.getConnection(jdbcUrl);
            String sql = "INSERT INTO event(date,restaurant,type,people,price,menu) values(?,?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, adddate);
            statement.setString(2, resid);
            statement.setString(3, typeid);
            statement.setInt(4,howmany);
            statement.setDouble(5,total);
            statement.setString(6,menuid);
            statement.executeUpdate();
            System.out.println("The event has been added!");
            if(Registration.role.equals("admin")){
                AdminWelcome();
            }else {
                ManagerWelcome();
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error in connection");
            throw new RuntimeException(e);
        }
    }

    private static void forprice() {
        String jdbcUrl =  "jdbc:postgresql://localhost:5430/postgres";

        try {
            Connection connection = DriverManager.getConnection(jdbcUrl);
            String sql = "SELECT * from menu";
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while(result.next()){
                String menuidfromtable = result.getString("id");
                if (menuid.equals(menuidfromtable)){
                    menuprice = result.getInt("total");
                }
            }
            connection.close();
        }catch (SQLException e) {
        throw new RuntimeException(e);
        }
        totalprice = howmany * menuprice;

    }

    private static void formenu() {
        String jdbcUrl =  "jdbc:postgresql://localhost:5430/postgres";
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please choose a menu:");
        Menu.Informationaboutmenu();
        System.out.println("Write the id: ");
        menuid = scanner.nextLine();
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl);
            String sql = "SELECT  * from menu";
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                String idmenufromtable = result.getString("id");
                if (menuid.equals(idmenufromtable)) {
                    System.out.println("Saved!");
                }
            }
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void forper() {
        System.out.println("How many people will come:");
        Scanner scan = new Scanner(System.in);
        howmany = scan.nextInt();
        System.out.println("Saved!");
    }

    private static void fortype() {
        String jdbcUrl =  "jdbc:postgresql://localhost:5430/postgres";
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl);

            DBTablePrinter.printTable(connection,"typeofevent");
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error in connection");
            throw new RuntimeException(e);
        }
        System.out.println("ID: ");
        Scanner scanner = new Scanner(System.in);
        typeid = scanner.nextLine();
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl);
            String sql = "SELECT  * from typeofevent";
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                String ideventfromtable = result.getString("id");
                if (typeid.equals(ideventfromtable)) {
                    System.out.println("Saved!");
                }
            }
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void fordate(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Example: (MM-DD-YYYY)\nDate: ");
        adddate = scanner.nextLine();
        String jdbcUrl =  "jdbc:postgresql://localhost:5430/postgres";

        try {
            Connection connection = DriverManager.getConnection(jdbcUrl);
            String sql = "SELECT  * from event";
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                String dateFromTable = result.getString("date");
                if (adddate.equals(dateFromTable)) {
                    System.out.println("This date is not available");
                    System.out.println("Please choose another date!");
                    fordate();
                }
            }
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Save");
    }
    private static void forrestaurants(){
        String jdbcUrl =  "jdbc:postgresql://localhost:5430/postgres";
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please choose a restaurant:");
        Restaurants.informationaboutrestaurants();
        System.out.println("Write the id: ");
        resid = scanner.nextLine();
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl);
            String sql = "SELECT  * from restaurants";
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                String idresfromtable = result.getString("id");
                if (resid.equals(idresfromtable)) {
                    System.out.println("Saved!");
                }
            }
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void informationaboutevent(){
        String jdbcUrl =  "jdbc:postgresql://localhost:5430/postgres";
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl);

            DBTablePrinter.printTable(connection,"event");
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error in connection");
            throw new RuntimeException(e);
        }
    }


}
