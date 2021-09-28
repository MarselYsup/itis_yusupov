package ru.itis.sign.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/profile")
public class Profile extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("User") != null) {
            String username = (String) request.getSession().getAttribute("User");
            request.setAttribute("User", username);
            request.getRequestDispatcher("jsp/profile.jsp").forward(request, response);
        } else {
            response.sendRedirect("/sign-in");
        }
    }
    }



