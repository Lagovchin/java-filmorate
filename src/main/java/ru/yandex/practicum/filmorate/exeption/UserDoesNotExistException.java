package ru.yandex.practicum.filmorate.exeption;

public class UserDoesNotExistException extends RuntimeException {
    public UserDoesNotExistException(String message){
        super(message);
    }
}
