package com.example.marketplace.domain.entity;

import com.example.marketplace.domain.enums.Statuses;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orders_gen")
    @SequenceGenerator(name = "orders_gen", sequenceName = "orders_gen")
    private Integer id;
    @Enumerated(EnumType.STRING)
    private Statuses status;
    @OneToOne
    private User user;
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "orders_goods",
                joinColumns = @JoinColumn(name = "order_id"),
                inverseJoinColumns = @JoinColumn(name = "goods_id"))
    private List<Goods> goods;
    private Double totalPrice;
}
