package com.example.marketplace.presentation.basket;

import com.example.marketplace.application.basket.BasketService;
import com.example.marketplace.presentation.basket.dto.commands.AddGoodsInBasketCommand;
import com.example.marketplace.presentation.basket.dto.commands.DeleteGoodsCommand;
import com.example.marketplace.presentation.basket.dto.queries.BasketQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/basket")
@RequiredArgsConstructor
public class BasketController {

    private final BasketService basketService;

    @GetMapping("/{id}")
    public BasketQuery getById(@PathVariable Integer id) {
        return basketService.getById(id);
    }

    @PostMapping
    public BasketQuery addGoodsInBasket(@RequestBody AddGoodsInBasketCommand addGoodsInBasketCommand) {
        return basketService.addGoodsInBasket(addGoodsInBasketCommand);
    }

    @DeleteMapping
    public BasketQuery deleteGoodsFromBasket(@RequestBody DeleteGoodsCommand deleteGoodsCommand) {
        return basketService.deleteGoodsFromBasket(deleteGoodsCommand);
    }

}
