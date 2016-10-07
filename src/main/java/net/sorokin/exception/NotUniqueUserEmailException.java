package net.sorokin.exception;


public class NotUniqueUserEmailException extends Exception{

    public NotUniqueUserEmailException(String message) {
        super(message);
    }
}
