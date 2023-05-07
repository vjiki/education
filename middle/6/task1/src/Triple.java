import java.util.Objects;

public class Triple <T, U, S> extends Pair<T,U> {

    private final S third;

    public Triple(T first, U second, S third) {
        super(first, second);
        this.third = third;
    }

    public S getThird() {
        return third;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Triple<?, ?, ?> triple = (Triple<?, ?, ?>) o;
        return Objects.equals(third, triple.third);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), third);
    }

    @Override
    public String toString() {
        return "Triple{" +
                "first=" + super.getFirst() +
                ", second=" + super.getSecond() +
                ", third=" + third +
                '}';
    }
}
