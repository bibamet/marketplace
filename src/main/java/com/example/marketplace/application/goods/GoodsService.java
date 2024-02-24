package com.example.marketplace.application.goods;

import com.example.marketplace.application.category.CategoryService;
import com.example.marketplace.domain.entity.Category;
import com.example.marketplace.domain.entity.Goods;
import com.example.marketplace.infrastructure.repository.GoodsRepository;
import com.example.marketplace.presentation.goods.dto.commands.CreateGoodsCommand;
import com.example.marketplace.presentation.goods.dto.commands.UpdateGoodsCommand;
import com.example.marketplace.presentation.goods.dto.queries.GoodsQuery;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GoodsService {

    private final GoodsRepository goodsRepository;
    private final ModelMapper modelMapper;
    private final CategoryService categoryService;

    public List<GoodsQuery> getAll() {
        return goodsRepository.findAll().stream().map(goods -> modelMapper.map(goods, GoodsQuery.class)).toList();
    }

    public GoodsQuery getById(Integer id) {
        Goods goodsFromDB = goodsRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.format("Не удалось найти товар с id = %d", id)));
        return modelMapper.map(goodsFromDB, GoodsQuery.class);
    }

    public List<GoodsQuery> findByCategoryName(String categoryName) {
        List<Goods> goodsFromDB = goodsRepository.findDistinctByCategories_Name(categoryName);
        return goodsFromDB.stream().map(goods -> modelMapper.map(goods, GoodsQuery.class)).toList();
    }

    public GoodsQuery createGoods(CreateGoodsCommand createGoodsCommand) {

        List<Category> categoriesFromDB = categoryService.getCategoriesById(createGoodsCommand.getCategory());
        if (categoriesFromDB.size() != createGoodsCommand.getCategory().size()) {
            throw new EntityNotFoundException("Не найдена(-ы) категория(-и) товара(-ов)");
        }

        Goods newGoods = modelMapper.map(createGoodsCommand, Goods.class);
        newGoods.setCategories(categoriesFromDB);

        Goods savedGoods = goodsRepository.save(newGoods);
        return modelMapper.map(savedGoods, GoodsQuery.class);

    }

    public void deleteGoods(Integer id) {
        Goods goodsFromDB = goodsRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Не удалось найти товар с id = %d", id)));
        goodsRepository.delete(goodsFromDB);
    }

    public GoodsQuery updateGoods(Integer id, UpdateGoodsCommand updateGoodsCommand) {

        boolean changed = false;

        List<Integer> newCategories = updateGoodsCommand.getCategory();

        Goods goodsFromDB = goodsRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Не удалось найти товар с id = %d", id)));
        List<Integer> goodsIdFromDB = goodsFromDB.getCategories().stream().map(goods -> goods.getId()).toList();

        if (!goodsFromDB.getTitle().equals(updateGoodsCommand.getTitle())) {
            goodsFromDB.setTitle(updateGoodsCommand.getTitle());
            changed = true;
        }
        if (!goodsFromDB.getDescription().equals(updateGoodsCommand.getDescription())) {
            goodsFromDB.setDescription(updateGoodsCommand.getDescription());
            changed = true;
        }
        if (!goodsFromDB.getPrice().equals(updateGoodsCommand.getPrice())) {
            goodsFromDB.setPrice(updateGoodsCommand.getPrice());
            changed = true;
        }
        if (goodsFromDB.getCategories().stream().filter(category -> !newCategories.contains(category.getId())).count() > 0 ||
                newCategories.stream().filter(newId -> !goodsIdFromDB.contains(newId)).count() > 0) {
            List<Category> categoriesById = categoryService.getCategoriesById(newCategories);
            if (categoriesById.size() != newCategories.size()) {
                throw new EntityNotFoundException("Не найдена(-ы) категория(-и) товара(-ов)");
            }
            goodsFromDB.setCategories(categoriesById);
            changed = true;
        }

        if (changed) {
            Goods savedGoods = goodsRepository.save(goodsFromDB);
            return modelMapper.map(savedGoods, GoodsQuery.class);
        }
        return modelMapper.map(goodsFromDB, GoodsQuery.class);
    }
}