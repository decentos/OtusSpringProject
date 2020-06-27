package me.decentos.service.impl;

import lombok.RequiredArgsConstructor;
import me.decentos.domain.Drink;
import me.decentos.domain.Food;
import me.decentos.domain.OrderItem;
import me.decentos.service.IOService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CookingService {

    private final IOService ioService;

    private static final String COOK = "ПРОЦЕСС";
    private static final String COOK_DONE = "ГОТОВО";
    private static final String DRINK_ALCO = "Алкогольный напиток";
    private static final String DRINK_NON_ALCO = "Безалкогольный напиток";
    private static final String FOOD_HOT = "Горячее блюдо";
    private static final String FOOD_COLD = "Холодное блюдо";

    private static final String STR_3_FORMATTER = "%-10s %-25s %-15s %n";
    private static final String STR_4_FORMATTER = "%-10s %-25s %-15s %-15s %n";

    public Food hotKitchenCook(OrderItem orderItem) {
        ioService.printMsg(STR_3_FORMATTER, COOK, orderItem.getItemName(), orderItem.orderNum);
        SleepService.sleepUnintelligibly(4);
        ioService.printMsg(STR_4_FORMATTER, COOK_DONE, orderItem.getItemName(), orderItem.orderNum, FOOD_HOT);
        return new Food(orderItem.getItemName(), true, orderItem.getOrderNum());
    }

    public Food coldKitchenCook(OrderItem orderItem) {
        ioService.printMsg(STR_3_FORMATTER, COOK, orderItem.getItemName(), orderItem.orderNum);
        SleepService.sleepUnintelligibly(3);
        ioService.printMsg(STR_4_FORMATTER, COOK_DONE, orderItem.getItemName(), orderItem.orderNum, FOOD_COLD);
        return new Food(orderItem.getItemName(), false, orderItem.getOrderNum());
    }

    public Drink alcoholDrinkCook(OrderItem orderItem) {
        ioService.printMsg(STR_3_FORMATTER, COOK, orderItem.getItemName(), orderItem.orderNum);
        SleepService.sleepUnintelligibly(1);
        ioService.printMsg(STR_4_FORMATTER, COOK_DONE, orderItem.getItemName(), orderItem.orderNum, DRINK_ALCO);
        return new Drink(orderItem.getItemName(), true, orderItem.getOrderNum());
    }

    public Drink nonAlcoholDrinkCook(OrderItem orderItem) {
        ioService.printMsg(STR_3_FORMATTER, COOK, orderItem.getItemName(), orderItem.orderNum);
        SleepService.sleepUnintelligibly(2);
        ioService.printMsg(STR_4_FORMATTER, COOK_DONE, orderItem.getItemName(), orderItem.orderNum, DRINK_NON_ALCO);
        return new Drink(orderItem.getItemName(), false, orderItem.getOrderNum());
    }
}
