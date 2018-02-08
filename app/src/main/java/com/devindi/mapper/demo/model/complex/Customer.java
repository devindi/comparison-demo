package com.devindi.mapper.demo.model.complex;

public class Customer {

    private String name;
    private Address shippingAddress;
    private Address billingAddress;

    public Customer(String name, Address shippingAddress, Address billingAddress) {
        this.name = name;
        this.shippingAddress = shippingAddress;
        this.billingAddress = billingAddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public Address getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        if (!name.equals(customer.name)) return false;
        if (!shippingAddress.equals(customer.shippingAddress)) return false;
        return billingAddress.equals(customer.billingAddress);

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + shippingAddress.hashCode();
        result = 31 * result + billingAddress.hashCode();
        return result;
    }
}
