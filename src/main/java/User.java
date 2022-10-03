public class User implements Comparable<User>{
    String id;
    String name;
    int loveScore;

    @Override
    public int compareTo(User o) {
        return Integer.compare(loveScore, o.loveScore);
    }
}
