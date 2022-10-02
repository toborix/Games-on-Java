package Games.TicTacToe.TicTacToe2;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class TicTacToe {
    static List<Integer> playerPositions = new ArrayList<>();
    static List<Integer> cpuPositions = new ArrayList<>();

    public static void printGameBoard(char[][] gameBoard) {
        for (char[] row : gameBoard) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    public static void placePiece(char[][] gameBoard, int pos, @NotNull String user) {

        char symbol = ' ';

        if (user.equals("player")) {
            symbol = 'X';
            playerPositions.add(pos);
        } else if (user.equals("AI")) {
            symbol = 'O';
            cpuPositions.add(pos);
        }
        switch (pos) {
            case 1 -> gameBoard[0][0] = symbol;
            case 2 -> gameBoard[0][2] = symbol;
            case 3 -> gameBoard[0][4] = symbol;
            case 4 -> gameBoard[2][0] = symbol;
            case 5 -> gameBoard[2][2] = symbol;
            case 6 -> gameBoard[2][4] = symbol;
            case 7 -> gameBoard[4][0] = symbol;
            case 8 -> gameBoard[4][2] = symbol;
            case 9 -> gameBoard[4][4] = symbol;
            default -> {
            }
        }

    }

    public static String checkWinner() {
        List<Integer> topRow = Arrays.asList(1, 2, 3);
        List<Integer> midRow = Arrays.asList(4, 5, 6);
        List<Integer> botRow = Arrays.asList(7, 8, 9);

        List<Integer> leftCol = Arrays.asList(1, 4, 7);
        List<Integer> midCol = Arrays.asList(2, 5, 8);
        List<Integer> rightCol = Arrays.asList(3, 6, 9);

        List<Integer> crossLine_1 = Arrays.asList(1, 5, 9);
        List<Integer> crossLine_2 = Arrays.asList(7, 5, 3);

        List<List<Integer>> winningConditions = new ArrayList<>();
        winningConditions.add(topRow);
        winningConditions.add(midRow);
        winningConditions.add(botRow);

        winningConditions.add(leftCol);
        winningConditions.add(midCol);
        winningConditions.add(rightCol);

        winningConditions.add(crossLine_1);
        winningConditions.add(crossLine_2);

        String result = "";
        for (List<Integer> l : winningConditions) {
            if (new HashSet<>(playerPositions).containsAll(l)) {
                result = "Congratulations you won!";
            } else if (new HashSet<>(cpuPositions).containsAll(l)) {
                result = "AI wins! Sorry :( ";
            } else if (playerPositions.size() + cpuPositions.size() == 9) {
                result = "Draw!";
            }
        }

        return result;
    }

}
