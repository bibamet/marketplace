package com.example.marketplace.presentation.goods.dto.queries;

import com.example.marketplace.presentation.category.dto.queries.CategoryQuery;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
public class GoodsQuery implements Serializable {
    private final String title;
    private final String description;
    private final Double price;
    private final List<CategoryQuery> categories;
}
