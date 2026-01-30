package com.johnakins.order.order;

import com.johnakins.order.customer.CustomerClient;
import com.johnakins.order.exception.BusinessException;
import com.johnakins.order.orderline.OrderLine;
import com.johnakins.order.outbox.OutboxService;
import com.johnakins.order.product.PurchaseResponse;
import com.johnakins.order.product.ProductClient;
import com.johnakins.order.product.PurchaseRequest;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repository;
    private final OrderMapper mapper;
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OutboxService outboxService;

    @Transactional
    public Integer createOrder(OrderRequest request) {

        // 1️⃣ Validate customer
        customerClient.findCustomerById(request.customerId())
                .orElseThrow(() ->
                        new BusinessException(
                                "Cannot create order: No customer exists with the provided ID"
                        )
                );

        // 2️⃣ Create order aggregate
        Order order = new Order();
        order.setReference(request.reference()); //generate reference for each order
        order.setCustomerId(request.customerId());
        order.setPaymentMethod(request.paymentMethod());

        BigDecimal totalAmount = BigDecimal.ZERO;

            for (PurchaseRequest purchase : request.products()) {
    
                PurchaseResponse product =
                        productClient.findById(purchase.productId())
                                .orElseThrow(() ->
                        new BusinessException("Product not found: " + purchase.productId())
                );
    
                BigDecimal unitPrice = product.price();
                BigDecimal lineTotal =
                        unitPrice.multiply(BigDecimal.valueOf(purchase.quantity()));
    
                OrderLine line = new OrderLine();
                line.setProductId(purchase.productId());
                line.setQuantity(purchase.quantity());
                line.setUnitPrice(unitPrice);
                line.setLineTotal(lineTotal);
    
                order.addLine(line);
                totalAmount = totalAmount.add(lineTotal);
            }

        order.setTotalAmount(totalAmount);

        // 3️⃣ Persist order + lines (same transaction)
        Order savedOrder = repository.save(order);

        // 4️⃣ 🔔 Publish OrderCreatedEvent via Outbox
        outboxService.saveEvent(
                "OrderCreatedEvent",
                savedOrder.getId(),
                new OrderCreatedEvent(
                        savedOrder.getId(),
                        savedOrder.getCustomerId(),
                        savedOrder.getTotalAmount(),
                        savedOrder.getPaymentMethod(),
                        request.products()
                )
        );

        return savedOrder.getId();
    }

    public List<OrderResponse> findAllOrders() {
        return this.repository.findAll()
                .stream()
                .map(this.mapper::fromOrder)
                .collect(Collectors.toList());
    }

    public OrderResponse findById(Integer id) {
        return this.repository.findById(id)
                .map(this.mapper::fromOrder)
                .orElseThrow(() -> new EntityNotFoundException(String.format("No order found with the provided ID: %d", id)));
    }
}
