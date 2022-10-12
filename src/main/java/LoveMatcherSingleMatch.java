import java.io.InputStream;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LoveMatcherSingleMatch extends LoveMatcher {
    private final Map<Integer, Stack<User>> map;
    public LoveMatcherSingleMatch(InputStream is) {
        super(is);
        this.map = IntStream.range(0, 101)
                .boxed()
                .collect(Collectors.toMap(Function.identity(), i -> new Stack<>()));
    }

    public Set<Match> match() {
        matchMillis = System.nanoTime() / 1000;

        Set<Match> matches = new HashSet<>();

        for (User user : users) {
            int requiredLoveScore = 100 - user.loveScore;

            if (map.get(requiredLoveScore).isEmpty()) {
                map.get(user.loveScore).push(user);
            } else {
                matches.add(new Match(user.id, map.get(requiredLoveScore).pop().id));
            }
        }

        matchMillis = System.nanoTime() / 1000 - matchMillis;

        return matches;
    }
}
