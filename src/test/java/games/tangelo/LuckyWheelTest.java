package games.tangelo;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class LuckyWheelTest {
    /**
     * Test case to create new Lucy Wheel with specified parameters (Probability is int).
     */
    @Test
    public void newLuckyWheelWithIntegerProbabilityTest() {
        final Integer[] multiplier = new Integer[]{1, 2, 3, 4, 5};
        final Integer[] probability = new Integer[]{20, 30, 10, 15, 25};
        final Integer[] probabilitySegment = new Integer[]{20, 50, 60, 75, 100};

        LuckyWheel wheel = new LuckyWheel(multiplier, probability);
        Assert.assertArrayEquals(multiplier, wheel.getMultiplier());
        Assert.assertArrayEquals(probability, wheel.getProbability());
        Assert.assertArrayEquals(probabilitySegment, wheel.getProbabilitySegment());
    }

    /**
     * Test case to create new Lucy Wheel with specified parameters (Probability is Double).
     */
    @Test
    public void newLuckyWheelWithDoubleProbabilityTest() {
        final Integer[] multiplier = new Integer[]{1, 2, 3, 4, 5};
        final Double[] probability = new Double[]{.20, .30, .10, .15, .25};
        final Integer[] expectedProbability = new Integer[]{20, 30, 10, 15, 25};
        final Integer[] probabilitySegment = new Integer[]{20, 50, 60, 75, 100};

        LuckyWheel wheel = new LuckyWheel(multiplier, probability);
        Assert.assertArrayEquals(multiplier, wheel.getMultiplier());
        Assert.assertArrayEquals(expectedProbability, wheel.getProbability());
        Assert.assertArrayEquals(probabilitySegment, wheel.getProbabilitySegment());
    }

    /**
     * Test case to create new Lucy Wheel with specified parameters (Probability is less 100).
     */
    @Test(expected = RuntimeException.class)
    public void newLuckyWheelWithProbabilityLess100Test() {
        final Integer[] multiplier = new Integer[]{1, 2, 3, 4, 5};
        final Integer[] probability = new Integer[]{20, 30, 10, 15};

        new LuckyWheel(multiplier, probability);
    }

    /**
     * Test case to create new Lucy Wheel with specified parameters (Probability is greater 100).
     */
    @Test(expected = RuntimeException.class)
    public void newLuckyWheelWithProbabilityGreater100Test() {
        final Integer[] multiplier = new Integer[]{1, 2, 3, 4, 5};
        final Integer[] probability = new Integer[]{20, 30, 10, 15, 35};

        new LuckyWheel(multiplier, probability);
    }

    /**
     * Test case to create new Lucy Wheel when probability and multiplier length not equals.
     */
    @Test(expected = RuntimeException.class)
    public void newLuckyWheelWithDifferentLengthParamsTest() {
        final Integer[] multiplier = new Integer[]{1, 2, 3, 4, 5};
        final Integer[] probability = new Integer[]{20, 10, 15, 35};

        new LuckyWheel(multiplier, probability);
    }

    /**
     * Test case to check correct working wheel with specified probability.
     * We emulate near 10000 twists and calculate probability on that.
     */
    @Test
    public void newLuckyWheelTwist() {
        final Integer[] multiplier = new Integer[]{3, 7, 15, 20, 50, 80};
        final Integer[] probability = new Integer[]{40, 30, 20, 5, 3, 2};

        final Map<Integer, Integer> multipliersCount = new HashMap<>();
        for (int i = 0; i < multiplier.length; i++) {
            multipliersCount.put(multiplier[i], 0);
        }
        Long twistsCount = 100000000L;

        final LuckyWheel luckyWheel = new LuckyWheel(multiplier, probability);
        for (long i = 10000; i < twistsCount; i++) {
            Integer currentMultiplier = (int) (luckyWheel.twist(i) / i);
            int count = multipliersCount.getOrDefault(currentMultiplier, 0);
            multipliersCount.put(currentMultiplier, count + 1);
        }

        for(int i =0; i < multiplier.length; i++) {
            Assert.assertEquals(probability[i], multipliersCount.get(multiplier[i]) * 1.0 / twistsCount * 100, 0.1);
        }
    }
}