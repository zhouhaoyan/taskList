package person.zhy.utils.base;

public class IsExistException extends RuntimeException {
    private static final long serialVersionUID = 3392682012248038548L;


    public IsExistException() {
    }

    public IsExistException(String message) {
        super(message);
    }

    public IsExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public IsExistException(Throwable cause) {
        super(cause);
    }

    public IsExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
