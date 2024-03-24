package ru.yandex.practicum.filmorate.exeption;

public class InvalidReleaseDateException extends RuntimeException {
    public InvalidReleaseDateException(String message){
        super(message);
    }
}
