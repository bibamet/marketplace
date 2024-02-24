package com.example.marketplace.presentation.category.dto.commands;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
public class CreateCategoryCommand {
    @NotBlank
    private String name;
    @NotBlank
    private String description;
}
