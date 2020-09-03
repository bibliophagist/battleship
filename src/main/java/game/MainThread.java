package game;

import game.exception.GameCouldNotBeCreatedException;
import game.initializer.BoardInitializer;
import game.objects.GameBoard;
import game.objects.Shot;

import java.util.Scanner;

//TODO this class need improvements
public class MainThread extends Thread {

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        BoardInitializer boardInitializer = new BoardInitializer();
        boolean shipsWasPlaced = false;
        boolean gameIsOver = false;
        GameBoard playerOneGameBoard = null;
        GameBoard playerTwoGameBoard = null;
        System.out.println("Please input game board size. Example 10*10");
        String gameBoardSize = scanner.nextLine();
        while (!shipsWasPlaced) {

            try {
                System.out.println("Player 1 please input you ship placement." +
                        " Example: А1 Б1, Г1 Г2 Г3, Ж1 З1 И1 К1, К3 К4, Б5 Б6 Б7, Ж6, Г9, Е9, И9 И10, А10");
                playerOneGameBoard = boardInitializer.createGameBoard(scanner.nextLine(), gameBoardSize);
            } catch (GameCouldNotBeCreatedException gameCouldNotBeCreatedException) {
                System.out.println(gameCouldNotBeCreatedException.getMessage());
            }

            if (playerOneGameBoard != null) {
                try {
                    System.out.println("Player 2 please input you ship placement." +
                            " Example: А1 Б1, Г1 Г2 Г3, Ж1 З1 И1 К1, К3 К4, Б5 Б6 Б7, Ж6, Г9, Е9, И9 И10, А10");
                    playerTwoGameBoard = boardInitializer.createGameBoard(scanner.nextLine(), gameBoardSize);
                } catch (GameCouldNotBeCreatedException gameCouldNotBeCreatedException) {
                    System.out.println(gameCouldNotBeCreatedException.getMessage());
                }
            }
            if (playerOneGameBoard != null && playerTwoGameBoard != null) {
                shipsWasPlaced = true;
            }
        }
        playerOneGameBoard.setPlayerShotPlacement(playerTwoGameBoard);
        playerTwoGameBoard.setPlayerShotPlacement(playerOneGameBoard);
        playerOneGameBoard.printShipPlacement();
        playerTwoGameBoard.printShipPlacement();

        // TODO while block are actually the same make a single method of them with parameter "player"
        while (!Thread.currentThread().isInterrupted()) {
            boolean playerOneMissedOrGameOver = false;
            while (!playerOneMissedOrGameOver) {
                System.out.println("Player 1 takes a shot");
                String playerOneShot = scanner.nextLine();
                System.out.printf("Player 1 shot was: %s%n", playerOneShot);
                Shot shot = playerTwoGameBoard.addShotPlacement(playerOneShot);
                if (shot.isSuccessfulHit()) {
                    System.out.println("Player 1 scored a hit");
                    if (shot.isShipWasDestroyed()) {
                        System.out.println("Player 1 destroyed a ship");
                    }
                    if (playerTwoGameBoard.allShipsDestroyed()) {
                        System.out.println("All ships of Player 2 were destroyed");
                        playerOneMissedOrGameOver = true;
                        gameIsOver = true;
                    }
                } else {
                    System.out.println("Player 1 missed");
                    playerOneMissedOrGameOver = true;
                }
                playerOneGameBoard.printShipPlacement();
            }
            boolean playerTwoMissedOrGameOver = gameIsOver;
            while (!playerTwoMissedOrGameOver) {
                System.out.println("Player 2 takes a shot");
                String playerTwoShot = scanner.nextLine();
                System.out.printf("Player 2 shot was: %s%n", playerTwoShot);
                Shot shot = playerOneGameBoard.addShotPlacement(playerTwoShot);
                if (shot.isSuccessfulHit()) {
                    System.out.println("Player 2 scored a hit");
                    if (shot.isShipWasDestroyed()) {
                        System.out.println("Player 2 destroyed a ship");
                    }
                    if (playerOneGameBoard.allShipsDestroyed()) {
                        System.out.println("All ships of Player 1 were destroyed");
                        playerTwoMissedOrGameOver = false;
                        gameIsOver = true;
                    }
                } else {
                    System.out.println("Player 2 missed");
                    playerTwoMissedOrGameOver = true;
                }
                playerTwoGameBoard.printShipPlacement();
            }
            if (gameIsOver) {
                Thread.currentThread().interrupt();
            }
        }
    }


    public static void main(String[] args) {
        (new MainThread()).start();
    }
}
