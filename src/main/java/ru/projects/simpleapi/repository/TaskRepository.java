package ru.projects.simpleapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.projects.simpleapi.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

}
