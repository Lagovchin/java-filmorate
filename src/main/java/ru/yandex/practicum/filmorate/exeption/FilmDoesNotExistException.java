package ru.yandex.practicum.filmorate.exeption;

public class FilmDoesNotExistException extends RuntimeException {
    public FilmDoesNotExistException(String message) {
        super(message);
    }
}
