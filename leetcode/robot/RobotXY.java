import java.util.HashSet;
import java.util.Set;

public class RobotXY {
    public int robotSim(int[] commands, int[][] obstacles) {
        // Convert obstacles list to a set of strings for faster lookup
        Set<String> obstacleSet = new HashSet<>();
        for (int[] obstacle : obstacles) {
            obstacleSet.add(obstacle[0] + "," + obstacle[1]);
        }

        // Directions are in the order of North, East, South, West
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int directionIdx = 0; // Start facing north
        int x = 0, y = 0; // Robot's starting position
        int maxDistanceSq = 0;

        for (int command : commands) {
            if (command == -1) { // Turn right 90 degrees
                directionIdx = (directionIdx + 1) % 4;
            } else if (command == -2) { // Turn left 90 degrees
                directionIdx = (directionIdx + 3) % 4;
            } else { // Move forward k units
                int dx = directions[directionIdx][0];
                int dy = directions[directionIdx][1];

                int steps = 0;
                while (steps < command) {
                    int nextX = x + dx;
                    int nextY = y + dy;
                    // Check for obstacle
                    if (obstacleSet.contains(nextX + "," + nextY)) {
                        break; // Hit an obstacle, stop moving forward
                    }
                    x = nextX;
                    y = nextY;
                    // Calculate the current distance squared and update
                    // maxDistanceSq
                    maxDistanceSq = Math.max(maxDistanceSq, x * x + y * y);
                    steps++;
                }
            }
        }

        return maxDistanceSq;
    }
}