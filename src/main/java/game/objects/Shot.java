package game.objects;

public class Shot {
    private final boolean successfulHit;
    private final boolean shipWasDestroyed;
    private final boolean validShot;

    public Shot(boolean validShot, boolean successfulHit, boolean shipWasDestroyed) {
        this.validShot = validShot;
        this.successfulHit = successfulHit;
        this.shipWasDestroyed = shipWasDestroyed;
    }

    public boolean isShipWasDestroyed() {
        return shipWasDestroyed;
    }

    public boolean isValidShot() {
        return validShot;
    }

    public boolean isSuccessfulHit() {
        return successfulHit;
    }
}
