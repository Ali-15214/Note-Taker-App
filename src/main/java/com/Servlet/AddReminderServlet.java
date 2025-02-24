package com.Servlet;

import com.model.Reminder;
import com.Dao.ReminderDAOImpl;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

@WebServlet("/AddReminderServlet")
public class AddReminderServlet extends HttpServlet {
    private ReminderDAOImpl reminderDAO = new ReminderDAOImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String content_title = request.getParameter("content_title"); // Assuming noteId is sent as a request parameter
        String reminderDatetimeStr = request.getParameter("reminderDatetime");
        LocalDateTime localDateTime = LocalDateTime.parse(reminderDatetimeStr);

        String email = (String) request.getSession().getAttribute("email");

        if (reminderDatetimeStr != null && !reminderDatetimeStr.isEmpty()) {
            Reminder reminder = new Reminder();
            reminder.setContent_title(content_title);
            reminder.setReminderDateTime(Timestamp.valueOf(localDateTime)); // Convert String to Timestamp
            reminder.setEmail(email);
            reminderDAO.saveReminder(reminder);
        }

        response.sendRedirect("DashboardServlet"); // Redirect back to the dashboard
    }
}