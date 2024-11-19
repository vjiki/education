import java.util.Comparator;
import java.util.PriorityQueue;

public class RobotOnStairs {

    int initialStair = 0;

    // 0+1=1, 1+1=2, 1+2=3, 2+3=5, 3+5=8, 5+8=13
    static int getNumberOfWays(int currentStep) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Comparator.reverseOrder());

        if (currentStep == 0 || currentStep == 1) return 1;

        int prev = 0;
        int curr = 1;
        int numberOfWays = 0;

        for (int i = 0; i <= currentStep; i++) { // 3
            numberOfWays = curr + prev;
            prev = curr;
            curr = numberOfWays;
        }

        return numberOfWays;
    }

    // 0 1
    // 1 1
    //

    public static void main(String[] args) {
        System.out.println(getNumberOfWays(5));
    }
}
