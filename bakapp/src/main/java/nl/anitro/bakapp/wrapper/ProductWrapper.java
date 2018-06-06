package nl.anitro.bakapp.wrapper;

import nl.anitro.bakapp.dto.ProductDto;

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
