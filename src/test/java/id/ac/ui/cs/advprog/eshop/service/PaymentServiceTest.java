package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

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
        orders = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId("product1");
        product1.setProductName("Product Name 1");
        product1.setProductQuantity(10);
        List<Product> productList = Arrays.asList(product1);

        Order order1 = new Order("13652556-012a-4c07-b546-54eb1396d79b", productList, 1708560000L, "Safira Sudrajat");
        orders.add(order1);

        Order order2 = new Order("7f9e15bb-4b15-42f4-aebc-c3af385fb078", productList, 1708570000L, "Safira Sudrajat");
        orders.add(order2);

        payments = new ArrayList<>();
        Payment payment1 = new Payment("payment-1", "Credit Card", PaymentStatus.PENDING.getValue(), null, order1);
        payments.add(payment1);

        Payment payment2 = new Payment("payment-2", "Credit Card", PaymentStatus.PENDING.getValue(), null, order2);
        payments.add(payment2);
    }

    @Test
    void testAddPayment() {
        Payment payment = payments.get(1);
        Map<String, String> paymentData = null;
        doReturn(payment).when(paymentRepository).addPayment(any(Order.class), eq(payment.getMethod()), eq(paymentData));
        Payment result = paymentService.addPayment(orders.get(1), payment.getMethod(), paymentData, "");

        verify(paymentRepository, times(1)).addPayment(any(Order.class), eq(payment.getMethod()), eq(paymentData));
        assertEquals(payment.getId(), result.getId());
    }

    @Test
    void testAddPaymentIfAlreadyExists() {
        Payment payment = payments.get(1);

        // When payment already exists, the service method should return null and not call addPayment
        assertNull(paymentService.addPayment(orders.get(1), payment.getMethod(), null, ""));

        // Verifying that addPayment is not invoked
        verify(paymentRepository, times(0)).addPayment(any(Order.class), eq(payment.getMethod()), anyMap());
    }



    @Test
    void testSetStatus() {
        Payment payment = payments.get(1);
        Payment newPayment = new Payment(payment.getId(), payment.getMethod(), PaymentStatus.SUCCESS.getValue(), payment.getPaymentData(),
                payment.getOrder());
        doReturn(payment).when(paymentRepository).getPayment(payment.getId());
        doReturn(newPayment).when(paymentRepository).setStatus(any(Payment.class), eq(PaymentStatus.SUCCESS.getValue()));

        Payment result = paymentService.setStatus(payment, PaymentStatus.SUCCESS.getValue());

        assertEquals(payment.getId(), result.getId());
        assertEquals(PaymentStatus.PENDING.getValue(), result.getStatus());
        verify(paymentRepository, times(1)).setStatus(any(Payment.class), eq(PaymentStatus.SUCCESS.getValue()));
    }

    @Test
    void testSetStatusInvalidStatus() {
        Payment payment = payments.get(1);
        doReturn(payment).when(paymentRepository).getPayment(payment.getId());

        assertThrows(IllegalArgumentException.class,
                () -> paymentService.setStatus(payment, "INVALID_STATUS"));

        verify(paymentRepository, times(0)).setStatus(any(Payment.class), eq("INVALID_STATUS"));
    }

    @Test
    void testSetStatusInvalidPaymentId() {
        doReturn(null).when(paymentRepository).getPayment("invalid-id");

        assertThrows(NoSuchElementException.class,
                () -> paymentService.setStatus(new Payment("invalid-id", "Credit Card", PaymentStatus.PENDING.getValue(), null, null), PaymentStatus.SUCCESS.getValue()));

        verify(paymentRepository, times(0)).setStatus(any(Payment.class), eq(PaymentStatus.SUCCESS.getValue()));
    }

    @Test
    void testFindPaymentByIdIfIdNotFound() {
        Product product1 = new Product();
        product1.setProductId("product1");
        product1.setProductName("Product Name 1");
        product1.setProductQuantity(10);

        List<Product> products = Arrays.asList(product1);
        Order validOrder = new Order("13652556-012a-4c07-b546-54eb1396d79b", products, 1708560000L, "Safira Sudrajat");

        doReturn(null).when(paymentRepository).getPayment("invalid-id");
        assertNull(paymentService.getPayment("invalid-id"));
    }

    @Test
    void testFindPaymentsByOrderId() {
        Order order = orders.get(1);
        Payment payment1 = payments.get(0);
        Payment payment2 = payments.get(1);

        doReturn(payments).when(paymentRepository).getAllPayments();

        List<Payment> results = paymentService.getAllPayments();
        assertEquals(2, results.size());
        assertTrue(results.contains(payment1));
        assertTrue(results.contains(payment2));
    }

    @Test
    void testFindPaymentsByOrderIdIfNoPayments() {
        doReturn(new ArrayList<Payment>()).when(paymentRepository).getAllPayments();

        List<Payment> results = paymentService.getAllPayments();
        assertTrue(results.isEmpty());
    }

}
