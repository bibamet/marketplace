package com.example.marketplace.application.goods;

import com.example.marketplace.domain.entity.Category;
import com.example.marketplace.domain.entity.Goods;
import com.example.marketplace.presentation.category.dto.queries.CategoryQuery;
import com.example.marketplace.presentation.goods.dto.commands.CreateGoodsCommand;
import com.example.marketplace.presentation.goods.dto.commands.UpdateGoodsCommand;
import com.example.marketplace.presentation.goods.dto.queries.GoodsQuery;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring",
        suppressTimestampInGenerated = true,
        unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface GoodsMapper {

    Goods fromCreateGoodsToGoods(CreateGoodsCommand createGoodsCommand);
    CategoryQuery categoryToCategoryQuery(Category category);
    List<CategoryQuery> listCategoryToListCategoryQuery(List<Category> categories);
    GoodsQuery fromGoodsToGoodsQuery(Goods goods);
    List<GoodsQuery> fromListGoodsToListGoodsQuery(List<Goods> goods);
    void updateGoods(UpdateGoodsCommand updateGoodsCommand, @MappingTarget Goods goods);

}
