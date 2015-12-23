package com.nixsolutions.laba7.task2;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import interfaces.task7.executor.CopyTask;
import interfaces.task7.executor.Executor;
import interfaces.task7.executor.SumTask;
import interfaces.task7.executor.TasksStorage;

public class BackgroundThreads {
    private static String sourceFile = "/tmp/source.f";
    private static String copy1 = "/tmp/copy1.f";
    private static String copy2 = "/tmp/copy2.f";
    private static String copy3 = "/tmp/copy3.f";
    private static String copy4 = "/tmp/copy4.f";

    public static void main(String[] args) {
        createSourceFile();

        // Create task storage
        TasksStorage tasksStorage = new TaskStorageImpl();

        // Create executors
        Executor executorA = new ExecutorImpl();
        Executor executorB = new ExecutorImpl();
        Executor executorC = new ExecutorImpl();

        // Create copy tasks
        CopyTask copyTaskA = new CopyTaskImpl();
        CopyTask copyTaskB = new CopyTaskImpl();
        CopyTask copyTaskC = new CopyTaskImpl();
        CopyTask copyTaskD = new CopyTaskImpl();

        // Create sum tasks
        SumTask sumTaskA = new SumTaskImpl();
        SumTask sumTaskB = new SumTaskImpl();
        SumTask sumTaskC = new SumTaskImpl();
        SumTask sumTaskD = new SumTaskImpl();
        SumTask sumTaskE = new SumTaskImpl();
        SumTask sumTaskF = new SumTaskImpl();
        SumTask sumTaskG = new SumTaskImpl();
        SumTask sumTaskH = new SumTaskImpl();

        // Configure copy tasks
        copyTaskA.setSource(sourceFile);
        copyTaskA.setDest(copy1);

        copyTaskB.setSource(sourceFile);
        copyTaskB.setDest(copy2);

        copyTaskC.setSource(sourceFile);
        copyTaskC.setDest(copy3);

        copyTaskD.setSource(sourceFile);
        copyTaskD.setDest(copy4);

        // Configure sum tasks
        sumTaskA.setCount(5);
        sumTaskA.setMax(Integer.MAX_VALUE);

        sumTaskB.setCount(5);
        sumTaskB.setMax(Integer.MAX_VALUE);

        sumTaskC.setCount(5);
        sumTaskC.setMax(Integer.MAX_VALUE);

        sumTaskD.setCount(5);
        sumTaskD.setMax(Integer.MAX_VALUE);

        sumTaskE.setCount(5);
        sumTaskE.setMax(Integer.MAX_VALUE);

        sumTaskF.setCount(5);
        sumTaskF.setMax(Integer.MAX_VALUE);

        sumTaskG.setCount(5);
        sumTaskG.setMax(Integer.MAX_VALUE);

        sumTaskH.setCount(5);
        sumTaskH.setMax(Integer.MAX_VALUE);

        // add tasks to task storage
        tasksStorage.add(copyTaskA);
        tasksStorage.add(copyTaskB);
        tasksStorage.add(copyTaskC);
        tasksStorage.add(copyTaskD);

        tasksStorage.add(sumTaskA);
        tasksStorage.add(sumTaskB);
        tasksStorage.add(sumTaskC);
        tasksStorage.add(sumTaskD);
        tasksStorage.add(sumTaskE);
        tasksStorage.add(sumTaskF);
        tasksStorage.add(sumTaskG);
        tasksStorage.add(sumTaskH);

        // add task storage to task executors
        executorA.setStorage(tasksStorage);
        executorB.setStorage(tasksStorage);
        executorC.setStorage(tasksStorage);

        // run executors
        executorA.start();
        executorB.start();
        executorC.start();

        // wait for tasks list empty
        while (tasksStorage.count() > 0) {
            System.out.println("wait...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                break;
            }
        }

        // shutdown executors
        executorA.stop();
        executorB.stop();
        executorC.stop();

        // clear temp files
        deleteCopies();

        System.out.println("All done");
    }

    public static void createSourceFile() {
        byte[] bytes = new byte[1024 * 1024];

        try (FileOutputStream out = new FileOutputStream(sourceFile)) {
            out.write(bytes);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteCopies() {
        new File(copy1).delete();
        new File(copy2).delete();
        new File(copy3).delete();
        new File(copy4).delete();
    }
}
