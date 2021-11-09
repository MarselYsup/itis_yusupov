package ru.itis.semester.servlets;


import ru.itis.semester.dto.SignInForm;
import ru.itis.semester.models.User;
import ru.itis.semester.services.SignInService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/sign-in")
public class SignInServlet extends HttpServlet {
    private SignInService signInService;

    @Override
    public void init(ServletConfig config) throws ServletException {
       ServletContext servletContext = config.getServletContext();
       this.signInService = (SignInService) servletContext.getAttribute("signInService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("view/jsp/signin.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        SignInForm signInForm = new SignInForm(username,password);
        if(signInService.checkAccount(signInForm)) {
            User user = signInService.getUserByUsername(username);
            boolean isAdmin = signInService.checkAdmin(user.getUserId());
            HttpSession session = req.getSession(true);
            session.setAttribute("user",user);
            if(isAdmin) {
                session.setAttribute("isAdmin", isAdmin);
            }
            resp.sendRedirect(req.getContextPath() +"/profile");
        }
        else {
            resp.sendRedirect(req.getContextPath() +"/sign-in");
        }
    }
}
