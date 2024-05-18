package br.com.desafio.dorotech.mapper;

import br.com.desafio.dorotech.entidade.Product;
import br.com.desafio.dorotech.entidade.request.ProductRecordRequest;
import br.com.desafio.dorotech.entidade.response.ProductRecordResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProductMapper {

    public static Product toEntity(ProductRecordRequest request) {
        return Product.builder()
                .name(request.name())
                .description(request.description())
                .price(request.price())
                .amount(request.amount())
                .build();
    }

    public static ProductRecordResponse toResponse(Product product) {
        return ProductRecordResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .amount(product.getAmount())
                .build();
    }
}
