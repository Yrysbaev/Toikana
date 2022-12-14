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
        if (checkFromDataBase()) {
            System.out.println("You logged in");
            System.out.println(role);
            switch (role) {
                case "admin":
                    Admin.adminwelcome();
                    break;
                case "manager":
                    Admin.managerwelcome();
                    break;
                case "waiter":
                    Admin.waiterwelcom();
                    break;
            }
        } else {
            System.out.println("Incorrect password or name");
            input();
        }
    }


    private static Boolean checkFromDataBase() {
        Boolean flag = false;
        String jdbcUrl =  "jdbc:postgresql://localhost:5430/postgres";
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl);
            String sql = "SELECT  * from users";
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                String usernameFromTable = result.getString("username");
                String password = result.getString("password");
                if (userpassword.equals(password) && username.equals(usernameFromTable)) {
                    role = result.getString("role");
                    flag = true;
                }
            }
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return flag;
    }
}


