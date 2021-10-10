package ru.itis.sign.servlets;

import ru.itis.sign.database.UserRepository;
import ru.itis.sign.database.impl.UserRepositoryImpl;
import ru.itis.sign.models.User;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/sign-in")
public class SignIn extends HttpServlet {
    private UserRepository userDao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        userDao = (UserRepository) config.getServletContext().getAttribute("usersRepository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("jsp/signin.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        System.out.println(username+" "+password);
        Optional<User> user = userDao.findByName(username);
        if(user.isEmpty()) {
            req.setAttribute("errorMessage","Invalid username or password!");
            req.getRequestDispatcher("jsp/signin.jsp").forward(req,resp);
        }
        else if(!user.get().getPassword().equals(password)) {
            req.setAttribute("errorMessagePassword","Wrong password!");
            req.getRequestDispatcher("jsp/signin.jsp").forward(req,resp);
        }
        else {
            req.getSession().setAttribute("User", user.get());
            resp.sendRedirect("/profile");
        }

    }
}
