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
@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            int noteId = Integer.parseInt(req.getParameter("note_id").trim());

            Session sess= FactoryProvider.getFactory().openSession();
            Transaction transaction= sess.beginTransaction();
            Note note=(Note)sess.get(Note.class, noteId);
            sess.delete(note);
          resp.sendRedirect("all_notes.jsp");
          transaction.commit();
          sess.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
