import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Core class that manages the question bank and quiz results.
 * Handles adding questions, running a quiz (with shuffled question
 * order and per-question timing), scoring, and saving/loading data
 * to disk so records persist between runs.
 */
public class QuizManager {

    private List<Question> questions;
    private List<QuizResult> results;

    private static final String QUESTIONS_FILE = "questions.dat";
    private static final String RESULTS_FILE = "results.dat";

    public QuizManager() {
        questions = loadQuestions();
        results = loadResults();
    }

    // ---------------- Question bank operations ----------------

    public void addQuestion(Question q) {
        questions.add(q);
        saveQuestions();
    }

    public List<Question> getAllQuestions() {
        return questions;
    }

    public int getNextQuestionId() {
        return questions.size() + 1;
    }

    public List<Question> getQuestionsByCategory(String category) {
        List<Question> filtered = new ArrayList<>();
        for (Question q : questions) {
            if (q.getCategory().equalsIgnoreCase(category)) {
                filtered.add(q);
            }
        }
        return filtered;
    }

    /**
     * Returns a shuffled copy of the question bank so each quiz
     * attempt presents questions in a different order.
     */
    public List<Question> getShuffledQuestions() {
        List<Question> shuffled = new ArrayList<>(questions);
        Collections.shuffle(shuffled);
        return shuffled;
    }

    // ---------------- Result operations ----------------

    public void addResult(QuizResult result) {
        results.add(result);
        saveResults();
    }

    public List<QuizResult> getAllResults() {
        return results;
    }

    // ---------------- Persistence (file handling) ----------------

    @SuppressWarnings("unchecked")
    private List<Question> loadQuestions() {
        File file = new File(QUESTIONS_FILE);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (List<Question>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Warning: Could not load questions.dat, starting fresh. (" + e.getMessage() + ")");
            return new ArrayList<>();
        }
    }

    @SuppressWarnings("unchecked")
    private List<QuizResult> loadResults() {
        File file = new File(RESULTS_FILE);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (List<QuizResult>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Warning: Could not load results.dat, starting fresh. (" + e.getMessage() + ")");
            return new ArrayList<>();
        }
    }

    private void saveQuestions() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(QUESTIONS_FILE))) {
            oos.writeObject(questions);
        } catch (IOException e) {
            System.out.println("Error saving questions: " + e.getMessage());
        }
    }

    private void saveResults() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(RESULTS_FILE))) {
            oos.writeObject(results);
        } catch (IOException e) {
            System.out.println("Error saving results: " + e.getMessage());
        }
    }

    /**
     * Seeds the question bank with a starter set of sample questions
     * the first time the program runs (so the app is demo-ready
     * even before the admin adds their own questions).
     */
    public void seedSampleQuestionsIfEmpty() {
        if (!questions.isEmpty()) {
            return;
        }
        questions.add(new Question(1, "Which keyword is used to inherit a class in Java?",
                "extend", "extends", "inherits", "implements", 'B', "Java", "Easy"));
        questions.add(new Question(2, "Which data structure uses FIFO order?",
                "Stack", "Queue", "Tree", "Graph", 'B', "DSA", "Easy"));
        questions.add(new Question(3, "What is the time complexity of binary search?",
                "O(n)", "O(n log n)", "O(log n)", "O(1)", 'C', "DSA", "Medium"));
        questions.add(new Question(4, "Which of these is NOT an OOP concept?",
                "Inheritance", "Encapsulation", "Compilation", "Polymorphism", 'C', "Java", "Easy"));
        questions.add(new Question(5, "Which collection class does NOT allow duplicate elements?",
                "ArrayList", "LinkedList", "HashSet", "Vector", 'C', "Java", "Medium"));
        saveQuestions();
    }
}
