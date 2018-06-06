package nl.anitro.bakapp.service;

import nl.anitro.bakapp.domain.BakappException;
import nl.anitro.bakapp.domain.Product;
import nl.anitro.bakapp.domain.User;
import nl.anitro.bakapp.dto.ProductDto;
import nl.anitro.bakapp.messaging.OrderSender;
import nl.anitro.bakapp.repository.ProductRepository;
import nl.anitro.bakapp.wrapper.ProductWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private ProductRepository productRepository;
    private OrderSender orderSender;

    @Autowired
    public ProductService(ProductRepository productRepository, OrderSender orderSender){
        this.productRepository = productRepository;
        this.orderSender = orderSender;

        Product whitebread = new Product("Whitebread", "700 grams");
        Product waldcorn = new Product("Waldcorn", "750 grams");

        try {
            registerProduct(whitebread);
            registerProduct(waldcorn);
        } catch (BakappException e) {
            e.printStackTrace();
        }
    }

    public List<Product> getAllProducts(){
        return this.productRepository.findAll();
    }

    public List<Product> getProductByName(String productname){
        return this.productRepository.findByProductnameContaining(productname);
    }

    public Product registerProduct(Product product) throws BakappException {
        try{
            return this.productRepository.save(product);
        } catch(Exception e) {
            throw new BakappException("Could not create product");
        }
    }

    public String placeOrder(String username, ProductWrapper products){
        orderSender.placeOrder(username, products);
        return "Order placed";
    }

}
