package Games.TicTacToe.TicTacToe2;

import javax.swing.*;
import java.util.*;
import java.util.stream.IntStream;

enum PLAYER {
    HUMAN(1),
    AI(2);

    private final int value;

    PLAYER(final int newValue) {
        value = newValue;
    }

    public int getValue() { return value; }
}


class HasWinnerException extends RuntimeException {
    public HasWinnerException(String errorMessage) {
        super(errorMessage);
    }
}

class HasDrawException extends RuntimeException {
    public HasDrawException(String errorMessage) {
        super(errorMessage);
    }
}

class PositionTakenException extends RuntimeException {
    public PositionTakenException(String errorMessage) {
        super(errorMessage);
    }
}

public class GameBoard {
    int boardSize;
    List<Integer> playersPositions;

    private static PLAYER current_move = PLAYER.HUMAN;

    public GameBoard(int boardSize) {
        this.boardSize = boardSize;
        this.playersPositions = this.generatePlayersPositions();
    }

    private List<Integer> generatePlayersPositions() {
        return new ArrayList<Integer>(Collections.nCopies(this.boardSize *
                this.boardSize, 0));
    }

    public PLAYER getCurrent_move() {
        return current_move;
    }

    public void setCurrent_move() {
        current_move = current_move == PLAYER.HUMAN ? PLAYER.AI : PLAYER.HUMAN;
    }

    public void printBoard() {
        int[][] all_rows = getAllRows();
        for (int[] row : all_rows) {
            for (int c : row) {
                System.out.print(c);
                System.out.print('|');
            }
            System.out.println();
        }
    }

    public void makeMove(int pos) throws HasWinnerException, PositionTakenException, HasDrawException {
        if(playersPositions.get(pos) == 0) {
            playersPositions.set(pos, current_move.getValue());
            if (hasWinner()) {
                throw new HasWinnerException("We have a winner! " + current_move.name());
            }
            if (isDraw()) {
                throw new HasDrawException("It's a draw!");
            }
            setCurrent_move();
        } else {
            throw new PositionTakenException("The cell is occupied, choose another cell to make a move");
        }
    }

    public boolean hasWinner() {
        int[][] all_rows = getAllRows();
        int[][] all_cols = getAllCols();
        int[][] all_diagonals = getAllDiagonals();

        boolean row_winner = checkWinningMatch(all_rows, current_move);
        boolean col_winner = checkWinningMatch(all_cols, current_move);
        boolean dia_winner = checkWinningMatch(all_diagonals, current_move);

        return row_winner || col_winner || dia_winner;
    }

    public boolean isDraw() {
        return playersPositions.stream().noneMatch(n -> n == 0);
    }

    private boolean checkWinningMatch(int[][] data, PLAYER player) {
        return Arrays.stream(data)
                .anyMatch(arr -> Arrays.stream(arr).allMatch(n -> n == player.getValue()) );
    }

    private int[][] getAllRows() {
        int[][] rows = new int[boardSize][boardSize];

        for (int i = 0; i < boardSize; ++i) {
            for (int j = 0; j < boardSize; ++j) {
                rows[i][j] = playersPositions.get(i*boardSize + j);
            }
        }
        return rows;
    }

    private int[][] getAllCols() {
        int[][] allCols = new int[boardSize][boardSize];
        for (int i = 0; i < boardSize; ++i) {
            for (int j = 0; j < boardSize; ++j) {
                int idx = i + j * boardSize ;
                allCols[i][j] = playersPositions.get(idx);
            }
        }
        return allCols;

    }

    private int[][] getAllDiagonals() {
        int[][] diagonals = new int[2][boardSize];
        int j = 0;
        for (int i = 0; i < playersPositions.size(); i = i+boardSize+1) {
            diagonals[0][j] = playersPositions.get(i);
            ++j;
        }

        j=0;
        for (int i = boardSize-1; i < playersPositions.size(); i = i+boardSize-1) {
            if (j < boardSize) {
                diagonals[1][j] = playersPositions.get(i);
            }
            ++j;
        }
        return diagonals;
    }



    public static void main(String[] args) throws PositionTakenException {

        Scanner console = new Scanner(System.in);

        System.out.println("Starting a Game...");
        System.out.println("how many rows and cols, min:3, max:15");
        GameBoard board = new GameBoard(console.nextInt());


        int position;

        while (true) {
            board.printBoard();
            if(current_move.equals(PLAYER.HUMAN)) {
                System.out.println("Enter your placement, max: " + board.boardSize);
                position = console.nextInt();
            } else {
                System.out.println("AI will make its placement: ");
                Random rand = new Random();
                position = rand.nextInt(board.boardSize*board.boardSize) + 1;
            }

            try {
                board.makeMove(position);
            } catch (HasWinnerException e) {
                System.out.println("We have a winner!");
                board.printBoard();
                break;
            } catch (HasDrawException e) {
                System.out.println(e.getMessage());
                break;
            } catch (PositionTakenException e) {
                System.out.println("Position is taken, Choose another PLACEMENT!");
            }
        }
        console.close();
    }



    }


