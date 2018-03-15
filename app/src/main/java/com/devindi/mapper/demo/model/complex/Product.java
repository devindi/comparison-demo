package com.devindi.mapper.demo.model.complex;

/**
 * @startuml
 * class Product {
 *     -String title
 * }
 * @enduml
 */
public class Product {

    private final String title;

    public Product(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        return title.equals(product.title);

    }

    @Override
    public int hashCode() {
        return title.hashCode();
    }
}
