package com.hw.taskmanager.task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExpiredTaskCompleterJobTest {

    private ExpiredTaskCompleterJob job;

    @Mock
    private TaskRepository taskRepository;

    @BeforeEach
    void setUp() {
        job = new ExpiredTaskCompleterJob(taskRepository);
    }

    @Test
    void tasksAreSetToDone() {

        Task task1 = newTask(1L, TaskStatus.PENDING);
        Task task2 = newTask(2L, TaskStatus.PENDING);
        List<Task> tasks = Arrays.asList(task1, task2);

        when(taskRepository.findTop10ByStatusAndDateTimeBefore(any(), any())).thenReturn(tasks).thenReturn(Collections.emptyList());

        job.completeExpiredTasks();
        Task expectedTask1 = newTask(1L, TaskStatus.DONE);
        Task expectedTask2 = newTask(2L, TaskStatus.DONE);
        Collection<Task> expectedTasks = Arrays.asList(expectedTask1, expectedTask2);
        verify(taskRepository).saveAll(expectedTasks);
    }

    private Task newTask(long l, TaskStatus status) {
        return Task.builder().id(l).status(status).build();
    }
}