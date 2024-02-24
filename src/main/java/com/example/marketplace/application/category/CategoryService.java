package com.example.marketplace.application.category;

import com.example.marketplace.domain.entity.Category;
import com.example.marketplace.infrastructure.repository.CategoryRepository;
import com.example.marketplace.presentation.category.dto.commands.CreateCategoryCommand;
import com.example.marketplace.presentation.category.dto.commands.UpdateCategoryCommand;
import com.example.marketplace.presentation.category.dto.queries.CategoryQuery;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    public List<CategoryQuery> getAll() {
        List<Category> categoriesFromDB = categoryRepository.findAll();
        if (categoriesFromDB.isEmpty()) {
            return new ArrayList<CategoryQuery>();
        }

        return (categoriesFromDB.stream().map(category -> modelMapper.map(category, CategoryQuery.class)).toList());

    }

    public CategoryQuery getById(Integer id) {
        return modelMapper.map(categoryRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Категория с \"id\" = %d не найдена", id))), CategoryQuery.class);
    }

    public CategoryQuery create(CreateCategoryCommand createCategoryCommand) {

        Category newCategory = modelMapper.map(createCategoryCommand, Category.class);
        Category savedCategory = categoryRepository.save(newCategory);

        return modelMapper.map(savedCategory, CategoryQuery.class);

    }


    public void delete(Integer id) {
        Category categoryFromDB = categoryRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Категория с \"id\" = %d не найдена", id)));
        categoryRepository.delete(categoryFromDB);
    }

    public CategoryQuery update(Integer id, UpdateCategoryCommand updateCategoryCommand) {
        Category categoryFromDB = categoryRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Категория с \"id\" = %d не найдена", id)));

        if (!categoryFromDB.getName().equals(updateCategoryCommand.getName())) {
            categoryFromDB.setName(updateCategoryCommand.getName());
        }
        if (!categoryFromDB.getDescription().equals(updateCategoryCommand.getDescription())) {
            categoryFromDB.setDescription(updateCategoryCommand.getDescription());
        }

        Category updatedCategory = categoryRepository.save(categoryFromDB);
        return modelMapper.map(updatedCategory, CategoryQuery.class);
    }

    public List<Category> getCategoriesById(List<Integer> categories) {
        return categoryRepository.findAllById(categories);
    }

}
