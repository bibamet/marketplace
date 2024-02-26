package com.example.marketplace.presentation.basket.dto.queries;

import com.example.marketplace.presentation.goods.dto.queries.GoodsQuery;
import com.example.marketplace.presentation.user.dto.queries.UserQuery;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BasketQuery implements Serializable {
    private UserQuery user;
    private List<GoodsQuery> goods;
}
