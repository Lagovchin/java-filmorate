package ru.yandex.practicum.filmorate.exeption;

public class InvalidLoginException extends RuntimeException {
    public InvalidLoginException(String message) {
        super(message);
    }
}
