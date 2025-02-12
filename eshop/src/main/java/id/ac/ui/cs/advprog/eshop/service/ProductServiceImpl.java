package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    // Static counter to keep track of the last productId assigned
    private static int productIdCounter = 1;

    @Override
    public Product create(Product product) {
        // Set the productId as a string by converting the incrementing counter to a string
        product.setProductId(String.valueOf(productIdCounter++));
        return productRepository.create(product);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(String id) {
        return productRepository.findById(id);
    }

    @Override
    public Product update(Product product) {
        return productRepository.update(product);
    }
}
