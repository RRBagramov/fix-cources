package ru.ivmiit.product.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.ivmiit.product.models.Product;
import ru.ivmiit.product.repositories.ProductRepository;
import ru.ivmiit.product.services.ProductService;

import java.util.List;

/**
 * 18.04.2018
 *
 * @author Robert Bagramov.
 */
@Service("productServiceImpl")
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(@Qualifier("productRepositoryImpl") ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public void saveProduct(Product product) {
        productRepository.create(product);
    }

    @Override
    public Product findProductById(int id) {
        return productRepository.findById(id);
    }

    @Override
    public void updateProduct(Product product) {
        productRepository.update(product);
    }

    @Override
    public void deleteProduct(int id) {
        productRepository.delete(id);
    }
}
