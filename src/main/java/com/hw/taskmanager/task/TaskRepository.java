package com.hw.taskmanager.task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    Optional<Task> findByUserIdAndId(Long userId, Long taskId);

    @Transactional
    void deleteByUserIdAndId(Long userId, Long taskId);

    List<Task> findTop10ByStatusAndDateTimeBefore(TaskStatus pending, Date dateTime);
}
