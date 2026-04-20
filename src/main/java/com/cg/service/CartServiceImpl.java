package com.cg.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.dto.CartItemDto;
import com.cg.entity.CartItem;
import com.cg.entity.Customer;
import com.cg.entity.Product;
import com.cg.exception.NotFoundException;
import com.cg.repo.CartItemRepo;
import com.cg.repo.CustomerRepo;
import com.cg.repo.ProductRepo;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartItemRepo cartItemRepo;

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private CustomerRepo customerRepo;

    // 🛒 ADD TO CART
    @Override
    public String addToCart(Integer prodId, Integer custId, int qty) {

        Customer customer = customerRepo.findById(custId)
                .orElseThrow(() -> new NotFoundException("Customer not found"));

        Product product = productRepo.findById(prodId)
                .orElseThrow(() -> new NotFoundException("Product not found"));

        Optional<CartItem> existingItem =
                cartItemRepo.findByCustomer_CustIdAndProduct_ProdId(custId, prodId);

        if (existingItem.isPresent()) {

            CartItem item = existingItem.get();
            item.setQty(item.getQty() + qty);
            cartItemRepo.save(item);

        } else {

            CartItem item = new CartItem();
            item.setCustomer(customer);
            item.setProduct(product);
            item.setQty(qty);

            cartItemRepo.save(item);
        }

        return "Product added to cart";
    }

    // 📦 VIEW CART
    @Override
    public List<CartItemDto> getCartByCustomer(Integer custId) {

        List<CartItem> items = cartItemRepo.findByCustomer_CustId(custId);

        if (items.isEmpty()) {
            throw new NotFoundException("Cart is empty");
        }

        List<CartItemDto> dtoList = new ArrayList<>();

        for (CartItem item : items) {

            CartItemDto dto = new CartItemDto();

            dto.setProdName(item.getProduct().getProdName());
            dto.setPrice(item.getProduct().getPrice());
            dto.setQty(item.getQty());

            dtoList.add(dto);
        }

        return dtoList;
    }

    // ❌ REMOVE FROM CART
    @Override
    public String removeFromCart(Integer custId, Integer prodId) {

        CartItem item = cartItemRepo
                .findByCustomer_CustIdAndProduct_ProdId(custId, prodId)
                .orElseThrow(() -> new NotFoundException("Item not found in cart"));

        cartItemRepo.delete(item);

        return "Item removed from cart";
    }
}