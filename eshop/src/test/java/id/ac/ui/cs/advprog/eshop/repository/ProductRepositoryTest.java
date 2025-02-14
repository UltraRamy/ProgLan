package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ProductRepositoryTest {


    @InjectMocks
    ProductRepository productRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testCreateAndFind() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);
        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(savedProduct.getProductId(), product.getProductId());
        assertEquals(savedProduct.getProductName(), product.getProductName());
        assertEquals(savedProduct.getProductQuantity(), product.getProductQuantity());
    }

    @Test
    void testFindAllIfEmpty() {
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindAllIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);
        Product product2 = new Product();
        product2.setProductId("a0f9de45-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productRepository.create(product2);
        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product1.getProductId(), savedProduct.getProductId());
        savedProduct = productIterator.next();
        assertEquals(product2.getProductId(), savedProduct.getProductId());
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testUpdateProduct_Success() {
        Product product = new Product();
        product.setProductId("20");
        product.setProductName("Tablet");
        product.setProductQuantity(15);
        productRepository.create(product);

        Product updatedProduct = new Product();
        updatedProduct.setProductId("20");
        updatedProduct.setProductName("Updated Tablet");
        updatedProduct.setProductQuantity(20);
        productRepository.update(updatedProduct);

        Product foundProduct = productRepository.findById("20");
        assertNotNull(foundProduct);
        assertEquals("Updated Tablet", foundProduct.getProductName());
        assertEquals(20, foundProduct.getProductQuantity());
    }

    @Test
    void testUpdateProduct_Failure_ProductNotFound() {
        Product updatedProduct = new Product();
        updatedProduct.setProductId("40");
        updatedProduct.setProductName("Non-existent");
        updatedProduct.setProductQuantity(10);

        Product result = productRepository.update(updatedProduct);
        assertNull(result);  // Update should return null as the product does not exist
    }

    @Test
    void testDeleteProduct_Success() {
        Product product = new Product();
        product.setProductId("30");
        product.setProductName("Headphones");
        product.setProductQuantity(8);
        productRepository.create(product);

        boolean isDeleted = productRepository.deleteById("30");
        assertTrue(isDeleted);  // Deletion should be successful
        assertNull(productRepository.findById("30"));  // Product should be deleted
    }

    @Test
    void testDeleteProduct_Failure_ProductNotFound() {
        boolean isDeleted = productRepository.deleteById("40");  // No product with ID "40"
        assertFalse(isDeleted);  // Deletion should fail
        assertNull(productRepository.findById("40"));  // Still should not exist
    }
}