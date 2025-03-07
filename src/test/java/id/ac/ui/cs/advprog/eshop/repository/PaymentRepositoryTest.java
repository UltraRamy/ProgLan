package id.ac.ui.cs.advprog.eshop.repository;
import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PaymentRepositoryTest {

    private PaymentRepository paymentRepository;
    private OrderRepository orderRepository;
    private List<Order> orders;
    private Map<String, String> paymentData;

    @BeforeEach
    void setUp() {
        paymentRepository = new PaymentRepository();
        orderRepository = new OrderRepository();

        List<Product> products = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(2);
        products.add(product1);

        orders = new ArrayList<>();
        Order order1 = new Order("13652556-012a-4c07-b546-54eb1396d79b", products, 1708560000L, "Safira Sudrajat");
        orders.add(order1);
        Order order2 = new Order("7f9e15bb-4b15-42f4-aebc-c3af385fb078", products, 1708570000L, "Safira Sudrajat");
        orders.add(order2);
        Order order3 = new Order("e334ef40-9eff-4da8-9487-8ee697ecbf1e", products, 1708570000L, "Bambang Sudrajat");
        orders.add(order3);

        for (Order order : orders) {
            orderRepository.save(order);
        }

        paymentData = new HashMap<>();
        paymentData.put("transactionId", "txn123");
        paymentData.put("amount", "5000");
        paymentData.put("currency", "USD");
    }

    @Test
    void testAddPayment() {
        Order order = orders.get(1);
        Payment payment = paymentRepository.addPayment(order, "Credit Card", paymentData);

        assertNotNull(payment);
        assertEquals("Credit Card", payment.getMethod());
        assertEquals("PENDING", payment.getStatus());
        assertEquals(order.getId(), payment.getOrder().getId());
    }

    @Test
    void testSetStatusToSuccess() {
        Order order = orders.get(1);
        Payment payment = paymentRepository.addPayment(order, "Credit Card", paymentData);
        paymentRepository.setStatus(payment, PaymentStatus.SUCCESS.getValue());

        Payment updatedPayment = paymentRepository.getPayment(payment.getId());
        assertEquals(PaymentStatus.SUCCESS.getValue(), updatedPayment.getStatus());

    }

    @Test
    void testSetStatusToRejected() {
        Order order = orders.get(1);
        Payment payment = paymentRepository.addPayment(order, "Credit Card", paymentData);
        paymentRepository.setStatus(payment, PaymentStatus.REJECTED.getValue());

        Payment updatedPayment = paymentRepository.getPayment(payment.getId());
        assertEquals(PaymentStatus.REJECTED.getValue(), updatedPayment.getStatus());

    }

    @Test
    void testGetPayment() {
        Order order = orders.get(1);
        Payment payment = paymentRepository.addPayment(order, "Credit Card", paymentData);
        Payment foundPayment = paymentRepository.getPayment(payment.getId());

        assertEquals(payment.getId(), foundPayment.getId());
        assertEquals(payment.getStatus(), foundPayment.getStatus());
    }

    @Test
    void testGetAllPayments() {
        for (Order order : orders) {
            paymentRepository.addPayment(order, "Credit Card", paymentData);
        }

        List<Payment> payments = paymentRepository.getAllPayments();
        assertEquals(3, payments.size());
    }

    @Test
    void testSetStatusWithInvalidPayment() {
        Payment payment = new Payment("pay124", "Bank Transfer", null, paymentData, null);

        assertThrows(IllegalArgumentException.class, () -> {
            paymentRepository.setStatus(payment, "INVALID_STATUS");
        });
    }
}
