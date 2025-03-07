package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;
import java.util.Map;

class PaymentTest {

    private Payment payment;
    private Map<String, String> paymentData;

    @BeforeEach
    void setUp() {
        paymentData = new HashMap<>();
        paymentData.put("transactionId", "txn123");
        paymentData.put("amount", "5000");
        paymentData.put("currency", "USD");

        payment = new Payment("pay123", "Credit Card", PaymentStatus.PENDING.getValue(), paymentData, null);

    }

    @Test
    void testCreatePaymentSuccess() {
        assertNotNull(payment);
        assertEquals("pay123", payment.getId());
        assertEquals("Credit Card", payment.getMethod());
        assertEquals("PENDING", payment.getStatus());
        assertNotNull(payment.getPaymentData());
        assertEquals(3, payment.getPaymentData().size());
        assertEquals("txn123", payment.getPaymentData().get("transactionId"));
    }

    @Test
    void testSetStatusToPaid() {
        payment.setStatus("SUCCESS");
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
    }

    @Test
    void testSetStatusToCancelled() {
        payment.setStatus("CANCELLED");
        assertEquals("CANCELLED", payment.getStatus());
    }

    @Test
    void testSetInvalidStatus() {
        assertThrows(IllegalArgumentException.class, () -> {
            payment.setStatus("");
        });
    }

    @Test
    void testSetInvalidStatusEnum() {
        assertThrows(IllegalArgumentException.class, () -> {
            payment.setStatus("INVALID_STATUS");
        });
    }

    @Test
    void testCreatePaymentWithoutStatus() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Payment("pay124", "Bank Transfer", null, paymentData, null);
        });
    }

    @Test
    void testPaymentData() {
        assertNotNull(payment.getPaymentData());
        assertEquals("5000", payment.getPaymentData().get("amount"));
        assertEquals("USD", payment.getPaymentData().get("currency"));
    }
}
