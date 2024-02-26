package com.example.marketplace.infrastructure.repository;

import com.example.marketplace.domain.entity.Basket;
import com.example.marketplace.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BasketRepository extends JpaRepository<Basket, Integer> {
    Optional<Basket> findByUser(User user);
}
