package com.example.marketplace.infrastructure.repository;

import com.example.marketplace.domain.entity.Goods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoodsRepository extends JpaRepository<Goods, Integer> {
    public List<Goods> findDistinctByCategories_Name(String categoryName);
}
