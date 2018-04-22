package ru.ivmiit.product.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * 22.04.2018
 *
 * @author Robert Bagramov.
 */
@WebListener
public class ContextInitializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ApplicationContext springContext = new AnnotationConfigApplicationContext(AppConfig.class);

        sce.getServletContext().setAttribute("springContext", springContext);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
