package me.decentos.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Food extends OrderItem {

    private String itemName;
    private boolean isHot;

    @Override
    public boolean isFood() {
        return true;
    }

    public Food(String itemName, boolean isHot, String orderNum) {
        this.itemName = itemName;
        this.isHot = isHot;
        this.orderNum = orderNum;
    }
}
