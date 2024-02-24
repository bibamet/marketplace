package com.example.marketplace.presentation.goods;

import com.example.marketplace.application.goods.GoodsService;
import com.example.marketplace.presentation.goods.dto.commands.CreateGoodsCommand;
import com.example.marketplace.presentation.goods.dto.commands.UpdateGoodsCommand;
import com.example.marketplace.presentation.goods.dto.queries.GoodsQuery;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hibernate.sql.Update;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/goods")
@RequiredArgsConstructor
@Validated
public class GoodsController {

    private final GoodsService goodsService;

    @GetMapping
    public List<GoodsQuery> getAll() {
        return goodsService.getAll();
    }

    @GetMapping("/{id}")
    public GoodsQuery getById(@PathVariable Integer id) {
        return goodsService.getById(id);
    }

    @GetMapping("/category/{name}")
    public List<GoodsQuery> getByCategoryName(@PathVariable String categoryName) {
        return goodsService.findByCategoryName(categoryName);
    }

    @PostMapping
    public GoodsQuery createGoods(@RequestBody @Valid CreateGoodsCommand createGoodsCommand) {
        return goodsService.createGoods(createGoodsCommand);
    }

    @PutMapping("/{id}")
    public GoodsQuery updateGoods(@PathVariable Integer id, @RequestBody @Valid UpdateGoodsCommand updateGoodsCommand) {
        return goodsService.updateGoods(id, updateGoodsCommand);
    }

    @DeleteMapping("/{id}")
    public void deleteGoods(@PathVariable Integer id) {
        goodsService.deleteGoods(id);
    }
}
