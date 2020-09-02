package game.initializer;

import game.objects.Ship;
import game.objects.ShipClass;

import java.util.ArrayList;
import java.util.Arrays;

public class ShipInitializer {

    public ArrayList<Ship> placeShips(String stringOfShipsPlacement) {
        String[] shipPlacement = stringOfShipsPlacement.split(", ");
        ArrayList<Ship> ships = new ArrayList<>();
        for (String s : shipPlacement) {
            Ship ship = new Ship(getShipClass(s), new ArrayList<>(Arrays.asList(s.split(" "))));
            ships.add(ship);
        }
        return ships;
    }

    private ShipClass getShipClass(String stringOfShipPlacement) {
        int numberOfCells = stringOfShipPlacement.split(" ").length;
        if (numberOfCells == 1)
            return ShipClass.SINGLE_DECK;
        if (numberOfCells == 2)
            return ShipClass.DOUBLE_DECK;
        if (numberOfCells == 3)
            return ShipClass.THREE_DECK;
        return ShipClass.FOUR_DECK;
    }

}
