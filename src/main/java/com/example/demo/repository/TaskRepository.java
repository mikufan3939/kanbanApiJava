package com.example.demo.repository;

import com.example.demo.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
    List<Task> findByOrderByStatusAscPriorityAsc();
    List<Task> findByPriority(String priority);

    @Query("SELECT t FROM Task t WHERE t.deadlineDate < :currentDate AND t.status <> 'Concluido'")
    List<Task> findExpiredTasksWithIncompleteStatus(@Param("currentDate") LocalDate currentDate);
}
