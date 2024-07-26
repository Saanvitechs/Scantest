package com.reader.scanner.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/rfconsole")
public class DummyRFConsoleController {

    private static final Map<String, Map<String, String>> productDatabase = new HashMap<>();

    static {
        Map<String, String> product1 = new HashMap<>();
        product1.put("name", "Product A");
        product1.put("price", "10.99");
        product1.put("weight", "2.5");
        product1.put("rfid", "74562547558");
        product1.put("productId", "001"); // Ensure productId is included
        productDatabase.put("74562547558", product1);

        Map<String, String> product2 = new HashMap<>();
        product2.put("name", "Product B");
        product2.put("price", "5.99");
        product2.put("weight", "1.0");
        product2.put("rfid", "12345678901");
        product2.put("productId", "002"); // Ensure productId is included
        productDatabase.put("12345678901", product2);
    }

    @GetMapping("/product/{barcode}")
    public Map<String, String> getProductDetails(@PathVariable String barcode) {
        return productDatabase.getOrDefault(barcode, null);
    }
}
