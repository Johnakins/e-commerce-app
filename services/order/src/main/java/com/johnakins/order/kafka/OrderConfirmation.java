package com.johnakins.order.kafka;



import com.johnakins.order.customer.CustomerResponse;
import com.johnakins.order.order.PaymentMethod;
import com.johnakins.order.product.PurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation (
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<PurchaseResponse> products

) {
}
