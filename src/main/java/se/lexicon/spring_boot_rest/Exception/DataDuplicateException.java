package se.lexicon.spring_boot_rest.Exception;

public class DataDuplicateException extends RuntimeException {
    private String message;

    public DataDuplicateException(String message) {
        super(message);

    }
}
