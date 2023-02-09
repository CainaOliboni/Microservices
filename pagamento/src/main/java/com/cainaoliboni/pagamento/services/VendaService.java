package com.cainaoliboni.pagamento.services;

import com.cainaoliboni.pagamento.data.vo.VendaVO;
import com.cainaoliboni.pagamento.entity.ProdutoVenda;
import com.cainaoliboni.pagamento.entity.Venda;
import com.cainaoliboni.pagamento.exception.ResourceNotFoundException;
import com.cainaoliboni.pagamento.repository.ProdutoRepository;
import com.cainaoliboni.pagamento.repository.ProdutoVendaRepository;
import com.cainaoliboni.pagamento.repository.VendaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VendaService {

    private final VendaRepository vendaRepository;
    private final ProdutoVendaRepository produtoVendaRepository;

    public VendaService(VendaRepository vendaRepository, ProdutoVendaRepository produtoVendaRepository){
        this.vendaRepository = vendaRepository;
        this.produtoVendaRepository = produtoVendaRepository;
    }

    public VendaVO create (VendaVO vendaVO){
        Venda venda = vendaRepository.save(Venda.create(vendaVO));
        List<ProdutoVenda> produtosSalvos = new ArrayList<>();
        vendaVO.getProdutos().forEach(p -> {
            ProdutoVenda pv = ProdutoVenda.create(p);
            pv.setVenda(venda);
            produtosSalvos.add(produtoVendaRepository.save(pv));
        });
        venda.setProdutos(produtosSalvos);

        return VendaVO.create(venda);
    }

    public Page<VendaVO> findAll(Pageable pageable){
        var page = vendaRepository.findAll(pageable);
        return page.map(this::convertToProdutoVO);
    }

    public VendaVO findById(Long id){
        var entity = vendaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        return VendaVO.create(entity);
    }

    private VendaVO convertToProdutoVO(Venda venda){
        return VendaVO.create(venda);
    }


}
