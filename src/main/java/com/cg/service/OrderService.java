package com.cg.service;

import java.util.List;
import com.cg.dto.OrderDto;

public interface OrderService {

    // ✅ Create order from cart
    String createOrder(Integer custId);

    // ✅ Get all orders of a customer
    List<OrderDto> getOrdersByCustomer(Integer custId);

    // ✅ Get products inside an order
    List<OrderDto> getProductsByOrder(Integer orderId);

    // ✅ Cancel order
    String cancelOrder(Integer orderId);
}