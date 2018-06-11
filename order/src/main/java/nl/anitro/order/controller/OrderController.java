package nl.anitro.order.controller;

import nl.anitro.order.domain.BakappException;
import nl.anitro.order.service.OrderService;
import nl.anitro.order.wrapper.ProductWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sun.misc.Request;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value="/order/{id}", method = RequestMethod.GET)
    public ResponseEntity getOrderById(@PathVariable Long id){
        try {
            return new ResponseEntity(this.orderService.getOrderById(id), HttpStatus.OK);
        } catch (BakappException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value="/order/username/{username}", method = RequestMethod.GET)
    public ResponseEntity getOrderByUsername(@PathVariable String username){
        return new ResponseEntity(this.orderService.getOrderByUsername(username), HttpStatus.OK);
    }

    @RequestMapping(value="/order", method = RequestMethod.POST)
    public ResponseEntity placeOrder(@RequestHeader String username, @RequestBody ProductWrapper products){
        try {
            return new ResponseEntity(this.orderService.createOrder(username, products), HttpStatus.OK);
        } catch (BakappException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
