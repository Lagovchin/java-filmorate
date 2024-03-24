package ru.yandex.practicum.filmorate.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import ru.yandex.practicum.filmorate.exeption.InvalidReleaseDateException;
import ru.yandex.practicum.filmorate.model.Film;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class FilmControllerTest {
    FilmController controller = new FilmController();

    @Test
    public void shouldCreateFilm(){
        Film film = new Film("Тест", "Тестовый", LocalDate.of(2020,12,20), 100);
        controller.createFilm(film);
        assertTrue(controller.getAllFilms().contains(film));
    }

    @Test
    public void shouldNotCreateFilmBeforeCinemaBirthday(){
        Film film = new Film("Тест", "Тестовый", LocalDate.of(23,12,20), 100);

        InvalidReleaseDateException exception = assertThrows(
                InvalidReleaseDateException.class, () -> controller.createFilm(film));
        assertEquals("дата релиза — не может быть раньше 28 декабря 1895 года", exception.getMessage());
    }

    @Test
    public void shouldNotCreateFilmInCinemaBirthday() {
        Film film = new Film("Тест", "Тестовый", LocalDate.of(1895, 12, 28), 10);

        controller.createFilm(film);
        assertTrue(controller.getAllFilms().contains(film));
    }

}