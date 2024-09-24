package ru.yandex.practicum.filmorate.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.NonNull;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;

@Data
public class User {

    private Set<Long> friends = new HashSet<>();

    private long id;
    private String name;
    @NonNull
    @NotBlank
    @Email(message = "Ошибка! Неверный e-mail.")
    private String email;
    @NonNull
    @NotBlank(message = "Ошибка! Логин не может быть пустым.")
    private String login;
    @NonNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;

}