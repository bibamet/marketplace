package com.example.marketplace.domain.entity;

import com.example.marketplace.domain.valueobj.BaseInfo;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User extends BaseInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_gen")
    @SequenceGenerator(name = "users_gen", sequenceName = "users_gen")
    private Integer id;

    private String firstName;
    private String lastName;
    private Date birthDate;

    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "basket_id")
    private Basket basket;

}
