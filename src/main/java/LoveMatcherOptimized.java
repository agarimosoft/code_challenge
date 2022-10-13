import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Finds all possible matches
 */
public class LoveMatcherOptimized extends LoveMatcher{

    private final long lowerSize;
    private final long upperSize;

    public LoveMatcherOptimized(InputStream is) {
        super(is);
        lowerSize = users.stream().filter(c -> c.loveScore < 50).count();
        upperSize = users.stream().filter(c -> c.loveScore > 50).count();

    }
    public Set<Match> match() {
        return (lowerSize < upperSize) ? matchReverse() : matchForward();
    }

    public Set<Match> matchForward() {
        matchMillis = System.nanoTime() /1000;

        List<User> lower = users.stream().filter(c -> c.loveScore < 50).toList();
        List<User> mid = users.stream().filter(c -> c.loveScore == 50).toList();
        List<User> upper = users.stream().filter(c -> c.loveScore > 50).sorted(Comparator.reverseOrder()).toList();
        Set<Match> aux = matchAll(lower, mid, upper);

        matchMillis = System.nanoTime() / 1000 - matchMillis;

        return aux;
    }

    public Set<Match> matchReverse() {
        matchMillis = System.nanoTime() / 1000;

        List<User> lower = users.stream().filter(c -> c.loveScore < 50).sorted(Comparator.reverseOrder()).toList();
        List<User> mid = users.stream().filter(c -> c.loveScore == 50).toList();
        List<User> upper = users.stream().filter(c -> c.loveScore > 50).toList();
        Set<Match> aux = matchAll(upper, mid, lower);

        matchMillis = System.nanoTime() / 1000 - matchMillis;

        return aux;
    }

    public Set<Match> matchAll(List<User> smallerList, List<User> mid, List<User> biggerList) {
        Set<Match> aux = smallerList.stream()
                .map(c -> this.findMatches(c, biggerList))
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());

        if (mid.size() > 1) {
            aux.addAll(mid.stream()
                    .map(c -> this.findMatches(c, mid))
                    .flatMap(Collection::stream)
                    .filter(x -> !x.a.equals(x.b))
                    .collect(Collectors.toSet()));
        }

        return aux;
    }

    public Set<Match> findMatches(User user, List<User> users) {
        Set<Match> aux = new HashSet<>();
        int value = 100 - user.loveScore;

        for (User userAux : users) {
            if (userAux.loveScore < value) {
                break;
            } else if (userAux.loveScore == value) {
                aux.add(new Match(user.id, userAux.id));
            }
        }

        return aux;
    }
}
