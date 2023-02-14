package com.cainaoliboni.pagamento.entity;


import com.cainaoliboni.pagamento.data.vo.ProdutoVO;
import jakarta.persistence.*;
import lombok.*;
import org.modelmapper.ModelMapper;

@Entity
@Table(name="produto")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Produto {

    @Id
    @GeneratedValue(strategy =  GenerationType.TABLE)
    private Long id;

    @Column(name = "estoque", length = 10)
    private Integer estoque;

    public static Produto create(ProdutoVO produtoVO){
        return new ModelMapper().map(produtoVO, Produto.class);
    }

}
