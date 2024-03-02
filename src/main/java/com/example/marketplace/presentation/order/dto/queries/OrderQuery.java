package com.example.marketplace.presentation.order.dto.queries;

import com.example.marketplace.presentation.goods.dto.queries.GoodsQuery;
import com.example.marketplace.presentation.user.dto.queries.UserQuery;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class OrderQuery {

    private Integer id;
    private String status;
    private UserQuery user;
    private List<GoodsQuery> goods;
    private Double totalPrice;

}
