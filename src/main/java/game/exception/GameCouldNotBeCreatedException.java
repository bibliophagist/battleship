package game.exception;

public class GameCouldNotBeCreatedException extends Exception {
    public GameCouldNotBeCreatedException(String message) {
        super(message);
    }

    public GameCouldNotBeCreatedException(String message, Throwable cause) {
        super(message, cause);
    }

}
