package com.devindi.mapper.demo.data;

import com.devindi.mapper.demo.model.complex.Address;
import com.devindi.mapper.demo.model.complex.Customer;
import com.devindi.mapper.demo.model.complex.Order;
import com.devindi.mapper.demo.model.complex.Product;
import com.devindi.mapper.demo.model.simple.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Provider {

    public Order getOrder(int productCount) {
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < productCount; i ++) {
            products.add(new Product(UUID.randomUUID().toString()));
        }
        return new Order(
                new Customer(
                        UUID.randomUUID().toString(),
                        new Address(UUID.randomUUID().toString(), UUID.randomUUID().toString()),
                        new Address(UUID.randomUUID().toString(), UUID.randomUUID().toString())
                ),
                products
        );
    }

    public Person getPerson(int friendsCount) {
        List<String> friends = new ArrayList<>(friendsCount);
        for (int i = 0; i < friendsCount; i++) {
            friends.add(UUID.randomUUID().toString());
        }
        return new Person(UUID.randomUUID().toString(), friends);
    }
}
