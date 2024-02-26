package com.example.marketplace.presentation.basket.dto.commands;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddGoodsInBasketCommand {
    private Integer userId;
    private Integer goodsId;
}
