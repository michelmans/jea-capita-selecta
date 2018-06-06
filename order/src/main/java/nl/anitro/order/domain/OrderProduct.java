package nl.anitro.order.domain;

import javax.persistence.*;

@Entity
@Table(name="orderproduct")
public class OrderProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String productname;

    @ManyToOne
    private Order order;

    public OrderProduct(){}

    public OrderProduct(String productname, Order order){
        this.productname = productname;
        this.order = order;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }
}
