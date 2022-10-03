import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.Objects;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoveMatcherTest {

    private InputStream inputStream;
    private LoveMatcher loveMatcher;
    private Set<Match> matches;
    private static final String fileName100 = "data100.json";
    private static final String fileName1000 = "data1000.json";

    @BeforeAll
    public static void beforeAll(){
        //Warm up
        InputStream inputStream = Objects.requireNonNull(LoveMatcherTest.class.getClassLoader().getResourceAsStream(fileName1000));
        LoveMatcher loveMatcher = new LoveMatcherBruteForce(inputStream);
    }

    @Test
    public void bruteforce100() {
        inputStream = Objects.requireNonNull(LoveMatcherTest.class.getClassLoader().getResourceAsStream(fileName100));
        loveMatcher = new LoveMatcherBruteForce(inputStream);
        matches = loveMatcher.match();

        assertEquals(17, matches.size());

        System.out.println("BruteForce -- totalMatches:" + matches.size());
        System.out.println(loveMatcher.getMetrics());
    }

    @Test
    public void bruteforce1000() {
        inputStream = Objects.requireNonNull(LoveMatcherTest.class.getClassLoader().getResourceAsStream(fileName1000));
        loveMatcher = new LoveMatcherBruteForce(inputStream);
        matches = loveMatcher.match();

        assertEquals(1435, matches.size());

        System.out.println("BruteForce -- totalMatches:" + matches.size());
        System.out.println(loveMatcher.getMetrics());
    }

    @Test
    public void optimized100() {
        inputStream = Objects.requireNonNull(LoveMatcherTest.class.getClassLoader().getResourceAsStream(fileName100));
        loveMatcher = new LoveMatcherOptimized(inputStream);
        matches = loveMatcher.match();

        assertEquals(17, matches.size());

        System.out.println("Optimized -- totalMatches:" + matches.size());
        System.out.println(loveMatcher.getMetrics());
    }

    @Test
    public void optimized1000() {
        inputStream = Objects.requireNonNull(LoveMatcherTest.class.getClassLoader().getResourceAsStream(fileName1000));
        loveMatcher = new LoveMatcherOptimized(inputStream);
        matches = loveMatcher.match();

        assertEquals(1435, matches.size());

        System.out.println("Optimized -- totalMatches:" + matches.size());
        System.out.println(loveMatcher.getMetrics());
    }

    @Test
    public void single100() {
        inputStream = Objects.requireNonNull(LoveMatcherTest.class.getClassLoader().getResourceAsStream(fileName100));
        loveMatcher = new LoveMatcherSingleMatch(inputStream);
        matches = loveMatcher.match();

        assertEquals(6, matches.size());

        System.out.println("SingleMatch -- totalMatches:" + matches.size());
        System.out.println(loveMatcher.getMetrics());
    }

    @Test
    public void single1000() {
        inputStream = Objects.requireNonNull(LoveMatcherTest.class.getClassLoader().getResourceAsStream(fileName1000));
        loveMatcher = new LoveMatcherSingleMatch(inputStream);
        matches = loveMatcher.match();

        assertEquals(79, matches.size());

        System.out.println("SingleMatch -- totalMatches:" + matches.size());
        System.out.println(loveMatcher.getMetrics());
    }

}
