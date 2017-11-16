package person.zhy.utils.base;

public class NoRecordException extends RuntimeException {
    public NoRecordException() {
        super();
    }

    public NoRecordException(String message) {
        super(message);
    }

    public NoRecordException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoRecordException(Throwable cause) {
        super(cause);
    }

    protected NoRecordException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
