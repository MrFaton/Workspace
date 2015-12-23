package com.nixsolutions.laba7.task2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

import interfaces.task7.executor.CopyTask;

public class CopyTaskImpl implements CopyTask {
    private int tryCount;
    private String source;
    private String dest;

    @Override
    public boolean execute() throws Exception {
        try (FileInputStream in = new FileInputStream(source);
                FileOutputStream out = new FileOutputStream(dest);
                FileChannel inChannel = in.getChannel();
                FileChannel outChannel = out.getChannel()) {

            outChannel.transferFrom(inChannel, 0, inChannel.size());
        } catch (FileNotFoundException fileNotFoundEx) {
            return false;
        } catch (IOException ex) {
            new File(dest).delete();
            return false;
        }
        return true;
    }

    @Override
    public int getTryCount() {
        return tryCount;
    }

    @Override
    public void incTryCount() {
        tryCount++;
    }

    @Override
    public void setDest(String d) {
        if (d == null) {
            throw new NullPointerException("Argument is null");
        }
        dest = d;
    }

    @Override
    public void setSource(String s) {
        if (s == null) {
            throw new NullPointerException("Argument is null");
        }

        File sourceFile = new File(s);

        if (!sourceFile.exists()) {
            throw new IllegalArgumentException("Source file not found");
        }

        if (sourceFile.isDirectory()) {
            throw new IllegalArgumentException("Source file is directory",
                    new FileNotFoundException());
        }
        source = s;
    }

}
