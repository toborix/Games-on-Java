package Games.TicTacToe;

import java.util.*;

public class TicTacToe {
    static ArrayList<Integer> playerPositions = new ArrayList<>();
    static ArrayList<Integer> cpuPositions = new ArrayList<>();

    public static void main(String[] args) {

        char[][] gameBoard = {
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '}};

       printGameBoard(gameBoard);

        while(true){
            Scanner console = new Scanner(System.in);
            System.out.println("Enter your placement (1-9): ");
            int playerPos = console.nextInt();
            while(playerPositions.contains(playerPos) || cpuPositions.contains(playerPositions)){
                System.out.println("Position taken! Enter a correct Position");
                playerPos = console.nextInt();
            }

            placePiece(gameBoard, playerPos, "player" );

            String result = checkWinner();
            if(result.length() > 0){
                System.out.println(result);
                break;
            }

            Random rand = new Random();
            int cpuPos = rand.nextInt(9) + 1;
            while(playerPositions.contains(cpuPos) || cpuPositions.contains(cpuPositions)){
                cpuPos = console.nextInt();
            }
            placePiece(gameBoard, cpuPos, "AI" );

            printGameBoard(gameBoard);

            result = checkWinner();
            if(result.length() > 0){
                System.out.println(result);
                break;
            }


        }
    }

    public static void printGameBoard(char[][] gameBoard){
        for(char[] row : gameBoard){
            for(char c : row){
                System.out.print(c);
            }
            System.out.println();
        }
    }
    public static void placePiece(char[][] gameBoard, int pos, String user){

        char symbol = ' ';

        if (user.equals("player")){
            symbol = 'X';
            playerPositions.add(pos);
        } else if(user.equals("AI")){
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
        List topRow = Arrays.asList(1,2,3);
        List midRow = Arrays.asList(4,5,6);
        List botRow = Arrays.asList(7,8,9);

        List leftCol = Arrays.asList(1,4,7);
        List midCol = Arrays.asList(2,5,8);
        List rightCol = Arrays.asList(3,6,9);

        List crossLine_1 = Arrays.asList(1,5,9);
        List crossLine_2 = Arrays.asList(7,5,3);

        List<List> winningConditions = new ArrayList<>();
        winningConditions.add(topRow);
        winningConditions.add(midRow);
        winningConditions.add(botRow);

        winningConditions.add(leftCol);
        winningConditions.add(midCol);
        winningConditions.add(rightCol);

        winningConditions.add(crossLine_1);
        winningConditions.add(crossLine_2);

        for (List l : winningConditions) {
            if(playerPositions.containsAll(l)){
                return "Congratulations you won!";
            }else if(cpuPositions.contains(l)) {
                return "AI wins! Sorry :( ";
            }else if(playerPositions.size() + cpuPositions.size() == 9) {
                return "CAT!";
            }
        }




        return "";
    }

}
