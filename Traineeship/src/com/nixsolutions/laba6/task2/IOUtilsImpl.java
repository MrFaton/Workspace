package com.nixsolutions.laba6.task2;

import interfaces.task6.IOUtils;

import java.io.*;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of IOUtils interface
 * 
 * @author ponarin
 *
 */
public class IOUtilsImpl implements IOUtils {

    @Override
    public void copyFile(String source, String dest) {
        if (source == null || dest == null) {
            throw new NullPointerException("One or two arguments are null! "
                    + "source= " + source + " dest=" + dest);
        }

        try (InputStream in = new FileInputStream(source);
                OutputStream out = new FileOutputStream(dest)) {
            while (true) {
                int data = in.read();
                if (data == -1)
                    break;
                out.write(data);
            }
            out.flush();
        } catch (FileNotFoundException fileNotFoundEx) {
            throw new IllegalArgumentException("Exception during copy",
                    fileNotFoundEx);
        } catch (IOException ex) {
            new File(dest).delete();
            throw new RuntimeException("Dest file was deleted", ex);
        }
    }

    @Override
    public void copyFileBest(String source, String dest) {
        if (source == null || dest == null) {
            throw new NullPointerException("One or two foldars are null: "
                    + "source=" + source + " dest=" + dest);
        }

        try (FileInputStream in = new FileInputStream(source);
                FileOutputStream out = new FileOutputStream(dest);
                FileChannel inChannel = in.getChannel();
                FileChannel outChannel = out.getChannel()) {

            outChannel.transferFrom(inChannel, 0, inChannel.size());
        } catch (FileNotFoundException fileNotFoundEx) {
            throw new IllegalArgumentException("Exception during copy",
                    fileNotFoundEx);
        } catch (IOException ex) {
            new File(dest).delete();
            throw new RuntimeException("Dest file was deleted", ex);
        }
    }

    @Override
    public void copyFileBuffered(File source, File dest) {
        if (source == null || dest == null) {
            throw new NullPointerException("One or two arguments are null! "
                    + "source= " + source + " dest=" + dest);
        }

        try (InputStream bIn = new BufferedInputStream(
                new FileInputStream(source));
                OutputStream bOut = new BufferedOutputStream(
                        new FileOutputStream(dest))) {
            int data;
            while ((data = bIn.read()) != -1) {
                bOut.write(data);
            }
            bOut.flush();
        } catch (FileNotFoundException fileNotFoundEx) {
            throw new IllegalArgumentException("Exception during copy",
                    fileNotFoundEx);
        } catch (IOException ex) {
            dest.delete();
            throw new RuntimeException("Dest file was deleted", ex);
        }
    }

    @Override
    public String[] findFiles(String directoryPath) {
        if (directoryPath == null) {
            throw new NullPointerException("Argument is null");
        }

        File dir = new File(directoryPath);
        if (!dir.exists()) {
            throw new IllegalArgumentException("Directory does't exists");
        }

        List<String> fileList = new ArrayList<>();
        String anyExtention = "";
        fileSearcher(dir, anyExtention, fileList);
        return fileList.toArray(new String[fileList.size()]);
    }

    @Override
    public String[] findFiles(String directoryPath, String extension) {
        if (directoryPath == null) {
            throw new NullPointerException("Directory is null");
        }

        File dir = new File(directoryPath);
        if (!dir.exists()) {
            throw new IllegalArgumentException("Directory does't exists");
        }

        if (extension == null) {
            extension = "";
        }

        List<String> fileList = new ArrayList<>();
        fileSearcher(dir, extension, fileList);
        return fileList.toArray(new String[fileList.size()]);
    }

    @Override
    public void replaceChars(Reader reader, Writer writer, String inChars,
            String outChars) {
        if (reader == null || writer == null) {
            throw new NullPointerException("One or two streams are null! "
                    + "reader=" + reader + " writer=" + writer);
        }
        if (inChars == null || outChars == null) {
            inChars = "";
            outChars = "";
        }
        if (inChars.length() != outChars.length()) {
            throw new IllegalArgumentException(
                    "Input chars length != output chars length! "
                            + "input chars length=" + inChars.length()
                            + "output chars length=" + outChars.length());
        }

        int data;
        try {
            while ((data = reader.read()) != -1) {
                int index = inChars.indexOf(data);
                if (index >= 0) {
                    writer.write(outChars.charAt(index));
                } else {
                    writer.write(data);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Recursive file searcher
     * 
     * @param directory
     *            Root directory
     * @param extension
     *            File's extension
     * @param fileList
     *            List where will be added find files
     */
    private void fileSearcher(File directory, String extension,
            List<String> fileList) {
        File[] files = directory.listFiles();

        for (File file : files) {
            if (file.isDirectory()) {
                fileSearcher(file, extension, fileList);
            } else {
                if (extension.length() == 0) {
                    fileList.add(file.getPath());
                } else {
                    String fileExtension = getFileExtension(file);
                    if (extension.equals(fileExtension)) {
                        fileList.add(file.getPath());
                    }
                }
            }
        }
    }

    /**
     * Extract file extension from file's absolute path
     * 
     * @param file
     *            Path to file
     * @return File's extension
     */
    private String getFileExtension(File file) {
        String name = file.getName();
        try {
            return name.substring(name.lastIndexOf("."));
        } catch (Exception e) {
            return "";
        }
    }

}
