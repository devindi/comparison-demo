package com.devindi.mapper.demo;

import com.devindi.mapper.demo.dto.complex.OrderDto;
import com.devindi.mapper.demo.dto.immutable.ImmutablePerson;
import com.devindi.mapper.demo.dto.rename.UserDto;
import com.devindi.mapper.demo.dto.simple.PersonDto;
import com.devindi.mapper.demo.model.complex.Address;
import com.devindi.mapper.demo.model.complex.Customer;
import com.devindi.mapper.demo.model.complex.Order;
import com.devindi.mapper.demo.model.complex.Product;
import com.devindi.mapper.demo.model.simple.Person;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class MapperTest {

    @Test
    public void simpleName() {
        Person person = new Person("John", Arrays.asList("Mycroft", "Sherlock"));

        PersonDto personDto = new Mapper().toPersonDto(person);

        Assert.assertEquals("John", personDto.getName());
    }

    @Test
    public void simpleFriends() {
        Person person = new Person("John", Arrays.asList("Mycroft", "Sherlock"));

        PersonDto personDto = new Mapper().toPersonDto(person);

        Assert.assertEquals(Arrays.asList("Mycroft", "Sherlock"), personDto.getFriends());
    }

    @Test
    public void orderCustomerName() {
        Order order = new Order(
                new Customer(
                        "John Smith",
                        new Address("DC", "1st street"),
                        new Address("NY", "2nd street")
                ),
                Arrays.asList(
                        new Product("Milk"),
                        new Product("Water")
                ));

        OrderDto orderDto = new Mapper().toDto(order);

        Assert.assertEquals("John Smith", orderDto.getCustomerName());
    }

    @Test
    public void orderBillingCity() {
        Order order = new Order(
                new Customer(
                        "John Smith",
                        new Address("DC", "1st street"),
                        new Address("NY", "2nd street")
                ),
                Arrays.asList(
                        new Product("Milk"),
                        new Product("Water")
                ));

        OrderDto orderDto = new Mapper().toDto(order);

        Assert.assertEquals("NY", orderDto.getBillingCity());
    }

    @Test
    public void orderBillingStreet() {
        Order order = new Order(
                new Customer(
                        "John Smith",
                        new Address("DC", "1st street"),
                        new Address("NY", "2nd street")
                ),
                Arrays.asList(
                        new Product("Milk"),
                        new Product("Water")
                ));

        OrderDto orderDto = new Mapper().toDto(order);

        Assert.assertEquals("2nd street", orderDto.getBillingStreet());
    }

    @Test
    public void orderShippingCity() {
        Order order = new Order(
                new Customer(
                        "John Smith",
                        new Address("DC", "1st street"),
                        new Address("NY", "2nd street")
                ),
                Arrays.asList(
                        new Product("Milk"),
                        new Product("Water")
                ));

        OrderDto orderDto = new Mapper().toDto(order);

        Assert.assertEquals("DC", orderDto.getShippingCity());
    }

    @Test
    public void orderShippingStreet() {
        Order order = new Order(
                new Customer(
                        "John Smith",
                        new Address("DC", "1st street"),
                        new Address("NY", "2nd street")
                ),
                Arrays.asList(
                        new Product("Milk"),
                        new Product("Water")
                ));

        OrderDto orderDto = new Mapper().toDto(order);

        Assert.assertEquals("1st street", orderDto.getShippingStreet());
    }

    @Test
    public void orderFirstProduct() {
        Order order = new Order(
                new Customer(
                        "John Smith",
                        new Address("DC", "1st street"),
                        new Address("NY", "2nd street")
                ),
                Arrays.asList(
                        new Product("Milk"),
                        new Product("Water")
                ));

        OrderDto orderDto = new Mapper().toDto(order);

        Assert.assertEquals("Milk", orderDto.getProducts().get(0).getName());
    }

    @Test
    public void orderSecondProduct() {
        Order order = new Order(
                new Customer(
                        "John Smith",
                        new Address("DC", "1st street"),
                        new Address("NY", "2nd street")
                ),
                Arrays.asList(
                        new Product("Milk"),
                        new Product("Water")
                ));

        OrderDto orderDto = new Mapper().toDto(order);

        Assert.assertEquals("Water", orderDto.getProducts().get(1).getName());
    }

    @Test
    public void renameName() {
        Person person = new Person("John", Arrays.asList("Mycroft", "Sherlock"));

        UserDto dto = new Mapper().toUserDto(person);

        Assert.assertEquals("John", dto.getName());
    }

    @Test
    public void renameFriends() {
        Person person = new Person("John", Arrays.asList("Mycroft", "Sherlock"));

        UserDto dto = new Mapper().toUserDto(person);

        Assert.assertEquals(Arrays.asList("Mycroft", "Sherlock"), dto.getLinked());
    }

    @Test
    public void immutableName() {
        Person person = new Person("John", Arrays.asList("Mycroft", "Sherlock"));

        ImmutablePerson immutablePerson = new Mapper().toImmutable(person);

        Assert.assertEquals("John", immutablePerson.getName());
    }

    @Test
    public void immutableFriends() {
        Person person = new Person("John", Arrays.asList("Mycroft", "Sherlock"));

        ImmutablePerson immutablePerson = new Mapper().toImmutable(person);

        Assert.assertEquals(Arrays.asList("Mycroft", "Sherlock"), immutablePerson.getFriends());
    }
}
