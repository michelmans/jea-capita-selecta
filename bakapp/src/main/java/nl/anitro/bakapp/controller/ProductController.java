package nl.anitro.bakapp.controller;

import nl.anitro.bakapp.domain.BakappException;
import nl.anitro.bakapp.domain.Product;
import nl.anitro.bakapp.dto.ProductDto;
import nl.anitro.bakapp.service.ProductService;
import nl.anitro.bakapp.wrapper.ProductWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @RequestMapping(value="/products", method = RequestMethod.GET)
    public ResponseEntity getAllProducts(){
        return new ResponseEntity(this.productService.getAllProducts(), HttpStatus.OK);
    }

    @RequestMapping(value="/products", method = RequestMethod.POST)
    public ResponseEntity registerProduct(@RequestBody Product product){
        try {
            return new ResponseEntity(this.productService.registerProduct(product), HttpStatus.OK);
        } catch (BakappException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value="/products/{productname}", method = RequestMethod.GET)
    public ResponseEntity getProductByName(@PathVariable String productname) {
        return new ResponseEntity(this.productService.getProductByName(productname), HttpStatus.OK);
    }

    @RequestMapping(value="/placeorder", method = RequestMethod.POST)
    public ResponseEntity placeOrder(@RequestHeader String username, @RequestBody ProductWrapper products){
        return new ResponseEntity(this.productService.placeOrder(username, products), HttpStatus.OK);
    }

}
