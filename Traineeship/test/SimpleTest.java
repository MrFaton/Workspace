import interfaces.task5.ArrayCollection;
import org.junit.Test;

import com.nixsolutions.laba11.task4.ArrayCollectionWithLog;

import java.util.Arrays;

public class SimpleTest {
    @Test
    public void test() {
        ArrayCollection<Integer> collection = new ArrayCollectionWithLog<>();
        collection.add(1);
        collection.addAll(Arrays.asList(1, 8, -5, null, 3));
    }
}
