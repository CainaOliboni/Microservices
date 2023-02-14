package com.cainaoliboni.pagamento.config;

import com.cainaoliboni.pagamento.data.vo.ProdutoVO;
import com.cainaoliboni.pagamento.entity.Produto;
import com.cainaoliboni.pagamento.repository.ProdutoRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class ProdutoReceiveMessage {

    private final ProdutoRepository produtoRepository;

    public ProdutoReceiveMessage (ProdutoRepository produtoRepository){
        this.produtoRepository = produtoRepository;
    }
    @RabbitListener(queues = {"${crud.rabbitmq.queue}"})
    public void receive(@Payload ProdutoVO produtoVO){
        produtoRepository.save(Produto.create(produtoVO));
    }

}
