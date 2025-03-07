package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class PaymentRepository {

    private List<Payment> paymentData = new ArrayList<>();  // A simple list to store payments

    // Add a new Payment for the current Order and automatically save it
    public Payment addPayment(Order order, String method, Map<String, String> paymentData) {
        Payment payment = new Payment(order.getId(), method, paymentData, order);  // Create Payment and associate with Order
        this.paymentData.add(payment);  // Save Payment (for now, just adding to the list)
        return payment;
    }

    // Set the status of a Payment and update the corresponding Order status if needed
    public Payment setStatus(Payment payment, String status) {
        payment.setStatus(status);  // Set status for Payment

        // If Payment status is "SUCCESS", update the related Order status to "SUCCESS"
        if (status.equals("PAID")) {
            Order order = payment.getOrder();  // Get related Order from Payment
            order.setStatus("SUCCESS");  // Update Order status to "SUCCESS"
        }
        // If Payment status is "REJECTED", update the related Order status to "FAILED"
        else if (status.equals("FAILED")) {
            Order order = payment.getOrder();  // Get related Order from Payment
            order.setStatus("FAILED");  // Update Order status to "FAILED"
        }
        return payment;
    }

    // Get a Payment by its paymentId
    public Payment getPayment(String paymentId) {
        for (Payment payment : paymentData) {
            if (payment.getId().equals(paymentId)) {
                return payment;  // Return the Payment with the matching ID
            }
        }
        return null;  // If no Payment found, return null
    }

    // Get all Payments
    public List<Payment> getAllPayments() {
        return this.paymentData;  // Return the list of all Payments
    }
}
