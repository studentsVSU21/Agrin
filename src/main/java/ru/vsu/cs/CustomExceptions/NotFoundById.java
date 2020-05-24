package ru.vsu.cs.CustomExceptions;

public class NotFoundById extends Exception{

    public NotFoundById(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
