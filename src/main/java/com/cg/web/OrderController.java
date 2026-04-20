package com.cg.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
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
    public ResponseEntity<String> createOrder(@PathVariable Integer custId) {

        String response = orderService.createOrder(custId);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // ✅ VIEW ORDERS BY CUSTOMER
    @GetMapping("/customer/{custId}")
    public ResponseEntity<List<OrderDto>> getOrdersByCustomer(@PathVariable Integer custId) {

        List<OrderDto> orders = orderService.getOrdersByCustomer(custId);

        return ResponseEntity.ok(orders);
    }

    // ✅ VIEW PRODUCTS IN ORDER
    @GetMapping("/{orderId}/products")
    public ResponseEntity<List<OrderDto>> getProductsByOrder(@PathVariable Integer orderId) {

        List<OrderDto> products = orderService.getProductsByOrder(orderId);

        return ResponseEntity.ok(products);
    }

    // ✅ CANCEL ORDER
    @PutMapping("/cancel/{orderId}")
    public ResponseEntity<String> cancelOrder(@PathVariable Integer orderId) {

        String response = orderService.cancelOrder(orderId);

        return ResponseEntity.ok(response);
    }
}