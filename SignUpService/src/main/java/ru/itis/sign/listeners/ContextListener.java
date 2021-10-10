package ru.itis.sign.listeners;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.itis.sign.database.AvatarRepository;
import ru.itis.sign.database.UserRepository;
import ru.itis.sign.database.impl.AvatarRepositoryImpl;
import ru.itis.sign.database.impl.UserRepositoryImpl;
import ru.itis.sign.services.AvatarService;
import ru.itis.sign.services.AvatarServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextListener implements ServletContextListener {
    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "190202Marsel";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/java_web";
    private static final String DB_DRIVER = "org.postgresql.Driver";
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(DB_DRIVER);
        dataSource.setUsername(DB_USERNAME);
        dataSource.setPassword(DB_PASSWORD);
        dataSource.setUrl(DB_URL);
        UserRepository userRepository = new UserRepositoryImpl(dataSource);
        AvatarRepository avatarRepository = new AvatarRepositoryImpl(dataSource);
        AvatarService avatarService = new AvatarServiceImpl(avatarRepository);
        String path = "../uploads/";
        servletContext.setAttribute("usersRepository",userRepository);
        servletContext.setAttribute("avatarRepository",avatarRepository);
        servletContext.setAttribute("avatarService",avatarService);
        servletContext.setAttribute("pathForAvatar",path);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
