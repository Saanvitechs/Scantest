package com.reader.scanner.service;

import com.reader.scanner.model.Cart;
import com.reader.scanner.model.CartItem;
import com.reader.scanner.model.Product;
import com.reader.scanner.repository.CartRepository;
import com.reader.scanner.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    public List<CartItem> getCartItems(Cart cart) {
        return cart.getItems().stream()
                .map(item -> new CartItem(item.getProductId(), item.getName(), item.getPrice()))
                .toList();
    }

    public void addItemToCart(Cart cart, Product product) {
        cart.addItem(product);
    }

    public void saveCart(Cart cart) {
        cartRepository.save(cart);
    }

    public void clearCart(Cart cart) {
        cart.getItems().clear();
        saveCart(cart);
    }

    public void deleteCart(Cart cart) {
        cartRepository.delete(cart);
    }

    public String generateCartId() {
        return UUID.randomUUID().toString();
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }
}


//package com.reader.scanner.service;
//
//import com.reader.scanner.model.Cart;
//import com.reader.scanner.model.CartItem;
//import com.reader.scanner.model.Product;
//import com.reader.scanner.repository.CartRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.UUID;
//
//@Service
//public class CartService {
//
//    @Autowired
//    private CartRepository cartRepository;
//
//    public List<CartItem> getCartItems(Cart cart) {
//        return cart.getItems();
//    }
//
//    public void addItemToCart(Cart cart, Product product) {
//        CartItem item = new CartItem(product.getRfid(), product.getProductId(), product.getName(), product.getPrice());
//        cart.addItem(item);
//    }
//
//    public void saveCart(Cart cart) {
//        cartRepository.save(cart);
//    }
//
//    public void clearCart(Cart cart) {
//        cart.getItems().clear();
//        saveCart(cart);
//    }
//
//    public void deleteCart(Cart cart) {
//        cartRepository.delete(cart);
//    }
//
//    public String generateCartId() {
//        return UUID.randomUUID().toString();
//    }
//}
