package com.cainaoliboni.crud.services;

import com.cainaoliboni.crud.data.vo.ProdutoVO;
import com.cainaoliboni.crud.entity.Produto;
import com.cainaoliboni.crud.exception.ResourceNotFoundException;
import com.cainaoliboni.crud.message.ProdutoSendMessage;
import com.cainaoliboni.crud.repository.ProdutoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final ProdutoSendMessage produtoSendMessage;

    public ProdutoService(ProdutoRepository produtoRepository, ProdutoSendMessage produtoSendMessage){
        this.produtoRepository = produtoRepository;
        this.produtoSendMessage = produtoSendMessage;
    }

    public ProdutoVO create (ProdutoVO produtoVO){
        ProdutoVO produtoVOCreated = ProdutoVO.create(produtoRepository.save(Produto.create(produtoVO)));
        produtoSendMessage.sendMessage(produtoVOCreated);
        return produtoVOCreated;
    }

    public Page<ProdutoVO> findAll(Pageable pageable){
        var page = produtoRepository.findAll(pageable);
        return page.map(this::convertToProdutoVO);
    }

    private ProdutoVO convertToProdutoVO(Produto produto){
        return ProdutoVO.create(produto);
    }

    public ProdutoVO findById(Long id){
        var entity = produtoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        return ProdutoVO.create(entity);
    }

    public ProdutoVO update(ProdutoVO produtoVO){
        final Optional<Produto> optionalProduto = produtoRepository.findById(produtoVO.getId());

        if(optionalProduto.isEmpty()){
            throw new ResourceNotFoundException("No records found for this ID");
        }
        return ProdutoVO.create(produtoRepository.save(Produto.create(produtoVO)));
    }

    public void delete(Long id){
        var entity = produtoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        produtoRepository.delete(entity);
    }

}
