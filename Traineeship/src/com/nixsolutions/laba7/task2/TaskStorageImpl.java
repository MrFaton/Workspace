package com.nixsolutions.laba7.task2;

import interfaces.task7.executor.Task;
import interfaces.task7.executor.TasksStorage;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class TaskStorageImpl implements TasksStorage {
    private BlockingQueue<Task> queue = new LinkedBlockingQueue<>();

    @Override
    public void add(Task task) {
        if (task == null) {
            throw new NullPointerException("Argument is null");
        }
        queue.add(task);
    }

    @Override
    public int count() {
        return queue.size();
    }

    @Override
    public Task get() {
        return queue.poll();
    }
}
