import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.Map.Entry;

public class Users {

    public static void WelcomeforAdmin() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Welcome Admin");
        System.out.println("You are in User's menu");
        System.out.println("What are we going to do today?");
        System.out.println("1.Add users\n2.Delete users\n3.Change users\n\n9.Information\n0.Exit");

        String choice = scan.nextLine();
        if (choice.equals("1") || choice.equals("Add")) {
            addUser();
        } else if (choice.equals("2") || choice.equals("Delete")) {
            DeleteUser();
        } else if (choice.equals("9") || choice.equals("Information")) {
            InformationaboutUsers();
        } else if (choice.equals("3") || choice.equals("Change")){
            ChangeUser();
        }
    }


        private static void InformationaboutUsers () {
            String jdbcUrl = "jdbc:postgresql://localhost:5430/postgres";
            try {
                Connection connection = DriverManager.getConnection(jdbcUrl);
                System.out.println("Connected");
                DBTablePrinter.printTable(connection,"users");
                connection.close();
            } catch (SQLException e) {
                System.out.println("Error in connection");
                throw new RuntimeException(e);

            }
        }

        private static void DeleteUser () {
            String jdbcUrl = "jdbc:postgresql://localhost:5430/postgres";


            try {
                Connection connection = DriverManager.getConnection(jdbcUrl);
                System.out.println();
                Scanner scanner = new Scanner(System.in);
                connection.close();
            } catch (SQLException e) {
                System.out.println("Error in connection");
                throw new RuntimeException(e);
            }

        }

        private static void addUser () {
            String jdbcUrl = "jdbc:postgresql://localhost:5430/postgres";
            try {
                Connection connection = DriverManager.getConnection(jdbcUrl);
                Scanner scanner = new Scanner(System.in);
                System.out.println("Enter username: \n");
                String username = scanner.nextLine();
                System.out.println("Enter password: \n");
                String password = scanner.nextLine();
                System.out.println("Enter gender: \n");
                String gender = scanner.nextLine();
                System.out.println("Enter Email: \n");
                String email = scanner.nextLine();
                System.out.println("Enter role: \n");
                String role = scanner.nextLine();
                String sql = "INSERT INTO users(username,password,gender,email,role) values(?,?,?,?,?)";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, username);
                statement.setString(2, password);
                statement.setString(3, gender);
                statement.setString(4, email);
                statement.setString(5, role);
                statement.executeUpdate();
                connection.close();
                System.out.println("The User has been added!");
                WelcomeforAdmin();
            } catch (SQLException e) {
                System.out.println("Error in connection");
                throw new RuntimeException(e);
            }

        }
        private static void ChangeUser() {
        String jdbcUrl = "jdbc:postgresql://localhost:5430/postgres";


        try {
            Connection connection = DriverManager.getConnection(jdbcUrl);
            System.out.println("");
            Scanner scanner = new Scanner(System.in);

            connection.close();
        } catch (SQLException e) {
            System.out.println("Error in connection");
            throw new RuntimeException(e);
        }
    }
    }
