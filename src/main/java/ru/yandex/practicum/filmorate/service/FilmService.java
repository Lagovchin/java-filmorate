package ru.yandex.practicum.filmorate.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exeption.FilmDoesNotExistException;
import ru.yandex.practicum.filmorate.exeption.UserDoesNotExistException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.FilmStorage;
import ru.yandex.practicum.filmorate.storage.UserStorage;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FilmService {

    private FilmStorage filmStorage;
    private UserStorage userStorage;

    public void addLike(int filmId, int userId) {
        if (filmStorage.getFilmById(filmId) == null) {
            throw new FilmDoesNotExistException("Фильма с id " + filmId + " не найдено.");
        }
        if (userStorage.getUserById(userId) == null) {
            throw new UserDoesNotExistException("Пользователя с id " + userId + " не найдено");
        }
       filmStorage.getFilmById(filmId).getLikes().add(userId);
    }

    public void deleteLike(int filmId, int userId) {
        if (filmStorage.getFilmById(filmId) == null) {
            throw new FilmDoesNotExistException("Фильма с id " + filmId + " не найдено.");
        }
        if (userStorage.getUserById(userId) == null) {
            throw new UserDoesNotExistException("Пользователя с id " + userId + " не найдено");
        }
        filmStorage.getFilmById(filmId).getLikes().remove(userId);
    }

    public List<Film> getMostPopularFilms(int count) {
        return filmStorage.getAllFilms().stream()
                .sorted((f1,f2) -> Integer.compare(f2.getLikes().size(), f1.getLikes().size()))
                .limit(count)
                .collect(Collectors.toList());
    }

    public Film createFilm(Film film) {
        return filmStorage.createFilm(film);
    }

    public Collection<Film> getAllFilms() {
        return filmStorage.getAllFilms();
    }

    public void deleteFilm(Film film) {
        filmStorage.deleteFilm(film);
    }

    public Film getFilmById(int id) {
        return filmStorage.getFilmById(id);
    }

    public Film updateFilm(Film film) {
        return filmStorage.updateFilm(film);
    }
}
