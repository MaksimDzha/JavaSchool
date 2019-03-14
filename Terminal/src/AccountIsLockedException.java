public class AccountIsLockedException extends Exception{
    public AccountIsLockedException() {
        super();
    }

    public AccountIsLockedException(String message) {
        super(message);
    }

    public AccountIsLockedException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccountIsLockedException(Throwable cause) {
        super(cause);
    }
}
