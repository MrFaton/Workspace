package com.nixsolutions.laba6;

import com.nixsolutions.laba6.task2.IOUtilsImpl;
import interfaces.task6.IOUtils;
import org.junit.*;

import java.io.*;

import static org.junit.Assert.*;


/**
 * Testing class for IOUtilsImpl class
 *
 * @author Mr_Faton
 * @since 20.12.2015
 */
public class IOUtilsImplTest {
    private IOUtils ioUtils;
    private static final String sourceFilePath = System.getProperty("user.dir")
            + File.separator + "source.f";
    private static final String destFilePath = System.getProperty("user.dir")
            + File.separator + "dest.f";

    @BeforeClass
    public static void generalSetUp() throws Exception {
        byte[] file = new byte[1024 * 1024];
        try (FileOutputStream out = new FileOutputStream(sourceFilePath)) {
            out.write(file);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @AfterClass
    public static void generalTearDown() {
        new File(sourceFilePath).delete();
    }

    @Before
    public void setUp() {
        ioUtils = new IOUtilsImpl();
    }

    @After
    public void tearDown() {
        new File(destFilePath).delete();
    }


    @Test(timeout = 10000)
    public void testCopy() {
        ioUtils.copyFile(sourceFilePath, destFilePath);
        assertTrue("Dest file must exists", new File(destFilePath).exists());
    }

    @Test(timeout = 2000)
    public void testBufferedCopy() {
        ioUtils.copyFileBuffered(new File(sourceFilePath), new File(destFilePath));
        assertTrue("Dest file must exists", new File(destFilePath).exists());
    }

    @Test(timeout = 2000)
    public void testCopyBest() {
        ioUtils.copyFileBest(sourceFilePath, destFilePath);
        assertTrue("Dest file must exists", new File(destFilePath).exists());
    }

    @Test
    public void testFindFiles() {
        String[] files = ioUtils.findFiles(System.getProperty("user.dir"));
        assertNotNull("Must be at least one file (source.f)", files);
    }

    @Test
    public void testFindFilesWithExtension() {
        String[] files = ioUtils.findFiles(System.getProperty("user.dir"), ".f");
        assertNotNull("Must be at least one file (source.f)", files);
    }

    @Test
    public void testReplaceChars() {
        String original = "abcdefgh";
        String inChars = "adg";
        String outChars = "147";

        String expected = "1bc4ef7h";
        String actual = null;

        try (Reader reader = new StringReader(original);
             Writer writer = new StringWriter()) {
            ioUtils.replaceChars(reader, writer, inChars, outChars);
            actual = writer.toString();
        } catch (IOException ex) {
            fail("Wrong exception");
        }
        assertNotNull("Result String can't be null", actual);
        assertEquals("Strings must equals", expected, actual);
    }
}
