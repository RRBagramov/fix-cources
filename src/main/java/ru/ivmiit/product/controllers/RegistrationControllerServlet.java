package ru.ivmiit.product.controllers;

import org.springframework.context.ApplicationContext;
import ru.ivmiit.product.models.User;
import ru.ivmiit.product.services.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 05.04.2018
 *
 * @author Robert Bagramov.
 */
@WebServlet("/signUp")
public class RegistrationControllerServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        System.out.println("reg servlet");

        ApplicationContext springContext = (ApplicationContext) config.getServletContext().getAttribute("springContext");
        userService = (UserService) springContext.getBean("userServiceImpl");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getServletContext()
                .getRequestDispatcher("/signUp.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        User user = User.builder()
                .login(login)
                .password(password)
                .build();

        userService.saveUser(user);
        doGet(request, response);
    }
}
