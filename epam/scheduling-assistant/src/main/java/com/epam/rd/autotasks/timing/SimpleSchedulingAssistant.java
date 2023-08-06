package com.epam.rd.autotasks.timing;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Collection;

public class SimpleSchedulingAssistant implements SchedulingAssistant {

    Collection<Developer> team;
    LocalDate today;

    public SimpleSchedulingAssistant(Collection<Developer> team, LocalDate today) {
        this.team = team;
        this.today = today;
    }

    @Override
    public LocalDateTime schedule(long meetingDurationMinutes, MeetingTimingPreferences preferences) {
        LocalDateTime minStartMeetingTime = today.atTime(LocalTime.MIN).atZone(ZoneId.of("UTC")).toLocalDateTime();
        LocalDateTime maxEndMeetingTime = today.atTime(LocalTime.MAX).atZone(ZoneId.of("UTC")).toLocalDateTime();

        //System.out.println("today: " + today);
        for (Developer developer : team) {
            String possibleTimeZone = ZoneId.getAvailableZoneIds().stream()
                    .filter(zid -> zid.endsWith("/" + developer.city.replace(" ", "_")))
                    .findFirst().orElse("Unknown");
            LocalDateTime maxStartAt = today.atTime(developer.workDayStartTime).atZone(ZoneId.of(possibleTimeZone)).withZoneSameInstant(ZoneId.of("UTC")).toLocalDateTime();
            LocalDateTime minEndsAt = maxStartAt.plusHours(8);

            if (maxStartAt.isAfter(minStartMeetingTime)) {
                minStartMeetingTime = maxStartAt;
            }

            if (minEndsAt.isBefore(maxEndMeetingTime)) {
                maxEndMeetingTime = minEndsAt;
            }

            //System.out.println(startAt.isAfter(localDateTime));
            //System.out.println("name: " + developer.name + " city: " + developer.city + " workDayStartTime: " + developer.workDayStartTime);
            //System.out.println("start at: " + maxStartAt + " ends at: " + minEndsAt);
            //System.out.println(ZoneId.of(possibleTimeZone).getRules().getOffset(zonedDateTime.toLocalDateTime()));
        }
        //System.out.println("meetingDurationMinutes: " + meetingDurationMinutes);
        //System.out.println("period: " + preferences.period + " inPeriod: " + preferences.inPeriod);


        Duration duration = Duration.between(minStartMeetingTime, maxEndMeetingTime);

        if (preferences.inPeriod == MeetingTimingPreferences.InPeriodPreference.EARLIEST) {
            if ((duration.toMinutes() - meetingDurationMinutes) < 0) {
                return null;
            }
        }

        if (preferences.inPeriod == MeetingTimingPreferences.InPeriodPreference.LATEST) {
            //System.out.println("duration till end of day: " + (duration.toMinutes() - meetingDurationMinutes));
            if ((duration.toMinutes() - meetingDurationMinutes) >= 0) {
                //System.out.println("plussing " + minStartMeetingTime.plusMinutes(duration.toMinutes() - meetingDurationMinutes));
                minStartMeetingTime = minStartMeetingTime.plusMinutes(duration.toMinutes() - meetingDurationMinutes);
            } else {
                return null;
            }

            if (preferences.period == MeetingTimingPreferences.PeriodPreference.THIS_WEEK) {
                int plusDays = DayOfWeek.SATURDAY.getValue() - minStartMeetingTime.getDayOfWeek().getValue() > 0 ? DayOfWeek.SATURDAY.getValue() - minStartMeetingTime.getDayOfWeek().getValue() : 6;
                //System.out.println("day of week: " + minStartMeetingTime.plusDays(plusDays));
                if (minStartMeetingTime.getDayOfWeek() != DayOfWeek.SATURDAY) {
                    minStartMeetingTime = minStartMeetingTime.plusDays(plusDays);
                }
            }

        }
        if (preferences.period == MeetingTimingPreferences.PeriodPreference.TOMORROW) {
            minStartMeetingTime = minStartMeetingTime.plusDays(1);
        }

        //System.out.println(minStartMeetingTime);
        //System.out.println("\n========= END ==========\n");

        return minStartMeetingTime;
    }
}
