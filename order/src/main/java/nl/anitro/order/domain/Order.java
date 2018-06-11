package nl.anitro.order.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String username;

    private boolean userValidated;

    @OneToMany(mappedBy = "order")
    private List<OrderProduct> orderProducts;

    public Order(){}

    public Order(String username, boolean userValidated, List<OrderProduct> orderProducts){
        this.username = username;
        this.orderProducts = orderProducts;
        this.userValidated = userValidated;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<OrderProduct> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(List<OrderProduct> orderProducts) {
        this.orderProducts = orderProducts;
    }

    public boolean isUserValidated() {
        return userValidated;
    }

    public void setUserValidated(boolean userValidated) {
        this.userValidated = userValidated;
    }
}
