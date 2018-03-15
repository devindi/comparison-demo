package com.devindi.mapper.demo.model.complex;

import java.util.List;

/**
 * @startuml
 * !include Product.java
 * !include Customer.java
 *
 * class Order {
 *     -Customer customer
 *     -List<Product> products
 * }
 *
 * Order "1" *--> "*" Product
 * Order "1" *--> "1" Customer
 * @enduml
 */
public class Order {

    private final Customer customer;
    private final List<Product> products;

    public Order(Customer customer, List<Product> products) {
        this.customer = customer;
        this.products = products;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<Product> getProducts() {
        return products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (!customer.equals(order.customer)) return false;
        return products.equals(order.products);

    }

    @Override
    public int hashCode() {
        int result = customer.hashCode();
        result = 31 * result + products.hashCode();
        return result;
    }
}
