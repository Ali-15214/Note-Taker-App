package com.Servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.util.Collections;

import com.google.gson.*;
import com.helper.FactoryProvider;
import org.hibernate.Session;
import org.hibernate.Transaction;

@WebServlet("/CompleteTaskServlet")
public class CompleteTaskServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader reader = request.getReader();
        JsonObject json = new Gson().fromJson(reader, JsonObject.class);
        String contentTitle = json.get("content_title").getAsString();

        // Call a method to remove this reminder from DB using the content title
        boolean success = removeReminderByTitle(contentTitle);

        // Send JSON response back
        response.setContentType("application/json");
        response.getWriter().write(new Gson().toJson(Collections.singletonMap("success", success)));
    }


    private boolean removeReminderByTitle(String contentTitle) {
        Transaction transaction = null;
        boolean isDeleted = false;

        // Obtain session from Hibernate session factory
        try (Session session = FactoryProvider.getFactory().openSession()) {
            session.beginTransaction();

            // HQL query to delete the reminder based on content title
            String hql = "DELETE FROM Reminder r WHERE r.content_title = :contentTitle";
            int rowsAffected = session.createQuery(hql)
                    .setParameter("contentTitle", contentTitle)
                    .executeUpdate();

            // Commit transaction if deletion was successful
            if (rowsAffected > 0) {
                transaction.commit();
                isDeleted = true;
            } else {
                if (transaction != null) {
                    transaction.rollback(); // Rollback if there was an issue
                }
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback(); // Rollback on exception
            }
            e.printStackTrace(); // Log the exception
        }

        return isDeleted; // Return whether the deletion was successful
    }

}