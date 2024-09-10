package ru.yandex.practicum.filmorate.controller;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exeption.InvalidLoginException;
import ru.yandex.practicum.filmorate.exeption.UserDoesNotExistException;
import ru.yandex.practicum.filmorate.model.User;

import javax.validation.Valid;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {
    private int generatorID = 1;
    @Getter
    private Map<Integer, User> users = new HashMap<>();

    @GetMapping
    public Collection<User> getAllUsers() {
        log.info("Получен GET-запрос");
        return users.values();
    }

    @PostMapping
    public User createUser(@Valid @RequestBody User user) {
        log.info("Получен POST-запрос");
        validate(user);
        user.setId(generatorID++);
        users.put(user.getId(), user);
        log.info("Пользователь {} успешно добавлен и присвоено id {}.", user.getLogin(), user.getId());
        return user;
    }

    @PutMapping
    public User updateUser(@Valid @RequestBody User user) {
        log.info("Получен PUT-запрос");
        validate(user);
        if (!users.containsKey(user.getId())) {
            throw new UserDoesNotExistException("Пользователя с id " + user.getId() + " не существует");
        }
        users.put(user.getId(), user);
        log.info("Пользователь с id {} успешно обновлен", user.getId());
        return user;
    }

    private void validate(User user) {
        if (user.getLogin().contains(" ")) {
            log.warn("Ошибка валидации пользователя");
            throw new InvalidLoginException("логин не может содержать пробелы");
        }
        if (user.getName() == null || user.getName().isBlank()) {
            user.setName(user.getLogin());
            log.info("Пустое имя. Установлено значение {}", user.getLogin());
        }
    }
}
