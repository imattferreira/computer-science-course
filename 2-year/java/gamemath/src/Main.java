import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        while(true) {
            System.out.println("Would you like to play ??");
            System.out.println("1 - Yes");
            System.out.println("2 - No");
            System.out.print("-> ");
            Scanner scanner = new Scanner(System.in);

            try{
                int option = scanner.nextInt();

                switch (option) {
                    case 1 : new Game().play(); break;
                    case 2 : System.exit(0);break;
                }
            } catch (InputMismatchException e) {
                System.err.println("Type an existing value!!");
            }
        }
    }
}