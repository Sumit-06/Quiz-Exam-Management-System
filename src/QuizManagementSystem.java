import java.util.List;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Quiz & Exam Management System
 * -----------------------------
 * A console-based Java application that lets an Admin build a
 * question bank (MCQs with category & difficulty) and lets Students
 * attempt a timed quiz with shuffled questions, instant scoring,
 * and a saved score history.
 *
 * Concepts demonstrated: OOP (classes, encapsulation), Collections
 * (ArrayList), File Handling (object serialization for persistence),
 * Exception Handling, and simple randomization/timing logic.
 */
public class QuizManagementSystem {

    private static Scanner scanner = new Scanner(System.in);
    private static QuizManager manager = new QuizManager();

    public static void main(String[] args) {
        manager.seedSampleQuestionsIfEmpty();

        boolean running = true;
        while (running) {
            printMainMenu();
            int choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1:
                    adminMenu();
                    break;
                case 2:
                    takeQuiz();
                    break;
                case 3:
                    viewScoreHistory();
                    break;
                case 4:
                    running = false;
                    System.out.println("\nThank you for using the Quiz & Exam Management System. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }

    private static void printMainMenu() {
        System.out.println("\n===================================");
        System.out.println("   QUIZ & EXAM MANAGEMENT SYSTEM");
        System.out.println("===================================");
        System.out.println("1. Admin Menu (Manage Questions)");
        System.out.println("2. Take Quiz (Student)");
        System.out.println("3. View Score History");
        System.out.println("4. Exit");
    }

    // ---------------- Admin section ----------------

    private static void adminMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n----- ADMIN MENU -----");
            System.out.println("1. Add New Question");
            System.out.println("2. View All Questions");
            System.out.println("3. Back to Main Menu");
            int choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1:
                    addQuestion();
                    break;
                case 2:
                    viewAllQuestions();
                    break;
                case 3:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addQuestion() {
        System.out.println("\n--- Add New Question ---");
        scanner.nextLine(); // consume leftover newline
        System.out.print("Enter question text: ");
        String text = scanner.nextLine();
        System.out.print("Enter option A: ");
        String a = scanner.nextLine();
        System.out.print("Enter option B: ");
        String b = scanner.nextLine();
        System.out.print("Enter option C: ");
        String c = scanner.nextLine();
        System.out.print("Enter option D: ");
        String d = scanner.nextLine();

        char correct = readCorrectOption();

        System.out.print("Enter category (e.g. Java, DSA, Aptitude): ");
        String category = scanner.nextLine();
        System.out.print("Enter difficulty (Easy/Medium/Hard): ");
        String difficulty = scanner.nextLine();

        int id = manager.getNextQuestionId();
        Question q = new Question(id, text, a, b, c, d, correct, category, difficulty);
        manager.addQuestion(q);
        System.out.println("Question added successfully with ID " + id + ".");
    }

    private static char readCorrectOption() {
        while (true) {
            System.out.print("Enter correct option (A/B/C/D): ");
            String input = scanner.nextLine().trim().toUpperCase();
            if (input.length() == 1 && "ABCD".contains(input)) {
                return input.charAt(0);
            }
            System.out.println("Invalid input. Please enter one letter: A, B, C, or D.");
        }
    }

    private static void viewAllQuestions() {
        List<Question> all = manager.getAllQuestions();
        if (all.isEmpty()) {
            System.out.println("No questions in the bank yet.");
            return;
        }
        System.out.println("\n--- Question Bank (" + all.size() + " questions) ---");
        for (Question q : all) {
            System.out.println(q);
        }
    }

    // ---------------- Student quiz section ----------------

    private static void takeQuiz() {
        List<Question> all = manager.getAllQuestions();
        if (all.isEmpty()) {
            System.out.println("\nNo questions available yet. Ask the admin to add some first.");
            return;
        }

        scanner.nextLine(); // consume leftover newline
        System.out.print("\nEnter your name: ");
        String studentName = scanner.nextLine();

        List<Question> quizQuestions = manager.getShuffledQuestions();
        int score = 0;

        System.out.println("\nQuiz starting! Answer each question with A, B, C, or D.\n");
        long startTime = System.currentTimeMillis();

        for (Question q : quizQuestions) {
            q.display();
            char answer = readCorrectOption();
            if (q.isCorrect(answer)) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Incorrect. The correct answer was " + q.getCorrectOption() + ".");
            }
        }

        long endTime = System.currentTimeMillis();
        long timeTakenSeconds = (endTime - startTime) / 1000;

        System.out.println("\n----- QUIZ COMPLETE -----");
        System.out.println("Name: " + studentName);
        System.out.println("Score: " + score + " / " + quizQuestions.size());
        System.out.printf("Percentage: %.1f%%\n", (score * 100.0) / quizQuestions.size());
        System.out.println("Time Taken: " + timeTakenSeconds + " seconds");

        String dateTime = new SimpleDateFormat("dd-MM-yyyy HH:mm").format(new Date());
        QuizResult result = new QuizResult(studentName, score, quizQuestions.size(), timeTakenSeconds, dateTime);
        manager.addResult(result);
        System.out.println("Your result has been saved.");
    }

    private static void viewScoreHistory() {
        List<QuizResult> results = manager.getAllResults();
        if (results.isEmpty()) {
            System.out.println("\nNo quiz attempts recorded yet.");
            return;
        }
        System.out.println("\n--- Score History (" + results.size() + " attempts) ---");
        for (QuizResult r : results) {
            System.out.println(r);
        }
    }

    // ---------------- Utility ----------------

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.next();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }
}
