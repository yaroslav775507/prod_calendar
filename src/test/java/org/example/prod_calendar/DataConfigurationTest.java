package org.example.prod_calendar;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class DataConfigurationTest {

    @Test
    void sendDate_WeekendDay() {
        assertTrue(DataConfiguration.sendDate(LocalDate.of(2024, 5, 11)));
    }

    @Test
    void sendDate_NotWeekendDay() {
        assertFalse(DataConfiguration.sendDate(LocalDate.of(2024, 5, 14)));
    }

    @Test
    void checkDateTime_Weekend() {
        LocalDate weekendDay = LocalDate.of(2024, 5, 11);
        LocalTime time = LocalTime.of(10, 0);
        assertTrue(DataConfiguration.checkDateTime(weekendDay, time));
    }

    @Test
    void checkDateTime_OutsideWorkingHours() {
        LocalDate weekday = LocalDate.of(2024, 5, 14);
        LocalTime beforeWorkTime = LocalTime.of(9, 0);
        assertFalse(DataConfiguration.checkDateTime(weekday, beforeWorkTime));
        LocalTime afterWorkTime = LocalTime.of(18, 0);
        assertFalse(DataConfiguration.checkDateTime(weekday, afterWorkTime));
    }

    @Test
    void checkDateTime_WorkingHours() {
        LocalDate weekday = LocalDate.of(2024, 5, 10);
        LocalTime duringWorkTime = LocalTime.of(12, 0);
        assertFalse(DataConfiguration.checkDateTime(weekday, duringWorkTime));
    }
}
