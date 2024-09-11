package ru.yandex.practicum.filmorate.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exeption.UserDoesNotExistException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.UserStorage;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {

    private UserStorage userStorage;

    public Collection<User> getAllUsers() {
        return userStorage.getAllUsers();
    }

    public User getUserById(int id) {
        return userStorage.getUserById(id);
    }

    public User createUser(User user) {
        return userStorage.createUser(user);
    }

    public User updateUser(User user) {
        return userStorage.updateUser(user);
    }

    public void deleteUser(User user) {
        userStorage.deleteUser(user);
    }

    public List<User> getFriendList(int id) {
        if (userStorage.getUserById(id) == null) {
            throw new UserDoesNotExistException("Пользователь с ID " + id + " не существует");
        }
        return userStorage.getUserById(id).getFriends().stream()
                .map(userStorage::getUserById)
                .collect(Collectors.toList());
    }

    public void addFriend(int userId, int friendId) {
        if (userStorage.getUserById(userId) == null || userStorage.getUserById(friendId) == null) {
            throw new UserDoesNotExistException("Пользователь с ID " + userId + " или " + friendId + " не существует");
        }
        userStorage.getUserById(userId).getFriends().add(friendId);
        userStorage.getUserById(friendId).getFriends().add(userId);
    }

    public void deleteFriend(int userId, int friendId) {
        if (userStorage.getUserById(userId) == null || userStorage.getUserById(friendId) == null) {
            throw new UserDoesNotExistException("Пользователь с ID " + userId + " или " + friendId + " не существует");
        }
        userStorage.getUserById(userId).getFriends().remove(friendId);
        userStorage.getUserById(friendId).getFriends().remove(userId);
    }

    public List<User> getMatchFriendList(int userId, int friendId) {
        if (userStorage.getUserById(userId) == null || userStorage.getUserById(friendId) == null) {
            throw new UserDoesNotExistException("Пользователь с ID " + userId + " или " + friendId + " не существует");
        }
        return userStorage.getUserById(userId).getFriends().stream()
                .filter(u -> userStorage.getUserById(friendId).getFriends().contains(u))
                .map(userStorage::getUserById)
                .collect(Collectors.toList());
    }
}
