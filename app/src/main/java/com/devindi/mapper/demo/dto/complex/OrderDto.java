package com.devindi.mapper.demo.dto.complex;

import java.util.List;

/**
 * @startuml
 * !include ProductDto.java
 * class OrderDto {
 *     -String customerName
 *     -String billingCity
 *     -String billingStreet
 *     -String shippingCity
 *     -String shippingStreet
 *     -List<ProductDto> products
 * }
 *
 * OrderDto "1" *--> "*" ProductDto
 * @enduml
 */
public class OrderDto {

    private String customerName;
    private String billingCity;
    private String billingStreet;
    private String shippingCity;
    private String shippingStreet;
    private List<ProductDto> products;

    public OrderDto() {
    }

    public OrderDto(String customerName, String billingCity, String billingStreet, String shippingCity, String shippingStreet, List<ProductDto> products) {
        this.customerName = customerName;
        this.billingCity = billingCity;
        this.billingStreet = billingStreet;
        this.shippingCity = shippingCity;
        this.shippingStreet = shippingStreet;
        this.products = products;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getBillingCity() {
        return billingCity;
    }

    public void setBillingCity(String billingCity) {
        this.billingCity = billingCity;
    }

    public String getBillingStreet() {
        return billingStreet;
    }

    public void setBillingStreet(String billingStreet) {
        this.billingStreet = billingStreet;
    }

    public String getShippingCity() {
        return shippingCity;
    }

    public void setShippingCity(String shippingCity) {
        this.shippingCity = shippingCity;
    }

    public String getShippingStreet() {
        return shippingStreet;
    }

    public void setShippingStreet(String shippingStreet) {
        this.shippingStreet = shippingStreet;
    }

    public List<ProductDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDto> products) {
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderDto orderDto = (OrderDto) o;

        if (!customerName.equals(orderDto.customerName)) return false;
        if (!billingCity.equals(orderDto.billingCity)) return false;
        if (!billingStreet.equals(orderDto.billingStreet)) return false;
        if (!shippingCity.equals(orderDto.shippingCity)) return false;
        if (!shippingStreet.equals(orderDto.shippingStreet)) return false;
        return products.equals(orderDto.products);

    }

    @Override
    public int hashCode() {
        int result = customerName.hashCode();
        result = 31 * result + billingCity.hashCode();
        result = 31 * result + billingStreet.hashCode();
        result = 31 * result + shippingCity.hashCode();
        result = 31 * result + shippingStreet.hashCode();
        result = 31 * result + products.hashCode();
        return result;
    }
}
