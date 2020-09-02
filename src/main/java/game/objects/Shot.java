package game.objects;

public class Shot {
    private final boolean successfulHit;
    private final boolean shipWasDestroyed;

    public Shot(boolean successfulHit, boolean shipWasDestroyed) {
        this.successfulHit = successfulHit;
        this.shipWasDestroyed = shipWasDestroyed;
    }

    public boolean isShipWasDestroyed() {
        return shipWasDestroyed;
    }

    public boolean isSuccessfulHit() {
        return successfulHit;
    }
}
