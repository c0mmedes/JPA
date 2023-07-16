package jpabook.jpashop.domain;

import javax.persistence.*;

@Entity
public class OrderItem {

    @Id @GeneratedValue
    @Column(name = "ORDER_ITEM_ID")
    private Long id;
//    @Column(name = "ORDER_ID")
//    private Long orderId;

//    @Column(name = "ITEM_ID")
//    private Long itemid;
    @ManyToOne
//    @Column(name = "ORDER_ID")
    private Order order;

    @ManyToOne
//    @Column(name = "ITEM_ID")
    private Item item;

    private Long orderPrice;
    private int count;

    public Long getId() {
        return id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public Long getOrderId() {
//        return orderId;
//    }
//
//    public void setOrderId(Long orderId) {
//        this.orderId = orderId;
//    }
//
//    public Long getItemid() {
//        return itemid;
//    }
//
//    public void setItemid(Long itemid) {
//        this.itemid = itemid;
//    }

    public Long getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(Long orderPrice) {
        this.orderPrice = orderPrice;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
