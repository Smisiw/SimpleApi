package ru.projects.simpleapi.service;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    public ResponseEntity<Task> createTask(TaskDTO taskDTO) {
        Task task = Task.builder()
                .title(taskDTO.getTitle())
                .description(taskDTO.getDescription())
                .priority(taskDTO.getPriority())
                .status(taskDTO.getStatus())
                .date(taskDTO.getDate())
                .build();
        taskRepository.save(task);
        return new ResponseEntity<>(task, HttpStatus.CREATED);
    }

    public ResponseEntity<List<Task>> findAll() {
        return new ResponseEntity<>(taskRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<Task> findById(Long id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isEmpty()){
            throw new IllegalStateException("Задача с id = " + id + " не найдена");
        }
        return new ResponseEntity<>(optionalTask.get(), HttpStatus.OK);
    }

    public ResponseEntity<Task> updateTask(Long id, TaskDTO task) {
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
        return new ResponseEntity<>(taskRepository.save(updatedTask), HttpStatus.OK);
    }

    public ResponseEntity<?> deleteTask(Long id) {
        taskRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
