package games.tangelo;

import lombok.Getter;

import java.util.Arrays;
import java.util.Random;

@Getter
public class LuckyWheel {
    private Integer[] multiplier;
    private Integer[] probability;

    private Integer[] probabilitySegment;
    private Random random;

    public LuckyWheel() {
        this(new Integer[]{3, 7, 15, 20, 50, 80}, new Integer[]{40, 30, 20, 5, 3, 2});
    }

    public LuckyWheel(Integer[] multiplier, Integer[] probability) {
        if (multiplier.length != probability.length)
            throw new RuntimeException("Multiplier and Probability length for Lucky Wheel must be equal!");
        if (Arrays.stream(probability).mapToInt(Integer::intValue).sum() != 100)
            throw new RuntimeException("Probability sum should be equal 100!");
        this.multiplier = multiplier;
        this.probability = probability;
        buildProbabilitySegment();
        random = new Random();
    }

    public LuckyWheel(Integer[] multiplier, Double[] probability) {
        this(multiplier, Arrays.stream(probability).mapToInt(value -> (int) (value * 100)).boxed().toArray(Integer[]::new));
    }

    public Long twist(Long bet) {
        return bet * selectMultiplier(this.random.nextInt(100));
    }

    private void buildProbabilitySegment() {
        probabilitySegment = new Integer[this.probability.length];
        probabilitySegment[0] = probability[0];
        for (int i = 1; i < probability.length; i++) {
            probabilitySegment[i] = probabilitySegment[i - 1] + probability[i];
        }
    }

    private Integer selectMultiplier(Integer random) {
        int segment = 0;
        for (int i = 0; i < probabilitySegment.length; i++) {
            segment = i;
            if (random < probabilitySegment[i]) {
                break;
            }
        }
        return this.multiplier[segment];
    }
}
