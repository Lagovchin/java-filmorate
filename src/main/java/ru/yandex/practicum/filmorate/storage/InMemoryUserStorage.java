package ru.yandex.practicum.filmorate.storage;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exeption.InvalidLoginException;
import ru.yandex.practicum.filmorate.exeption.UserDoesNotExistException;
import ru.yandex.practicum.filmorate.model.User;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class InMemoryUserStorage implements UserStorage{

    private final Map<Integer, User> users = new HashMap<>();
    private int generatorID = 1;

    @Override
    public User createUser(User user) {
        validate(user);
        user.setId(generatorID++);
        users.put(user.getId(), user);
        log.info("Пользователь {} успешно добавлен и присвоено id {}.", user.getLogin(), user.getId());
        return user;
    }

    @Override
    public Collection<User> getAllUsers() {
        return users.values();
    }

    @Override
    public User updateUser(User user) {
        validate(user);
        if (!users.containsKey(user.getId())) {
            throw new UserDoesNotExistException("Пользователя с id " + user.getId() + " не существует");
        }
        users.put(user.getId(), user);
        log.info("Пользователь с id {} успешно обновлен", user.getId());
        return user;
    }

    @Override
    public void deleteUser(User user) {
        if (users.containsKey(user.getId())) {
            users.remove(user.getId());
        }
    }

    @Override
    public User getUserById(int id) {
        return users.get(id);
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
