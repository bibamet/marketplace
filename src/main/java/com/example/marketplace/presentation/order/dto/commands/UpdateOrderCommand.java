package com.example.marketplace.presentation.order.dto.commands;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UpdateOrderCommand {
    private Integer orderId;
    private String status;
}
