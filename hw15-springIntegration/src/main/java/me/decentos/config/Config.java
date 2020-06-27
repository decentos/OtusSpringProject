package me.decentos.config;

import lombok.RequiredArgsConstructor;
import me.decentos.domain.*;
import me.decentos.gateway.Cafe;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.scheduling.PollerMetadata;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;

import java.util.concurrent.Executors;
import java.util.stream.Collectors;

@Configuration
@EnableIntegration
@IntegrationComponentScan(basePackageClasses = Cafe.class)
@RequiredArgsConstructor
public class Config {

    private static final String COOK_SVC = "cookingService";
    private static final String ORDER_NUM = "orderNum";

    @Bean(name = PollerMetadata.DEFAULT_POLLER)
    public PollerMetadata poller() {
        return Pollers.fixedDelay(1000).get();
    }

    @Bean
    public IntegrationFlow orders() {
        return f -> f
                .split(Order.class, Order::getOrderItems)
                .channel(c -> c.executor(Executors.newCachedThreadPool()))
                .<OrderItem, Boolean>route(OrderItem::isFood, food -> food
                        .subFlowMapping(true, sf -> sf.gateway(gatewayKitchen ->
                                gatewayKitchen.channel(ch -> ch.executor(Executors.newCachedThreadPool()))
                                        .<Food, Boolean>route(Food::isHot, hotMapping -> hotMapping
                                                .subFlowMapping(true, hot -> hot
                                                        .handle(COOK_SVC, "hotKitchenCook"))
                                                .subFlowMapping(false, cold -> cold
                                                        .handle(COOK_SVC, "coldKitchenCook")))
                                        .transform(Message.class, m -> MessageBuilder.fromMessage(m)
                                                .setHeader(ORDER_NUM, ((Food) m.getPayload()).getOrderNum()))))

                        .subFlowMapping(false, sf -> sf.gateway(gatewayBar ->
                                gatewayBar.channel(ch -> ch.executor(Executors.newCachedThreadPool()))
                                        .<Drink, Boolean>route(Drink::isAlcohol, alcoholMapping -> alcoholMapping
                                                .subFlowMapping(true, alcohol -> alcohol
                                                        .handle(COOK_SVC, "alcoholDrinkCook"))
                                                .subFlowMapping(false, nonAlcohol -> nonAlcohol
                                                        .handle(COOK_SVC, "nonAlcoholDrinkCook")))
                                        .transform(Message.class, m -> MessageBuilder.fromMessage(m)
                                                .setHeader(ORDER_NUM, ((Drink) m.getPayload()).getOrderNum())))))

                .aggregate(aggregator -> aggregator
                        .outputProcessor(g -> new AssembledOrder(
                                g.getOne().getHeaders().get(ORDER_NUM, String.class),
                                g.getMessages().stream().filter(message -> message.getPayload() instanceof Food)
                                        .map(m -> (Food) m.getPayload()).collect(Collectors.toList()),
                                g.getMessages().stream().filter(message -> message.getPayload() instanceof Drink)
                                        .map(m -> (Drink) m.getPayload()).collect(Collectors.toList())))
                        .correlationStrategy(m -> m.getHeaders().get(ORDER_NUM)));
    }
}
