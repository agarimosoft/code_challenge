import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

public class LoveMatcherBruteForce extends LoveMatcher {
    public LoveMatcherBruteForce(InputStream is) {
        super(is);
    }

    public Set<Match> match() {
        matchMillis = System.currentTimeMillis();
        Set<Match> aux = users.stream()
                .map(this::match)
                .flatMap(Collection::stream)
                .filter(x -> !x.a.equals(x.b)) //only necessary when 50's
                .collect(Collectors.toSet());
        matchMillis = System.currentTimeMillis() - matchMillis;

        return aux;
    }

    public Set<Match> match(User user) {
        int value = 100 - user.loveScore;
        return users.stream()
                .filter(c -> c.loveScore == value)
                .map(c -> new Match(user.id, c.id))
                .collect(Collectors.toSet());
    }
}


