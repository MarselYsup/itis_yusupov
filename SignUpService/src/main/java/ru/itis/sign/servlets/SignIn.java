package ru.itis.sign.servlets;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.itis.sign.database.CrudRepository;
import ru.itis.sign.database.UserRepository;
import ru.itis.sign.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet("/sign-in")
public class SignIn extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("jsp/signin.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        System.out.println(username+" "+password);
        //Connecting with DB
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUsername("postgres");
        dataSource.setPassword("190202Marsel");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/java_web");
        CrudRepository<User,Long> userDao = new UserRepository(dataSource);
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
            req.getSession().setAttribute("User", username);
            resp.sendRedirect("/profile");
        }

    }
}
