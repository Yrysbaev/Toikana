import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Weclome to our restaurant!\nPress s to start\n");
        String start = scan.nextLine();
        Users user = new Users();
        Registration registration = new Registration();
        registration.input();
    }
}