package com.epam.rd.autocode.observer.git;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.*;

public class GitRepository implements Repository {

    Map<String, WebHook> webHooks = new HashMap<>();
    Map<String, List<Commit>> repo = new HashMap<>();
    private List<PropertyChangeListener> listener = new ArrayList<>();

    public GitRepository() {
    }

    @Override
    public void addWebHook(WebHook webHook) {
        webHooks.put(webHook.branch(), webHook);
        this.addChangeListener(webHook);
    }

    @Override
    public Commit commit(String branch, String author, String[] changes) {
        Commit commit = new Commit(author, changes);
        List<Commit> commits = new ArrayList<>();
        if (repo.get(branch) == null) {
            commits.add(commit);
            repo.put(branch, commits);
        } else {
            commits = repo.get(branch);
            commits.add(commit);
            repo.put(branch, commits);
        }

        notifyListeners(
                this,
                Event.Type.COMMIT.toString(),
                null,
                new Event(Event.Type.COMMIT, branch, Collections.singletonList(commit)));

        return commit;
    }

    @Override
    public void merge(String sourceBranch, String targetBranch) {
        List<Commit> commitsSource;
        List<Commit> eventCommitsSource = new ArrayList<>();
        List<Commit> commitsTarget;

        if (repo.get(sourceBranch) != null ) {
            commitsSource = repo.get(sourceBranch);
            if (repo.get(targetBranch) != null) {
                commitsTarget = repo.get(targetBranch);
                if (!commitsTarget.containsAll(commitsSource)) {
                    commitsTarget.addAll(commitsSource);
                }
                repo.put(targetBranch, commitsTarget);
            } else {
                repo.put(targetBranch, commitsSource);
            }
        }

        if (webHooks.get(sourceBranch) != null) {
            for (Event e: webHooks.get(sourceBranch).caughtEvents()) {
                eventCommitsSource.addAll(e.commits());
            }
        }

        notifyListeners(
                this,
                Event.Type.MERGE.toString(),
                null,
                new Event(Event.Type.MERGE, targetBranch, eventCommitsSource));
    }

    public void printAll () {
        System.out.println("\n Printing repo:");
        repo.forEach((key, value) -> System.out.println(key + ":" + value));
    }

    private void notifyListeners(Object object, String property, Object oldValue, Object newValue) {
        for (PropertyChangeListener name : listener) {
            name.propertyChange(new PropertyChangeEvent(this, property, oldValue, newValue));
        }
    }

    public void addChangeListener(PropertyChangeListener newListener) {
        listener.add(newListener);
    }
}
