package ru.practicum.common;

public class InsufficientPermissionException extends LaterApplicationException{
    public InsufficientPermissionException(String message) {
        super(message);
    }

    public InsufficientPermissionException(String message, Throwable cause) {
        super(message, cause);
    }
}
