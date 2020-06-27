package me.decentos.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Drink extends OrderItem {

    private String itemName;
    private boolean isAlcohol;

    @Override
    public boolean isFood() {
        return false;
    }

    public Drink(String itemName, boolean isAlcohol, String orderNum) {
        this.itemName = itemName;
        this.isAlcohol = isAlcohol;
        this.orderNum = orderNum;
    }
}
