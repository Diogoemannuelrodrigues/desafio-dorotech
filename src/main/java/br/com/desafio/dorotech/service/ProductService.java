package br.com.desafio.dorotech.service;

import br.com.desafio.dorotech.entidade.Product;
import br.com.desafio.dorotech.entidade.request.ProductRecordRequest;
import br.com.desafio.dorotech.entidade.response.ProductRecordResponse;
import br.com.desafio.dorotech.exceptions.ProductNotCanBeNullException;
import br.com.desafio.dorotech.exceptions.ProductNotFoundException;
import br.com.desafio.dorotech.mapper.ProductMapper;
import br.com.desafio.dorotech.producer.ProductMessageProducer;
import br.com.desafio.dorotech.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;
    private final ProductMessageProducer productMessageProducer;

    public ProductRecordResponse createProduct(ProductRecordRequest request) {
        validateRequest(request);
        Product savedProduct = repository.save(ProductMapper.toEntity(request));
        productMessageProducer.sendMessage("Product created: " + savedProduct.toString());
        return ProductMapper.toResponse(savedProduct);
    }

    public ProductRecordResponse getProductById(String id) {
        return repository.findById(id)
                .map(ProductMapper::toResponse)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));
    }

    public List<ProductRecordResponse> getAllProduct() {
        return repository.findAll().stream()
                .map(ProductMapper::toResponse)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public ProductRecordResponse updateProduct(ProductRecordRequest request, String id) {
        return repository.findById(id)
                .map(existingProduct -> {
                    Product updatedProduct = ProductMapper.toEntity(request);
                    updatedProduct.setId(existingProduct.getId());
                    return repository.save(updatedProduct);
                })
                .map(ProductMapper::toResponse)
                .orElseThrow(() -> new ProductNotFoundException("Product does not exist"));
    }


    public void delete(String id){
        Product product = repository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product does not exist"));
        repository.delete(product);
    }

    private void validateRequest(ProductRecordRequest request) {
        if (Objects.isNull(request)) {
            throw new ProductNotCanBeNullException("The request cannot be null");
        }
        if (Objects.isNull(request.name()) || request.name().isEmpty()) {
            throw new ProductNotCanBeNullException("Product name cannot be null or empty");
        }
        if (Objects.isNull(request.description()) || request.description().isEmpty()) {
            throw new ProductNotCanBeNullException("Product description cannot be null or empty");
        }
        if (Objects.isNull(request.price())) {
            throw new ProductNotCanBeNullException("Product price cannot be null");
        }
        if (Objects.isNull(request.amount())) {
            throw new ProductNotCanBeNullException("Product amount cannot be null");
        }
    }
}
