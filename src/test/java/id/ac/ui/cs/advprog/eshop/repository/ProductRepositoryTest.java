package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ProductRepositoryTest {

    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        productRepository = new ProductRepository();
    }

    @Test
    void testCreateProduct() {
        // Arrange
        Product product = new Product();
        product.setProductId("1");
        product.setProductName("Test Product");
        product.setProductQuantity(10);

        // Act
        Product createdProduct = productRepository.create(product);

        // Assert
        assertNotNull(createdProduct);
        assertEquals("1", createdProduct.getProductId());
        assertEquals("Test Product", createdProduct.getProductName());
        assertEquals(10, createdProduct.getProductQuantity());
    }

    @Test
    void testFindAllProducts() {
        // Arrange
        Product product1 = new Product();
        product1.setProductId("1");
        product1.setProductName("Product 1");
        product1.setProductQuantity(5);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("2");
        product2.setProductName("Product 2");
        product2.setProductQuantity(10);
        productRepository.create(product2);

        // Act
        Iterator<Product> productIterator = productRepository.findAll();
        List<Product> productList = new ArrayList<>();
        productIterator.forEachRemaining(productList::add);

        // Assert
        assertEquals(2, productList.size());
        assertEquals("1", productList.get(0).getProductId());
        assertEquals("Product 1", productList.get(0).getProductName());
        assertEquals(5, productList.get(0).getProductQuantity());
        assertEquals("2", productList.get(1).getProductId());
        assertEquals("Product 2", productList.get(1).getProductName());
        assertEquals(10, productList.get(1).getProductQuantity());
    }

    @Test
    void testDeleteProduct_Success() {
        // Arrange
        Product product = new Product();
        product.setProductId("1");
        product.setProductName("Test Product");
        product.setProductQuantity(10);
        productRepository.create(product);

        // Act
        boolean isDeleted = productRepository.deleteById("1");

        // Assert
        assertTrue(isDeleted);
        assertNull(productRepository.findById("1"));
    }

    @Test
    void testDeleteProduct_Failure_ProductNotFound() {
        // Arrange
        Product product = new Product();
        product.setProductId("1");
        product.setProductName("Test Product");
        product.setProductQuantity(10);
        productRepository.create(product);

        // Act
        boolean isDeleted = productRepository.deleteById("999"); // Non-existent ID

        // Assert
        assertFalse(isDeleted);
    }

    @Test
    void testFindProductById_Success() {
        // Arrange
        Product product = new Product();
        product.setProductId("1");
        product.setProductName("Test Product");
        product.setProductQuantity(10);
        productRepository.create(product);

        // Act
        Product foundProduct = productRepository.findById("1");

        // Assert
        assertNotNull(foundProduct);
        assertEquals("1", foundProduct.getProductId());
        assertEquals("Test Product", foundProduct.getProductName());
        assertEquals(10, foundProduct.getProductQuantity());
    }

    @Test
    void testFindProductById_Failure_ProductNotFound() {
        // Arrange
        Product product = new Product();
        product.setProductId("1");
        product.setProductName("Test Product");
        product.setProductQuantity(10);
        productRepository.create(product);

        // Act
        Product foundProduct = productRepository.findById("999"); // Non-existent ID

        // Assert
        assertNull(foundProduct);
    }

    @Test
    void testUpdateProduct_Success() {
        // Arrange
        Product product = new Product();
        product.setProductId("1");
        product.setProductName("Test Product");
        product.setProductQuantity(10);
        productRepository.create(product);

        Product updatedProduct = new Product();
        updatedProduct.setProductId("1");
        updatedProduct.setProductName("Updated Product");
        updatedProduct.setProductQuantity(20);

        // Act
        Product result = productRepository.update("",updatedProduct);

        // Assert
        assertNotNull(result);
        assertEquals("1", result.getProductId());
        assertEquals("Updated Product", result.getProductName());
        assertEquals(20, result.getProductQuantity());

        Product foundProduct = productRepository.findById("1");
        assertNotNull(foundProduct);
        assertEquals("Updated Product", foundProduct.getProductName());
        assertEquals(20, foundProduct.getProductQuantity());
    }

    @Test
    void testUpdateProduct_Failure_ProductNotFound() {
        // Arrange
        Product updatedProduct = new Product();
        updatedProduct.setProductId("999"); // Non-existent ID
        updatedProduct.setProductName("Non-existent Product");
        updatedProduct.setProductQuantity(10);

        // Act
        Product result = productRepository.update("", updatedProduct);

        // Assert
        assertNull(result);
    }
    @Test
    void testUpdateProduct_WhenMultipleProducts_OnlyCorrectOneUpdated() {
        // Create and add multiple products
        Product product1 = new Product();
        product1.setProductId("001");
        product1.setProductName("Laptop");
        product1.setProductQuantity(10);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("002");
        product2.setProductName("Mouse");
        product2.setProductQuantity(5);
        productRepository.create(product2);

        Product product3 = new Product();
        product3.setProductId("003");
        product3.setProductName("Keyboard");
        product3.setProductQuantity(7);
        productRepository.create(product3);

        // Update product2
        Product updatedProduct2 = new Product();
        updatedProduct2.setProductId("002");
        updatedProduct2.setProductName("Wireless Mouse");
        updatedProduct2.setProductQuantity(8);

        productRepository.update("",updatedProduct2);

        // Check the updated product2
        Product foundProduct2 = productRepository.findById("002");
        assertNotNull(foundProduct2, "Product 002 should be found");
        assertEquals("Wireless Mouse", foundProduct2.getProductName());
        assertEquals(8, foundProduct2.getProductQuantity());

        // Check that other products are not updated
        Product foundProduct1 = productRepository.findById("001");
        assertNotNull(foundProduct1, "Product 001 should be found");
        assertEquals("Laptop", foundProduct1.getProductName());
        assertEquals(10, foundProduct1.getProductQuantity());  // Ensure no update happened to product1

        Product foundProduct3 = productRepository.findById("003");
        assertNotNull(foundProduct3, "Product 003 should be found");
        assertEquals("Keyboard", foundProduct3.getProductName());
        assertEquals(7, foundProduct3.getProductQuantity());  // Ensure no update happened to product3
    }

}