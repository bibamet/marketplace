package com.example.marketplace.application.category;

import com.example.marketplace.domain.entity.Category;
import com.example.marketplace.presentation.category.dto.commands.CreateCategoryCommand;
import com.example.marketplace.presentation.category.dto.commands.UpdateCategoryCommand;
import com.example.marketplace.presentation.category.dto.queries.CategoryQuery;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring",
        suppressTimestampInGenerated = true,
        unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface CategoryMapper {

    CategoryQuery fromCategoryToCategoryQuery(Category category);
    Category fromCreateCategoryToCategory(CreateCategoryCommand createCategoryCommand);
    List<CategoryQuery> fromListCategoryToListQuery(List<Category> categories);
    void updateCategory(UpdateCategoryCommand categoryQuery, @MappingTarget Category category);

}
