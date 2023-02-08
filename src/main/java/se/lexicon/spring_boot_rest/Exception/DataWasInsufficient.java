package se.lexicon.spring_boot_rest.Exception;

public class DataWasInsufficient extends RuntimeException{
    private String message;

    public DataWasInsufficient(String message) {
        this.message = message;
    }
}
