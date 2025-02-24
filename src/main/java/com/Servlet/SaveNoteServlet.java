package com.Servlet;

import com.model.Note;
import com.helper.FactoryProvider;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.type.DateType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;


@WebServlet("/SaveNoteServlet")
public class SaveNoteServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title =req.getParameter("title");
        String content =req.getParameter("content");

        HttpSession sessionUser = req.getSession();
        String password= (String) sessionUser.getAttribute("password");
        String email= (String) sessionUser.getAttribute("email");



        Note note=new Note(content,title,new Date(),password,email);

        Session session= FactoryProvider.getFactory().openSession();
        Transaction transaction=session.beginTransaction();
        session.save(note);
        transaction.commit();
        session.close();
       resp.setContentType("text/html");
        PrintWriter out=resp.getWriter();
        out.println("<html><head><title>Note Added</title></head><body>");
        out.println("<div style='text-align: center;'>");
        out.println("<h1 style='color: blue;'>Note is added successfully</h1>");
        out.println("<p>Date: " + new Date() + "</p>"); // Display the formatted date
        out.println("<a href='all_notes.jsp' style='text-decoration: none;'>"
                + "<button style='background-color: #4CAF50; color: white; padding: 10px 20px; border: none; border-radius: 5px; cursor: pointer; font-size: 16px;'>"
                + "View All Notes"
                + "</button></a>");
        out.println("</div>");
        out.println("</body></html>");
    }
}
