import java.util.Scanner;

public class Main extends Users{

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Weclome to our restaurant!\nPress s to start\n");
        String start = scan.nextLine();
        Users user = new Users();
        Users.auto();
        Registration registration = new Registration();
        registration.input();
    }

}