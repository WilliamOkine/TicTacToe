 import java.util.Scanner;

public class TikTakToe {
    private static final int row = 3;
    private static final int col = 3;
    private static String board[][] = new String[row][col];
    private String[][] players = {{"Player 1", "X"}, {"Player 2", "O"}};
    private int currentPlayer = 0;

    public boolean isBoardFull() {
        for (String[] row : board) {
            for (String col : row) {
                if (col == null) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean gameIsWon() {
        // Checking rows, columns, and diagonals for a win
        for (int i = 0; i < row; i++) {
            if (board[i][0] != null && board[i][0].equals(board[i][1]) && board[i][0].equals(board[i][2])) {
                return true; // row check
            }
            if (board[0][i] != null && board[0][i].equals(board[1][i]) && board[0][i].equals(board[2][i])) {
                return true; // column check
            }
        }

        // Diagonal checks
        if (board[0][0] != null && board[0][0].equals(board[1][1]) && board[0][0].equals(board[2][2])) {
            return true; // top-left to bottom-right diagonal
        }
        if (board[0][2] != null && board[0][2].equals(board[1][1]) && board[0][2].equals(board[2][0])) {
            return true; // top-right to bottom-left diagonal
        }

        return false; // no win condition
    }

    public void printBoard() {
        for (String[] row : board) {
            for (String col : row) {
                System.out.print("|" + (col == null ? " " : col) + "|");
            }
            System.out.println("\n---------");
        }
    }

    public void play() {
        try (Scanner scanner = new Scanner(System.in)) {
            while (!isBoardFull()) {
                boolean valid = false;

                // Loop until a certain player makes a valid move
                while (!valid) {
                    System.out.print(players[currentPlayer][0] + ", choose your row: \n> ");
                    int row = scanner.nextInt() - 1;

                    System.out.print(players[currentPlayer][0] + ", you have chosen row " + (row + 1) + ". Choose your column: \n> ");
                    int col = scanner.nextInt() - 1;

                    if (row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == null) {
                        board[row][col] = players[currentPlayer][1];
                        printBoard();

                        if (gameIsWon()) {
                            System.out.println(players[currentPlayer][0] + " wins!");
                            return;
                        }

                        currentPlayer = (currentPlayer + 1) % 2; // Switch player after a valid move
                        valid = true;
                    } else {
                        System.out.println("Invalid move, try again!");
                    }
                }
            }

            System.out.println("Draw!");
        }
    }

    public static void main(String[] args) {
        TikTakToe game = new TikTakToe();
        game.play();
    }
}
