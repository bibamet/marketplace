package com.example.marketplace.presentation.order.dto.commands;

import com.example.marketplace.domain.entity.Goods;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class CreateOrderCommand {
    private Integer userId;
    private List<Integer> goodsIds;
}
