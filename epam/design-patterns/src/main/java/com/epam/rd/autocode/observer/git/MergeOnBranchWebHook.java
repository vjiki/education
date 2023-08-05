package com.epam.rd.autocode.observer.git;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;

public class MergeOnBranchWebHook implements WebHook {
    String branch;
    List<Commit> commitList = new ArrayList<>();
    List<Event> mergeEventList = new ArrayList<>();

    public MergeOnBranchWebHook(String branch) {
        this.branch = branch;
    }

    @Override
    public String branch() {
        return this.branch;
    }

    @Override
    public Event.Type type() {
        return Event.Type.MERGE;
    }

    @Override
    public List<Event> caughtEvents() {
        return mergeEventList;
    }

    @Override
    public void onEvent(Event event) {


    }

    @Override
    public void propertyChange(PropertyChangeEvent event) {
        //System.out.println("Changed property: " + event.getPropertyName() + " [old -> "
        //        + event.getOldValue() + "] | [new -> " + event.getNewValue() +"]");
        Event eventRepo = (Event) event.getNewValue();

        if (event.getPropertyName().contains(Event.Type.COMMIT.toString())) {
            if (this.branch.contains(eventRepo.branch())) {
                commitList.addAll(eventRepo.commits());
            }
        }
        if (event.getPropertyName().contains(Event.Type.MERGE.toString())) {
            if (this.branch.contains(eventRepo.branch())) {
                eventRepo.commits().removeAll(commitList);
                if (!mergeEventList.contains(new Event(Event.Type.COMMIT, "branch", eventRepo.commits()))) {
                    commitList.addAll(eventRepo.commits());
                    if ( !eventRepo.commits().isEmpty()) {
                        mergeEventList.add(new Event(Event.Type.COMMIT, "branch", eventRepo.commits()));
                    }
                }
            }
        }
    }
}
