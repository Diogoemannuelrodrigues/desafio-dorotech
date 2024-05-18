package br.com.desafio.dorotech.controller;

import br.com.desafio.dorotech.entidade.request.ProductRecordRequest;
import br.com.desafio.dorotech.entidade.response.ProductRecordResponse;
import br.com.desafio.dorotech.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    @PostMapping
    public ResponseEntity<ProductRecordResponse> saveProduct(@RequestBody ProductRecordRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createProduct(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductRecordResponse> getProductById(@PathVariable String id) {
        var productResponse = service.getProductById(id);
        if (productResponse == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(productResponse);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProductRecordResponse>> getAllProducts() {
        List<ProductRecordResponse> products = service.getAllProduct();
        if (products.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(products);
    }

}
