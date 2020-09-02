package game.initializer;

import game.exception.GameCouldNotBeCreatedException;
import game.objects.GameBoard;
import game.objects.Ship;
import game.utils.ShipUtils;

import java.util.ArrayList;

public class BoardInitializer {

    public GameBoard createGameBoard(String shipInputPlacement) throws GameCouldNotBeCreatedException {
        //TODO replace with static?
        ShipInitializer shipInitializer = new ShipInitializer();
        ArrayList<Ship> ships = shipInitializer.placeShips(shipInputPlacement);
        GameBoard gameBoard = new GameBoard();

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
