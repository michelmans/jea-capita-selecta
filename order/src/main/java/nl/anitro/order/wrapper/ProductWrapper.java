package nl.anitro.order.wrapper;

import nl.anitro.order.dto.ProductDto;

import java.util.List;

public class ProductWrapper {

    private List<ProductDto> products;

    public List<ProductDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDto> products) {
        this.products = products;
    }
}
