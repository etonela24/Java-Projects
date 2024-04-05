import java.io.PrintStream;
import java.util.Scanner;

/**
 * The QuestionTree class represents a binary tree for playing a question-guessing game.
 * It allows the user to play the game, save the current state of the tree to a file,
 * and load a previously saved tree from a file.
 * @Author: Ornela Amouzou-Adoun
 */
public class QuestionTree {
    private UserInterface ui;
    private QuestionNode overallRoot;
    private int totalGames;
    private int gamesWon;

    /**
     * Constructs a QuestionTree object with the specified UserInterface.
     *
     * @param ui the UserInterface to use for input and output
     * @throws IllegalArgumentException if the UserInterface is null
     */
    public QuestionTree(UserInterface ui) {
        if (ui == null) {
            throw new IllegalArgumentException("UserInterface cannoNodet be null");
        }
        this.ui = ui;
        this.overallRoot = new QuestionNode("Jedi");
        this.totalGames = 0;
        this.gamesWon = 0;
    }

    /**
     * Plays the question-guessing game starting from the overallRoot noNodede.
     * Updates the game statistics based on the user's responses.
     */
    public void play() {
        overallRoot = playNode(overallRoot);
        totalGames++;
    }

    /**
     * Recursive helper method for playing the question-guessing game.
     * Handles the logic for asking questions, making guesses, and learning new information.
     *
     * @param current the current noNodede in the game tree
     * @return the updated current noNodede after playing the game
     */
    private QuestionNode playNode(QuestionNode current) {
        if (current.isAnswer()) {
            ui.print("Would your object happen to be " + current.data + "? ");
            if (ui.nextBoolean()) {
                ui.println("I win!");
                gamesWon++;
            } else {
                ui.print("I lose. What is your object? ");
                String newAnswer = ui.nextLine().trim();
                ui.print("Type a yes/no question to distinguish your item from " + current.data + ": ");
                String newQuestion = ui.nextLine().trim();
                ui.print("And what is the answer for your object? ");
                boolean newAnswerYes = ui.nextBoolean();

                QuestionNode newQuestionNode = new QuestionNode(newQuestion);
                QuestionNode newAnswerNode = new QuestionNode(newAnswer);
                if (newAnswerYes) {
                    newQuestionNode.yesNode = newAnswerNode;
                    newQuestionNode.noNode = current;
                } else {
                    newQuestionNode.yesNode = current;
                    newQuestionNode.noNode = newAnswerNode;
                }

                return newQuestionNode;
            }
        } else {
            ui.print(current.data + " ");
            if (ui.nextBoolean()) {
                current.yesNode = playNode(current.yesNode);
            } else {
                current.noNode = playNode(current.noNode);
            }
        }
        return current;
    }

    /**
     * Saves the current state of the game tree to the specified PrintStream.
     *
     * @param output the PrintStream to save the tree to
     */
    public void save(PrintStream output) {
        saveNode(output, overallRoot);
    }

    /**
     * Recursive helper method for saving the game tree to a PrintStream.
     * Uses a pre-order traversal to save each noNodede to the output stream.
     *
     * @param output  the PrintStream to save the tree to
     * @param current the current noNodede being saved
     */
    private void saveNode(PrintStream output, QuestionNode current) {
        if (current.isAnswer()) {
            output.println("A:" + current.data);
        } else {
            output.println("Q:" + current.data);
            saveNode(output, current.yesNode);
            saveNode(output, current.noNode);
        }
    }

    /**
     * Loads a previously saved game tree from the specified Scanner.
     *
     * @param input the Scanner to load the tree from
     */
    public void load(Scanner input) {
        overallRoot = loadHelper(input);
    }

    /**
     * Recursive helper method for loading a game tree from a Scanner.
     * Uses a pre-order traversal to reconstruct the tree from the input.
     *
     * @param input the Scanner to load the tree from
     * @return the root noNodede of the loaded tree
     * @throws IllegalArgumentException if the input file format is invalid
     */
    private QuestionNode loadHelper(Scanner input) {
        String line = input.nextLine();
        String[] parts = line.split(":", 2);
        String type = parts[0];
        String data = parts[1];

        QuestionNode current;
        if (type.equals("A")) {
            current = new QuestionNode(data);
        } else if (type.equals("Q")) {
            current = new QuestionNode(data);
            current.yesNode = loadHelper(input);
            current.noNode = loadHelper(input);
        } else {
            throw new IllegalArgumentException("Invalid input file format");
        }

        return current;
    }

    /**
     * Returns the total number of games played.
     *
     * @return the total number of games played
     */
    public int totalGames() {
        return totalGames;
    }

    /**
     * Returns the number of games won by the computer.
     *
     * @return the number of games won by the computer
     */
    public int gamesWon() {
        return gamesWon;
    }
}
