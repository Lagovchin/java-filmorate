package ru.yandex.practicum.filmorate.controller;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.exeption.InvalidReleaseDateException;
import ru.yandex.practicum.filmorate.model.Film;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class FilmControllerTest {
    private FilmController controller = new FilmController();

    @Test
    public void shouldCreateFilm() {
        Film film = Film.builder()
                .name("Тест")
                .description("Тестовый")
                .releaseDate(LocalDate.of(2020, 12, 20))
                .duration(100)
                .build();

        controller.createFilm(film);
        assertTrue(controller.getAllFilms().contains(film));
    }

    @Test
    public void shouldNotCreateFilmBeforeCinemaBirthday() {
        Film film = Film.builder()
                .name("Тест")
                .description("Тестовый")
                .releaseDate(LocalDate.of(23, 12, 20))
                .duration(100)
                .build();

        InvalidReleaseDateException exception = assertThrows(
                InvalidReleaseDateException.class, () -> controller.createFilm(film));
        assertEquals("дата релиза — не может быть раньше 28 декабря 1895 года", exception.getMessage());
    }

    @Test
    public void shouldCreateFilmInCinemaBirthday() {
        Film film = Film.builder()
                .name("Тест")
                .description("Тестовый")
                .releaseDate(LocalDate.of(1895, 12, 28))
                .duration(100)
                .build();

        controller.createFilm(film);
        assertTrue(controller.getAllFilms().contains(film));
    }
}