package com.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "reminders")
public class Reminder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // Add userEmail to link the reminder to a specific user
    @Column(name = "user_email", nullable = false)
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "content_title", nullable = false)
    private String content_title;

    @Column(name = "reminder_datetime", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date reminderDateTime;

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public Date getReminderDateTime() {
        return reminderDateTime;
    }

    public void setReminderDateTime(Date reminderDateTime) {
        this.reminderDateTime = reminderDateTime;
    }
    public String getContent_title() {
        return content_title;
    }

    public void setContent_title(String content_title) {
        this.content_title = content_title;
    }
}