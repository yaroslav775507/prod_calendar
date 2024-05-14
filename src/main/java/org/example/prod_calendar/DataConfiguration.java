package org.example.prod_calendar;

import org.springframework.stereotype.Service;

import java.time.*;

@Service
public class DataConfiguration {
    public static boolean sendDate(LocalDate variable) {
        int[] weekendDays = {1, 4, 5, 9, 10, 11, 12, 18, 19, 25, 26};
        int nowDay = variable.getDayOfMonth();
        for (int weekendDay : weekendDays) {
            if (weekendDay == nowDay) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkDateTime(LocalDate date, LocalTime time) {
        LocalDateTime dateTime = LocalDateTime.of(date, time);
        ZonedDateTime moscowDateTime = dateTime.atZone(ZoneId.of("Europe/Moscow"));
        LocalTime startOfWorkDay = LocalTime.of(9, 0);
        LocalTime endOfWorkDay = LocalTime.of(18, 0);
        LocalTime lunchStartTime = LocalTime.of(12, 0);
        LocalTime lunchEndTime = LocalTime.of(13, 0);
        DayOfWeek dayOfWeek = moscowDateTime.getDayOfWeek();
        boolean isWeekend = dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY;
        LocalTime moscowTime = moscowDateTime.toLocalTime();
        boolean isWorkingTime = !moscowTime.isBefore(startOfWorkDay) && !moscowTime.isAfter(endOfWorkDay);
        boolean isLunchTime = moscowTime.isAfter(lunchStartTime) && moscowTime.isBefore(lunchEndTime);
        return isWeekend || !isWorkingTime || isLunchTime;
    }

}
