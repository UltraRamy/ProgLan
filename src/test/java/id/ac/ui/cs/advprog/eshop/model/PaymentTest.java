package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PaymentTest {

    private Order order;
    private Map<String, String> cashOnDelivery;

    @BeforeEach
    void setUp() {
        // Setting up a mock order object
        this.order = new Order("13652556-012a-4c07-b546-54eb1396d79b",
                new ArrayList<>(), 1708560000L, "Safira Sudrajat");

        // Setting up a mock cashOnDelivery map
        this.cashOnDelivery = new HashMap<>();
        this.cashOnDelivery.put("amount", "1000000");
        this.cashOnDelivery.put("currency", "IDR");
    }

    @Test
    void testCreatePaymentSuccess() {
        Payment payment = new Payment("1", "CREDIT_CARD", "PENDING", this.cashOnDelivery, this.order);
        assertEquals("1", payment.getId());
        assertEquals("CREDIT_CARD", payment.getMethod());
        assertEquals("PENDING", payment.getStatus());
        assertSame(this.cashOnDelivery, payment.getCashOnDelivery());
        assertSame(this.order, payment.getOrder());
    }

    @Test
    void testCreatePaymentWithEmptyStatus() {
        Payment payment = new Payment("1", "CREDIT_CARD", "", this.cashOnDelivery, this.order);
        assertEquals("", payment.getStatus());
    }

    @Test
    void testSetStatusToPaid() {
        Payment payment = new Payment("1", "CREDIT_CARD", "PENDING", this.cashOnDelivery, this.order);
        payment.setStatus("PAID");
        assertEquals("PAID", payment.getStatus());
    }

    @Test
    void testSetStatusToInvalidStatus() {
        Payment payment = new Payment("1", "CREDIT_CARD", "PENDING", this.cashOnDelivery, this.order);
        assertThrows(IllegalArgumentException.class, () -> payment.setStatus("INVALID_STATUS"));
    }

    @Test
    void testCreatePaymentWithoutStatus() {
        Payment payment = new Payment("1", "CREDIT_CARD", this.cashOnDelivery, this.order);
        assertNull(payment.getStatus()); // Ensure status is null by default
    }
}
