# Scheduling assistant

## Description

Implement [SchedulingAssistant](src/main/java/com/epam/rd/autotasks/timing/SchedulingAssistant.java) methods.

Scheduling Assistant helps to schedule team meetings.
Team is a collection of Developers and each developer lives in a particular city.
Cities are across the world and time zones, so it may be hard to plan a meeting which everyone can attend.

So, Scheduling Assistant tries to schedule a meeting considering different time zones of developers and some preferences.
Preferences are basically combination of what period is OK to setup a meeting (today, tomorrow or this week) and what is a preferred part of the period: earliest or latest.

### More details
- A Scheduling Assistant relates to combination of a dev team and a date of today.
- Scheduling Assistant schedule() method returns a LocalDateTime instance if there is success and null otherwise.
- schedule() method return value is a proposed meeting start date time in the GMT time zone.
- schedule() method considers meeting length in minutes and preferences of its setup.

**NB:** *Workday lasts 8 hours.*
