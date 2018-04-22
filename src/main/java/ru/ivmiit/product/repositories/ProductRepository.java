package ru.ivmiit.product.repositories;

import ru.ivmiit.product.models.Product;

import java.util.List;

/**
 * 06.04.2018
 *
 * @author Robert Bagramov.
 */
public interface ProductRepository {
    List<Product> findAll();

    boolean create(Product product);

    Product findById(int id);

    void update(Product product);

    void delete(int id);
}
