package com.example.marketplace.domain.entity;

import com.example.marketplace.domain.valueobj.BaseInfo;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "categories")
public class Category extends BaseInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "categories_gen")
    @SequenceGenerator(name = "categories_gen", sequenceName = "categories_gen")
    private Integer id;
    @Column(unique = true, nullable = false)
    private String name;
    private String description;

//    @ManyToMany
//    @JoinTable(name = "goods_categories",
//            joinColumns = @JoinColumn(name = "categories_id"),
//            inverseJoinColumns = @JoinColumn(name = "goods_id"))
//    @ToString.Exclude
//    private List<Goods> goods;

}
