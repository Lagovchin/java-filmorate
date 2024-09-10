package ru.yandex.practicum.filmorate.controller;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.exeption.InvalidLoginException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.UserService;
import ru.yandex.practicum.filmorate.storage.InMemoryUserStorage;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class UserControllerTest {
    private InMemoryUserStorage storage = new InMemoryUserStorage();
    private UserService service = new UserService(storage);
    private UserController controller = new UserController(service);

    @Test
    public void shouldCreateUser() {
        User user = User.builder()
                .login("test")
                .name("test test")
                .email("test@test.ru")
                .birthday(LocalDate.of(1995, 7, 27))
                .build();

        controller.createUser(user);
        assertTrue(controller.getAllUsers().contains(user));
    }

    @Test
    public void shouldNotCreateUserWithSpaceInLogin() {
        User user = User.builder()
                .login("t est")
                .name("test test")
                .email("test@test.ru")
                .birthday(LocalDate.of(1995, 7, 27))
                .build();

        InvalidLoginException exception = assertThrows(
                InvalidLoginException.class, () -> controller.createUser(user));
        assertEquals("логин не может содержать пробелы", exception.getMessage());
    }

    @Test
    public void shouldCreateUserWithEmptyName() {
        User user = User.builder()
                .login("test")
                .name("")
                .email("test@test.ru")
                .birthday(LocalDate.of(1995, 7, 27))
                .build();

        controller.createUser(user);
        assertEquals(user.getLogin(), controller.getUserById(user.getId()).getName());
    }
}