package com.example.marketplace.application.order;

import com.example.marketplace.domain.entity.Goods;
import com.example.marketplace.domain.entity.Order;
import com.example.marketplace.domain.entity.User;
import com.example.marketplace.presentation.goods.dto.queries.GoodsQuery;
import com.example.marketplace.presentation.order.dto.commands.CreateOrderCommand;
import com.example.marketplace.presentation.order.dto.queries.OrderQuery;
import com.example.marketplace.presentation.user.dto.queries.UserQuery;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(target = "status", expression = "java(order.getStatus().getValue())")
    OrderQuery toOrderQuery(Order order);
    List<OrderQuery> toOrderQueryList(List<Order> orders);
    UserQuery toUserQuery(User user);
    GoodsQuery toGoodsQuery(Goods goods);

}
