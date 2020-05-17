package ru.vsu.cs.CustomExceptions;

public class EmailBusy extends Exception {

    public EmailBusy(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
