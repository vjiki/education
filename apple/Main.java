/*
 * Click `Run` to execute the snippet below!
 */

import java.io.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

// # - Try to run job with highest priority first, but:
// # - Can’t run a job until all its dependencies have been run

class JobScheduler {

    private PriorityQueue<Job> pq = new PriorityQueue<>(Comparator.reverseOrder());
    private Set<Job> notReadyJobs = ConcurrentHashMap.newKeySet();

    public void addJob(Job job) {
        if (job.isReadyToRun()) {
            pq.add(job);
        } else {
            notReadyJobs.add(job);
        }
    }

    public void runAllJobs() {
        System.out.println(Runtime.getRuntime().availableProcessors());
        try (ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors())) {

            while (!pq.isEmpty() || !notReadyJobs.isEmpty()) {
                if (!pq.isEmpty()) {
                    Job job = pq.poll();
                    executor.submit(job);
                }
//                while (!job.isDone().get()) {
//                    try {
//                        Thread.sleep(100);
//                    } catch (Exception e) {
//                        System.out.println(e);
//                    }
//                }
                List<Job> readyToRunJobs = notReadyJobs.stream().filter(Job::isReadyToRun).toList();
                if (!readyToRunJobs.isEmpty()) {
                    for (Job j : readyToRunJobs) {
                        pq.add(j);
                        notReadyJobs.remove(j);
                    }
                }
            }
            executor.shutdown();
        }
    }
}

class Job implements Runnable, Comparable<Job> {
    private final int priority;
    private final Set<Job> dependencies;
    private final AtomicBoolean isDone = new AtomicBoolean(false);
    private final int jobId;

    public Job(int jobId, int priority, Set<Job> dependencies) {
        this.priority = priority;
        this.dependencies = dependencies;
        this.jobId = jobId;
    }

    public boolean isReadyToRun() {
        return dependencies.isEmpty() || dependencies.stream().allMatch(j -> j.isDone().get());
    }

    public AtomicBoolean isDone() {
        return isDone;
    }

    @Override
    public void run() {
        System.out.println("priority:" + priority + " jobId: " + jobId);
        isDone.set(true);
    }



    @Override
    public int compareTo(Job o) {
        return new Integer(this.priority).compareTo(o.priority);
    }

    public static void main(String[] args) {
        JobScheduler jobScheduler = new JobScheduler();
        Job job5 = new Job(5, 3, Collections.emptySet());
        Job job4 = new Job(4, 7, Collections.emptySet());
        Job job3 = new Job(3, 8, Set.of(job5, job4));
        Job job1 = new Job(1, 10, Set.of(job3));
        Job job2 = new Job(2, 9, Set.of(job4));

        jobScheduler.addJob(job1);
        jobScheduler.addJob(job2);
        jobScheduler.addJob(job3);
        jobScheduler.addJob(job4);
        jobScheduler.addJob(job5);

        jobScheduler.runAllJobs();

    }
}

