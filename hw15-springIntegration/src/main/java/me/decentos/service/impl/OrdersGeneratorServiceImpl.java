package me.decentos.service.impl;

import lombok.RequiredArgsConstructor;
import me.decentos.domain.*;
import me.decentos.gateway.Cafe;
import me.decentos.service.IOService;
import me.decentos.service.OrdersGeneratorService;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrdersGeneratorServiceImpl implements OrdersGeneratorService {

    private final IOService ioService;

    private static final String[] HOT_MENU = {"Суп", "Пицца", "Шашлык", "Паста", "Плов"};
    private static final String[] COLD_MENU = {"Роллы", "Салат", "Холодец", "Мороженное"};
    private static final String[] DRINKS_MENU = {"Кофе", "Чай", "Смузи", "Кола", "Вода", "Молочный коктейль"};
    private static final String[] DRINKS_ALCOHOL_MENU = {"Виски", "Пиво", "Вино", "Водка", "Коньяк"};

    private static final String SEPARATOR_ORDERS = "#------------------------------------------------------------#";
    private static final String SEPARATOR_TOTALS = ">>>>>>>>>>>>>";

    private final Cafe cafe;

    public void processNewOrder() {
        Order order = new Order();
        String orderNum = new SimpleDateFormat("MMdd-MMss").format(new Date());
        order.setOrderNumber(orderNum);
        order.setOrderItems(generateOrderItems(orderNum));

        ioService.printMsg("НОВЫЙ ЗАКАЗ №" + order.getOrderNumber() + " / содержит: " +
                order.getOrderItems().stream().map(OrderItem::getItemName)
                        .collect(Collectors.joining(", ")) + "\nОтдан в работу " +
                new Timestamp(System.currentTimeMillis()));

        AssembledOrder assembledOrder = cafe.placeOrder(order);

        ioService.printMsg(SEPARATOR_TOTALS);
        ioService.printMsg("ЗАВЕРШЕН ЗАКАЗ №: " + assembledOrder.getOrderNum() + " (Время закрытия " +
                new Timestamp(System.currentTimeMillis()) +
                ")\nБлюда: " + assembledOrder.getFoodList().stream().map(Food::getItemName).collect(Collectors.joining(", ")) +
                "\nНапитки: " + assembledOrder.getDrinkList().stream().map(Drink::getItemName).collect(Collectors.joining(", ")));
        ioService.printMsg(SEPARATOR_ORDERS);
    }

    private List<OrderItem> generateOrderItems(String orderNum) {
        List<OrderItem> items = new ArrayList<>();
        for (int i = 0; i < RandomUtils.nextInt(2, 15); ++i) {
            items.add(RandomUtils.nextBoolean() ? generateFood(orderNum) : generateDrink(orderNum));
        }
        return items;
    }

    private Food generateFood(String orderNum) {
        boolean isHotKitchen = RandomUtils.nextBoolean();
        Food food;
        if (isHotKitchen) {
            food = new Food(HOT_MENU[RandomUtils.nextInt(0, HOT_MENU.length)], true);
        } else {
            food = new Food(COLD_MENU[RandomUtils.nextInt(0, COLD_MENU.length)], false);
        }
        food.setOrderNum(orderNum);
        return food;
    }

    private Drink generateDrink(String orderNum) {
        boolean isAlcohol = RandomUtils.nextBoolean();
        Drink drink;
        if (isAlcohol) {
            drink = new Drink(DRINKS_ALCOHOL_MENU[RandomUtils.nextInt(0, DRINKS_ALCOHOL_MENU.length)], true);
        } else {
            drink = new Drink(DRINKS_MENU[RandomUtils.nextInt(0, DRINKS_MENU.length)], false);
        }
        drink.setOrderNum(orderNum);
        return drink;
    }
}
