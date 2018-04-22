package ru.ivmiit.product.services;

import ru.ivmiit.product.models.Product;

import java.util.List;

/**
 * 18.04.2018
 *
 * @author Robert Bagramov.
 */
public interface ProductService {
    List<Product> getProducts();

    void saveProduct(Product product);

    Product findProductById(int id);

    void updateProduct(Product product);

    void deleteProduct(int id);
}
