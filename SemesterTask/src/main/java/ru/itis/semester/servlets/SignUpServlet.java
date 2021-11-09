package ru.itis.semester.servlets;

import ru.itis.semester.dto.SignUpForm;
import ru.itis.semester.services.SignUpService;


import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/sign-up")
public class SignUpServlet extends HttpServlet {
    private SignUpService signUpService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        signUpService = (SignUpService) servletContext.getAttribute("signUpService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("view/jsp/signup.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        SignUpForm signUpForm = new SignUpForm(firstName,lastName,username,password);
        if(signUpService.validate(signUpForm)) {
            signUpService.save(signUpForm);
            resp.sendRedirect(req.getContextPath() +"/sign-in");
        }
        else {
            resp.sendRedirect(req.getContextPath() +"/sign-up");
        }
    }
}
