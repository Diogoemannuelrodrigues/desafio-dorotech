package br.com.desafio.dorotech.service;

import br.com.desafio.dorotech.entidade.Product;
import br.com.desafio.dorotech.entidade.request.ProductRecordRequest;
import br.com.desafio.dorotech.entidade.response.ProductRecordResponse;
import br.com.desafio.dorotech.mapper.ProductMapper;
import br.com.desafio.dorotech.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;

    public ProductRecordResponse createProduct(ProductRecordRequest request) {
        Product product = ProductMapper.toEntity(request);
        Product savedProduct = repository.save(product);
        return ProductMapper.toResponse(savedProduct);
    }

    public ProductRecordResponse getProductById(String id) {
        return repository.findById(id)
                .map(ProductMapper::toResponse)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }
}
