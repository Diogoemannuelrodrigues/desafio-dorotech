package br.com.desafio.dorotech.entidade;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collation = "products")
public class Product {

    @Id
    private String id;
    private String name;
    private String description;
    private Double price;
    private Integer amount;
}
