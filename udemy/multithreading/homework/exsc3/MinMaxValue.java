public class MinMaxMetrics {

    // Add all necessary member variables
    // atomic only for read !! for increment it will not work for long!!!
    private volatile long minValue;
    private volatile long maxValue;

    /**
     * Initializes all member variables
     */
    public MinMaxMetrics() {
        // Add code here
        this.minValue = Long.MIN_VALUE;
        this.maxValue = Long.MAX_VALUE;
    }

    /**
     * Adds a new sample to our metrics.
     */
    public synchronized void addSample(long newSample) {
        // Add code here
        if (minValue > newSample) {
            minValue = newSample;
        }
        if (maxValue < newSample) {
            maxValue = newSample;
        }
    }

    /**
     * Returns the smallest sample we've seen so far.
     */
    public long getMin() {
        // Add code here
        return minValue;
    }

    /**
     * Returns the biggest sample we've seen so far.
     */
    public long getMax() {
        // Add code here
        return maxValue;
    }
}
