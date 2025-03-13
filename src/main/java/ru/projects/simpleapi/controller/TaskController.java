package ru.projects.simpleapi.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.projects.simpleapi.dto.TaskDTO;
import ru.projects.simpleapi.model.Task;
import ru.projects.simpleapi.service.TaskService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody @Validated TaskDTO taskDTO) {
        return taskService.createTask(taskDTO);
    }

    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        return taskService.findAll();
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable(name = "id") Long id) {
        return taskService.findById(id);
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<Task> updateTask(@PathVariable(name = "id") Long id, @RequestBody @Validated TaskDTO taskDTO) {
        return taskService.updateTask(id, taskDTO);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<?> deleteTaskById(@PathVariable(name = "id") Long id) {
        return taskService.deleteTask(id);
    }
}
