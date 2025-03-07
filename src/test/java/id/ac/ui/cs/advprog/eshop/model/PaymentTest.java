package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PaymentTest {
    private PaymentRepository paymentRepository;
    private Order order;
    private Map<String, String> cashOnDelivery;

    @BeforeEach
    void setUp() {
        paymentRepository = mock(PaymentRepository.class);
        cashOnDelivery = new HashMap<>();
        cashOnDelivery.put("address", "123 Main St");
        cashOnDelivery.put("deliveryFee", "5000");

        order = mock(Order.class);
        when(order.getId()).thenReturn("13652556-012a-4c07-b546-54eb1396d79b");
        when(order.getStatus()).thenReturn("WAITING_PAYMENT");
    }

    @Test
    void testCreatePayment() {
        Payment payment = new Payment("payment123", "CASH_ON_DELIVERY", cashOnDelivery, order);

        assertEquals("payment123", payment.getId());
        assertEquals("CASH_ON_DELIVERY", payment.getMethod());
        assertEquals(cashOnDelivery, payment.getPaymentData());
        assertEquals("WAITING_PAYMENT", order.getStatus());
        assertEquals(PaymentStatus.PENDING.getValue(), payment.getStatus());
    }

    @Test
    void testSetStatusToSuccess() {
        Payment payment = new Payment("payment123", "CASH_ON_DELIVERY", cashOnDelivery, order);
        payment.setStatus(PaymentStatus.SUCCESS.getValue());

        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
        verify(order).setStatus("SUCCESS");
    }

    @Test
    void testSetStatusToRejected() {
        Payment payment = new Payment("payment123", "BANK_TRANSFER", cashOnDelivery, order);
        payment.setStatus(PaymentStatus.REJECTED.getValue());

        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
        verify(order).setStatus("FAILED");
    }

    @Test
    void testSetStatusToInvalidStatus() {
        Payment payment = new Payment("payment123", "CASH_ON_DELIVERY", cashOnDelivery, order);
        assertThrows(IllegalArgumentException.class, () -> payment.setStatus("INVALID"));
    }

    @Test
    void testGetPaymentById() {
        Payment payment = new Payment("payment123", "CASH_ON_DELIVERY", cashOnDelivery, order);
        when(paymentRepository.getPayment("payment123")).thenReturn(payment);

        Payment retrievedPayment = paymentRepository.getPayment("payment123");
        assertNotNull(retrievedPayment);
        assertEquals("payment123", retrievedPayment.getId());
    }

    @Test
    void testGetAllPayments() {
        when(paymentRepository.getAllPayments()).thenReturn(new HashMap<>());
        assertNotNull(paymentRepository.getAllPayments());
    }
}