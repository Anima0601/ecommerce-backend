package com.cg.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.cg.dto.CartItemDto;
import com.cg.service.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    // ✅ ADD TO CART
    @PostMapping("/add")
    public ResponseEntity<String> addToCart(
            @RequestParam Integer custId,
            @RequestParam Integer prodId,
            @RequestParam int qty) {

        String response = cartService.addToCart(custId, prodId, qty);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // ✅ GET CART
    @GetMapping("/{custId}")
    public ResponseEntity<List<CartItemDto>> getCart(@PathVariable Integer custId) {

        List<CartItemDto> cart = cartService.getCartByCustomer(custId);

        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    // ✅ REMOVE FROM CART
    @DeleteMapping("/remove")
    public ResponseEntity<String> removeFromCart(
            @RequestParam Integer custId,
            @RequestParam Integer prodId) {

        String response = cartService.removeFromCart(custId, prodId);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}