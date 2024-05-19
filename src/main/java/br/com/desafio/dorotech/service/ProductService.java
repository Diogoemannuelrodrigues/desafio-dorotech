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
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;

    public ProductRecordResponse createProduct(ProductRecordRequest request) {
        validateRequest(request);
        Product savedProduct = repository.save(ProductMapper.toEntity(request));
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

    public ProductRecordResponse updateProduct(ProductRecordRequest request, String id) {
        Optional<Product> optionalProduct = repository.findById(id);
        if (optionalProduct.isPresent()) {
            Product existingProduct = optionalProduct.get();
            Product newProduct = ProductMapper.toEntity(request);
            newProduct.setId(existingProduct.getId());
            Product updatedProduct = repository.save(newProduct);
            return ProductMapper.toResponse(updatedProduct);
        } else {
            throw new IllegalArgumentException("Product does not exist");
        }
    }

    private void validateRequest(ProductRecordRequest request) {
        if (Objects.isNull(request)) {
            throw new IllegalArgumentException("The request cannot be null");
        }
        if (Objects.isNull(request.name()) || request.name().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be null or empty");
        }
        if (Objects.isNull(request.description()) || request.description().isEmpty()) {
            throw new IllegalArgumentException("Product description cannot be null or empty");
        }
        if (Objects.isNull(request.price())) {
            throw new IllegalArgumentException("Product price cannot be null");
        }
        if (Objects.isNull(request.amount())) {
            throw new IllegalArgumentException("Product amount cannot be null");
        }
    }
}
