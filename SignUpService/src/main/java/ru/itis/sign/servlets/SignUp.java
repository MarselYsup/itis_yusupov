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
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet("/sign-up")
public class SignUp extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("jsp/signup.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println(username+" "+password);
        //Connecting with DB
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUsername("postgres");
        dataSource.setPassword("190202Marsel");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/java_web");
        CrudRepository<User,Long> userDao = new UserRepository(dataSource);
        List<String> userList = userDao.findAllNames();

        if(userList.contains(username)) {

            request.setAttribute("errorMessageNames","Current username already registered!");
            request.getRequestDispatcher("jsp/signup.jsp").forward(request,response);
        }
        else if(password.length()<5||!isValidUsername(username)) {

            request.setAttribute("errorMessageInvalid","Invalid username or password!");
            request.getRequestDispatcher("jsp/signup.jsp").forward(request,response);
        }
        else {
            userDao.save(new User(username,password));
            request.getSession().setAttribute("User", username);
            response.sendRedirect("/profile");
        }


    }
    private static boolean isValidUsername(String name)
    {

        // Regex to check valid username.
        String regex = "^[A-Za-z]\\w{4,30}$";

        // Compile the ReGex
        Pattern p = Pattern.compile(regex);

        // If the username is empty
        // return false
        if (name == null) return false;
        if(name.toLowerCase().equals("null")) return false;

        // Pattern class contains matcher() method
        // to find matching between given username
        // and regular expression.
        Matcher m = p.matcher(name);

        // Return if the username
        // matched the ReGex
        return m.matches();
    }
}
