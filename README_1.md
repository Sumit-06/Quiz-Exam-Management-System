# Quiz & Exam Management System

A console-based **Java** application for creating, administering, and auto-grading multiple-choice quizzes — built as a Minor Project for B.Tech CSE (6th Semester).

No database, no internet connection, no external dependencies — just core Java (Collections, OOP, File Handling via Object Serialization).

---

## Features

- **Admin Menu** — add unlimited MCQs with 4 options, correct answer, category, and difficulty
- **Randomized Quizzes** — question order shuffled every attempt using `Collections.shuffle()`
- **Instant Auto-Grading** — right/wrong feedback the moment you answer
- **Score History** — every attempt (score, %, time taken, date) is saved permanently
- **Persistent Storage** — data survives program restarts via Java Object Serialization (`.dat` files)
- **Zero Setup** — no database, no internet required; runs anywhere Java runs

---

## Tech Stack

| Component      | Technology                                      |
|-----------------|--------------------------------------------------|
| Language        | Java (JDK 8+)                                    |
| Data Structures  | `ArrayList` (Java Collections Framework)         |
| Persistence      | `ObjectInputStream` / `ObjectOutputStream`       |
| Input Handling   | `Scanner`                                        |
| Randomization    | `Collections.shuffle()`                          |
| Timing           | `System.currentTimeMillis()`                     |

---

## Project Structure

```
QuizManagementSystem/
├── src/
│   ├── Question.java              # MCQ data model
│   ├── QuizResult.java            # Quiz attempt record
│   ├── QuizManager.java           # Core logic + file persistence
│   └── QuizManagementSystem.java  # Main class / console menu
└── README.md
```

---

## How to Run

**Prerequisite:** JDK 8 or above installed ([download here](https://www.oracle.com/java/technologies/downloads/) or install OpenJDK).

```bash
# 1. Clone the repository
git clone https://github.com/yourusername/Quiz-Exam-Management-System.git
cd Quiz-Exam-Management-System/src

# 2. Compile
javac *.java

# 3. Run
java QuizManagementSystem
```

On first run, 5 sample Java/DSA questions are pre-loaded so you can try it out immediately.

---

## Menu Overview

```
1. Admin Menu (Manage Questions)
   1. Add New Question
   2. View All Questions
   3. Back

2. Take Quiz (Student)
   - Enter name → answer shuffled questions → get instant score

3. View Score History
   - See every past attempt with score, %, time, and date

4. Exit
```

---

## Sample Output

```
===================================
   QUIZ & EXAM MANAGEMENT SYSTEM
===================================
1. Admin Menu (Manage Questions)
2. Take Quiz (Student)
3. View Score History
4. Exit
Enter your choice: 2

Enter your name: Sumit Kumar

Q3. What is the time complexity of binary search?  [DSA | Medium]
   A) O(n)
   B) O(n log n)
   C) O(log n)
   D) O(1)
Enter correct option (A/B/C/D): C
Correct!

----- QUIZ COMPLETE -----
Score: 5 / 5   Percentage: 100.0%
```

*(Add your own screenshots here — Main Menu, Admin adding a question, a live quiz question, and the final score screen.)*

---

## Key Concepts Demonstrated

- **Object-Oriented Programming** — encapsulation via private fields + public getters across `Question`, `QuizResult`, and `QuizManager`
- **Collections Framework** — `ArrayList` for storing and querying questions/results
- **File Handling** — Java Object Serialization for persistent storage without a database
- **Exception Handling** — `try-catch` around file I/O and input parsing
- **Randomization & Timing** — `Collections.shuffle()` and `System.currentTimeMillis()`

---

## Future Scope

- Graphical User Interface using JavaFX or Swing
- Admin/Student login authentication
- Migration to a relational database (MySQL)
- Per-question countdown timer
- Support for True/False and fill-in-the-blank question types
- Downloadable PDF score reports

---

## Author

**Sumit Kumar**
B.Tech CSE, 6th Semester — IES College of Technology
Enrollment No.: 0177CS231183

---

## License

This project was created for academic purposes as part of a Minor Project submission.
