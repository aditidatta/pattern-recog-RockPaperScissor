package game;

public class Pattern {
    private String pattern;

    public Pattern(String p) {
        pattern = p;
    }

    public String getPattern() {
        return pattern;
    }

    @Override
    public int hashCode() {
        return pattern.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Pattern)) {
            return false;
        }
        Pattern s = (Pattern) o;
        return pattern.equals(s.getPattern());
    }
}
