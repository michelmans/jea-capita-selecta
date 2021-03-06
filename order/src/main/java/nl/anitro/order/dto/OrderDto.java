package nl.anitro.order.dto;

import java.util.ArrayList;
import java.util.List;

public class OrderDto {

    private String username;
    private List<ProductDto> products = new ArrayList<>();

    public OrderDto() {}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<ProductDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDto> products) {
        this.products = products;
    }
}
