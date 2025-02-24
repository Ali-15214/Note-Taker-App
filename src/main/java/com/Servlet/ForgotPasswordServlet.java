package com.Servlet;

import com.Dao.UserDaoImpl;
import com.helper.FactoryProvider;
import com.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/ForgotPasswordServlet")
public class ForgotPasswordServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String newPassword = request.getParameter("newPassword");
        UserDaoImpl userDao=new UserDaoImpl();
        if(userDao.isValidEmail(email)){

            updatePasswordInDatabase(email, newPassword);

            response.sendRedirect("Login.jsp?update=1");

                }

        else {
            response.sendRedirect("forgot_password.jsp?error=1");
                }
            }








    private boolean updatePasswordInDatabase(String email, String newPassword) {
        Session session= FactoryProvider.getFactory().openSession();
        Transaction transaction=session.beginTransaction();
        boolean success = false;
        
        try {
            String hql = "UPDATE User u SET u.password = :password WHERE u.email = :email";
            Query query = session.createQuery(hql);
            query.setParameter("password", newPassword); // Hash the password in a real application
            query.setParameter("email", email);
            int result = query.executeUpdate();

            String hql1 = "UPDATE Note n SET n.password = :password WHERE n.email = :email";
            Query query1= session.createQuery(hql1);
            query1.setParameter("password", newPassword); // Hash the password in a real application
            query1.setParameter("email", email);
            int result1 = query1.executeUpdate();
            transaction.commit();

            success = result1 > 0;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        
        return success;
    }


}