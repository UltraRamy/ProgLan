package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    @Autowired
    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Payment addPayment(Order order, String method, Map<String, String> paymentData, String voucherCode) {
        if (voucherCode != null) {
            if (isValidVoucherCode(voucherCode)) {
                Payment payment = new Payment("order0", method, PaymentStatus.SUCCESS.getValue(), paymentData, order);
                paymentRepository.addPayment(order, method, paymentData);
                return paymentRepository.addPayment(order, method, paymentData); // Add with SUCCESS status
            } else {
                Payment payment = new Payment("order", method, PaymentStatus.REJECTED.getValue(), paymentData, order);
                return paymentRepository.addPayment(order, method, paymentData); // Add with REJECTED status
            }
        }
        String address = paymentData != null ? paymentData.get("address") : null;
        String deliveryFee = paymentData != null ? paymentData.get("deliveryFee") : null;
        if (address == null || address.isEmpty() || deliveryFee == null || deliveryFee.isEmpty()) {
            Payment payment = new Payment("order1", method, PaymentStatus.REJECTED.getValue(), paymentData, order);
            return paymentRepository.addPayment(order, method, paymentData); // Add with REJECTED status if COD is incomplete
        }

        // If no voucher code and COD data is valid, set status as PENDING
        Payment payment = new Payment("order2", method, PaymentStatus.PENDING.getValue(), paymentData, order);
        return paymentRepository.addPayment(order, method, paymentData); // Pending payment status
    }

    // Helper method to validate voucher code
    private boolean isValidVoucherCode(String voucherCode) {
        // Check if it is 16 characters, starts with "ESHOP" and contains 8 numerical digits
        return voucherCode.length() == 16
                && voucherCode.startsWith("ESHOP")
                && voucherCode.substring(5).matches(".*\\d.*")
                && voucherCode.substring(5).replaceAll("[^0-9]", "").length() == 8;
    }


    @Override
    public Payment setStatus(Payment payment, String status) {
        Payment existingPayment = paymentRepository.getPayment(payment.getId());
        if (existingPayment == null) {
            throw new NoSuchElementException("Payment with ID " + payment.getId() + " not found.");
        }
        existingPayment.setStatus(status);
        return paymentRepository.setStatus(existingPayment, status);
    }

    @Override
    public Payment getPayment(String paymentId) {
        return paymentRepository.getPayment(paymentId);
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.getAllPayments();
    }
}
