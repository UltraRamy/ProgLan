package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PaymentServiceTest {

    @InjectMocks
    PaymentServiceImpl paymentService;

    @Mock
    PaymentRepository paymentRepository;

    List<Payment> payments;
    List<Order> orders;

    @BeforeEach
    void setUp() {
        List<Order> orders = new ArrayList<>();
        Order order1 = new Order("13652556-012a-4c07-b546-54eb1396d79b", new ArrayList<>(), 1708560000L, "Safira Sudrajat");
        orders.add(order1);
        Order order2 = new Order("7f9e15bb-4b15-42f4-aebc-c3af385fb078", new ArrayList<>(), 1708570000L, "Safira Sudrajat");
        orders.add(order2);

        payments = new ArrayList<>();
        Payment payment1 = new Payment("payment-1", "Credit Card", null, order1, PaymentStatus.PENDING.getValue());
        payments.add(payment1);
        Payment payment2 = new Payment("payment-2", "Credit Card", null, order2, PaymentStatus.PENDING.getValue());
        payments.add(payment2);
    }

    @Test
    void testCreatePayment() {
        Payment payment = payments.get(1);
        doReturn(payment).when(paymentRepository).save(payment);
        Payment result = paymentService.createPayment(payment);
        verify(paymentRepository, times(1)).save(payment);
        assertEquals(payment.getId(), result.getId());
    }

    @Test
    void testCreatePaymentIfAlreadyExists() {
        Payment payment = payments.get(1);
        doReturn(payment).when(paymentRepository).findById(payment.getId());

        assertNull(paymentService.createPayment(payment));
        verify(paymentRepository, times(0)).save(payment);
    }

    @Test
    void testUpdatePaymentStatus() {
        Payment payment = payments.get(1);
        Payment newPayment = new Payment(payment.getId(), payment.getMethod(), payment.getPaymentData(),
                payment.getOrder(), PaymentStatus.SUCCESS.getValue());
        doReturn(payment).when(paymentRepository).findById(payment.getId());
        doReturn(newPayment).when(paymentRepository).save(any(Payment.class));

        Payment result = paymentService.updatePaymentStatus(payment.getId(), PaymentStatus.SUCCESS.getValue());

        assertEquals(payment.getId(), result.getId());
        assertEquals(PaymentStatus.SUCCESS.getValue(), result.getStatus());
        verify(paymentRepository, times(1)).save(any(Payment.class));
    }

    @Test
    void testUpdatePaymentStatusInvalidStatus() {
        Payment payment = payments.get(1);
        doReturn(payment).when(paymentRepository).findById(payment.getId());

        assertThrows(IllegalArgumentException.class,
                () -> paymentService.updatePaymentStatus(payment.getId(), "INVALID_STATUS"));

        verify(paymentRepository, times(0)).save(any(Payment.class));
    }

    @Test
    void testUpdatePaymentStatusInvalidPaymentId() {
        doReturn(null).when(paymentRepository).findById("invalid-id");

        assertThrows(NoSuchElementException.class,
                () -> paymentService.updatePaymentStatus("invalid-id", PaymentStatus.SUCCESS.getValue()));

        verify(paymentRepository, times(0)).save(any(Payment.class));
    }

    @Test
    void testFindPaymentByIdIfIdFound() {
        Payment payment = payments.get(1);
        doReturn(payment).when(paymentRepository).findById(payment.getId());

        Payment result = paymentService.findPaymentById(payment.getId());
        assertEquals(payment.getId(), result.getId());
    }

    @Test
    void testFindPaymentByIdIfIdNotFound() {
        doReturn(null).when(paymentRepository).findById("invalid-id");
        assertNull(paymentService.findPaymentById("invalid-id"));
    }

    @Test
    void testFindPaymentsByOrderId() {
        Order order = orders.get(1);
        Payment payment1 = payments.get(0);
        Payment payment2 = payments.get(1);

        doReturn(payments).when(paymentRepository).findByOrderId(order.getId());

        List<Payment> results = paymentService.findPaymentsByOrderId(order.getId());
        assertEquals(2, results.size());
        assertTrue(results.contains(payment1));
        assertTrue(results.contains(payment2));
    }

    @Test
    void testFindPaymentsByOrderIdIfNoPayments() {
        doReturn(new ArrayList<Payment>()).when(paymentRepository).findByOrderId("invalid-order-id");

        List<Payment> results = paymentService.findPaymentsByOrderId("invalid-order-id");
        assertTrue(results.isEmpty());
    }
}
