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

    public Payment(String id, String method, String status, Map<String, String> paymentData) {
        this.id = id;
        this.method = method;
        this.paymentData = paymentData;
        setStatus(status);
    }

    public Payment(String id, String method, Map<String, String> paymentData) {
        this(id, method, null, paymentData);
    }

    public void setStatus(String status) {
        if (status != null && !status.isEmpty() && PaymentStatus.contains(status)) {
            this.status = status;
        } else {
            throw new IllegalArgumentException("Invalid payment status.");
        }
    }
}
