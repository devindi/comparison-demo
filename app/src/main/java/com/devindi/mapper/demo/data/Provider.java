package com.devindi.mapper.demo.data;

import com.devindi.mapper.demo.dto.complex.OrderDto;
import com.devindi.mapper.demo.dto.complex.ProductDto;
import com.devindi.mapper.demo.model.complex.Address;
import com.devindi.mapper.demo.model.complex.Customer;
import com.devindi.mapper.demo.model.complex.Order;
import com.devindi.mapper.demo.model.complex.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class Provider {

    public Order getRandomOrder() {
        List<Product> products = new ArrayList<>();
        Random random = new Random();
        int productCount = 1000;
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

    public OrderDto getRandomDto() {
        List<ProductDto> productDtos = new ArrayList<>();
        Random random = new Random();
        int productCount = random.nextInt(10000);
        for (int i = 0; i < 1000; i ++) {
            productDtos.add(new ProductDto(UUID.randomUUID().toString()));
        }
        return new OrderDto(
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                productDtos
        );
    }
}
