package ru.ivmiit.product.controllers;

import org.springframework.context.ApplicationContext;
import ru.ivmiit.product.models.Product;
import ru.ivmiit.product.services.ProductService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * 12.03.2018
 *
 * @author Robert Bagramov.
 */
@WebServlet(urlPatterns = {"/new", "/products", "/add", "/updateProduct", "/edit", "/deleteProduct"})
public class ProductControllerServlet extends HttpServlet {
    private ProductService productService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        System.out.println("prod servlet");

        ApplicationContext springContext = (ApplicationContext) config.getServletContext().getAttribute("springContext");
        productService = (ProductService) springContext.getBean("productServiceImpl");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/deleteProduct":
                    delete(request, response);
                    break;
                case "/products":
                    getProductsList(request, response);
                    break;
                case "/add":
                    create(request, response);
                    break;
                case "/updateProduct":
                    update(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                default:
                    response.sendRedirect("products");
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException();
        }
    }

    private void getProductsList(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Product> listProducts = productService.getProducts();

        request.setAttribute("listProduct", listProducts);
        RequestDispatcher dispatcher = request.getRequestDispatcher("productList.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("productForm.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = productService.findProductById(id);

        RequestDispatcher dispatcher = request.getRequestDispatcher("productForm.jsp");
        request.setAttribute("product", product);
        dispatcher.forward(request, response);

    }

    private void update(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String type = request.getParameter("type");
        double price = Double.parseDouble(request.getParameter("price"));

        Product product = Product.builder()
                .id(id)
                .name(name)
                .type(type)
                .price(price)
                .build();

        productService.updateProduct(product);

        response.sendRedirect("products");
    }

    private void delete(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        productService.deleteProduct(id);

        response.sendRedirect("products");

    }

    private void create(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String name = request.getParameter("name");
        String type = request.getParameter("type");
        double price = Double.parseDouble(request.getParameter("price"));

        Product product = Product.builder()
                .name(name)
                .type(type)
                .price(price)
                .build();

        productService.saveProduct(product);

        response.sendRedirect("products");
    }
}
