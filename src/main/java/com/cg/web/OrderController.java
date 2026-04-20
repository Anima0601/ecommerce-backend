package com.cg.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.cg.dto.OrderDto;
import com.cg.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    // ✅ CREATE ORDER
    @PostMapping("/create/{custId}")
    public String createOrder(@PathVariable Integer custId) {

        return orderService.createOrder(custId);
    }

    // ✅ VIEW ORDERS BY CUSTOMER
    @GetMapping("/customer/{custId}")
    public List<OrderDto> getOrdersByCustomer(@PathVariable Integer custId) {

        return orderService.getOrdersByCustomer(custId);
    }

    // ✅ VIEW PRODUCTS IN ORDER
    @GetMapping("/{orderId}/products")
    public List<OrderDto> getProductsByOrder(@PathVariable Integer orderId) {

        return orderService.getProductsByOrder(orderId);
    }

    // ✅ CANCEL ORDER
    @PutMapping("/cancel/{orderId}")
    public String cancelOrder(@PathVariable Integer orderId) {

        return orderService.cancelOrder(orderId);
    }
}