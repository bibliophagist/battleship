package game.objects;

import game.utils.Constants;
import game.utils.ShipUtils;

import java.util.ArrayList;
import java.util.HashMap;

public class GameBoard {
    private final HashMap<String, Ship> shipPlacement = new HashMap<>();
    private final HashMap<String, Boolean> enemyShotPlacement = new HashMap<>();
    private final ArrayList<Character> x_axis = new ArrayList<>();
    private final ArrayList<Integer> y_axis = new ArrayList<>();
    private HashMap<String, Boolean> playerShotPlacement = new HashMap<>();

    public GameBoard(int x_length, int y_length) {
        char x = Constants.xEntry;
        int y = Constants.yEntry;
        x_length = Math.min(x_length, Constants.yEnd);
        x_length = Math.max(x_length, Constants.yEntry);
        y_length = Math.min(y_length, Constants.yEnd);
        y_length = Math.max(y_length, Constants.yEntry);
        while (x_length > 0 || y_length > 0) {
            if (x_length > 0) {
                if (Constants.unusedChars.contains(x)) {
                    x++;
                }
                x_axis.add(x);
                x_length--;
                x++;
            }
            if (y_length > 0) {
                y_axis.add(y);
                y_length--;
                y++;
            }
        }
    }

    public void addShipPlacement(String placement, Ship ship) {
        shipPlacement.put(placement, ship);
    }

    public Shot addShotPlacement(String placement) {
        if (shipPlacement.containsKey(placement)) {
            Ship ship = shipPlacement.get(placement);
            ship.addShipHitCoordinates(placement);
            shipPlacement.remove(placement);
            enemyShotPlacement.put(placement, true);
            if (ship.isShipDestroyed()) {
                for (String cellOfShipBorders : ShipUtils.GetCellsOfShipBorders(ship)) {
                    enemyShotPlacement.put(cellOfShipBorders, false);
                }
            }
            return new Shot(true, true, ship.isShipDestroyed());
        } else {
            if (enemyShotPlacement.containsKey(placement))
                return new Shot(false, false, false);
            enemyShotPlacement.put(placement, false);
            return new Shot(true, false, false);
        }
    }

    public void setPlayerShotPlacement(GameBoard gameBoard) {
        playerShotPlacement = gameBoard.enemyShotPlacement;
    }

    public HashMap<String, Ship> getShipPlacement() {
        return shipPlacement;
    }

    public HashMap<String, Boolean> getEnemyShotPlacement() {
        return enemyShotPlacement;
    }

    public boolean allShipsDestroyed() {
        return shipPlacement.isEmpty();
    }

    // TODO hard coded symbols, add them to Constants?
    public void printShipPlacement() {
        System.out.print("  ");
        for (Character x : x_axis) {
            System.out.print(" " + x);
        }
        System.out.print(" " + "|" + " ");
        System.out.print("  ");
        for (Character x : x_axis) {
            System.out.print(" " + x);
        }
        System.out.print(" " + "|" + " ");
        System.out.println();
        for (Integer y : y_axis) {
            System.out.printf("%02d", y);
            for (Character x : x_axis) {
                if (shipPlacement.containsKey(x + y.toString())) {
                    System.out.print(" " + "█");
                } else if (enemyShotPlacement.containsKey(x + y.toString())) {
                    if (enemyShotPlacement.get(x + y.toString()))
                        System.out.print(" " + "X");
                    else System.out.print(" " + "·");
                } else System.out.print(" " + " ");
            }
            System.out.print(" " + "|" + " ");
            System.out.printf("%02d", y);
            for (Character x : x_axis) {
                if (playerShotPlacement.containsKey(x + y.toString())) {
                    if (playerShotPlacement.get(x + y.toString()))
                        System.out.print(" " + "X");
                    else System.out.print(" " + "·");
                } else System.out.print(" " + " ");
            }
            System.out.print(" " + "|" + " ");
            System.out.println();
        }
    }
}
