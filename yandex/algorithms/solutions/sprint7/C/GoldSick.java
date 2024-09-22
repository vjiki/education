import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Objects;

public class GoldSick {

    // Объяснить готовое решение можно так:
    // чем раньше закончится мероприятие,
    // тем больше времени для других встреч у тебя останется!

    private static long getMaxCostOfGolds(Gold[] golds, int backpackSize) {

        int currentSize = 0;
        long maxCost = 0;
        if (golds.length > 0) {
            Arrays.sort(golds);

            for (int i = 0; i < golds.length; i++) {
                if (currentSize >= backpackSize) {
                    break;
                } else {
                    Gold currentGold = golds[i];
                    if (currentGold.getAmount() <= (backpackSize - currentSize)) {
                        maxCost += (long) currentGold.getCost() * currentGold.getAmount();
                        currentSize += currentGold.getAmount();
                    } else {
                        maxCost += (long) currentGold.getCost() * (backpackSize - currentSize);
                        currentSize += (backpackSize - currentSize);
                    }
                }
            }
        }

        return maxCost;
    }


    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int backpackSize = readInt(reader);
            int goldsNum = readInt(reader);
            Gold[] golds = new Gold[goldsNum];
            for (int i = 0; i < goldsNum; i++) {
                int[] gold = readArray(reader);
                golds[i] = new Gold(gold[0], gold[1]);
            }

            System.out.println(getMaxCostOfGolds(golds, backpackSize));
        }
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static int[] readArray(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().split(" "))
                .map(Integer::parseInt)
                .mapToInt(Integer::intValue).toArray();
    }

    public static class Gold implements Comparable<Gold> {
        private final Integer cost;
        private final Integer amount;

        public Integer getCost() {
            return cost;
        }

        public Integer getAmount() {
            return amount;
        }

        public Gold(Integer paramK, Integer paramV) {
            this.cost = paramK;
            this.amount = paramV;
        }

        public String toString() {
            return this.cost + "-" + this.amount;
        }

        public int hashCode() {
            return this.cost.hashCode() * 13 + (this.amount == null ? 0 : this.amount.hashCode());
        }

        public boolean equals(Object paramObject) {
            if (this == paramObject) return true;
            if ((paramObject instanceof Gold)) {
                Gold localGold = (Gold) paramObject;
                if (!Objects.equals(this.cost, localGold.cost)) return false;
                return Objects.equals(this.amount, localGold.amount);
            }
            return false;
        }

        @Override
        public int compareTo(Gold o) {

            return o.getCost().compareTo(this.getCost());
        }
    }
}
