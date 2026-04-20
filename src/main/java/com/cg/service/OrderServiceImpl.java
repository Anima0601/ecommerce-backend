package com.cg.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.dto.OrderDto;
import com.cg.entity.CartItem;
import com.cg.entity.Customer;
import com.cg.entity.Order;
import com.cg.entity.OrderProduct;
import com.cg.exception.NotFoundException;
import com.cg.exception.BadRequestException;
import com.cg.repo.CartItemRepo;
import com.cg.repo.CustomerRepo;
import com.cg.repo.OrderProductRepo;
import com.cg.repo.OrderRepo;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private CartItemRepo cartRepo;

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private OrderProductRepo orderProductRepo;

    // ✅ CREATE ORDER
    @Override
    public String createOrder(Integer custId) {

        Customer customer = customerRepo.findById(custId)
                .orElseThrow(() -> new NotFoundException("Customer not found"));

        List<CartItem> cartItems = cartRepo.findByCustomer_CustId(custId);

        if (cartItems.isEmpty()) {
            throw new BadRequestException("Cart is empty");
        }

        Order order = new Order();
        order.setCustomer(customer);
        order.setOrderStatus("CREATED");
        order.setOrderDate(java.time.LocalDate.now().toString());

        double totalAmount = 0;

        Order savedOrder = orderRepo.save(order);

        // move cart → order products
        for (CartItem item : cartItems) {

            OrderProduct op = new OrderProduct();
            op.setOrder(savedOrder);
            op.setProduct(item.getProduct());
            op.setQty(item.getQty());

            totalAmount += item.getProduct().getPrice() * item.getQty();

            orderProductRepo.save(op);
        }

        savedOrder.setOrderAmt(totalAmount);
        orderRepo.save(savedOrder);

        cartRepo.deleteAll(cartItems);

        return "Order placed successfully";
    }

    // ✅ VIEW ORDERS BY CUSTOMER
    @Override
    public List<OrderDto> getOrdersByCustomer(Integer custId) {

        List<Order> orders = orderRepo.findByCustomer_CustId(custId);

        if (orders.isEmpty()) {
            throw new NotFoundException("No orders found for this customer");
        }

        List<OrderDto> dtoList = new ArrayList<>();

        for (Order order : orders) {

            OrderDto dto = new OrderDto();

            dto.setOrderId(order.getId());
            dto.setOrderAmt(order.getOrderAmt());
            dto.setOrderStatus(order.getOrderStatus());

            dtoList.add(dto);
        }

        return dtoList;
    }

    // ✅ VIEW PRODUCTS IN ORDER
    @Override
    public List<OrderDto> getProductsByOrder(Integer orderId) {

        List<OrderProduct> list = orderProductRepo.findByOrder_Id(orderId);

        if (list.isEmpty()) {
            throw new NotFoundException("No products found for this order");
        }

        List<OrderDto> dtoList = new ArrayList<>();

        for (OrderProduct op : list) {

            OrderDto dto = new OrderDto();

            dto.setOrderId(orderId);
            dto.setProdName(op.getProduct().getProdName());
            dto.setQty(op.getQty());

            dtoList.add(dto);
        }

        return dtoList;
    }

    // ❌ CANCEL ORDER
    @Override
    public String cancelOrder(Integer orderId) {

        Order order = orderRepo.findById(orderId)
                .orElseThrow(() -> new NotFoundException("Order not found"));

        if ("CANCELLED".equals(order.getOrderStatus())) {
            throw new BadRequestException("Order is already cancelled");
        }

        order.setOrderStatus("CANCELLED");

        orderRepo.save(order);

        return "Order cancelled successfully";
    }
}