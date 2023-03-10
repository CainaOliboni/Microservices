package com.cainaoliboni.crud.message;

import com.cainaoliboni.crud.data.vo.ProdutoVO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ProdutoSendMessage {

    @Value("${crud.rabbitmq.exchange}")
    String exchange;

    @Value("${crud.rabbitmq.routing-key}")
    String routingKey;

    public final RabbitTemplate rabbitTemplate;

    public ProdutoSendMessage(RabbitTemplate rabbitTemplate){
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(ProdutoVO produtoVO){
        rabbitTemplate.convertAndSend(exchange, routingKey, produtoVO);
    }

}
