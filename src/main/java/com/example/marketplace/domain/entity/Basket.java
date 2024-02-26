package com.example.marketplace.domain.entity;

import com.example.marketplace.domain.valueobj.BaseInfo;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "baskets")
public class Basket extends BaseInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "baskets_gen")
    @SequenceGenerator(name = "baskets_gen", sequenceName = "baskets_gen")
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private User user;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "goods_baskets",
                joinColumns = @JoinColumn(name = "baskets_id"),
                inverseJoinColumns = @JoinColumn(name = "goods_id"))
    private List<Goods> goods;


}
