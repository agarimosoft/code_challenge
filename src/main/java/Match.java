public class Match {
     String a;
     String b;

    public Match(String a, String b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Match match = (Match) o;
        return (a.equals(match.a) && b.equals(match.b) || (a.equals(match.b) && b.equals(match.a)));
    }

    @Override
    public int hashCode() {
        int result1 = 31 * a.hashCode() + b.hashCode();
        int result2 = 31 * b.hashCode() + a.hashCode();
        return result1 + result2;
    }

    @Override
    public String toString() {
        return "Match{" +
                "a='" + a + '\'' +
                ", b='" + b + '\'' +
                '}';
    }
}
