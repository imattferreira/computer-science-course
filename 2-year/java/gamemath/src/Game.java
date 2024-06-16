import java.util.*;

public class Game {
    private Timer timer;
    private Repository repository;
    public static boolean over;

    public Game() {
        over = false;
        this.timer = new Timer();
        this.repository = new Repository();

        this.timer.schedule(new GameOver(), 30000);
    }

    public void play() {
        this.timer = new Timer();
        Random randomNumber = new Random();
        String[] operators = {"+", "-", "*", "/"};
        Scanner scanner = new Scanner(System.in);

        System.out.println("What's your name?");
        String name = scanner.next();

        int rightAnswers = 0;
        int totalAnswers = 0;

        while(!over) {
            int firstNumber = randomNumber.nextInt(51);
            int secondNumber = randomNumber.nextInt(51);
            int userResult;

            String operator = operators[randomNumber.nextInt(4)];

            try {
                switch (operator) {
                    case "+" -> {
                        System.out.printf("%d + %d %n", firstNumber, secondNumber);
                        System.out.print("Answer: ");

                        userResult = scanner.nextInt();
                        int sum = firstNumber + secondNumber;

                        if (userResult == sum) {
                            rightAnswers++;
                            System.out.println("Correct");
                        } else {
                            System.out.println("Incorrect");
                        }

                    }

                    case "-" -> {
                        System.out.printf("%d - %d %n", firstNumber, secondNumber);
                        System.out.print("Answer: ");

                        userResult = scanner.nextInt();

                        int subtraction = firstNumber - secondNumber;

                        if (userResult == subtraction) {
                            rightAnswers++;
                            System.out.println("Correct!");
                        } else {
                            System.out.println("Incorrect");
                        }
                    }

                    case "*" -> {
                        System.out.printf("%d X %d %n", firstNumber, secondNumber);
                        System.out.print("Answer: ");

                        userResult = scanner.nextInt();

                        int multiplication = firstNumber * secondNumber;

                        if (userResult == multiplication) {
                            rightAnswers++;
                            System.out.println("Correct!");
                        } else {
                            System.out.println("Incorrect");
                        }
                    }

                    case "/" -> {
                        while (secondNumber == 0){
                            secondNumber = randomNumber.nextInt(51);
                        }

                        System.out.printf("%d / %d %n", firstNumber, secondNumber);
                        System.out.print("Answer: ");

                        userResult = scanner.nextInt();

                        int division = firstNumber / secondNumber;

                        if (userResult == division) {
                            rightAnswers++;
                            System.out.println("Correct!");
                        } else {
                            System.out.println("Incorrect");
                        }
                    }

                }

            } catch (InputMismatchException e) {
                System.err.println("Integer values only !!");
                scanner.nextLine();
            }

            totalAnswers ++;

        }

        this.repository.createRecord(name, rightAnswers);
        this.showUserResult(rightAnswers, totalAnswers);
        this.showBetterRecords();
    }

    private void showBetterRecords() {
        ArrayList<Record> betterRecords = this.repository.getBetterRecords();

        System.out.println("\n\nRecords atuais");

        for (int i = 0; i < betterRecords.size(); i++) {
            Record record = betterRecords.get(i);

            System.out.println(i + 1 + " | " + record.getUsername() + " -> " + record.getPoints());
        }
    }

    private void showUserResult(int rightAnswers, int totalAnswers) {
        System.out.println("\n\nFinished!!");
        System.out.println("Total questions: " + totalAnswers);
        System.out.println("Total right answers: " + rightAnswers);
    }

    private void exit() {
        System.exit(0);
    }
}
