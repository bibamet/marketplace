package com.example.marketplace.application.basket;

import com.example.marketplace.application.goods.GoodsService;
import com.example.marketplace.application.user.UserService;
import com.example.marketplace.domain.entity.Basket;
import com.example.marketplace.domain.entity.Goods;
import com.example.marketplace.domain.entity.User;
import com.example.marketplace.infrastructure.repository.BasketRepository;
import com.example.marketplace.presentation.basket.dto.commands.AddGoodsInBasketCommand;
import com.example.marketplace.presentation.basket.dto.commands.DeleteGoodsCommand;
import com.example.marketplace.presentation.basket.dto.queries.BasketQuery;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BasketService {

    private final BasketRepository basketRepository;
    private final BasketMapper basketMapper;
    private final UserService userService;
    private final GoodsService goodsService;

    public BasketQuery getById(Integer id) {
        Basket basketFromDB = basketRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Не найдена корзина с таким \"id\" = %d", id)));
        return basketMapper.basketToBasketQuery(basketFromDB);
    }

    @Transactional
    public BasketQuery deleteGoodsFromBasket(DeleteGoodsCommand deleteGoodsCommand) {

        User userFromDB = userService.getUserFromDB(deleteGoodsCommand.getUserId());
        Basket basketFromDB = basketRepository
                .findByUser(userFromDB)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Не найдена корзина для пользователя с \"id\" = %d", deleteGoodsCommand.getUserId())));
        Goods goodsFromDB = goodsService.getGoodsFromDB(deleteGoodsCommand.getGoodsId());
        basketFromDB.getGoods().remove(goodsFromDB);
        Basket savedBasket = basketRepository.save(basketFromDB);
        return basketMapper.basketToBasketQuery(savedBasket);
    }

    @Transactional
    public BasketQuery addGoodsInBasket(AddGoodsInBasketCommand addGoodsInBasketCommand) {
        User userFromDB = userService.getUserFromDB(addGoodsInBasketCommand.getUserId());
        Basket basketFromDB = null;
        Optional<Basket> optionalBasket = basketRepository.findByUser(userFromDB);
        if (optionalBasket.isEmpty()) {
            basketFromDB = new Basket();
            basketFromDB.setUser(userFromDB);
        } else {
            basketFromDB = optionalBasket.get();
        }
        Goods goodsFromDB = goodsService.getGoodsFromDB(addGoodsInBasketCommand.getGoodsId());
        if (basketFromDB.getGoods() == null) {
            basketFromDB.setGoods(new ArrayList<>());
        }
        basketFromDB.getGoods().add(goodsFromDB);
        Basket savedBasket = basketRepository.save(basketFromDB);
        userFromDB.setBasket(basketFromDB);
        User savedUser = userService.saveUser(userFromDB);
//        return basketMapper.basketToBasketQuery(savedBasket);
        return basketMapper.basketToBasketQuery(savedUser.getBasket());
    }
}
