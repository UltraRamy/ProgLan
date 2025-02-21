package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testCreateProduct() {
        // Arrange
        Product product = new Product();
        product.setProductName("Test Product");
        product.setProductQuantity(10);

        when(productRepository.create(product)).thenReturn(product);

        // Act
        Product createdProduct = productService.create(product);

        // Assert
        assertNotNull(createdProduct);
        assertEquals("1", createdProduct.getProductId()); // First product should have ID "1"
        assertEquals("Test Product", createdProduct.getProductName());
        assertEquals(10, createdProduct.getProductQuantity());
        verify(productRepository).create(product);
    }

    @Test
    void testFindAllProducts() {
        // Arrange
        List<Product> productList = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId("1");
        product1.setProductName("Product 1");
        product1.setProductQuantity(5);
        productList.add(product1);

        Product product2 = new Product();
        product2.setProductId("2");
        product2.setProductName("Product 2");
        product2.setProductQuantity(10);
        productList.add(product2);

        when(productRepository.findAll()).thenReturn(productList.iterator());

        // Act
        List<Product> allProducts = productService.findAll();

        // Assert
        assertEquals(2, allProducts.size());
        assertEquals("1", allProducts.get(0).getProductId());
        assertEquals("Product 1", allProducts.get(0).getProductName());
        assertEquals(5, allProducts.get(0).getProductQuantity());
        assertEquals("2", allProducts.get(1).getProductId());
        assertEquals("Product 2", allProducts.get(1).getProductName());
        assertEquals(10, allProducts.get(1).getProductQuantity());
        verify(productRepository).findAll();
    }

    @Test
    void testDeleteProduct_Success() {
        // Arrange
        String productId = "1";
        when(productRepository.deleteById(productId)).thenReturn(true);

        // Act
        productService.delete(productId);

        // Assert
        verify(productRepository).deleteById(productId);
    }

    @Test
    void testDeleteProduct_Failure_ProductNotFound() {
        // Arrange
        String productId = "999";
        when(productRepository.deleteById(productId)).thenReturn(false);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            productService.delete(productId);
        });
        assertEquals("Product with ID 999 not found", exception.getMessage());
        verify(productRepository).deleteById(productId);
    }

    @Test
    void testFindProductById() {
        // Arrange
        String productId = "1";
        Product product = new Product();
        product.setProductId(productId);
        product.setProductName("Test Product");
        product.setProductQuantity(10);

        when(productRepository.findById(productId)).thenReturn(product);

        // Act
        Product foundProduct = productService.findById(productId);

        // Assert
        assertNotNull(foundProduct);
        assertEquals(productId, foundProduct.getProductId());
        assertEquals("Test Product", foundProduct.getProductName());
        assertEquals(10, foundProduct.getProductQuantity());
        verify(productRepository).findById(productId);
    }

    @Test
    void testUpdateProduct_Success() {
        // Arrange
        Product product = new Product();
        product.setProductId("1");
        product.setProductName("Updated Product");
        product.setProductQuantity(20);

        when(productRepository.update(product)).thenReturn(product);

        // Act
        Product updatedProduct = productService.update(product);

        // Assert
        assertNotNull(updatedProduct);
        assertEquals("1", updatedProduct.getProductId());
        assertEquals("Updated Product", updatedProduct.getProductName());
        assertEquals(20, updatedProduct.getProductQuantity());
        verify(productRepository).update(product);
    }

    @Test
    void testUpdateProduct_Failure_ProductNotFound() {
        // Arrange
        Product product = new Product();
        product.setProductId("999");
        product.setProductName("Non-existent Product");
        product.setProductQuantity(10);

        when(productRepository.update(product)).thenReturn(null);

        // Act
        Product result = productService.update(product);

        // Assert
        assertNull(result);
        verify(productRepository).update(product);
    }
}