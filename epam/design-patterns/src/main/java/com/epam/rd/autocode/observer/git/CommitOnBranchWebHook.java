package com.epam.rd.autocode.observer.git;

import java.beans.PropertyChangeEvent;
import java.util.*;

public class CommitOnBranchWebHook implements WebHook {
    String branch;
    List<Event> commitEventList = new ArrayList<>();

    public CommitOnBranchWebHook(String branch) {
        this.branch = branch;
    }

    @Override
    public String branch() {
        return this.branch;
    }

    @Override
    public Event.Type type() {
        return Event.Type.COMMIT;
    }

    @Override
    public List<Event> caughtEvents() {
        return commitEventList;
    }

    @Override
    public void onEvent(Event event) {


    }

    @Override
    public void propertyChange(PropertyChangeEvent event) {
        //System.out.println("Changed property: " + event.getPropertyName() + " [old -> "
        //        + event.getOldValue() + "] | [new -> " + event.getNewValue() +"]");
        if (event.getPropertyName().contains(Event.Type.COMMIT.toString())) {
            Event eventRepo = (Event) event.getNewValue();
            if (this.branch.contains(eventRepo.branch())) {
                commitEventList.add(eventRepo);
            }
        }
    }
}
