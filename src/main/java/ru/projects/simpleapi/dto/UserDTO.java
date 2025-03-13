package ru.projects.simpleapi.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDTO {
    @NotEmpty(message = "Введите имя пользователя")
    @Size(min = 2, message = "Имя пользователя должно быть больше 2 символов")
    private String username;

    @NotEmpty(message = "Введите пароль")
    private String password;
}
