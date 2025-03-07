package id.ac.ui.cs.advprog.eshop.model;

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
    private Map<String, String> cashOnDelivery;
    private Order order;

    public Payment(String id, String method, Map<String, String> cashOnDelivery, Order order) {
        // Empty constructor for TDD
    }

    public Payment(String id, String method, String status, Map<String, String> cashOnDelivery, Order order) {
        // Empty constructor for TDD
    }
}
