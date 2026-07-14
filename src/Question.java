import java.io.Serializable;

/**
 * Represents a single Multiple-Choice Question (MCQ) in the quiz bank.
 * Implements Serializable so questions can be saved to and loaded
 * from disk, giving the application persistent storage.
 */
public class Question implements Serializable {

    private static final long serialVersionUID = 1L;

    private int questionId;
    private String questionText;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private char correctOption; // 'A', 'B', 'C', or 'D'
    private String category;
    private String difficulty; // Easy, Medium, Hard

    public Question(int questionId, String questionText, String optionA, String optionB,
                     String optionC, String optionD, char correctOption,
                     String category, String difficulty) {
        this.questionId = questionId;
        this.questionText = questionText;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.correctOption = Character.toUpperCase(correctOption);
        this.category = category;
        this.difficulty = difficulty;
    }

    public int getQuestionId() {
        return questionId;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String getOptionA() {
        return optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public String getOptionD() {
        return optionD;
    }

    public char getCorrectOption() {
        return correctOption;
    }

    public String getCategory() {
        return category;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public boolean isCorrect(char givenAnswer) {
        return Character.toUpperCase(givenAnswer) == correctOption;
    }

    public void display() {
        System.out.println("\nQ" + questionId + ". " + questionText
                + "  [" + category + " | " + difficulty + "]");
        System.out.println("   A) " + optionA);
        System.out.println("   B) " + optionB);
        System.out.println("   C) " + optionC);
        System.out.println("   D) " + optionD);
    }

    @Override
    public String toString() {
        return "ID: " + questionId + " | " + questionText
                + " | Category: " + category + " | Difficulty: " + difficulty
                + " | Correct: " + correctOption;
    }
}
