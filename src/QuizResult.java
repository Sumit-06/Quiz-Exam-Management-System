import java.io.Serializable;

/**
 * Represents the result of one quiz attempt by a student.
 * Stored persistently so score history can be reviewed later.
 */
public class QuizResult implements Serializable {

    private static final long serialVersionUID = 1L;

    private String studentName;
    private int score;
    private int totalQuestions;
    private long timeTakenSeconds;
    private String dateTime;

    public QuizResult(String studentName, int score, int totalQuestions,
                       long timeTakenSeconds, String dateTime) {
        this.studentName = studentName;
        this.score = score;
        this.totalQuestions = totalQuestions;
        this.timeTakenSeconds = timeTakenSeconds;
        this.dateTime = dateTime;
    }

    public String getStudentName() {
        return studentName;
    }

    public int getScore() {
        return score;
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public long getTimeTakenSeconds() {
        return timeTakenSeconds;
    }

    public String getDateTime() {
        return dateTime;
    }

    public double getPercentage() {
        return (score * 100.0) / totalQuestions;
    }

    @Override
    public String toString() {
        return String.format("%-15s | Score: %2d/%-2d (%.1f%%) | Time: %ds | %s",
                studentName, score, totalQuestions, getPercentage(), timeTakenSeconds, dateTime);
    }
}
