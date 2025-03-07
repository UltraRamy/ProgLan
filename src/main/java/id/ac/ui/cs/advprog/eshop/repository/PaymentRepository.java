package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class PaymentRepository {

    // Add a new Payment for the current Order and automatically save it
    public Payment addPayment(Order order, String method, Map<String, String> paymentData) {
        return null;
    }

    // Set the status of a Payment and update the corresponding Order status if needed
    public Payment setStatus(Payment payment, String status) {
        return null;
    }

    // Get a Payment by its paymentId
    public Payment getPayment(String paymentId) {
        return null;
    }

    // Get all Payments
    public List<Payment> getAllPayments() {
        return null;
    }
}
