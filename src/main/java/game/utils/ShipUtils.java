package game.utils;

import game.objects.GameBoard;
import game.objects.Ship;

import java.util.*;

public class ShipUtils {

    public static Boolean CheckShipPositions(GameBoard gameBoard) {
        Set<String> shipCells = gameBoard.getShipPlacement().keySet();
        HashSet<String> borderCells = new HashSet<>();
        for (Ship ship : gameBoard.getShipPlacement().values()) {
            borderCells.addAll(GetCellsOfShipBorders(ship));
        }
        return Collections.disjoint(shipCells, borderCells);
    }

    public static ArrayList<String> GetCellsOfShipBorders(Ship ship) {
        ArrayList<String> shipAndBordersCells = new ArrayList<>();
        HashSet<Character> x_axis = new HashSet<>();
        HashSet<Integer> y_axis = new HashSet<>();
        for (String shipCoordinate : ship.getShipCoordinates()) {
            char x = shipCoordinate.charAt(0);
            int y = Integer.parseInt(shipCoordinate.substring(1));
            // TODO if x char why is x - 1 = int and 'x' - 1 = char
            // TODO hard coded table borders
            // TODO remove Й Ъ Ь
            for (char i = (char) (x - 1); i < x + 2; i++) {
                if (i >= 'А' && i <= 'Я') {
                    x_axis.add(i);
                }
            }
            for (int i = y - 1; i < y + 2; i++) {
                if (i >= 1 && i <= 32) {
                    y_axis.add(i);
                }
            }
        }
        for (Character x : x_axis) {
            for (Integer y : y_axis) {
                if (!ship.getShipCoordinates().contains(x + y.toString()))
                    shipAndBordersCells.add(x + y.toString());
            }
        }
        return shipAndBordersCells;
    }
}
