package com.example.marketplace.application.basket;

import com.example.marketplace.domain.entity.Basket;
import com.example.marketplace.domain.entity.Goods;
import com.example.marketplace.presentation.basket.dto.queries.BasketQuery;
import com.example.marketplace.presentation.goods.dto.queries.GoodsQuery;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring",
        suppressTimestampInGenerated = true,
        unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface BasketMapper {

    BasketQuery basketToBasketQuery(Basket basket);

    GoodsQuery goodsToGoodsQuery(Goods goods);

    List<BasketQuery> listBasketToListBasketQuery(List<Basket> baskets);

}
