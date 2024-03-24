package ru.yandex.practicum.filmorate.controller;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.exeption.InvalidLoginException;
import ru.yandex.practicum.filmorate.model.User;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class UserControllerTest {
    private UserController controller = new UserController();

    @Test
    public void shouldCreateUser() {
        User user = new User("test@test.ru", "test", "test test", LocalDate.of(1995, 7, 27));
        controller.createUser(user);
        assertTrue(controller.getAllUsers().contains(user));
    }

    @Test
    public void shouldNotCreateUserWithSpaceInLogin() {
        User user = new User("test@test.ru", "t est", "test test", LocalDate.of(1995, 7, 27));
        InvalidLoginException exception = assertThrows(
                InvalidLoginException.class, () -> controller.createUser(user));
        assertEquals("логин не может содержать пробелы", exception.getMessage());
    }

    @Test
    public void shouldCreateUserWithEmptyName() {
        User user = new User("test@test.ru", "test", "", LocalDate.of(1995, 7, 27));
        controller.createUser(user);
        assertEquals(user.getLogin(), controller.getUsers().get(user.getId()).getName());
    }
}