package com.epam.rd.autocode.observer.git;

import java.beans.PropertyChangeListener;
import java.util.List;

public interface WebHook extends PropertyChangeListener {
    String branch();
    Event.Type type();
    List<Event> caughtEvents();
    void onEvent(Event event);
}
