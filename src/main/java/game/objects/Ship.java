package game.objects;

import java.util.ArrayList;
import java.util.Objects;

public class Ship {
    private ShipClass shipClass;
    //TODO refactor to Set
    private ArrayList<String> shipCoordinates;
    private final ArrayList<String> shipHitCoordinates = new ArrayList<>();

    public Ship(ShipClass shipClass, ArrayList<String> shipCoordinates) {
        this.shipClass = shipClass;
        this.shipCoordinates = shipCoordinates;
    }

    public ShipClass getShipClass() {
        return shipClass;
    }

    public void setShipClass(ShipClass shipClass) {
        this.shipClass = shipClass;
    }

    public ArrayList<String> getShipCoordinates() {
        return shipCoordinates;
    }

    public boolean isShipDestroyed() {
        return shipCoordinates.size() == shipHitCoordinates.size();
    }

    public void addShipHitCoordinates(String coordinate) {
        shipHitCoordinates.add(coordinate);
    }

    public void setShipCoordinates(ArrayList<String> shipCoordinates) {
        this.shipCoordinates = shipCoordinates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ship ship = (Ship) o;
        return shipClass == ship.shipClass &&
                shipCoordinates.equals(ship.shipCoordinates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shipClass, shipCoordinates);
    }

    @Override
    public String toString() {
        return "Ship{" +
                "shipClass=" + shipClass +
                ", shipCoordinates=" + String.join(", ", shipCoordinates) +
                '}';
    }
}
