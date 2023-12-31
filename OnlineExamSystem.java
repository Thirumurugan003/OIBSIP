import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void updateProfile(Scanner scanner) {
        System.out.println("Enter new username: ");
        this.username = scanner.nextLine();
    }

    public void updatePassword(Scanner scanner) {
        System.out.println("Enter new password: ");
        this.password = scanner.nextLine();
    }
}

class Exam {
    private String subject;
    private List<String> questions;
    private List<String> answers;
    private int score;

    public Exam(String subject) {
        this.subject = subject;
        this.questions = new ArrayList<>();
        answers = new ArrayList<>();
        score = 0;
    }

    public String getSubject() {
        return subject;
    }

    public void addQuestion(String question) {
        questions.add(question);
    }

    public void addAnswer(String answer) {
        answers.add(answer);
    }

    public void start(User user, Scanner scanner) {
        System.out.println("\n--- " + subject + " Exam ---");
        System.out.println("Hello, " + user.getUsername() + "!");

        for (int i = 0; i < questions.size(); i++) {
            System.out.println("\nQuestion " + (i + 1) + ":");
            System.out.println(questions.get(i));
            System.out.print("Your answer: ");
            String userAnswer = scanner.nextLine();
            checkAnswer(userAnswer, i);
        }

        System.out.println("\nExam completed!");
        System.out.println("Your score in " + subject + " exam: " + score + " out of " + questions.size());
    }

    private void checkAnswer(String userAnswer, int questionIndex) {
        String correctAnswer = answers.get(questionIndex);

        if (userAnswer.equalsIgnoreCase(correctAnswer)) {
            System.out.println("Correct!");
            score++;
        } else {
            System.out.println("Incorrect!");
        }
    }
}

public class OnlineExamSystem {
    private List<Exam> exams;
    private User currentUser;

    public OnlineExamSystem() {
        exams = new ArrayList<>();
        currentUser = null;
        initializeExams();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            if (currentUser == null) {
                showLoginMenu();
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        login(scanner);
                        break;
                    case 2:
                        registerUser(scanner);
                        break;
                    case 3:
                        System.out.println("Thank you for using the online examination system. Goodbye!");
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            } else {
                showMainMenu();
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        updateProfile(scanner);
                        break;
                    case 2:
                        updatePassword(scanner);
                        break;
                    case 3:
                        startExam(scanner);
                        break;
                    case 4:
                        logout();
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            }
        }
    }

    private void showLoginMenu() {
        System.out.println("Welcome to the Online Examination System!");
        System.out.println("Please select an option:");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("3. Quit");
        System.out.print("Enter your choice: ");
    }

    private void showMainMenu() {
        if (currentUser != null) {
            System.out.println("\nWelcome, " + currentUser.getUsername() + "!");
            System.out.println("Please select an option:");
            System.out.println("1. Update Profile");
            System.out.println("2. Update Password");
            System.out.println("3. Start Exam");
            System.out.println("4. Logout");
            System.out.print("Enter your choice: ");
        }
    }

    private void login(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        // Simulate login logic (replace with your actual logic)
        User user = new User(username, password);

        if (user != null && user.getPassword().equals(password)) {
            currentUser = user;
            System.out.println("Login successful!");
        } else {
            System.out.println("Invalid username or password. Please try again.");
        }
    }

    private void registerUser(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        // Simulate registration logic (replace with your actual logic)
        User user = new User(username, password);

        if (user != null) {
            currentUser = user;
            System.out.println("Registration successful! You can now login.");
        } else {
            System.out.println("Username already exists. Please choose a different username.");
        }
    }

    private void updateProfile(Scanner scanner) {
        if (currentUser != null) {
            currentUser.updateProfile(scanner);
        }
    }

    private void updatePassword(Scanner scanner) {
        if (currentUser != null) {
            currentUser.updatePassword(scanner);
        }
    }

    private void startExam(Scanner scanner) {
        if (currentUser != null) {
            System.out.println("Available Exams:");
            for (int i = 0; i < exams.size(); i++) {
                System.out.println((i + 1) + ". " + exams.get(i).getSubject());
            }
            System.out.print("Enter the exam number: ");
            int examNumber = scanner.nextInt();
            scanner.nextLine();

            if (examNumber >= 1 && examNumber <= exams.size()) {
                Exam selectedExam = exams.get(examNumber - 1);
                selectedExam.start(currentUser, scanner);
            } else {
                System.out.println("Invalid exam number. Please try again.");
            }
        }
    }

    private void logout() {
        currentUser = null;
        System.out.println("Logout successful!");
    }

    private void initializeExams() {
        Exam generalKnowledgeExam = new Exam("General Knowledge");
        generalKnowledgeExam.addQuestion("Which famous scientist developed the theory of relativity?");
        generalKnowledgeExam.addAnswer("Albert Einstein");
        generalKnowledgeExam.addQuestion("What is the currency used in Japan?");
        generalKnowledgeExam.addAnswer("Japanese Yen (JPY)");
        generalKnowledgeExam.addQuestion("What is the tallest mountain in the world?");
        generalKnowledgeExam.addAnswer("Mount Everest");
        generalKnowledgeExam.addQuestion("In which city is the Eiffel Tower located?");
        generalKnowledgeExam.addAnswer("Paris");
        generalKnowledgeExam.addQuestion("Who painted the famous artwork \"Starry Night\"");
        generalKnowledgeExam.addAnswer(" Vincent van Gogh");

        Exam scienceExam = new Exam("Science");
        scienceExam.addQuestion("What is the chemical symbol for the element oxygen?");
        scienceExam.addAnswer("O");
        scienceExam.addQuestion("What is the process by which plants convert light energy into chemical energy?");
        scienceExam.addAnswer("Photosynthesis");
        scienceExam.addQuestion("What is the smallest unit of an element that retains its chemical properties?");
        scienceExam.addAnswer("Atom");
        scienceExam.addQuestion("What is the process by which a liquid changes into a gas at its boiling point?");
        scienceExam.addAnswer("Boiling");
        scienceExam.addQuestion("What is the study of the Earth's atmosphere and weather patterns called?");
        scienceExam.addAnswer("Meteorology");

        exams.add(generalKnowledgeExam);
        exams.add(scienceExam);
    }

    public static void main(String[] args) {
        OnlineExamSystem examSystem = new OnlineExamSystem();
        examSystem.start();
    }
}
