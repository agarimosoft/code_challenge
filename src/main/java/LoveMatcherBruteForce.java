import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Finds all possible matches
 */
public class LoveMatcherBruteForce extends LoveMatcher {
    public LoveMatcherBruteForce(InputStream is) {
        super(is);
    }

    public Set<Match> match() {
        matchMillis = System.nanoTime() / 1000;
        Set<Match> aux = users.stream()
                .map(this::match)
                .flatMap(Collection::stream)
                .filter(x -> !x.a.equals(x.b)) //only necessary when 50's
                .collect(Collectors.toSet());
        matchMillis = System.nanoTime() / 1000 - matchMillis;

        return aux;
    }

    protected Set<Match> match(User user) {
        int value = 100 - user.loveScore;
        return users.stream()
                .filter(c -> c.loveScore == value)
                .map(c -> new Match(user.id, c.id))
                .collect(Collectors.toSet());
    }
}


