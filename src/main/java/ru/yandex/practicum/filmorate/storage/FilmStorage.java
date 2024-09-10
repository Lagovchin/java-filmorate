package ru.yandex.practicum.filmorate.storage;

import ru.yandex.practicum.filmorate.model.Film;

import java.util.Collection;

public interface FilmStorage {

    Film createFilm(Film film);

    Collection<Film> getAllFilms();

    void deleteFilm(Film film);

    Film updateFilm(Film film);

    Film getFilmById(int id);

}
