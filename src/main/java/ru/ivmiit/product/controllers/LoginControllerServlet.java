package ru.ivmiit.product.controllers;

import org.springframework.context.ApplicationContext;
import ru.ivmiit.product.services.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 04.04.2018
 *
 * @author Robert Bagramov.
 */
@WebServlet("/login")
public class LoginControllerServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        System.out.println("log servlet");

        ApplicationContext springContext = (ApplicationContext) config.getServletContext().getAttribute("springContext");
        userService = (UserService) springContext.getBean("userServiceImpl");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getServletContext()
                .getRequestDispatcher("/login.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        if (userService.authenticateUserByLoginAndPassword(login, password)) {
            HttpSession session = request.getSession(true);
            session.setAttribute("login", login);
            session.setMaxInactiveInterval(5 * 60);

            Date dateNow = new Date();
            SimpleDateFormat formatForDateNow = new SimpleDateFormat("hh:mm:ss");

            Cookie timeCookie = new Cookie("loginTime", formatForDateNow.format(dateNow));
            timeCookie.setMaxAge(5 * 60);

            response.addCookie(timeCookie);

            request.getServletContext()
                    .getRequestDispatcher("/products")
                    .forward(request, response);
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
            dispatcher.forward(request, response);
        }
    }
}
