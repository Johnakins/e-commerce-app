package com.johnakins.order.order;

import com.johnakins.order.product.PurchaseRequest;

import java.math.BigDecimal;
import java.util.List;

public record OrderCreatedEvent(
        Integer orderId,
        String customerId,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        List<PurchaseRequest> products
) {
}
