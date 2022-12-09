import java.sql.*;
import java.util.Scanner;

public class Servingtable {
    public static void ManagerWelcome() {
        System.out.println("Welcome to Serving Table!\nWhat are we going to do today?");
        System.out.println("1.Add Serving Table\n2.Delete Serving Table\n3.Change Serving Table\n\n9.Information\n0.Exit");
        Scanner scan = new Scanner(System.in);
        String choice = scan.nextLine();
        if (choice.equals("1") || choice.equals("Add")) {
            AddSer();
        } else if (choice.equals("2") || choice.equals("Delete")) {
            Deletetable();
        } else if (choice.equals("3") || choice.equals("Change")) {
            Changetable();
        } else if (choice.equals("9") || choice.equals("Information")){
            Informationabouttable1();
        } else if (choice.equals("0") || choice.equals("Exit")){
            Admin.managerwelcome();
        }
    }
    public static void AdminWelcome() {

        System.out.println("Welcome to Serving Table!\nWhat are we going to do today?");
        System.out.println("1.Add Serving Table\n2.Delete Serving Table\n3.Change Serving Table\n\n9.Information\n0.Exit");
        Scanner scan = new Scanner(System.in);
        String choice = scan.nextLine();
        if (choice.equals("1") || choice.equals("Add")) {
            AddSer();
        } else if (choice.equals("2") || choice.equals("Delete")) {
            Deletetable();
        } else if (choice.equals("3") || choice.equals("Change")) {
            Changetable();
        } else if (choice.equals("9") || choice.equals("Information")){
            Informationabouttable1();
        } else if (choice.equals("0") || choice.equals("Exit")){
            Admin.adminwelcome();
        }

    }
    private static void Informationabouttable(){
        String jdbcUrl =  "jdbc:postgresql://localhost:5430/postgres";
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl);
            DBTablePrinter.printTable(connection,"servingtable");
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error in connection");
            throw new RuntimeException(e);
        }
    }
    private static void Informationabouttable1(){
        Informationabouttable();
        System.out.println("Please press m to back! ");
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();
        if(choice.equals("m")){
            if(Registration.role.equals("Admin")){
                AdminWelcome();
            }else {
                ManagerWelcome();
            }
        }else{
            System.out.println("Error");
            if(Registration.role.equals("Admin")){
                AdminWelcome();
            }else {
                ManagerWelcome();
            }
        }
    }
    private static void AddSer(){
        String jdbcUrl =  "jdbc:postgresql://localhost:5430/postgres";

        try {
            Connection connection = DriverManager.getConnection(jdbcUrl);
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter hall: \n");
            String hall = scanner.nextLine();
            System.out.println("Enter format: \n");
            String format = scanner.nextLine();
            System.out.println("Enter Number of table: \n");
            Integer numberoftable = scanner.nextInt();
            String sql = "INSERT INTO servingtable(hall,format,numberoftable) values(?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, hall);
            statement.setString(2, format);
            statement.setInt(3, numberoftable);
            statement.executeUpdate();
            System.out.println("The servingtable has been added!");
            if(Registration.role.equals("Admin")){
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
    private static void Deletetable() {
        Informationabouttable();
        System.out.println("There are all tables that having at the moment!");
        System.out.println("Please write id that you want to delete!");
        Scanner scanner = new Scanner(System.in);
        String idfordelete = scanner.nextLine();

        String jdbcUrl =  "jdbc:postgresql://localhost:5430/postgres";
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl);
            String sql = "SELECT  * from servingtable";
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                String idfromtable = result.getString("id");
                String hall = result.getString("hall");
                String format = result.getString("format");
                String numberoftable = result.getString("numberoftable");
                if (idfordelete.equals(idfromtable)) {
                    System.out.println(hall +"\t"+format +"\t" + numberoftable +"\t");
                    System.out.println("Please press 'y' to delete!");
                    String choicefordelete = scanner.nextLine();
                    if (choicefordelete.equals("y")){
                        sql = String.format("DELETE FROM servingtable WHERE id = '%s';",idfordelete);
                        Statement statement2 = connection.createStatement();
                        statement2.executeUpdate(sql);
                        System.out.println("The Servingtable has been deleted!");
                        if(Registration.role.equals("Admin")){
                            AdminWelcome();
                        }else {
                            ManagerWelcome();
                        }
                    }else{
                        System.out.println("Incorrect username");
                        if(Registration.role.equals("Admin")){
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
    private static void Changetable(){
        Informationabouttable();
        System.out.println("There are all tables that having at the moment!");
        System.out.println("Please write id that you want to change!");
        Scanner scanner = new Scanner(System.in);
        String idforchange = scanner.nextLine();

        String jdbcUrl =  "jdbc:postgresql://localhost:5430/postgres";
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl);
            String sql = "SELECT * from servingtable";
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                String idfromtable = result.getString("id");
                String hall = result.getString("hall");
                String format = result.getString("format");
                String numberoftable = result.getString("numberoftable");
                if (idforchange.equals(idfromtable)) {
                    System.out.println(hall +"\t"+format +"\t" + numberoftable +"\t");
                    System.out.println("Please choose what part you want to change! \n1.Hall\n2.Format\n3.Number of table");
                    String choiceforchange= scanner.nextLine();
                    if (choiceforchange.equals("1")){
                        System.out.println("Enter Hall: ");
                        String newhall = scanner.nextLine();
                        sql = String.format("UPDATE servingtable SET hall = '%s' WHERE id = %s;",newhall,idforchange);
                        Statement statement2 = connection.createStatement();
                        statement2.executeUpdate(sql);
                        System.out.println("The Servingtable has been changed!");
                        if(Registration.role.equals("Admin")){
                            AdminWelcome();
                        }else {
                            ManagerWelcome();
                        }
                    }else if(choiceforchange.equals("2")){
                        System.out.println("Enter Format: ");
                        String newformat = scanner.nextLine();
                        sql = String.format("UPDATE servingtable SET format = '%s' WHERE id = %s;",newformat,idforchange);
                        Statement statement2 = connection.createStatement();
                        statement2.executeUpdate(sql);
                        System.out.println("The Servingtable has been changed!");
                        if(Registration.role.equals("Admin")){
                            AdminWelcome();
                        }else {
                            ManagerWelcome();
                        }
                    }else if(choiceforchange.equals("3")){
                        System.out.println("Enter Number of table: ");
                        Integer newnum = scanner.nextInt();
                        sql = String.format("UPDATE servingtable SET numberoftable = %s WHERE id = %s;",newnum,idforchange);
                        Statement statement2 = connection.createStatement();
                        statement2.executeUpdate(sql);
                        System.out.println("The Servingtable has been changed!");
                        if(Registration.role.equals("Admin")){
                            AdminWelcome();
                        }else {
                            ManagerWelcome();
                        }
                    }else{
                        System.out.println("Incorrect username");
                        if(Registration.role.equals("Admin")){
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