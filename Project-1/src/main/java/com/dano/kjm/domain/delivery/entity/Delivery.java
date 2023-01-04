package com.dano.kjm.domain.delivery.entity;

import com.dano.kjm.domain.order.entity.Order;
import com.dano.kjm.domain.member.entity.Address;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Delivery {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delivery_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus;

    @Embedded
    private Address address;

    @OneToOne(mappedBy = "delivery", orphanRemoval = true, cascade = CascadeType.ALL)
    private Order order;

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
