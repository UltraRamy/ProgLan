package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductPageControllerTest {

    @Mock
    private ProductService productService;

    @Mock
    private Model model;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    void setUp() {
        // Setup can be done here if needed
    }

    @Test
    void testCreateProductPage() {
        // Act
        String viewName = productController.createProductPage(model);

        // Assert
        assertEquals("createProduct", viewName);
        verify(model).addAttribute(eq("product"), any(Product.class));
    }

    @Test
    void testCreateProductPost() {
        // Arrange
        Product product = new Product();
        product.setProductName("Test Product");
        product.setProductQuantity(10);

        // Act
        String viewName = productController.createProductPost(product, model);

        // Assert
        assertEquals("redirect:list", viewName);
        verify(productService).create(product);
    }

    @Test
    void testProductListPage() {
        // Arrange
        List<Product> productList = new ArrayList<>();
        productList.add(new Product());
        when(productService.findAll()).thenReturn(productList);

        // Act
        String viewName = productController.productListPage(model);

        // Assert
        assertEquals("productList", viewName);
        verify(model).addAttribute("products", productList);
    }

    @Test
    void testDeleteProduct() {
        // Arrange
        String productId = "123";

        // Act
        String viewName = productController.deleteProduct(productId);

        // Assert
        assertEquals("redirect:/product/list", viewName);
        verify(productService).delete(productId);
    }

    @Test
    void testEditProductPage_ProductFound() {
        // Arrange
        String productId = "123";
        Product product = new Product();
        product.setProductId(productId);
        when(productService.findById(productId)).thenReturn(product);

        // Act
        String viewName = productController.editProductPage(productId, model);

        // Assert
        assertEquals("editProduct", viewName);
        verify(model).addAttribute("product", product);
    }

    @Test
    void testEditProductPage_ProductNotFound() {
        // Arrange
        String productId = "123";
        when(productService.findById(productId)).thenReturn(null);

        // Act
        String viewName = productController.editProductPage(productId, model);

        // Assert
        assertEquals("redirect:/product/list", viewName);
        verify(model, never()).addAttribute(eq("product"), any(Product.class));
    }

    @Test
    void testEditProductPost() {
        // Arrange
        Product product = new Product();
        product.setProductId("123");
        product.setProductName("Updated Product");
        product.setProductQuantity(20);

        // Act
        String viewName = productController.editProductPost(product);

        // Assert
        assertEquals("redirect:/product/list", viewName);
        verify(productService).update(product);
    }
}