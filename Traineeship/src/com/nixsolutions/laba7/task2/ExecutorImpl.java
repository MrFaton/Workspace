package com.nixsolutions.laba7.task2;

import interfaces.task7.executor.Executor;
import interfaces.task7.executor.Task;
import interfaces.task7.executor.TasksStorage;

public class ExecutorImpl implements Executor {
    private TasksStorage tasksStorage;
    private boolean running = false;
    private Thread thread;

    @Override
    public TasksStorage getStorage() {
        return tasksStorage;
    }

    @Override
    public void setStorage(TasksStorage storage) {
        tasksStorage = storage;
    }

    @Override
    public void start() {
        if (tasksStorage == null) {
            throw new NullPointerException("TaskStorage is null");
        }
        if (running) {
            throw new IllegalStateException("Executor already running");
        }
        running = true;
        thread = new Thread(new TaskExecutor());
        thread.start();
    }

    @Override
    public void stop() {
        if (!running) {
            throw new IllegalStateException("Executor not running");
        }
        thread.interrupt();
    }

    private class TaskExecutor implements Runnable {
        @Override
        public void run() {
            while (!Thread.interrupted()) {
                Task task = tasksStorage.get();
                if (task == null) {
                    try {
                        Thread.sleep(100);
                        continue;
                    } catch (InterruptedException e) {
                        break;
                    }
                }
                if (task.getTryCount() < 5) {
                    boolean result;
                    try {
                        result = task.execute();
                    } catch (Exception e) {
                        result = false;
                    }
                    if (!result) {
                        task.incTryCount();
                        tasksStorage.add(task);
                    }
                }
            }
        }
    }
}
