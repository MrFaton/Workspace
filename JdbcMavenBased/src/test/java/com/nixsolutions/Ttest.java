package com.nixsolutions;

import org.junit.Test;

public class Ttest {
    private static final String DATASET_COMMON = "dataset/role/common.xml";
    
    @Test
    public void ttt() {
        Object ob = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream(DATASET_COMMON);
        System.out.println(ob);
    }
}
