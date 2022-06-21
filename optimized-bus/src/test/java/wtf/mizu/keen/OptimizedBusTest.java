package wtf.mizu.keen;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assumptions.assumeTrue;

public class OptimizedBusTest {

    private final Bus bus = new OptimizedBus();
    private final IncreasingSubscription increasingSubscription = new IncreasingSubscription();

    @Test
    void invocationTest() {
        Listener l = () -> Map.of(Integer.class, List.of(increasingSubscription));
        bus.add(l);
        bus.publish(1);
        assumeTrue(increasingSubscription.number == 1);
    }

    public static class IncreasingSubscription implements Subscription<Integer> {
        int number;

        @Override
        public Class<Integer> topic() {
            return Integer.class;
        }

        @Override
        public int priority() {
            return 0;
        }

        @Override
        public void consume(Integer event) {
            number++;
        }
    }

}