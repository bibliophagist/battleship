package game.objects;

import java.util.HashMap;

public class GameBoard {
    private final HashMap<String, Ship> shipPlacement = new HashMap<>();
    private final HashMap<String, Boolean> shotPlacement = new HashMap<>();

    public GameBoard() {
    }

    public void addShipPlacement(String placement, Ship ship) {
        shipPlacement.put(placement, ship);
    }

    public Shot addShotPlacement(String placement) {
        // TODO do smth if cell has already been shot
        if (shipPlacement.containsKey(placement)) {
            Ship ship = shipPlacement.get(placement);
            ship.addShipHitCoordinates(placement);
            shipPlacement.remove(placement);
            shotPlacement.put(placement, true);
            return new Shot(true, ship.isShipDestroyed());
        } else {
            shotPlacement.put(placement, false);
            return new Shot(false, false);
        }
    }

    public HashMap<String, Ship> getShipPlacement() {
        return shipPlacement;
    }

    public HashMap<String, Boolean> getShotPlacement() {
        return shotPlacement;
    }

    public boolean allShipsDestroyed() {
        return shipPlacement.isEmpty();
    }


    //TODO add beautiful output
    public void printShipPlacement() {
        System.out.println(shipPlacement.toString());
    }
}
