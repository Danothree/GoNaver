package com.dano.kjm.domain.order.entity;

import com.dano.kjm.domain.item.entity.Item;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class OrderItem {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    private int orderPrice;

    private int count;

    public void setOrder(Order order) {
        this.order = order;
    }

    public void cancel() {
        getItem().addStock(count);
    }

    public static OrderItem createOrderItem(Item item, int orderPrice, int count) {
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item, orderPrice, count);
        item.removeStock(count);

        return orderItem;
    }

    private void setItem(Item item, int orderPrice, int count) {
        this.item = item;
        this.orderPrice = orderPrice;
        this.count = count;
    }

    public int getTotalPrice() {
        return orderPrice * count;
    }
}
