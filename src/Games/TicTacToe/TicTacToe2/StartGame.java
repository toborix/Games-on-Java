package Games.TicTacToe;

import java.util.Random;
import java.util.Scanner;

public class StartGame {

    public StartGame() {

    }

    public static void main(String[] args) {

        char[][] gameBoard = {
                { ' ', '|', ' ', '|', ' ' },
                { '-', '+', '-', '+', '-' },
                { ' ', '|', ' ', '|', ' ' },
                { '-', '+', '-', '+', '-' },
                { ' ', '|', ' ', '|', ' ' } };

        TicTacToe.printGameBoard(gameBoard);
        Scanner console = new Scanner(System.in);

        while (true) {
            System.out.println("Enter your placement (1-9): ");
            int playerPos = console.nextInt();
            while (TicTacToe.playerPositions.contains(playerPos) ||
                    TicTacToe.cpuPositions.contains(playerPos)) {

                System.out.println("Position taken! Enter a correct Position");
                playerPos = console.nextInt();
            }

            TicTacToe.placePiece(gameBoard, playerPos, "player");

            String result = TicTacToe.checkWinner();
            if (result.length() > 0) {
                System.out.println(result);
                break;
            }

            Random rand = new Random();
            int cpuPos = rand.nextInt(9) + 1;

            System.out.println("AI MOVE:" + cpuPos);
            // while (TicTacToe.playerPositions.contains(cpuPos) ||
            // TicTacToe.cpuPositions.contains(cpuPos)) {
            // cpuPos = console.nextInt();

            // }
            TicTacToe.placePiece(gameBoard, cpuPos, "AI");

            TicTacToe.printGameBoard(gameBoard);

            result = TicTacToe.checkWinner();
            if (result.length() > 0) {

                System.out.println(result);

            }

        }
        console.close();
    }
}