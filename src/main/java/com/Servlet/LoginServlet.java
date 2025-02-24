package com.Servlet;

import com.Dao.UserDao;
import com .Dao.UserDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    private static UserDao userDao=new UserDaoImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     String email = request.getParameter("email");
        String password = request.getParameter("password");


        if (userDao.isValidUser(email, password)) {
            HttpSession session = request.getSession();
            session.setAttribute("password", password);
            session.setAttribute("email", email);

            response.sendRedirect("index.jsp");

        } else {
            response.sendRedirect("Login.jsp?error=1");

        }
    }

}
