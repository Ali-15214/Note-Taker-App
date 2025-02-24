package com.Servlet;

import com.model.Note;
import com.helper.FactoryProvider;
import org.hibernate.Session;
import org.hibernate.Transaction;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.util.Date;

@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            String title=req.getParameter("title");
            String content=req.getParameter("content");

            int noteId = Integer.parseInt(req.getParameter("noteId").trim());

            Session sess= FactoryProvider.getFactory().openSession();
            Transaction transaction= sess.beginTransaction();
            Note note=(Note)sess.get(Note.class, noteId);
            note.setTitle(title);
            note.setContent(content);
            note.setAddedDate(new Date());

            transaction.commit();
            sess.close();
            resp.sendRedirect("all_notes.jsp");

        }catch (Exception e){
            e.printStackTrace();
        }

        }

}
