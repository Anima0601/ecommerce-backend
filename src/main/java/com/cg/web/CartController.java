package com.cg.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.dto.CartItemDto;
import com.cg.service.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/add")
    public String addToCart(@RequestParam Integer custId,
                           @RequestParam Integer prodId,
                           @RequestParam int qty) {

        return cartService.addToCart(custId, prodId, qty);
    }

    @GetMapping("/{custId}")
    public List<CartItemDto> getCart(@PathVariable Integer custId) {
        return cartService.getCartByCustomer(custId);
    }

    @DeleteMapping("/remove")
    public String removeFromCart(@RequestParam Integer custId,
                                @RequestParam Integer prodId) {

        return cartService.removeFromCart(custId, prodId);
    }
}
