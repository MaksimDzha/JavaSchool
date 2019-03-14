public class DepositOutException extends Exception{

    public DepositOutException() {
        super();
    }

    public DepositOutException(String message) {
        super(message);
    }

    public DepositOutException(String message, Throwable cause) {
        super(message, cause);
    }

    public DepositOutException(Throwable cause) {
        super(cause);
    }
}
