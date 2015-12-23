package com.nixsolutions.laba10.task3;

import com.nixsolutions.laba10.task2.ArrayCollectionTest;
import com.nixsolutions.laba10.task2.ArrayIteratorTest;

import interfaces.junit.JunitTester;
import junit.framework.JUnit4TestAdapter;
import junit.framework.TestSuite;

/**
 * @author Ponarin Igor
 * @since 20.12.2015
 */
public class TestSuiteCreator implements JunitTester {
    @Override
    public TestSuite suite() {
        TestSuite testSuite = new TestSuite(
                "Test ArrayCollection and ArrayIterator");
        testSuite.addTest(new JUnit4TestAdapter(ArrayCollectionTest.class));
        testSuite.addTestSuite(ArrayIteratorTest.class);
        return testSuite;
    }
}
