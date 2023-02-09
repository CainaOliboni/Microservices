package com.cainaoliboni.pagamento.data.vo;

import com.cainaoliboni.pagamento.entity.ProdutoVenda;
import com.cainaoliboni.pagamento.entity.Venda;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@JsonPropertyOrder({"id", "sellDate", "produtos", "valorTotal"})
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class VendaVO extends RepresentationModel<VendaVO> {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("sellDate")
    private LocalDate sellDate;

    @JsonProperty("produtos")
    private List<ProdutoVendaVO> produtos;
    @JsonProperty("valorTotal")
    private Double valorTotal;

    public static VendaVO create(Venda venda){
        return new ModelMapper().map(venda, VendaVO.class);
    }

}
