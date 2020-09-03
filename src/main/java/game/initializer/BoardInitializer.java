package game.initializer;

import game.exception.GameCouldNotBeCreatedException;
import game.objects.GameBoard;
import game.objects.Ship;
import game.utils.ShipUtils;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class BoardInitializer {

    public GameBoard createGameBoard(String shipInputPlacement, String gameBoardSize) throws GameCouldNotBeCreatedException {
        //TODO replace with static?
        ShipInitializer shipInitializer = new ShipInitializer();
        ArrayList<Ship> ships = shipInitializer.placeShips(shipInputPlacement);
        String[] size = gameBoardSize.split(Pattern.quote("*"));
        GameBoard gameBoard = new GameBoard(Integer.parseInt(size[0]), Integer.parseInt(size[1]));

        for (Ship ship : ships) {
            for (String shipCoordinate : ship.getShipCoordinates()) {
                gameBoard.addShipPlacement(shipCoordinate, ship);
            }
        }
        if (ShipUtils.CheckShipPositions(gameBoard))
            return gameBoard;
        throw new GameCouldNotBeCreatedException("The game could not be created. Location of the ships is incorrect.");
    }
}
