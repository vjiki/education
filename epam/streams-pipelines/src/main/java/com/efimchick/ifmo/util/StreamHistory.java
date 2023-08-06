package com.efimchick.ifmo.util;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class StreamHistory<T> {

    private final int numberOfElementsToRemember;
    private LinkedList<T> queue = new LinkedList<T>(); // queue will store at most numberOfElementsToRemember

    public StreamHistory(int numberOfElementsToRemember) {
        this.numberOfElementsToRemember = numberOfElementsToRemember;
    }

    public StreamHistory save(T curElem) {

        if (queue.size() == numberOfElementsToRemember) {
            queue.pollLast(); // remove last to keep only requested number of elements
        }

        if (curElem instanceof CourseResult) {
            if(!queue.isEmpty()) {
                if (!queue.getFirst().equals(curElem)) {
                    System.out.println(((CourseResult) queue.getFirst()).getTaskResults().keySet());
                    System.out.println(((CourseResult) curElem).getTaskResults().keySet());
                    boolean isPrevElemContainsTask = false;
                    for (String s : ((CourseResult) curElem).getTaskResults().keySet()) {
                        if (((CourseResult) queue.getFirst()).getTaskResults().keySet().contains(s)) {
                            isPrevElemContainsTask = true;
                        }
                    }

                    if(isPrevElemContainsTask) {
                        Map<String, Integer> newTaskResults = new HashMap<>();

                        for (String s : ((CourseResult) curElem).getTaskResults().keySet()) {
                            if (((CourseResult) queue.getFirst()).getTaskResults().keySet().contains(s)) {
                                newTaskResults.put(s, ((CourseResult) curElem).getTaskResults().get(s));
                            } else
                            {
                                newTaskResults.put(s,0);
                            }
                        }
                        CourseResult courseResult = new CourseResult(((CourseResult) curElem).getPerson(), newTaskResults);

                        System.out.println("previous elem contains key");
                        queue.offerFirst((T) courseResult);

                        return this;

                    } else {
                        System.out.println("previous elem not contains key");
                    }
                }

            }

//            if(!queue.isEmpty()) {
//                System.out.println("last: " + queue.getLast());
//            }
//
//            System.out.println("curElem: " + curElem);

        }

        queue.offerFirst(curElem);


        return this;
    }


    public LinkedList<T> getLastElements() {
        return queue; // or return immutable copy or immutable view on the queue. Depends on what you want.
    }
}
