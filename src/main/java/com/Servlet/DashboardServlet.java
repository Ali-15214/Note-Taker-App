package com.Servlet;

import com.Dao.ReminderDAOImpl;
import com.model.Reminder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/DashboardServlet")
public class DashboardServlet extends HttpServlet {
    private ReminderDAOImpl reminderDAO = new ReminderDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String email = (String) request.getSession().getAttribute("email");

        // Get due reminders
        List<Reminder> dueReminders = reminderDAO.getDueReminders(email);
        List<Reminder> futureReminders = reminderDAO.getFutureReminders(email);
        request.setAttribute("dueReminders", dueReminders);
        request.setAttribute("futureReminders", futureReminders);


      //  request.setAttribute("dueReminders", dueReminders);

        // Forward to the dashboard
        request.getRequestDispatcher("/dashboard.jsp").forward(request, response);
    }
}