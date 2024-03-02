package com.example.marketplace.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Statuses {

    CREATED("Заказ создан"),
    IN_PROGRESS("Заказ собирается"),
    READY("Заказ готов к выдаче"),
    RECEIVED("Заказ получен"),
    CANCELLED("Заказ отменен");

    private final String value;

}
