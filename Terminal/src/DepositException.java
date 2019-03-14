public class DepositException extends Exception{

    public DepositException() {
        super();
    }

    public DepositException(String message) {
        super(message);
    }

    public DepositException(String message, Throwable cause) {
        super(message, cause);
    }

    public DepositException(Throwable cause) {
        super(cause);
    }
}
