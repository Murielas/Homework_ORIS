package ru.itis.config;

import ru.itis.converter.UserToUserEntityConverter;
import ru.itis.dao.UsersData;
import ru.itis.service.UserService;
import ru.itis.service.UserServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ApplicationListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();

        UsersData usersData = new UsersData();
        UserToUserEntityConverter userConverter = new UserToUserEntityConverter();
        UserService userService = new UserServiceImpl(usersData, userConverter);

        context.setAttribute("userService", userService);
    }
}
