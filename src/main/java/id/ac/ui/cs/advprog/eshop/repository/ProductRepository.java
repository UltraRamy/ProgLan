package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;
import java.util.Objects;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository {

    private List<Product> productData = new ArrayList<>();

    public Product create(Product product) {
        productData.add(product);
        return product;
    }

    public Iterator<Product> findAll() {
        return productData.iterator();
    }

    // Delete product by ID

    public boolean deleteById(String id) {
        Optional<Product> productToDelete = productData.stream()
                .filter(product -> product.getProductId().equals(id))  // Compare the productId (String)
                .findFirst();

        if (productToDelete.isPresent()) {
            productData.remove(productToDelete.get());
            return true;
        }

        return false;
    }

    public Product findById(String id) {
        return productData.stream()
                .filter(product -> product.getProductId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Product update(String id, Product updatedProduct) {
        for (Product product : productData) {
            if (Objects.equals(product.getProductId(), updatedProduct.getProductId())) {
                productData.set(productData.indexOf(product), updatedProduct);
                return updatedProduct;
            }
        }
        return null;
    }

}
