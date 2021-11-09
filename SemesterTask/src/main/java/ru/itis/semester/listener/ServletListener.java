package ru.itis.semester.listener;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.itis.semester.dao.*;
import ru.itis.semester.dao.impl.*;
import ru.itis.semester.services.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ServletListener implements ServletContextListener {

    private static String DB_USERNAME = "postgres";

    private static String DB_PASSWORD = "190202Marsel";

    private static String DB_URL = "jdbc:postgresql://localhost:5432/semester_task";

    private static String DB_DRIVER = "org.postgresql.Driver";
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();
        //download data in driver

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(DB_DRIVER);
        dataSource.setUsername(DB_USERNAME);
        dataSource.setPassword(DB_PASSWORD);
        dataSource.setUrl(DB_URL);

        UserRepository userRepository = new UserRepositoryImpl(dataSource);
        AvatarRepository avatarRepository = new AvatarRepositoryImpl(dataSource);
        AnimeRepository animeRepository = new AnimeRepositoryImpl(dataSource);
        GenreRepository genreRepository = new GenreRepositoryImpl(dataSource);
        StudioRepository studioRepository = new StudioRepositoryImpl(dataSource);
        UserAnimeRepository userAnimeRepository = new UserAnimeRepositoryImpl(dataSource);

        SignUpService signUpService = new SignUpServiceImpl(userRepository);
        SignInService signInService = new SignInServiceImpl(userRepository);
        AvatarService avatarService = new AvatarServiceImpl(avatarRepository);
        ProfileService profileService = new ProfileServiceImpl(userRepository);
        AnimeService animeService = new AnimeServiceImpl(genreRepository,studioRepository,animeRepository,
                avatarService,userAnimeRepository);
        StudioService studioService = new StudioServiceImpl(avatarService,studioRepository);
        UserAnimeService userAnimeService = new UserAnimeServiceImpl(userAnimeRepository);

        servletContext.setAttribute("profileService",profileService);
        servletContext.setAttribute("signUpService",signUpService);
        servletContext.setAttribute("signInService",signInService);
        servletContext.setAttribute("avatarService",avatarService);
        servletContext.setAttribute("animeService",animeService);
        servletContext.setAttribute("studioService",studioService);
        servletContext.setAttribute("userAnimeService",userAnimeService);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}

