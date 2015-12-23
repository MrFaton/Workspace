package com.nixsolutions.laba8.task1;

import interfaces.task8.SerializableUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

/**
 * @author Mr_Faton
 * @since 18.12.2015
 */
public class SerializableUtilsImpl implements SerializableUtils {
    @Override
    public void serialize(OutputStream outputStream, Object object) {
        if (outputStream == null || object == null) {
            throw new NullPointerException("One or two arguments are null! "
                    + "outputStream=" + outputStream + " object=" + object);
        }
        try {
            ObjectOutputStream objectOut = new ObjectOutputStream(outputStream);
            objectOut.writeObject(object);
            objectOut.flush();
        } catch (IOException ioEx) {
            throw new RuntimeException(ioEx);
        }
    }

    @Override
    public Object deserialize(InputStream inputStream) {
        if (inputStream == null) {
            throw new NullPointerException("Argiment is null");
        }
        try {
            ObjectInputStream objectIn = new ObjectInputStream(inputStream);
            return objectIn.readObject();
        } catch (ClassNotFoundException classEx) {
            throw new RuntimeException(classEx);
        } catch (IOException ioEx) {
            throw new RuntimeException(ioEx);
        }
    }
}
