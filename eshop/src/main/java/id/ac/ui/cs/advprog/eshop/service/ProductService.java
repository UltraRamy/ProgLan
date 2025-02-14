package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import java.util.List;

public interface ProductService {
    public Product create(Product product);
    public List<Product> findAll();
    Product findById(String id);  // Added method for fetching a product by ID
    Product update(Product product);  // Added method for updating a product
    // Add the delete method signature
    public void delete(String id);
}
