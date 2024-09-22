import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Objects;

public class Schedular {

    // Объяснить готовое решение можно так:
    // чем раньше закончится мероприятие,
    // тем больше времени для других встреч у тебя останется!

    private static int[] getMaxNumberOfLessons(Lesson[] lessons) {
        int[] indexes = new int[lessons.length];

        if (lessons.length > 0) {
            Arrays.fill(indexes, -1);
            Arrays.sort(lessons);
            indexes[0] = 0;
            Lesson currentLesson = lessons[0];

            for (int i = 1; i < lessons.length; i++) {
                if (lessons[i].getStart() >= currentLesson.getEnd()) {
                    indexes[i] = i;
                    currentLesson = lessons[i];
                }
            }
        }

        return indexes;
    }


    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int numberOfLessons = readInt(reader);
            Lesson[] lessons = new Lesson[numberOfLessons];
            for (int i = 0; i < numberOfLessons; i++) {
                double[] days = readArray(reader);
                lessons[i] = new Lesson(days[0], days[1]);
            }

            int[] indexes = getMaxNumberOfLessons(lessons);
            StringBuilder sb = new StringBuilder();
            sb.append(Arrays.stream(indexes).filter(e -> e != -1).count()).append("\n");
            DecimalFormat format = new DecimalFormat("0.##");
            for (int index: indexes) {
                if (index != -1) {

                    sb.append(format.format(lessons[index].getStart()))
                            .append(" ")
                            .append(format.format(lessons[index].getEnd()))
                            .append("\n");
                }
            }
            System.out.println(sb);
        }
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static double[] readArray(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().split(" "))
                .map(Double::parseDouble)
                .mapToDouble(Double::doubleValue).toArray();
    }

    public static class Lesson implements Comparable<Lesson> {
        private final Double start;
        private final Double end;

        public Double getStart() {
            return this.start;
        }

        public Double getEnd() {
            return this.end;
        }

        public Lesson(Double paramK, Double paramV) {
            this.start = paramK;
            this.end = paramV;
        }

        public String toString() {
            return this.start + "-" + this.end;
        }

        public int hashCode() {
            return this.start.hashCode() * 13 + (this.end == null ? 0 : this.end.hashCode());
        }

        public boolean equals(Object paramObject) {
            if (this == paramObject) return true;
            if ((paramObject instanceof Lesson)) {
                Lesson localLesson = (Lesson) paramObject;
                if (!Objects.equals(this.start, localLesson.start)) return false;
                return Objects.equals(this.end, localLesson.end);
            }
            return false;
        }

        @Override
        public int compareTo(Lesson o) {
            if (this.getEnd().equals(o.getEnd())) {
                return this.getStart().compareTo(o.getStart());
            }

            return this.getEnd().compareTo(o.getEnd());
        }
    }
}
