import java.sql.*;
import java.util.Scanner;

public class Registration {
    static String username;
    static String userpassword;
    static String role;
    public Registration() {


    }

    public static void input() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please log in\nUsername:");
        username = scan.nextLine().trim();
        System.out.println("Password:");
        userpassword = scan.nextLine().trim();
        if(checkFromDataBase()){
            System.out.println("You logged in");
            System.out.println(role);
            switch(role){
                case "admin":
                    Admin.welcome();
                    break;
                case "manager":
                    break;
                case "waiter":
                    System.out.println("You are waiter");
                    break;
            }
        } else{
            System.out.println("Incorrect password or name");
            input();
        }
    }


    private static Boolean  checkFromDataBase(){
        Boolean flag = false;
        String jdbcUrl = "jdbc:postgresql://localhost:5432/postgres";
        String databaseUserName = "umut";
        String databaseUserPassword = "0000";
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, databaseUserName, databaseUserPassword);
            System.out.println("Connected");
            String sql = "SELECT  * from users";
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                String usernameFromTable = result.getString("username");
                String password = result.getString("password");

                if(userpassword.equals(password) && username.equals(usernameFromTable)){
                    role = result.getString("role");
                    flag = true;
                }

            }
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error in connection");
            throw new RuntimeException(e);

        }
        return flag;
    }

    }

