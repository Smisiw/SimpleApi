package ru.projects.simpleapi.service;

import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import ru.projects.simpleapi.dto.TaskDTO;
import ru.projects.simpleapi.model.Task;
import ru.projects.simpleapi.repository.TaskRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    public Task createTask(TaskDTO taskDTO) {
        Task task = Task.builder()
                .title(taskDTO.getTitle())
                .description(taskDTO.getDescription())
                .priority(taskDTO.getPriority())
                .status(taskDTO.getStatus())
                .date(taskDTO.getDate())
                .build();
        return taskRepository.save(task);
    }

    @Cacheable(value = "taskList", unless = "#result == null or #result.isEmpty()")
    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    @Cacheable(value = "tasks", key = "#id", unless = "#result == null")
    public Task findById(Long id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isEmpty()){
            throw new IllegalStateException("Задача с id = " + id + " не найдена");
        }
        return optionalTask.get();
    }

    @CacheEvict(value = "tasks", key = "#id")
    public Task updateTask(Long id, TaskDTO task) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isEmpty()) {
            throw new IllegalStateException("Задача с id = " + id + " не найдена");
        }
        Task updatedTask = optionalTask.get();
        updatedTask.setTitle(task.getTitle());
        updatedTask.setDescription(task.getDescription());
        updatedTask.setPriority(task.getPriority());
        updatedTask.setStatus(task.getStatus());
        updatedTask.setDate(task.getDate());
        return taskRepository.save(updatedTask);
    }

    @CacheEvict(value = "tasks", key = "#id")
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}
