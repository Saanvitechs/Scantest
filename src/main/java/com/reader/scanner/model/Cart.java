package com.reader.scanner.model;

import jakarta.persistence.*;


import java.util.ArrayList;
import java.util.List;

@Entity
public class Cart {
    @Id
    private String cartId;

    @ManyToOne
    private User user;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Product> items = new ArrayList<>();

    private double total;

    public void addItem(Product product) {
        this.items.add(product);
        this.total += product.getPrice();
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List<Product> getItems() {
        return items;
    }

    public void setItems(List<Product> items) {
        this.items = items;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}
