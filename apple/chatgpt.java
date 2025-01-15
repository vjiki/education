//import java.util.*;
//import java.util.concurrent.*;
//
//public class MultithreadedJobScheduler<J> {
//    private static class JobNode<J> {
//        J job;
//        int priority;
//        Set<JobNode<J>> dependencies;
//
//        JobNode(J job, int priority) {
//            this.job = job;
//            this.priority = priority;
//            this.dependencies = new HashSet<>();
//        }
//    }
//
//    private final Map<J, JobNode<J>> jobMap = new HashMap<>();
//    private final Set<JobNode<J>> completedJobs = ConcurrentHashMap.newKeySet();
//    private final ExecutorService executorService = Executors.newCachedThreadPool();
//
//    /**
//     * Adds a job to the scheduler.
//     *
//     * @param job         The job to be added
//     * @param priority    The priority of the job
//     * @param dependencies The dependencies of the job
//     */
//    public void addJob(J job, int priority, Set<J> dependencies) {
//        jobMap.putIfAbsent(job, new JobNode<>(job, priority));
//        JobNode<J> jobNode = jobMap.get(job);
//        jobNode.priority = priority;
//
//        for (J dependency : dependencies) {
//            jobMap.putIfAbsent(dependency, new JobNode<>(dependency, Integer.MIN_VALUE));
//            jobNode.dependencies.add(jobMap.get(dependency));
//        }
//    }
//
//    /**
//     * Runs all jobs in the scheduler, adhering to priority and dependency constraints.
//     */
//    public void runAllJobs() {
//        // Priority Queue to manage jobs based on priority
//        PriorityQueue<JobNode<J>> jobQueue = new PriorityQueue<>((a, b) -> b.priority - a.priority);
//        jobQueue.addAll(jobMap.values());
//
//        while (!jobQueue.isEmpty()) {
//            JobNode<J> job = jobQueue.poll();
//
//            // Check if the job's dependencies are satisfied
//            if (dependenciesSatisfied(job)) {
//                executorService.submit(() -> runJob(job, jobQueue));
//            } else {
//                // Re-add the job for future consideration
//                synchronized (jobQueue) {
//                    jobQueue.add(job);
//                }
//            }
//        }
//
//        executorService.shutdown();
//        try {
//            executorService.awaitTermination(1, TimeUnit.HOURS);
//        } catch (InterruptedException e) {
//            Thread.currentThread().interrupt();
//            System.err.println("Job execution interrupted.");
//        }
//
//        // Check if any jobs couldn't run (cyclic dependency)
//        if (completedJobs.size() != jobMap.size()) {
//            System.err.println("Error: Some jobs could not be run due to cyclic dependencies.");
//        }
//    }
//
//    private boolean dependenciesSatisfied(JobNode<J> job) {
//        return completedJobs.containsAll(job.dependencies);
//    }
//
//    private void runJob(JobNode<J> job, PriorityQueue<JobNode<J>> jobQueue) {
//        // Simulate running the job
//        System.out.println("Running job: " + job.job);
//
//        // Mark the job as completed
//        completedJobs.add(job);
//
//        // Notify other jobs that may depend on this one
//        synchronized (jobQueue) {
//            jobQueue.notifyAll();
//        }
//    }
//
//    public static void main(String[] args) {
//        MultithreadedJobScheduler<String> scheduler = new MultithreadedJobScheduler<>();
//
//        // Define jobs and their dependencies
//        scheduler.addJob("A", 3, Set.of());
//        scheduler.addJob("B", 2, Set.of("A"));
//        scheduler.addJob("C", 1, Set.of("A", "B"));
//        scheduler.addJob("D", 4, Set.of("C"));
//
//        // Run all jobs
//        scheduler.runAllJobs();
//    }
//}
//import java.util.*;
//        import java.util.concurrent.*;
//
//public class MultithreadedJobScheduler<J> {
//    private static class JobNode<J> {
//        J job;
//        int priority;
//        Set<JobNode<J>> dependencies;
//
//        JobNode(J job, int priority) {
//            this.job = job;
//            this.priority = priority;
//            this.dependencies = new HashSet<>();
//        }
//    }
//
//    private final Map<J, JobNode<J>> jobMap = new HashMap<>();
//    private final Set<JobNode<J>> completedJobs = ConcurrentHashMap.newKeySet();
//    private final ExecutorService executorService = Executors.newCachedThreadPool();
//
//    /**
//     * Adds a job to the scheduler.
//     *
//     * @param job         The job to be added
//     * @param priority    The priority of the job
//     * @param dependencies The dependencies of the job
//     */
//    public void addJob(J job, int priority, Set<J> dependencies) {
//        jobMap.putIfAbsent(job, new JobNode<>(job, priority));
//        JobNode<J> jobNode = jobMap.get(job);
//        jobNode.priority = priority;
//
//        for (J dependency : dependencies) {
//            jobMap.putIfAbsent(dependency, new JobNode<>(dependency, Integer.MIN_VALUE));
//            jobNode.dependencies.add(jobMap.get(dependency));
//        }
//    }
//
//    /**
//     * Runs all jobs in the scheduler, adhering to priority and dependency constraints.
//     */
//    public void runAllJobs() {
//        // Priority Queue to manage jobs based on priority
//        PriorityQueue<JobNode<J>> jobQueue = new PriorityQueue<>((a, b) -> b.priority - a.priority);
//        jobQueue.addAll(jobMap.values());
//
//        while (!jobQueue.isEmpty()) {
//            JobNode<J> job = jobQueue.poll();
//
//            // Check if the job's dependencies are satisfied
//            if (dependenciesSatisfied(job)) {
//                executorService.submit(() -> runJob(job, jobQueue));
//            } else {
//                // Re-add the job for future consideration
//                synchronized (jobQueue) {
//                    jobQueue.add(job);
//                }
//            }
//        }
//
//        executorService.shutdown();
//        try {
//            executorService.awaitTermination(1, TimeUnit.HOURS);
//        } catch (InterruptedException e) {
//            Thread.currentThread().interrupt();
//            System.err.println("Job execution interrupted.");
//        }
//
//        // Check if any jobs couldn't run (cyclic dependency)
//        if (completedJobs.size() != jobMap.size()) {
//            System.err.println("Error: Some jobs could not be run due to cyclic dependencies.");
//        }
//    }
//
//    private boolean dependenciesSatisfied(JobNode<J> job) {
//        return completedJobs.containsAll(job.dependencies);
//    }
//
//    private void runJob(JobNode<J> job, PriorityQueue<JobNode<J>> jobQueue) {
//        // Simulate running the job
//        System.out.println("Running job: " + job.job);
//
//        // Mark the job as completed
//        completedJobs.add(job);
//
//        // Notify other jobs that may depend on this one
//        synchronized (jobQueue) {
//            jobQueue.notifyAll();
//        }
//    }
//
//    public static void main(String[] args) {
//        MultithreadedJobScheduler<String> scheduler = new MultithreadedJobScheduler<>();
//
//        // Define jobs and their dependencies
//        scheduler.addJob("A", 3, Set.of());
//        scheduler.addJob("B", 2, Set.of("A"));
//        scheduler.addJob("C", 1, Set.of("A", "B"));
//        scheduler.addJob("D", 4, Set.of("C"));
//
//        // Run all jobs
//        scheduler.runAllJobs();
//    }
//}
