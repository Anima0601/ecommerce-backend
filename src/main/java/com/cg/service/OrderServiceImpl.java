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
import com.cg.entity.Product;
import com.cg.exception.NotFoundException;
import com.cg.exception.BadRequestException;
import com.cg.repo.CartItemRepo;
import com.cg.repo.CustomerRepo;
import com.cg.repo.OrderProductRepo;
import com.cg.repo.OrderRepo;
import com.cg.repo.ProductRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional   // 🔥 VERY IMPORTANT
public class OrderServiceImpl implements OrderService {

    @Autowired
    private CartItemRepo cartRepo;

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private OrderProductRepo orderProductRepo;

    @Autowired
    private ProductRepo productRepo;

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
        order.setOrderStatus("PLACED");
        order.setOrderDate(java.time.LocalDateTime.now().toString());

        double totalAmount = 0;

        Order savedOrder = orderRepo.save(order);

        for (CartItem item : cartItems) {

            Product product = item.getProduct();

            // 🔥 STOCK VALIDATION
            if (item.getQty() > product.getStock()) {
                throw new BadRequestException(
                        product.getProdName() + " out of stock"
                );
            }

            // 🔻 REDUCE STOCK
            product.setStock(product.getStock() - item.getQty());
            productRepo.save(product);

            OrderProduct op = new OrderProduct();
            op.setOrder(savedOrder);
            op.setProduct(product);
            op.setQty(item.getQty());

            totalAmount += product.getPrice() * item.getQty();

            orderProductRepo.save(op);
        }

        savedOrder.setOrderAmt(totalAmount);
        orderRepo.save(savedOrder);

        // 🧹 CLEAR CART
        cartRepo.deleteAll(cartItems);

        return "Order placed successfully";
    }
    
    @Override
    public List<OrderDto> getOrdersByCustomer(Integer custId) {

        List<Order> orders = orderRepo.findByCustomer_CustId(custId);

        List<OrderDto> dtoList = new ArrayList<>();

        for (Order order : orders) {
            OrderDto dto = new OrderDto();
            dto.setOrderId(order.getId());
            dto.setOrderAmt(order.getOrderAmt());
            dto.setOrderStatus(order.getOrderStatus());
            dto.setOrderDate(order.getOrderDate());
            dtoList.add(dto);
        }

        return dtoList; // ✅ return empty list instead of exception
    }
    
    @Override
    public List<OrderDto> getProductsByOrder(Integer orderId) {

        List<OrderProduct> list = orderProductRepo.findByOrder_Id(orderId);

        List<OrderDto> dtoList = new ArrayList<>();

        for (OrderProduct op : list) {

            Order order = op.getOrder();

            OrderDto dto = new OrderDto();

            dto.setOrderId(order.getId());
            dto.setOrderDate(order.getOrderDate());  
            dto.setOrderStatus(order.getOrderStatus());
            dto.setOrderAmt(order.getOrderAmt());

            dto.setProdName(op.getProduct().getProdName());
            dto.setQty(op.getQty());
            dto.setProdImage(op.getProduct().getProdImage());
            dto.setPrice(op.getProduct().getPrice());

            dtoList.add(dto);
        }

        return dtoList;
    }
    
    @Override
    public String cancelOrder(Integer orderId) {

        Order order = orderRepo.findById(orderId)
                .orElseThrow(() -> new NotFoundException("Order not found"));

        if ("CANCELLED".equals(order.getOrderStatus())) {
            throw new BadRequestException("Order is already cancelled");
        }

        // 🔄 RESTORE STOCK
        List<OrderProduct> items = orderProductRepo.findByOrder_Id(orderId);

        for (OrderProduct op : items) {
            Product p = op.getProduct();
            p.setStock(p.getStock() + op.getQty());
            productRepo.save(p);
        }

        order.setOrderStatus("CANCELLED");

        orderRepo.save(order);

        return "Order cancelled successfully";
    }
}