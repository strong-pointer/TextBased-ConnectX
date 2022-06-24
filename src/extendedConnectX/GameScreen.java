package src.extendedConnectX;

import java.util.Scanner;

public class GameScreen {
    /**
     * @pre None
     * @param args command line arguments of String type
     * @post [implementing the game itself; prompting players of the game]
     */
    // Controls the flow of the game like alternating players and prompting them
    public static void main(String[] args) {
        IGameBoard board = null;
        int columnSelect;
        char player;
        Scanner scan = new Scanner(System.in);
        boolean selection = false;
        char playAgain = 'y';

        // Loop that tests for the 'y' character to determine if the game is to be played again
        while (playAgain == 'y' || playAgain == 'Y') {
            // Setting these values to trip each loop the first time around
            int numRows = 101;
            int numColumns = 101;
            int numToWin = 26;
            int playerCount = -1;
            int turnNumber = 0;
            char testChar;
            char speedSelection = 'Z';

            // Loop that gets the amount of players
            while (playerCount < 2 || playerCount > 10) {
                System.out.println("How many players?");
                playerCount = scan.nextInt();
                if (playerCount > 10) {
                    System.out.println("Must be 10 players or fewer");
                }
                if (playerCount < 2) {
                    System.out.println("Must be at least 2 players");
                }
            }
            // Character array that stores each player in order
            char[] players = new char[playerCount];
            // Loop that gets the character to represent each player
            for (int i = 1; i < playerCount + 1; i++) {
                boolean sameChar = true;
                System.out.println("Enter the character to represent player " + i);
                testChar = scan.next().charAt(0);
                testChar = Character.toUpperCase(testChar);
                // Loop makes sure that the character entered is not a duplicate
                while (sameChar) {
                    for (int j = 0; j < playerCount; j++) {
                        if (players[j] == testChar) {
                            System.out.println(testChar + " is already taken as a player token!");
                            System.out.println("Enter the character to represent player " + i);
                            testChar = scan.next().charAt(0);
                            testChar = Character.toUpperCase(testChar);
                            break;
                        }
                        else {
                            sameChar = false;
                        }
                    }
                }
                sameChar = true;
                players[i-1] = testChar;
            }

            // Gets the rows, columns, and number to win, and confirms they are within the mins/maxes
            while (numRows > 100 || numRows < 3) {
                System.out.println("How many rows should be on the board?");
                numRows = scan.nextInt();
            }
            while (numColumns > 100 || numColumns < 3) {
                System.out.println("How many columns should be on the board?");
                numColumns = scan.nextInt();
            }
            while (numToWin > 25 || numToWin > numColumns || numToWin > numRows || numToWin < 3) {
                System.out.println("How many in a row to win?");
                numToWin = scan.nextInt();
            }

            columnSelect = -1;
            player = players[turnNumber];
            while (speedSelection != 'f' && speedSelection != 'F' && speedSelection != 'm' && speedSelection != 'M') {
                System.out.println("Would you like a Fast Game (F/f) or a Memory Efficient Game (M/m)?");
                speedSelection = scan.next().charAt(0);
                if (speedSelection != 'f' && speedSelection != 'F' && speedSelection != 'm' && speedSelection != 'M') {
                    System.out.println("Please enter F or M");
                }
            }
            if (speedSelection == 'f' || speedSelection == 'F') {
                board = new GameBoard(numRows, numColumns, numToWin);
            }
            else {
                board = new GameBoardMem(numRows, numColumns, numToWin);
            }

            System.out.println(board.toString());

            // Loop for checking if the game is to end before switching to the next player
            while (!board.checkTie() && !board.checkForWin(columnSelect)) {

                // Makes sure the column selection is valid (0-8)
                while (!selection) {
                    System.out.println("Player " + player + ", what column do you want to place your marker in?");
                    columnSelect = scan.nextInt();
                    if (columnSelect < 0) {
                        System.out.println("Column cannot be less than " + (board.getNumColumns() - 1));
                    }
                    else if (columnSelect > (board.getNumColumns() - 1)) {
                        System.out.println("Column cannot be greater than " + (board.getNumColumns() - 1));
                    }
                    else if (board.checkIfFree(columnSelect)) {
                        selection = true;
                    }
                    else {
                        System.out.println("Column is full");
                    }
                }
                board.placeToken(player, columnSelect);


                System.out.println(board.toString());

                // Checks for wins here
                if (board.checkForWin(columnSelect)) {
                    System.out.println("Player " + player + " Won!");
                }
                else if (board.checkTie()) {
                    System.out.println("Game has ended in a tie!");
                }

                selection = false;
                // Switches players each loop
                if (turnNumber == playerCount - 1) {
                    turnNumber = 0;
                }
                else {
                    turnNumber++;
                }
                player = players[turnNumber];
            }

            playAgain = 'Z';
            // Asks if the players want to play again until yes or no is properly answered
            while (playAgain != 'y' && playAgain != 'Y' && playAgain != 'N' && playAgain != 'n') {
                System.out.println("Would you like to play again? Y/N");
                playAgain = scan.next().charAt(0);
            }
        }
    }
}
