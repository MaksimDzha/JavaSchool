public class AccountNotFound extends Exception{
    public AccountNotFound() {
        super();
    }

    public AccountNotFound(String message) {
        super(message);
    }

    public AccountNotFound(String message, Throwable cause) {
        super(message, cause);
    }

    public AccountNotFound(Throwable cause) {
        super(cause);
    }
}
