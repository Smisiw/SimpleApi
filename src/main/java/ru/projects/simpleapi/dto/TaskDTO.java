package ru.projects.simpleapi.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import ru.projects.simpleapi.model.TaskStatus;

import java.time.LocalDateTime;

@Data
public class TaskDTO {
    @NotEmpty(message = "Задача должна иметь заголовок")
    public String title;
    public String description;
    public Integer priority = 1;
    public TaskStatus status = TaskStatus.NEW;
    public LocalDateTime date = LocalDateTime.now();
}
