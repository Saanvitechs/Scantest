//package com.reader.scanner.controller;
//
//import com.reader.scanner.model.Cart;
//import com.reader.scanner.service.CartService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api")
//public class CartController {
//
//    @Autowired
//    private CartService cartService;
//
//    @GetMapping("/cart/{cartId}")
//    public ResponseEntity<Cart> getCart(@PathVariable String cartId) {
//
////        String cartId = (String) session.getAttribute("cartId");
////        if (cartId == null) {
////            return ResponseEntity.notFound().build();
////        }
//
//        Cart cart = cartService.getCart(cartId);
//        if (cart == null) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(cart);
//    }
//}

//
//package com.reader.scanner.controller;
//
//import com.reader.scanner.model.Cart;
//import com.reader.scanner.model.CartItem;
//import com.reader.scanner.model.User;
//import com.reader.scanner.repository.UserRepository;
//import com.reader.scanner.service.CartService;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import org.springframework.http.HttpStatus;
//import jakarta.servlet.http.HttpSession;
//import java.util.List;
//
//@RestController
//@RequestMapping("/api")
//public class CartController {
//
//    @Autowired
//    private CartService cartService;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @GetMapping("/cart")
//    public ResponseEntity<List<CartItem>> getCart(HttpSession session) {
//        Cart cart = (Cart) session.getAttribute("cart");
//        if (cart != null) {
//            return ResponseEntity.ok(cartService.getCartItems(cart));
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    @PostMapping("/checkout")
//    public ResponseEntity<String> checkout(@RequestParam("userId") Long userId, HttpSession session) {
//        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
//        Cart cart = (Cart) session.getAttribute("cart");
//        if (cart != null && cart.getUser().getId().equals(userId)) {
//            cartService.saveCart(cart);
//            session.removeAttribute("cart");
//            return ResponseEntity.ok("Checkout complete. The cart is now empty.");
//        } else if (cart == null) {
//            return ResponseEntity.ok("The cart is already empty.");
//        } else {
//            return ResponseEntity.badRequest().body("No cart found in session for this user.");
//        }
//    }
//}


//package com.reader.scanner.controller;
//
//import com.reader.scanner.model.Cart;
//import com.reader.scanner.model.CartItem;
//import com.reader.scanner.model.User;
//import com.reader.scanner.service.CartService;
//import com.reader.scanner.repository.UserRepository;
//import jakarta.servlet.http.HttpSession;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api")
//public class CartController {
//
//    @Autowired
//    private CartService cartService;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @GetMapping("/cart")
//    public ResponseEntity<List<CartItem>> getCart(@RequestParam("userId") Long userId, HttpSession session) {
//        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
//        Cart cart = (Cart) session.getAttribute("cart");
//        if (cart != null && cart.getUser().getId().equals(userId)) {
//            return ResponseEntity.ok(cartService.getCartItems(cart));
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    @PostMapping("/checkout")
//    public ResponseEntity<String> checkout(@RequestParam("userId") Long userId, HttpSession session) {
//        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
//        Cart cart = (Cart) session.getAttribute("cart");
//        if (cart != null && cart.getUser().getId().equals(userId)) {
//            cartService.saveCart(cart);
//            cartService.deleteCart(cart);
//            session.removeAttribute("cart");
//            return ResponseEntity.ok("Checkout complete. The cart is now empty.");
//        } else if (cart == null) {
//            return ResponseEntity.ok("The cart is already empty.");
//        } else {
//            return ResponseEntity.badRequest().body("No cart found in session for this user.");
//        }
//    }
//}




