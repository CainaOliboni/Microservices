package com.cainaoliboni.pagamento.entity;


import com.cainaoliboni.pagamento.data.vo.VendaVO;
import jakarta.persistence.*;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="venda")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Venda {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name= "sellDate", nullable = false)
    private LocalDate sellDate;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "venda", cascade = {CascadeType.REFRESH})
    private List<ProdutoVenda> produtos;
    @Column(name= "valorTotal", nullable = false, length = 10)
    private Double valorTotal;

    public static Venda create(VendaVO vendaVO){
        return new ModelMapper().map(vendaVO, Venda.class);
    }

}
