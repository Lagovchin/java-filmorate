package ru.yandex.practicum.filmorate.model;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDate;

/**
 * Film.
 */
@Data
@Builder
public class Film {
    private int id;
    @NotBlank(message = "название не может быть пустым")
    private String name;
    @Size(max = 200)
    private String description;
    private LocalDate releaseDate;
    @Positive(message = "продолжительность фильма должна быть положительной")
    private int duration;
}
