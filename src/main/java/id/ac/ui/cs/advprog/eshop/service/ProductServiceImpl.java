package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    private static int productIdCounter = 1;

    @Override
    public Product create(Product product) {
        product.setProductId(String.valueOf(productIdCounter++));
        return productRepository.create(product);
    }

    @Override
    public List<Product> findAll() {
        Iterator<Product> productIterator = productRepository.findAll();
        List<Product> allProducts = new ArrayList<>();
        productIterator.forEachRemaining(allProducts::add);
        return allProducts;
    }

    @Override
    public void delete(String id) {
        boolean isDeleted = productRepository.deleteById(id);  // Call deleteById with String ID
        if (!isDeleted) {
            throw new IllegalArgumentException("Product with ID " + id + " not found");
        }
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
