package br.com.desafio.dorotech.service;

import br.com.desafio.dorotech.entidade.Product;
import br.com.desafio.dorotech.entidade.request.ProductRecordRequest;
import br.com.desafio.dorotech.entidade.response.ProductRecordResponse;
import br.com.desafio.dorotech.mapper.ProductMapper;
import br.com.desafio.dorotech.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<ProductRecordResponse> getAllProduct() {
        return repository.findAll().stream()
                .map(ProductMapper::toResponse)
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
