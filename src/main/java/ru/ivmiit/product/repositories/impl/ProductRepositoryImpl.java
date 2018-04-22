package ru.ivmiit.product.repositories.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.ivmiit.product.models.Product;
import ru.ivmiit.product.repositories.ProductRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 06.04.2018
 *
 * @author Robert Bagramov.
 */
@Repository("productRepositoryImpl")
public class ProductRepositoryImpl implements ProductRepository {
    private static String INSERT_PRODUCT_SQL = "INSERT INTO product (name, type, price) VALUES (?, ?, ?)";
    private static String UPDATE_PRODUCT_SQL = "UPDATE product SET name = ?, type = ?, price = ? WHERE id = ?";
    private static String SELECT_ALL_FROM_PRODUCTS_SQL = "SELECT * FROM product";
    private static String DELETE_BY_ID_SQL = "DELETE FROM product p WHERE p.id = ?";
    private static String FIND_BY_ID_SQL = "SELECT * FROM product p WHERE p.id = ?";
    private final Connection jdbcConnection;

    @Autowired
    public ProductRepositoryImpl(Connection jdbcConnection) {
        this.jdbcConnection = jdbcConnection;
    }

    public List<Product> findAll() {
        List<Product> listProduct = new ArrayList();

        try {
            Statement statement = jdbcConnection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_FROM_PRODUCTS_SQL);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String type = resultSet.getString("type");
                double price = resultSet.getFloat("price");

                Product product = Product.builder()
                        .id(id)
                        .name(name)
                        .type(type)
                        .price(price)
                        .build();

                listProduct.add(product);
            }

            return listProduct;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public boolean create(Product product) {
        try {
            PreparedStatement statement = jdbcConnection.prepareStatement(INSERT_PRODUCT_SQL);
            statement.setString(1, product.getName());
            statement.setString(2, product.getType());
            statement.setDouble(3, product.getPrice());

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public Product findById(int id) {
        try {
            PreparedStatement statement = jdbcConnection.prepareStatement(FIND_BY_ID_SQL);
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            resultSet.next();

            String name = resultSet.getString("name");
            String type = resultSet.getString("type");
            double price = resultSet.getFloat("price");

            return Product.builder()
                    .id(id)
                    .name(name)
                    .type(type)
                    .price(price)
                    .build();

        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }

    }

    @Override
    public void update(Product product) {
        try {
            PreparedStatement statement = jdbcConnection.prepareStatement(UPDATE_PRODUCT_SQL);
            statement.setString(1, product.getName());
            statement.setString(2, product.getType());
            statement.setDouble(3, product.getPrice());
            statement.setInt(4, product.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void delete(int id) {
        try {
            PreparedStatement statement = jdbcConnection.prepareStatement(DELETE_BY_ID_SQL);

            statement.setInt(1, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
