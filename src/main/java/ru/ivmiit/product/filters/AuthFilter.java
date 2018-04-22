package ru.ivmiit.product.filters;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 04.04.2018
 *
 * @author Robert Bagramov.
 */
@WebFilter(urlPatterns = {"/", "/new", "/products", "/add"}, filterName = "authenticationFilter")
public class AuthFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest filterRequest, ServletResponse filterResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) filterRequest;
        HttpServletResponse response = (HttpServletResponse) filterResponse;

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("login") == null) {
            filterRequest.getServletContext().getRequestDispatcher("/login").forward(request, response);
        }

        filterChain.doFilter(request, response);
    }

    public void destroy() {

    }
}
