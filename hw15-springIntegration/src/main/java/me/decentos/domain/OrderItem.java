package me.decentos.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public abstract class OrderItem {

    public String orderNum;

    public abstract String getItemName();

    public abstract boolean isFood();
}
