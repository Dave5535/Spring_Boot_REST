package se.lexicon.spring_boot_rest.Exception;

public class DataNotFoundException extends RuntimeException {

    private String message;

    public DataNotFoundException(String message) {
        super(message);

    }
}
