package com.example.marketplace.presentation.goods.dto.commands;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
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
public class UpdateGoodsCommand implements Serializable {
    @NotBlank
    private String title;
    private String description;
    @Positive
    private Double price;
    @NotEmpty(message = "Не указаны категории")
    private List<Integer> category;
}
