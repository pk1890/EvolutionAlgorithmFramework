import genetics.operators.RandomTournamentSelector;
import genetics.utilities.Pair;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Random;

public class UtilsTests {
    @Test
    void testPair(){
        Random random = new Random();
        for(int i = 0; i < 100; i++){
            int key = random.nextInt(1000000);
            double value = random.nextDouble();
            Pair<Integer, Double> pair = new Pair<>(key, value);
            Assertions.assertEquals(key, pair.getFirst());
            Assertions.assertEquals(value, pair.getSecond());
            key = random.nextInt(1000000);
            value = random.nextDouble();
            pair.setFirst(key);
            pair.setSecond(value);
            Assertions.assertEquals(key, pair.getFirst());
            Assertions.assertEquals(value, pair.getSecond());

        }
    }
}
