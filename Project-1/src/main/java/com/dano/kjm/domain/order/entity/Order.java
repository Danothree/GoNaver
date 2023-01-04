package com.dano.kjm.domain.order.entity;

import com.dano.kjm.domain.delivery.entity.DeliveryStatus;
import com.dano.kjm.domain.delivery.entity.Delivery;
import com.dano.kjm.domain.member.entity.Member;
import com.dano.kjm.domain.common.entity.BaseTimeEntity;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "orders")
public class Order extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "order", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "delibery_id")
    private Delivery delivery;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    private LocalDateTime orderDate;

    public Order createOrder(Member member, Delivery delivery, OrderItem ...OrderItems) {
        Order order = new Order();
        order.setMember(member);
        setDelivery(delivery);
        for (OrderItem orderItem : orderItems) {
            order.addOrderItem(orderItem);
        }
        setStatusAndDate(OrderStatus.ORDER, LocalDateTime.now());
        return order;
    }

    private void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    private void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrder(this);
    }

    private void setMember(Member member) {
        this.member = member;
        member.getOrders().add(this);
    }

    private void setStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    private void setStatusAndDate(OrderStatus orderStatus, LocalDateTime localDateTime) {
        this.orderStatus = orderStatus;
        this.orderDate = localDateTime;
    }

    public void cancel() {
        if(isDelivered()) {
            throw new IllegalStateException("배송 완료되어 취소 불가능합니다.");
        }
        this.setStatus(OrderStatus.CANCEL);
        for(OrderItem orderItem : orderItems) {
            orderItem.cancel();
        }
    }

    private boolean isDelivered() {
        if(delivery.getDeliveryStatus() == DeliveryStatus.COMPLETE) {
            return true;
        }
        return false;
    }
}
