package ru.projects.simpleapi.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.projects.simpleapi.model.Task;
import ru.projects.simpleapi.model.TaskStatus;
import ru.projects.simpleapi.repository.TaskRepository;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertNotNull;


@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;
    @InjectMocks
    private TaskService taskService;

    @Test
    void testFindById() {
        LocalDateTime now = LocalDateTime.now();
        Task task = new Task(1L, "Test task", "Description", 1, TaskStatus.NEW, now);
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));
        Task result = taskService.findById(1L);

        assertNotNull("", result);
        assertEquals("", "Test task", result.getTitle());
        verify(taskRepository, times(1)).findById(1L);
    }
}
