package com.johnakins.order.orderline;

import com.johnakins.order.order.Order;
import org.springframework.stereotype.Service;

@Service
public class OrderLineMapper {
//    public OrderLine toOrderLine(OrderLineRequest request) {
//        return OrderLine.builder()
//                .id(request.orderId())
//                .productId(request.productId())
//                .parentOrder(
//                        Order.builder()
//                                .id(request.orderId())
//                                .build()
//                )
//                .quantity(request.quantity())
//                .build();
//    }

    public OrderLineResponse toOrderLineResponse(OrderLine orderLine) {
        return new OrderLineResponse(
                orderLine.getId(),
                orderLine.getQuantity()
        );
    }
}
