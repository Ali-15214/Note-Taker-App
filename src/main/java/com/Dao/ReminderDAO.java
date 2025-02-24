package com.Dao;

import com.model.Reminder;

import java.util.List;

public interface ReminderDAO {
    void saveReminder(Reminder reminder);
    List<Reminder> getRemindersForUser(String content_title);
    List<Reminder> getDueReminders(String email); // New method to get reminders that are due
    List<Reminder> getFutureReminders(String email);
}