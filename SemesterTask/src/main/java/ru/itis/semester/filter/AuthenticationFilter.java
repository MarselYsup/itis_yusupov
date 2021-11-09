package ru.itis.semester.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class AuthenticationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // преобразуем запросы к нужному виду
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        if(request.getRequestURI().startsWith(request.getContextPath()+"/view")
                ||request.getRequestURI().startsWith(request.getContextPath()+"/homepage")) {
            filterChain.doFilter(request, response);
            return;
        }
        // берем сессию у запроса
        // берем только существующую, если сессии не было - то вернет null
        HttpSession session = request.getSession(false);
        //Проверка на админа
        boolean isAdmin = false;
        if (session!=null) {
             isAdmin = session.getAttribute("isAdmin") != null;
        }
        if(!isAdmin&&(request.getRequestURI().startsWith(request.getContextPath()+"/add-anime")
                ||request.getRequestURI().startsWith(request.getContextPath()+"/add-studio"))) {
            response.sendRedirect(request.getContextPath()+"/homepage");
            return;
        }
        // флаг, аутентифицирован ли пользователь
        boolean isAuthenticated = false;
        // существует ли сессия вообще?
        boolean sessionExists = session != null;
        // идет ли запрос на страницу входа или регистрации?
        boolean isRequestOnAuthPage = request.getRequestURI().startsWith(request.getContextPath()+"/sign-in") ||
                request.getRequestURI().startsWith(request.getContextPath()+"/sign-up") ;

        // если сессия есть
        if (sessionExists) {
            // проверим, есть ли атрибует user?
            isAuthenticated = session.getAttribute("user")!= null;
        }


        // если авторизован и запрашивает не открытую страницу или если не авторизован и запрашивает открытую
        if (isAuthenticated && !isRequestOnAuthPage || !isAuthenticated && isRequestOnAuthPage) {
            // отдаем ему то, что он хочет
            filterChain.doFilter(request, response);
            return;
        } else if (isAuthenticated && isRequestOnAuthPage) {
            // пользователь аутенцифицирован и запрашивает страницу входа
            // - отдаем ему профиль
            response.sendRedirect(request.getContextPath()+"/profile");
        } else {
            // если пользователь не аутенцицицирован и запрашивает другие страницы
            response.sendRedirect(request.getContextPath()+"/sign-in");
        }

    }

    @Override
    public void destroy() {

    }
}
