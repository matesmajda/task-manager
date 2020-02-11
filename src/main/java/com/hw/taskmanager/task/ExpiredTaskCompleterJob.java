package com.hw.taskmanager.task;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class ExpiredTaskCompleterJob {

    private TaskRepository taskRepository;

    @Scheduled(fixedRate = 1000 * 15)
    public void completeExpiredTasks() {
        log.info("Starting ExpiredTaskCompleterJob");
        Date now = new Date();

        while (true) {
            Collection<Task> tasks = taskRepository.findTop10ByStatusAndDateTimeBefore(TaskStatus.PENDING, now);

            if (tasks.size() == 0) {
                log.info("Finished ExpiredTaskCompleterJob");
                return;
            }
            log.info("Updating tasks to DONE: {}", tasks.stream().map(Task::getId).collect(Collectors.toList()));
            tasks.forEach(t -> t.setStatus(TaskStatus.DONE));
            taskRepository.saveAll(tasks);
        }
    }
}
