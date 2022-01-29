package toothlessblue;

import java.util.Random;

public class RandomUtils {
    private static final Random random = new Random();

    /**
     * @return a random integer between minInclusive and maxExclusive
     */
    public static int getRandomInt(int minInclusive, int maxExclusive) {
        return RandomUtils.random.nextInt(minInclusive, maxExclusive);
    }

    public static <T> T getRandomOfArray(T[] array) {
        int max = array.length;

        return array[getRandomInt(0, max)];
    }

    public static boolean rollChance(float chance) {
        if (chance == 0) return false;

        return RandomUtils.random.nextFloat() < chance;
    }
}
