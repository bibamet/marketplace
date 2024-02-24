package com.example.marketplace.presentation.category;

import com.example.marketplace.application.category.CategoryService;
import com.example.marketplace.presentation.category.dto.commands.CreateCategoryCommand;
import com.example.marketplace.presentation.category.dto.commands.UpdateCategoryCommand;
import com.example.marketplace.presentation.category.dto.queries.CategoryQuery;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
@Validated
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public List<CategoryQuery> getAll() {
        return categoryService.getAll();
    }

    @GetMapping("/{id}")
    public CategoryQuery getById(@Positive Integer id) {
        return categoryService.getById(id);
    }

    @PostMapping
    public CategoryQuery createCategory(@RequestBody @Valid CreateCategoryCommand createCategoryCommand) {
        return categoryService.create(createCategoryCommand);
    }

    @PutMapping
    public CategoryQuery changeCategory(@Positive Integer id, @RequestBody @Valid UpdateCategoryCommand updateCategoryCommand) {
        return categoryService.update(id, updateCategoryCommand);
    }

    @DeleteMapping
    public void deleteCategory(@Positive Integer id) {
        categoryService.delete(id);
    }


}
