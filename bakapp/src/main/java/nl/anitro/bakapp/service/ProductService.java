package nl.anitro.bakapp.service;

import nl.anitro.bakapp.domain.BakappException;
import nl.anitro.bakapp.domain.Product;
import nl.anitro.bakapp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;

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

}
