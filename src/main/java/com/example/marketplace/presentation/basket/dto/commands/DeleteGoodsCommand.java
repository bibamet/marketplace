package com.example.marketplace.presentation.basket.dto.commands;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeleteGoodsCommand {
    private Integer userId;
    private Integer goodsId;
}
