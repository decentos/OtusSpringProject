package me.decentos.gateway;


import me.decentos.domain.AssembledOrder;
import me.decentos.domain.Order;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway
public interface Cafe {

    @Gateway(requestChannel = "orders.input")
    AssembledOrder placeOrder(Order order);
}
