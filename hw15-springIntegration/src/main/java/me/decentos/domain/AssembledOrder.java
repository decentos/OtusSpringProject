package me.decentos.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssembledOrder {

    private String orderNum;
    private List<Food> foodList;
    private List<Drink> drinkList;
}
