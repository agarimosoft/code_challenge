import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.Formatter;
import java.util.Objects;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoveMatcherTest {
    private static InputStream inputStream;
    private static LoveMatcher loveMatcher;
    private static final String FILE_NAME_100 = "data100.json";
    private static final String FILE_NAME_1000 = "data1000.json";
    private static final String RESULT_FORMAT = "%s - %s matches";
    private Set<Match> matches;

    @BeforeAll
    public static void beforeAll() {
        //Warm up
        inputStream = Objects.requireNonNull(LoveMatcherTest.class.getClassLoader().getResourceAsStream(FILE_NAME_1000));
        loveMatcher = new LoveMatcherParallel(inputStream);
        loveMatcher.match();

        inputStream = Objects.requireNonNull(LoveMatcherTest.class.getClassLoader().getResourceAsStream(FILE_NAME_1000));
        loveMatcher = new LoveMatcherOptimized(inputStream);
        loveMatcher.match();

        inputStream = Objects.requireNonNull(LoveMatcherTest.class.getClassLoader().getResourceAsStream(FILE_NAME_1000));
        loveMatcher = new LoveMatcherBruteForce(inputStream);
        loveMatcher.match();

        inputStream = Objects.requireNonNull(LoveMatcherTest.class.getClassLoader().getResourceAsStream(FILE_NAME_1000));
        loveMatcher = new LoveMatcherSingleMatch(inputStream);
        loveMatcher.match();
    }

    @Test
    public void all100() {
        bruteforce100();
        parallel100();
        optimized100();
    }

    @Test
    public void all1000() {
        bruteforce1000();
        parallel1000();
        optimized1000();
    }

    @Test
    public void singleMatch(){
        single100();
        single1000();
    }

    public void bruteforce100() {
        inputStream = Objects.requireNonNull(LoveMatcherTest.class.getClassLoader().getResourceAsStream(FILE_NAME_100));
        loveMatcher = new LoveMatcherBruteForce(inputStream);
        matches = loveMatcher.match();

        assertEquals(17, matches.size());

        System.out.println(new Formatter().format(RESULT_FORMAT, loveMatcher.getMetrics(), matches.size()));
    }

    public void bruteforce1000() {
        inputStream = Objects.requireNonNull(LoveMatcherTest.class.getClassLoader().getResourceAsStream(FILE_NAME_1000));
        loveMatcher = new LoveMatcherBruteForce(inputStream);
        matches = loveMatcher.match();

        assertEquals(1435, matches.size());

        System.out.println(new Formatter().format(RESULT_FORMAT, loveMatcher.getMetrics(), matches.size()));
    }

    public void optimized100() {
        inputStream = Objects.requireNonNull(LoveMatcherTest.class.getClassLoader().getResourceAsStream(FILE_NAME_100));
        loveMatcher = new LoveMatcherOptimized(inputStream);
        matches = loveMatcher.match();

        assertEquals(17, matches.size());

        System.out.println(new Formatter().format(RESULT_FORMAT, loveMatcher.getMetrics(), matches.size()));
    }

    public void optimized1000() {
        inputStream = Objects.requireNonNull(LoveMatcherTest.class.getClassLoader().getResourceAsStream(FILE_NAME_1000));
        loveMatcher = new LoveMatcherOptimized(inputStream);
        matches = loveMatcher.match();

        assertEquals(1435, matches.size());

        System.out.println(new Formatter().format(RESULT_FORMAT, loveMatcher.getMetrics(), matches.size()));
    }

    public void single100() {
        inputStream = Objects.requireNonNull(LoveMatcherTest.class.getClassLoader().getResourceAsStream(FILE_NAME_100));
        loveMatcher = new LoveMatcherSingleMatch(inputStream);
        matches = loveMatcher.match();

        assertEquals(6, matches.size());

        System.out.println(new Formatter().format(RESULT_FORMAT, loveMatcher.getMetrics(), matches.size()));
    }

    public void single1000() {
        inputStream = Objects.requireNonNull(LoveMatcherTest.class.getClassLoader().getResourceAsStream(FILE_NAME_1000));
        loveMatcher = new LoveMatcherSingleMatch(inputStream);
        matches = loveMatcher.match();

        assertEquals(79, matches.size());

        System.out.println(new Formatter().format(RESULT_FORMAT, loveMatcher.getMetrics(), matches.size()));
    }

    public void parallel100() {
        inputStream = Objects.requireNonNull(LoveMatcherTest.class.getClassLoader().getResourceAsStream(FILE_NAME_100));
        loveMatcher = new LoveMatcherParallel(inputStream);
        matches = loveMatcher.match();

        assertEquals(17, matches.size());

        System.out.println(new Formatter().format(RESULT_FORMAT, loveMatcher.getMetrics(), matches.size()));
    }

    public void parallel1000() {
        inputStream = Objects.requireNonNull(LoveMatcherTest.class.getClassLoader().getResourceAsStream(FILE_NAME_1000));
        loveMatcher = new LoveMatcherParallel(inputStream);
        matches = loveMatcher.match();

        assertEquals(1435, matches.size());

        System.out.println(new Formatter().format(RESULT_FORMAT, loveMatcher.getMetrics(), matches.size()));
    }
}
