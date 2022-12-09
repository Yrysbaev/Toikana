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
        System.out.println("1.Add users\n2.Delete users\n3.Change Users\n\n9.Information\n0.Exit");

        String choice = scan.nextLine();
        if (choice.equals("1") || choice.equals("Add")) {
            addUser();
        } else if (choice.equals("2") || choice.equals("Delete")) {
            DeleteUser();
        } else if (choice.equals("3") || choice.equals("Change")) {
            ChangeUser();
        } else if (choice.equals("9") || choice.equals("Information")){
            InformationaboutUsers1();
        } else if (choice.equals("0") || choice.equals("Exit")){
            Admin.adminwelcome();
        }
    }
    private static void ChangeUser() {
        InformationaboutUsers();
        System.out.println("There are all Users that having at the moment!");
        System.out.println("Please write id that you want to change!");
        Scanner scanner = new Scanner(System.in);
        String idforchange = scanner.nextLine();

        String jdbcUrl =  "jdbc:postgresql://ec2-54-75-26-218.eu-west-1.compute.amazonaws.com:5432/d76rlbpbnjk96j";
        String databaseUserName = "aipsdsjuqegbvf";
        String databaseUserPassword = "0d9cbb30ef98cc294991fc63006f9a6685590912fee984771fd133a11935945c";
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl,databaseUserName,databaseUserPassword);
            String sql = "SELECT  * from users";
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                String idfromtable = result.getString("id");
                String username = result.getString("username");
                String password = result.getString("password");
                String gender = result.getString("gender");
                String gmail = result.getString("email");
                String role = result.getString("role");
                if (idforchange.equals(idfromtable)) {
                    System.out.println(username +"\t"+ password +"\t"+ gender + "\t" + gmail + "\t" + role);
                    System.out.println("Please choose what part you want to change! \n1.Username\n2.Password\n3.Gender\n4.Email\n5.Role");
                    String choiceforchange = scanner.nextLine();
                    if (choiceforchange.equals("1")){
                        System.out.println("Enter Username: ");
                        String newname = scanner.nextLine();
                        sql = String.format("UPDATE users SET username = '%s' WHERE id = %s;",newname,idforchange);
                        Statement statement2 = connection.createStatement();
                        statement2.executeUpdate(sql);
                        System.out.println("The Username has been changed!");
                        WelcomeforAdmin();
                    }else if(choiceforchange.equals("2")){
                        System.out.println("Enter Password: ");
                        String newpassword = scanner.nextLine();
                        sql = String.format("UPDATE users SET password = '%s' WHERE id = %s;",newpassword,idforchange);
                        Statement statement2 = connection.createStatement();
                        statement2.executeUpdate(sql);
                        System.out.println("The Password has been changed!");
                        WelcomeforAdmin();
                    }else if(choiceforchange.equals("3")){
                        System.out.println("Enter gender: ");
                        String newgender = scanner.nextLine();
                        sql = String.format("UPDATE users SET gender = '%s' WHERE id = %s;",newgender,idforchange);
                        Statement statement2 = connection.createStatement();
                        statement2.executeUpdate(sql);
                        System.out.println("The Gender has been changed!");
                        WelcomeforAdmin();
                    }else if(choiceforchange.equals("4")){
                        System.out.println("Enter Email: ");
                        String newemail = scanner.nextLine();
                        sql = String.format("UPDATE users SET email = '%s' WHERE id = %s;",newemail,idforchange);
                        Statement statement2 = connection.createStatement();
                        statement2.executeUpdate(sql);
                        System.out.println("The Email has been changed!");
                        WelcomeforAdmin();
                    }else if(choiceforchange.equals("5")){
                        System.out.println("Enter Role: ");
                        String newrole = scanner.nextLine();
                        sql = String.format("UPDATE users SET role = '%s' WHERE id = %s;",newrole,idforchange);
                        Statement statement2 = connection.createStatement();
                        statement2.executeUpdate(sql);
                        System.out.println("The role has been changed!");
                        WelcomeforAdmin();
                    }else{
                        System.out.println("Incorrect command!");
                        WelcomeforAdmin();
                    }
                }
            }
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private static void InformationaboutUsers1 () {
            InformationaboutUsers();
            System.out.println("Please press m to back! ");
            Scanner scanner = new Scanner(System.in);
            String choice = scanner.nextLine();
            if(choice.equals("m")){
                WelcomeforAdmin();
            }else{
                System.out.println("Error");
                Admin.adminwelcome();
            }

    }
    private static void InformationaboutUsers () {
            String jdbcUrl =  "jdbc:postgresql://ec2-54-75-26-218.eu-west-1.compute.amazonaws.com:5432/d76rlbpbnjk96j";
            String databaseUserName = "aipsdsjuqegbvf";
            String databaseUserPassword = "0d9cbb30ef98cc294991fc63006f9a6685590912fee984771fd133a11935945c";
            try {
                Connection connection = DriverManager.getConnection(jdbcUrl, databaseUserName, databaseUserPassword);

                DBTablePrinter.printTable(connection,"users");
                connection.close();
            } catch (SQLException e) {
                System.out.println("Error in connection");
                throw new RuntimeException(e);
            }
    }
    private static void DeleteUser () {
            InformationaboutUsers();
            System.out.println("There are all accounts that working at the moment!");
            System.out.println("Please write id that you want to delete!");
            Scanner scanner = new Scanner(System.in);
            String idfordelete = scanner.nextLine();

            String jdbcUrl =  "jdbc:postgresql://ec2-54-75-26-218.eu-west-1.compute.amazonaws.com:5432/d76rlbpbnjk96j";
            String databaseUserName = "aipsdsjuqegbvf";
            String databaseUserPassword = "0d9cbb30ef98cc294991fc63006f9a6685590912fee984771fd133a11935945c";
            try {
                Connection connection = DriverManager.getConnection(jdbcUrl, databaseUserName, databaseUserPassword);
                String sql = "SELECT  * from users";
                Statement statement = connection.createStatement();
                ResultSet result = statement.executeQuery(sql);
                while (result.next()) {
                    String idfromtable = result.getString("id");
                    String usernameFromTable = result.getString("username");
                    String passwordFromTable = result.getString("password");
                    String genderFromTable = result.getString("gender");
                    String roleFromTable = result.getString("role");
                    if (idfordelete.equals(idfromtable)) {
                        System.out.println(usernameFromTable +"\t"+passwordFromTable +"\t" + genderFromTable +"\t" + roleFromTable +"\t");
                        System.out.println("Please press 'y' to delete!");
                        String choicefordelete = scanner.nextLine();
                        if (choicefordelete.equals("y")){
                            sql = String.format("DELETE FROM users WHERE id = %s;",idfordelete);
                            Statement statement2 = connection.createStatement();
                            statement2.executeUpdate(sql);
                            System.out.println("The User has been deleted!");
                            WelcomeforAdmin();
                        }else{
                            System.out.println("Incorrect username");
                            WelcomeforAdmin();
                        }
                    }
                }
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    private static void addUser () {
            String jdbcUrl =  "jdbc:postgresql://ec2-54-75-26-218.eu-west-1.compute.amazonaws.com:5432/d76rlbpbnjk96j";
            String databaseUserName = "aipsdsjuqegbvf";
            String databaseUserPassword = "0d9cbb30ef98cc294991fc63006f9a6685590912fee984771fd133a11935945c";

            try {
                Connection connection = DriverManager.getConnection(jdbcUrl, databaseUserName, databaseUserPassword);
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
                System.out.println("The user has been added!");
                WelcomeforAdmin();
                connection.close();
            } catch (SQLException e) {
                System.out.println("Error in connection");
                throw new RuntimeException(e);
            }
        }
    }