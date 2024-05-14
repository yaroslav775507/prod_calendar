package org.example.prod_calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Controller
public class DateController {

    @GetMapping("/date-input")
    public String showDateInputPage() {
        return "date-form";
    }

    @PostMapping("/check-date")
    public String checkDate(@RequestParam(value = "date", defaultValue = "1970-01-01") String dateStr, Model model) {
        LocalDate date = LocalDate.parse(dateStr, DateTimeFormatter.ISO_LOCAL_DATE);
        boolean isWeekend = DataConfiguration.sendDate(date);
        System.out.println(isWeekend);
        model.addAttribute("date", date);
        model.addAttribute("isWeekend", isWeekend);

        if (isWeekend) {
            return "weekend-page";
        } else {
            return "weekday-page";
        }
    }

    @PostMapping("/check-date-time")
    public String checkDateTime(
            @RequestParam(value = "date", defaultValue = "1970-01-01") String dateStr,
            @RequestParam(value = "time", defaultValue = "00:00") String timeStr, Model model) {
        LocalDate date = LocalDate.parse(dateStr, DateTimeFormatter.ISO_LOCAL_DATE);
        LocalTime time = LocalTime.parse(timeStr, DateTimeFormatter.ISO_LOCAL_TIME);
        System.out.println(date);
        System.out.println(timeStr);
        boolean isNonWorkingTime = DataConfiguration.checkDateTime(date, time);
        System.out.println(isNonWorkingTime);
        model.addAttribute("date", date);
        model.addAttribute("time", time);
        model.addAttribute("isNonWorkingTime", isNonWorkingTime);

        if (isNonWorkingTime) {
            return "weekend-page";
        } else {
            return "weekday-page";
        }
    }
}
