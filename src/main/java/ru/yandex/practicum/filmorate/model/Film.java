package ru.yandex.practicum.filmorate.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.NonNull;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonFormat;

@Data
public class Film {

    private Set<Long> likes = new HashSet<>();
    private Set<Genre> genres = new HashSet<>();

    private long id;
    @NonNull
    @NotBlank(message = "Ошибка! Название не может быть пустым.")
    private String name;
    @NonNull
    private String description;
    @NonNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate releaseDate;
    @Positive(message = "Ошибка! Продолжительность фильма должна быть положительной.")
    private int duration;
    private int rate;
    private RatingMPA mpa;

}