//package com.reader.scanner.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.google.zxing.BarcodeFormat;
//import com.google.zxing.WriterException;
//import com.google.zxing.client.j2se.MatrixToImageWriter;
//import com.google.zxing.common.BitMatrix;
//import com.google.zxing.qrcode.QRCodeWriter;
//import com.reader.scanner.model.Cart;
//import com.reader.scanner.model.CartItem;
//import com.reader.scanner.model.User;
//import com.reader.scanner.service.CartService;
//import com.reader.scanner.repository.UserRepository;
//import com.reader.scanner.service.EmailService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import jakarta.servlet.http.HttpSession;
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//import java.util.List;
//
//@RestController
//@RequestMapping("/api")
//public class CartController {
//
//    @Autowired
//    private CartService cartService;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private EmailService emailService;
//
//    @GetMapping("/cart")
//    public ResponseEntity<List<CartItem>> getCart(@RequestParam("userId") Long userId, HttpSession session) {
//        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
//        Cart cart = (Cart) session.getAttribute("cart");
//        if (cart != null && cart.getUser().getId().equals(userId)) {
//            if (cart.getItems().isEmpty()) {
//                return ResponseEntity.ok(List.of());
//            } else {
//                return ResponseEntity.ok(cartService.getCartItems(cart));
//            }
//        } else {
//            return ResponseEntity.ok(List.of()); // Return an empty cart if no cart is found or the cart is empty
//        }
//    }
//
//    @PostMapping("/checkout")
//    public ResponseEntity<byte[]> checkout(@RequestParam("userId") Long userId, HttpSession session) throws WriterException, IOException {
//        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
//        Cart cart = (Cart) session.getAttribute("cart");
//        if (cart != null && cart.getUser().getId().equals(userId)) {
//            List<CartItem> items = cartService.getCartItems(cart);
//            double totalPrice = items.stream().mapToDouble(CartItem::getPrice).sum();
//            ObjectMapper objectMapper = new ObjectMapper();
//            String cartItemsJson = objectMapper.writeValueAsString(items);
//            String sessionId = session.getId();
//
//            // Generate QR code containing the cart items, total price, and session ID
//            String qrCodeData = "Cart Items: " + cartItemsJson + "\nTotal Price: " + totalPrice + "\nSession ID: " + sessionId;
//            QRCodeWriter qrCodeWriter = new QRCodeWriter();
//            BitMatrix bitMatrix = qrCodeWriter.encode(qrCodeData, BarcodeFormat.QR_CODE, 300, 300);
//
//            ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
//            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
//            byte[] pngData = pngOutputStream.toByteArray();
//
//            emailService.sendCartDetailsEmail(user.getEmail(), user.getUsername(), items, totalPrice);
//
//
//            // Clear the cart items
//            cartService.clearCart(cart);
//
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.IMAGE_PNG);
//            headers.setContentLength(pngData.length);
//
//            return ResponseEntity.ok().headers(headers).body(pngData);
//        } else if (cart == null) {
//            return ResponseEntity.ok("The cart is already empty.".getBytes());
//        } else {
//            return ResponseEntity.badRequest().body("No cart found in session for this user.".getBytes());
//        }
//    }
//}

//package com.reader.scanner.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.google.zxing.BarcodeFormat;
//import com.google.zxing.WriterException;
//import com.google.zxing.client.j2se.MatrixToImageWriter;
//import com.google.zxing.common.BitMatrix;
//import com.google.zxing.qrcode.QRCodeWriter;
//import com.reader.scanner.model.Cart;
//import com.reader.scanner.model.CartItem;
//import com.reader.scanner.model.User;
//import com.reader.scanner.service.CartService;
//import com.reader.scanner.repository.UserRepository;
//import com.reader.scanner.service.EmailService;
//import com.reader.scanner.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import jakarta.servlet.http.HttpSession;
//
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//import java.util.List;
//
//@RestController
//@RequestMapping("/api")
//public class CartController {
//
//    @Autowired
//    private CartService cartService;
//
////    @Autowired
////    private UserRepository userRepository;
//
//    @Autowired
//    private EmailService emailService;
//
//    @Autowired
//    private UserService userService;
//
//    @GetMapping("/cart")
//    public ResponseEntity<List<CartItem>> getCart(HttpSession session) {
//        // Extract username from JWT token
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String email = authentication.getName();
//
//        // Find user by email
//        User user = userService.findByEmail(email);
//        if (user == null) {
//            return ResponseEntity.status(404).body(List.of()); // User not found
//        }
//
//        // Get cart from session
//        Cart cart = (Cart) session.getAttribute("cart");
//        if (cart != null && cart.getUser().getId().equals(user.getId())) {
//            if (cart.getItems().isEmpty()) {
//                return ResponseEntity.ok(List.of());
//            } else {
//                return ResponseEntity.ok(cartService.getCartItems(cart));
//            }
//        } else {
//            return ResponseEntity.ok(List.of()); // Return an empty list if no cart or cart doesn't match
//        }
//    }
//
//    @PostMapping("/checkout")
//    public ResponseEntity<byte[]> checkout(HttpSession session) throws WriterException, IOException {
//        // Extract username from JWT token
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String email = authentication.getName();
//
//        // Find user by email
//        User user = userService.findByEmail(email);
//        if (user == null) {
//            return ResponseEntity.status(404).body("User not found.".getBytes()); // User not found
//        }
//
//        Cart cart = (Cart) session.getAttribute("cart");
//        if (cart != null && cart.getUser().getId().equals(user.getId())) {
//            List<CartItem> items = cartService.getCartItems(cart);
//            double totalPrice = items.stream().mapToDouble(CartItem::getPrice).sum();
//            ObjectMapper objectMapper = new ObjectMapper();
//            String cartItemsJson = objectMapper.writeValueAsString(items);
//            String sessionId = session.getId();
//
//            // Generate QR code containing the cart items, total price, and session ID
//            String qrCodeData = "Cart Items: " + cartItemsJson + "\nTotal Price: " + totalPrice + "\nSession ID: " + sessionId;
//            QRCodeWriter qrCodeWriter = new QRCodeWriter();
//            BitMatrix bitMatrix = qrCodeWriter.encode(qrCodeData, BarcodeFormat.QR_CODE, 300, 300);
//
//            ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
//            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
//            byte[] pngData = pngOutputStream.toByteArray();
//
//            emailService.sendCartDetailsEmail(user.getEmail(), user.getUsername(), items, totalPrice);
//
//            // Clear the cart items
//            cartService.clearCart(cart);
//
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.IMAGE_PNG);
//            headers.setContentLength(pngData.length);
//
//            return ResponseEntity.ok().headers(headers).body(pngData);
//        } else if (cart == null) {
//            return ResponseEntity.ok("The cart is already empty.".getBytes());
//        } else {
//            return ResponseEntity.badRequest().body("No cart found in session for this user.".getBytes());
//        }
//    }
//}

