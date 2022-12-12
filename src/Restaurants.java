import java.sql.*;
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
            addrestaurant();
        } else if (choice.equals("2") || choice.equals("Delete")) {
            Deleterestaurants();
        } else if (choice.equals("3") || choice.equals("Change")) {
            Changerestaurants();
        } else if (choice.equals("9") || choice.equals("Information")){
            informationaboutrestaurants1();
        } else if (choice.equals("0") || choice.equals("Exit")){
            Admin.adminwelcome();
        }
    }
    public static void informationaboutrestaurants(){
        String jdbcUrl =  "jdbc:postgresql://localhost:5430/postgres";
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl);

            DBTablePrinter.printTable(connection,"restaurants");
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error in connection");
            throw new RuntimeException(e);
        }
    }
    private static void informationaboutrestaurants1(){
        informationaboutrestaurants();
        System.out.println("Please press m to back! ");
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();
        if(choice.equals("m")){
            AdminWelcome();
        }else{
            System.out.println("Error");
            Admin.adminwelcome();
        }

    }
    private static void addrestaurant(){
        String jdbcUrl =  "jdbc:postgresql://localhost:5430/postgres";

        try {
            Connection connection = DriverManager.getConnection(jdbcUrl);
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter name: \n");
            String name = scanner.nextLine();
            System.out.println("Enter address: \n");
            String address = scanner.nextLine();
            System.out.println("Enter manager: \n");
            String manager = scanner.nextLine();
            System.out.println("Enter Number of halls: \n");
            Integer numberofhalls = scanner.nextInt();
            String sql = "INSERT INTO restaurants(name,address,manager,numberofhalls) values(?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, address);
            statement.setString(3, manager);
            statement.setInt(4, numberofhalls);
            statement.executeUpdate();
            System.out.println("The restaurant has been added!");
            AdminWelcome();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error in connection");
            throw new RuntimeException(e);
        }
    }
    private static void Deleterestaurants () {
        informationaboutrestaurants();
        System.out.println("There are all retaurants that working at the moment!");
        System.out.println("Please write id that you want to delete!");
        Scanner scanner = new Scanner(System.in);
        String idfordelete = scanner.nextLine();

        String jdbcUrl =  "jdbc:postgresql://localhost:5430/postgres";
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl);
            String sql = "SELECT  * from restaurants";
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                String idfromtable = result.getString("id");
                String nameFromTable = result.getString("name");
                String addressFromTable = result.getString("address");
                String managerFromTable = result.getString("manager");
                String numberofhallsFromTable = result.getString("numberofhalls");
                if (idfordelete.equals(idfromtable)) {
                    System.out.println(nameFromTable +"\t"+addressFromTable +"\t" + managerFromTable +"\t" + numberofhallsFromTable +"\t");
                    System.out.println("Please press 'y' to delete!");
                    String choicefordelete = scanner.nextLine();
                    if (choicefordelete.equals("y")){
                        sql = String.format("DELETE FROM restaurants WHERE id = '%s';",idfordelete);
                        Statement statement2 = connection.createStatement();
                        statement2.executeUpdate(sql);
                        System.out.println("The Restaurant has been deleted!");
                        AdminWelcome();
                    }else{
                        System.out.println("Incorrect username");
                        AdminWelcome();
                    }
                }
            }
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private static void Changerestaurants () {
        informationaboutrestaurants();
        System.out.println("There are all restaurants that working at the moment!");
        System.out.println("Please write id that you want to change!");
        Scanner scanner = new Scanner(System.in);
        String idforchange = scanner.nextLine();

        String jdbcUrl =  "jdbc:postgresql://localhost:5430/postgres";
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl);
            String sql = "SELECT * from restaurants";
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                String idfromtable = result.getString("id");
                String name = result.getString("name");
                String address = result.getString("address");
                String manager = result.getString("manager");
                String numberofhalls = result.getString("numberofhalls");
                if (idforchange.equals(idfromtable)) {
                    System.out.println(name +"\t"+address +"\t" + manager +"\t" + numberofhalls);
                    System.out.println("Please choose what part you want to change! \n1.Name\n2.Address\n3.Manager\n4.Number of halls");
                    String choiceforchange= scanner.nextLine();
                    if (choiceforchange.equals("1")){
                        System.out.println("Enter Name: ");
                        String newname = scanner.nextLine();
                        sql = String.format("UPDATE restaurants SET name = '%s' WHERE id = %s;",newname,idforchange);
                        Statement statement2 = connection.createStatement();
                        statement2.executeUpdate(sql);
                        System.out.println("The name has been changed!");
                        AdminWelcome();
                    }else if(choiceforchange.equals("2")){
                        System.out.println("Enter Address: ");
                        String newaddress = scanner.nextLine();
                        sql = String.format("UPDATE restaurants SET address = '%s' WHERE id = %s;",newaddress,idforchange);
                        Statement statement2 = connection.createStatement();
                        statement2.executeUpdate(sql);
                        System.out.println("The address has been changed!");
                        AdminWelcome();
                    }else if(choiceforchange.equals("3")){
                        System.out.println("Enter Manager: ");
                        String newmanager = scanner.nextLine();
                        sql = String.format("UPDATE restaurants SET manager = '%s' WHERE id = %s;",newmanager,idforchange);
                        Statement statement2 = connection.createStatement();
                        statement2.executeUpdate(sql);
                        System.out.println("The manager has been changed!");
                        AdminWelcome();
                    } else if (choiceforchange.equals("4")) {
                        System.out.println("Enter Number of halls: ");
                        String newnum = scanner.nextLine();
                        sql = String.format("UPDATE restaurants SET numberofhalls = %s WHERE id = %s;",newnum,idforchange);
                        Statement statement2 = connection.createStatement();
                        statement2.executeUpdate(sql);
                        System.out.println("The number of halls has been changed!");
                        AdminWelcome();
                    }else{
                        System.out.println("Incorrect username");
                        AdminWelcome();
                    }
                }
            }
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}