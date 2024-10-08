package ru.yandex.practicum.filmorate.controller;

import ru.yandex.practicum.filmorate.exeption.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.Genre;
import ru.yandex.practicum.filmorate.model.RatingMPA;
import ru.yandex.practicum.filmorate.service.FilmService;
import ru.yandex.practicum.filmorate.validator.ValidatorControllers;

import java.util.*;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

@RestController
@RequestMapping
@Slf4j
@Validated
@RequiredArgsConstructor
public class FilmController {

    private final FilmService filmService;

    @PostMapping("/films")
    @Validated
    // добавление фильма
    public Film create(@Valid @RequestBody Film film) {
        film = ValidatorControllers.validateFilm(film);
        Film newFilm = filmService.create(film);
        log.debug("Добавлен новый фильм: {}", newFilm);
        return newFilm;
    }

    @PutMapping("/films")
    @Validated
    // обновление фильма
    public Film update(@Valid @RequestBody Film film) {
        ValidatorControllers.validateFilm(film);
        Film newFilm = filmService.update(film);
        log.debug("Обновлен фильм: {}", newFilm);
        return newFilm;
    }

    @DeleteMapping("/films")
    @Validated
    // удаление фильма
    public void delete(@Valid @RequestBody Film film) {
        filmService.delete(film);
        log.debug("Удалён фильм: {}", film);
    }

    @GetMapping("/films")
    // получение всех фильмов
    public List<Film> findFilms() {
        List<Film> films = filmService.findFilms();
        log.debug("Получен список фильмов, количество = {}", films.size());
        return films;
    }

    @GetMapping("/films/{filmId}")
    // получение пользователя по id
    public Film findFilmById(@PathVariable long filmId) {
        Film film = filmService.findFilmById(filmId);
        log.debug("Получен фильм с id = {}", filmId);
        return film;
    }

    @PutMapping("/films/{id}/like/{userId}")
    //  пользователь ставит лайк фильму
    public boolean like(@PathVariable long id, @PathVariable long userId) {
        if (filmService.like(id, userId)) {
            log.debug("Пользователь id = {} лайкнул фильм id = {}", userId, id);
            return true;
        }
        return false;
    }

    @DeleteMapping("/films/{id}/like/{userId}")
    //  пользователь удаляет лайк
    public boolean dislike(@PathVariable long id, @PathVariable long userId) {
        if (filmService.dislike(id, userId)) {
            log.debug("Пользователь id = {} удалил лайк с фильма id = {}", userId, id);
            return true;
        }
        return false;
    }

    @GetMapping("/films/popular")
    //  возвращает список из первых count фильмов по количеству лайков.
    //  Если значение параметра count не задано, возвращает первые 10
    public List<Film> findPopularFilms(@RequestParam(defaultValue = "10") String count) {
        int countInt = Integer.parseInt(count);
        if (countInt < 0) {
            String message = "Параметр count не может быть отрицательным!";
            log.warn(message);
            throw new ValidationException(message);
        }
        List<Film> films = filmService.findPopularFilms(countInt);
        log.debug("Получен список из первых {} фильмов по количеству лайков, " +
                "количество = {}", countInt, films.size());
        return films;
    }

    @GetMapping("/genres")
    // получение всех жанров
    public List<Genre> findGenres() {
        List<Genre> genres = filmService.findGenres();
        log.debug("Получен список жанров, количество = {}", genres.size());
        return genres;
    }

    @GetMapping("/genres/{id}")
    // получение жанра по id
    public Genre findGenreById(@PathVariable long id) {
        Genre genre = filmService.findGenreById(id);
        log.debug("Получен жанр с id = {}", id);
        return genre;
    }

    @GetMapping("/mpa")
    // получение всех рейтингов МПА
    public List<RatingMPA> findRatingMPAs() {
        List<RatingMPA> ratingMPAs = filmService.findRatingMPAs();
        log.debug("Получен список рейтингов МПА, количество = {}", ratingMPAs.size());
        return ratingMPAs;
    }

    @GetMapping("/mpa/{id}")
    // получение рейтинга МПА по id
    public RatingMPA findRatingMPAById(@PathVariable long id) {
        RatingMPA ratingMPA = filmService.findRatingMPAById(id);
        log.debug("Получен рейтинг МПА с id = {}", id);
        return ratingMPA;
    }

}
