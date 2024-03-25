package ru.yandex.practicum.filmorate.model;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Data
@Builder
public class User {
    private int id;
    @Email(message = "e-mail не может быть пустой и должен содержать символ @")
    @NotBlank(message = "e-mail не может быть пустой и должен содержать символ @")
    private String email;
    @NotBlank(message = "логин не может быть пустым и содержать пробелы")
    private String login;
    private String name;
    @Past(message = "дата рождения не может быть в будущем")
    private LocalDate birthday;
}
