import java.sql.*;
import java.util.Scanner;

public class Menu {
    public static void ManagerWelcome() {
        System.out.println("Welcome to Menu!\nWhat are we going to do today?");
        System.out.println("1.Add Menu\n2.Delete Menu\n3.Change Menu\n\n9.Information\n0.Exit");
        Scanner scan = new Scanner(System.in);
        String choice = scan.nextLine();
        if (choice.equals("1") || choice.equals("Add")) {
            Addmenu();
        } else if (choice.equals("2") || choice.equals("Delete")) {
            Deletmenu();
        } else if (choice.equals("3") || choice.equals("Change")) {
            Changemenu();
        } else if (choice.equals("9") || choice.equals("Information")){
            Informationaboutmenu1();
        } else if (choice.equals("0") || choice.equals("Exit")){
            Admin.managerwelcome();
        }
    }
    public static void AdminWelcome() {
        System.out.println("Welcome to Menu!\nWhat are we going to do today?");
        System.out.println("1.Add Menu\n2.Delete Menu\n3.Change Menu\n\n9.Information\n0.Exit");
        Scanner scan = new Scanner(System.in);
        String choice = scan.nextLine();
        if (choice.equals("1") || choice.equals("Add")) {
            Addmenu();
        } else if (choice.equals("2") || choice.equals("Delete")) {
            Deletmenu();
        } else if (choice.equals("3") || choice.equals("Change")) {
            Changemenu();
        } else if (choice.equals("9") || choice.equals("Information")){
            Informationaboutmenu1();
        } else if (choice.equals("0") || choice.equals("Exit")){
            Admin.adminwelcome();
        }
    }
    private static void Informationaboutmenu1() {
        Informationaboutmenu();
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
    public static void Informationaboutmenu(){
        String jdbcUrl =  "jdbc:postgresql://localhost:5430/postgres";
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl);
            DBTablePrinter.printTable(connection,"menu");
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error in connection");
            throw new RuntimeException(e);
        }
    }

    private static void Addmenu(){
        String jdbcUrl =  "jdbc:postgresql://localhost:5430/postgres";

        try {
            Connection connection = DriverManager.getConnection(jdbcUrl);
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter name: \n");
            String name = scanner.nextLine();
            System.out.println("Enter meals: \n");
            String meals = scanner.nextLine();
            System.out.println("Enter prices: \n");
            String prices = scanner.nextLine();
            System.out.println("Enter total: \n");
            Integer total = scanner.nextInt();
            String sql = "INSERT INTO menu(name,meals,price,total) values(?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, meals);
            statement.setString(3, prices);
            statement.setInt(4,total);
            statement.executeUpdate();
            System.out.println("The menu has been added!");
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
    private static void Deletmenu() {
        Informationaboutmenu();
        System.out.println("There are all menu that having at the moment!");
        System.out.println("Please write id that you want to delete!");
        Scanner scanner = new Scanner(System.in);
        String idfordelete = scanner.nextLine();

        String jdbcUrl =  "jdbc:postgresql://localhost:5430/postgres";
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl);
            String sql = "SELECT  * from menu";
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                String idfromtable = result.getString("id");
                String name = result.getString("name");
                String meals = result.getString("meals");
                if (idfordelete.equals(idfromtable)) {
                    System.out.println(name +"\t"+ meals +"\t" );
                    System.out.println("Please press 'y' to delete!");
                    String choicefordelete = scanner.nextLine();
                    if (choicefordelete.equals("y")){
                        sql = String.format("DELETE FROM menu WHERE id = '%s';",idfordelete);
                        Statement statement2 = connection.createStatement();
                        statement2.executeUpdate(sql);
                        System.out.println("The menu has been deleted!");
                        if(Registration.role.equals("admin")){
                            AdminWelcome();
                        }else {
                            ManagerWelcome();
                        }
                    }else{
                        System.out.println("Incorrect username");
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

    private static void Changemenu(){
        Informationaboutmenu();
        System.out.println("There are all menu that having at the moment!");
        System.out.println("Please write id that you want to change!");
        Scanner scanner = new Scanner(System.in);
        String idforchange = scanner.nextLine();

        String jdbcUrl =  "jdbc:postgresql://localhost:5430/postgres";
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl);
            String sql = "SELECT  * from menu";
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                String idfromtable = result.getString("id");
                String name = result.getString("name");
                String meals = result.getString("meals");
                String prices = result.getString("price");
                Integer total = result.getInt("total");
                if (idforchange.equals(idfromtable)) {
                    System.out.println(name +"\t"+ meals +"\t"+ prices + "\t" + total);
                    System.out.println("Please choose what part you want to change! \n1.Name\n2.Meals\n3.Price\n4.Total");
                    String choiceforchange = scanner.nextLine();
                    if (choiceforchange.equals("1")){
                        System.out.println("Enter name: ");
                        String newname = scanner.nextLine();
                        sql = String.format("UPDATE menu SET name = '%s' WHERE id = %s;",newname,idforchange);
                        Statement statement2 = connection.createStatement();
                        statement2.executeUpdate(sql);
                        System.out.println("The menu has been changed!");
                        if(Registration.role.equals("admin")){
                            AdminWelcome();
                        }else {
                            ManagerWelcome();
                        }
                    }else if(choiceforchange.equals("2")){
                        System.out.println("Enter meals: ");
                        String newmeal = scanner.nextLine();
                        sql = String.format("UPDATE menu SET meals = '%s' WHERE id = %s;",newmeal,idforchange);
                        Statement statement2 = connection.createStatement();
                        statement2.executeUpdate(sql);
                        System.out.println("The menu has been changed!");
                        if(Registration.role.equals("admin")){
                            AdminWelcome();
                        }else {
                            ManagerWelcome();
                        }
                    }else if(choiceforchange.equals("3")){
                        System.out.println("Enter prices: ");
                        String newprices = scanner.nextLine();
                        sql = String.format("UPDATE menu SET prices = '%s' WHERE id = %s;",newprices,idforchange);
                        Statement statement2 = connection.createStatement();
                        statement2.executeUpdate(sql);
                        System.out.println("The menu has been changed!");
                        if(Registration.role.equals("admin")){
                            AdminWelcome();
                        }else {
                            ManagerWelcome();
                        }
                    }else if(choiceforchange.equals("4")){
                        System.out.println("Enter total: ");
                        Integer newtotal = scanner.nextInt();
                        sql = String.format("UPDATE menu SET total = %s WHERE id = %s;",newtotal,idforchange);
                        Statement statement2 = connection.createStatement();
                        statement2.executeUpdate(sql);
                        System.out.println("The menu has been changed!");
                        if(Registration.role.equals("admin")){
                            AdminWelcome();
                        }else {
                            ManagerWelcome();
                        }
                    }else{
                        System.out.println("Incorrect username");
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

}
