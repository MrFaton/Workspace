package com.nixsolutions.laba8.task5;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import interfaces.task8.PathClassLoader;

public class ExtendedClassLoader extends ClassLoader
        implements PathClassLoader {
    private Map<String, Class<?>> cache = new HashMap<>();
    private String rootDir;

    @Override
    public String getPath() {
        return rootDir;
    }

    @Override
    public void setPath(String dir) {
        if (dir == null) {
            throw new NullPointerException("Argument is null");
        }
        if (File.separatorChar != dir.charAt(dir.length() - 1)) {
            dir = dir + File.separatorChar;
        }
        rootDir = dir;
    }

    public Class loadClass(String className) throws ClassNotFoundException {
        Class result = null;

        result = cache.get(className);
        if (result != null) {
            return result;
        }

        try {
            return findSystemClass(className);
        } catch (ClassNotFoundException e) {
            // NOP
        }

        String localPath = className.replace('.', File.separatorChar)
                + ".class";
        String absolutePath = rootDir + localPath;

        File file = new File(absolutePath);
        if (!file.exists()) {
            throw new ClassNotFoundException();
        }

        byte[] bytes = getClassFileBytes(file);
        result = defineClass(className, bytes, 0, bytes.length, null);

        if (result == null) {
            throw new ClassNotFoundException();
        }

        cache.put(className, result);

        return result;
    }

    private byte[] getClassFileBytes(File file) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try (InputStream in = new FileInputStream(file)) {
            int data;
            while ((data = in.read()) != -1) {
                out.write(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toByteArray();
    }

}