package com.reader.scanner.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.reader.scanner.model.Cart;
import com.reader.scanner.model.CartItem;
import com.reader.scanner.model.User;
import com.reader.scanner.service.CartService;
import com.reader.scanner.service.UserService;
import com.reader.scanner.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

//
//    @GetMapping("/cart")
//    public ResponseEntity<?> getCart(HttpSession session) {
//        // Extract email from JWT token
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String email = authentication.getName();
//
//        // Find user by email
//        User user = userService.findByEmail(email);
//        if (user == null) {
//            return ResponseEntity.status(404).body("User not found"); // User not found
//        }
//
//        Cart cart = (Cart) session.getAttribute("cart");
//        if (cart != null && cart.getUser().getId().equals(user.getId())) {
//            List<CartItem> cartItems = cartService.getCartItems(cart);
//            if (cart.getItems().isEmpty()) {
//                return ResponseEntity.ok("Your cart is empty");
//            } else {
//                return ResponseEntity.ok(cartService.getCartItems(cart));
//            }
//        } else {
//            return ResponseEntity.ok("Your cart is empty"); // Return an empty cart message
//        }
//    }



   @GetMapping("/cart")
    public ResponseEntity<?> getCart(HttpSession session) {
        // Extract email from JWT token
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        // Find user by email
        User user = userService.findByEmail(email);
        if (user == null) {
            return ResponseEntity.status(404).body("User not found"); // User not found
        }

        Cart cart = (Cart) session.getAttribute("cart");
        if (cart != null && cart.getUser().getId().equals(user.getId())) {
            List<CartItem> cartItems = cartService.getCartItems(cart);
            if (cartItems.isEmpty()) {
                return ResponseEntity.ok("Your cart is empty");
            } else {
                return ResponseEntity.ok(cartItems);
            }
        } else {
            return ResponseEntity.ok("Your cart is empty"); // Return an empty cart message
        }
    }

    @PostMapping("/checkout")
    public ResponseEntity<byte[]> checkout(HttpSession session) throws WriterException, IOException {
        // Extract email from JWT token
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        // Find user by email
        User user = userService.findByEmail(email);
        if (user == null) {
            return ResponseEntity.status(404).body("User not found.".getBytes()); // User not found
        }

        Cart cart = (Cart) session.getAttribute("cart");
        if (cart != null && cart.getUser().getId().equals(user.getId())) {
            List<CartItem> items = cartService.getCartItems(cart);
            double totalPrice = items.stream().mapToDouble(CartItem::getPrice).sum();
            double totalWeight = items.stream().mapToDouble(CartItem::getWeight).sum(); // Calculate total weight
            ObjectMapper objectMapper = new ObjectMapper();
            String cartItemsJson = objectMapper.writeValueAsString(items);
            String sessionId = session.getId();

            // Generate QR code containing the cart items, total price, and session ID
            String qrCodeData = "Cart Items: " + cartItemsJson + "\nTotal Price: " + totalPrice + "\nSession ID: " + sessionId;
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(qrCodeData, BarcodeFormat.QR_CODE, 300, 300);

            ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
            byte[] pngData = pngOutputStream.toByteArray();

            emailService.sendCartDetailsEmail(user.getEmail(), user.getUsername(), items, totalPrice);

            // Clear the cart items
            cartService.clearCart(cart);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_PNG);
            headers.setContentLength(pngData.length);

            return ResponseEntity.ok().headers(headers).body(pngData);
        } else if (cart == null) {
            return ResponseEntity.ok("The cart is already empty.".getBytes());
        } else {
            return ResponseEntity.badRequest().body("No cart found in session for this user.".getBytes());
        }
    }
}
