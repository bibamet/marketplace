package com.example.marketplace.domain.entity;

import com.example.marketplace.domain.valueobj.BaseInfo;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "goods")
public class Goods extends BaseInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "goods_gen")
    @SequenceGenerator(name = "goods_gen", sequenceName = "goods_gen")
    private Integer id;
    private String title;
    private String description;
    private Double price;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "goods_categories",
            joinColumns = @JoinColumn(name = "goods_id"),
            inverseJoinColumns = @JoinColumn(name = "categories_id"))
    private List<Category> categories;

}
