package com.cainaoliboni.crud.data.vo;

import com.cainaoliboni.crud.entity.Produto;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;

@JsonPropertyOrder({"id", "nome", "estoque", "preco"})
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ProdutoVO extends RepresentationModel<ProdutoVO> implements Serializable {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("nome")
    private String nome;
    @JsonProperty("estoque")
    private Integer estoque;
    @JsonProperty("preco")
    private Double preco;

    public static ProdutoVO create(Produto produto){
        return new ModelMapper().map(produto, ProdutoVO.class);
    }

}
