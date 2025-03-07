package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.util.Map;

@Builder
@Getter
public class Payment {
    private String id;
    private String method;
    @Setter
    private String status;
    private Map<String, String> paymentData;
    private Order order;  // Add the Order object to represent the related order

    public Payment(String id, String method, String status, Map<String, String> paymentData, Order order) {
        this.id = id;
        this.method = method;
        this.paymentData = paymentData;
        this.order = order; // Set the related order
        this.status = PaymentStatus.PENDING.getValue();
    }

    public Payment(String id, String method, Map<String, String> paymentData, Order order) {
        this(id, method, null, paymentData, order);
    }

    public void setStatus(String status) {
        if (status != null && !status.isEmpty() && PaymentStatus.contains(status)) {
            this.status = status;
        } else {
            throw new IllegalArgumentException("Invalid payment status.");
        }
    }

}
