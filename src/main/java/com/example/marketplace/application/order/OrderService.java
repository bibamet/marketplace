package com.example.marketplace.application.order;

import com.example.marketplace.application.goods.GoodsService;
import com.example.marketplace.application.user.UserService;
import com.example.marketplace.domain.entity.Goods;
import com.example.marketplace.domain.entity.Order;
import com.example.marketplace.domain.entity.User;
import com.example.marketplace.domain.enums.Statuses;
import com.example.marketplace.infrastructure.repository.OrderRepository;
import com.example.marketplace.presentation.order.dto.commands.CreateOrderCommand;
import com.example.marketplace.presentation.order.dto.commands.UpdateOrderCommand;
import com.example.marketplace.presentation.order.dto.queries.OrderQuery;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final UserService userService;
    private final GoodsService goodsService;

    public List<OrderQuery> getAll() {
        List<Order> allOrders = orderRepository.findAll();
        return orderMapper.toOrderQueryList(allOrders);
    }

    public OrderQuery getByID(Integer orderId) {
        Order orderFromDB = orderRepository.findById(orderId).orElseThrow(getEntityNotFoundExceptionSupplier(orderId));
        return orderMapper.toOrderQuery(orderFromDB);
    }

    public List<OrderQuery> getUsersOrderHistory(Integer userId) {
        List<Order> ordersFromDB = orderRepository.findByUser_Id(userId);
        return orderMapper.toOrderQueryList(ordersFromDB);
    }

    @Transactional
    public OrderQuery updateStatus(UpdateOrderCommand updateOrderCommand) {

        Integer orderId = updateOrderCommand.getOrderId();
        Order order = orderRepository.findById(orderId).orElseThrow(getEntityNotFoundExceptionSupplier(orderId));

        Statuses newStatus = null;

        try {
            newStatus = Statuses.valueOf(updateOrderCommand.getStatus());
        } catch (IllegalArgumentException e) {
            throw new EntityNotFoundException("Неправильное имя статуса");
        }
        order.setStatus(newStatus);

        Order savedOrder = orderRepository.save(order);
        return orderMapper.toOrderQuery(savedOrder);

    }

    private Supplier<EntityNotFoundException> getEntityNotFoundExceptionSupplier(Integer orderId) {
        return () -> new EntityNotFoundException(String.format("Не найден заказ с \"id\" = %d", orderId));
    }

    @Transactional
    public OrderQuery createOrder(CreateOrderCommand createOrderCommand) {

        Integer userId = createOrderCommand.getUserId();
        List<Integer> goodsIds = createOrderCommand.getGoodsIds();

        User userFromDB = userService.getUserFromDB(userId);
        List<Goods> goodsByFromDB = goodsService.getGoodsByIds(goodsIds);
        Double totalPrice = goodsByFromDB.stream().map(goods -> goods.getPrice()).reduce(0D, (x1, x2) -> x1 + x2).doubleValue();

        List<Goods> userGoods = userFromDB.getBasket().getGoods();
        if (userGoods != null && userGoods.size() > 0) {
            userGoods.removeAll(goodsByFromDB);
        }

        Order newOrder = Order.builder()
                .status(Statuses.CREATED)
                .user(userFromDB)
                .goods(goodsByFromDB)
                .totalPrice(totalPrice)
                .build();

        Order savedOrder = orderRepository.save(newOrder);

        // вставить сохранение юзера, если корзина не будет очищаться/или сохранение корзины

        return orderMapper.toOrderQuery(savedOrder);

    }
}
