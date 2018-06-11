package nl.anitro.order.service;

import nl.anitro.order.domain.BakappException;
import nl.anitro.order.domain.Order;
import nl.anitro.order.domain.OrderProduct;
import nl.anitro.order.messaging.ValidateUser;
import nl.anitro.order.repository.OrderProductRepository;
import nl.anitro.order.repository.OrderRepository;
import nl.anitro.order.wrapper.ProductWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private OrderRepository orderRepository;
    private OrderProductRepository orderProductRepository;
    private ValidateUser validateUser;

    @Autowired
    public OrderService(OrderRepository orderRepository, OrderProductRepository orderProductRepository, ValidateUser validateUser){
        this.orderRepository = orderRepository;
        this.orderProductRepository = orderProductRepository;
        this.validateUser = validateUser;
    }

    public Order getOrderById(Long id) throws BakappException {
        Optional<Order> order = this.orderRepository.findById(id);
        if(!order.isPresent()) throw new BakappException("Order is not present");
        return order.get();
    }

    public List<Order> getOrderByUsername(String username){
        return this.orderRepository.findByUsername(username);
    }

    public Order saveOrder(Order o){
        orderProductRepository.saveAll(o.getOrderProducts());
        return orderRepository.save(o);
    }

    public Order createOrder(String username, ProductWrapper products) throws BakappException {

        Order o = new Order();
        o.setUsername(username);
        o.setUserValidated(false);
        this.orderRepository.save(o);

        products.getProducts().forEach(item -> {
            OrderProduct op = new OrderProduct(item.getProductname(), o);
            this.orderProductRepository.saveAndFlush(op);
        });
        this.orderRepository.flush();

        Order returnOrder = this.orderRepository.findById(o.getId()).get();

        this.validateUser.validateUser(o.getUsername(), o.getId());

        return returnOrder;
    }

    public void confirmOrder(boolean state, Long orderId) throws BakappException {
        Order o = getOrderById(orderId);
        o.setUserValidated(state);
        this.orderRepository.save(o);
    }

}
