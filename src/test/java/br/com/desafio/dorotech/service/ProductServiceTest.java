package br.com.desafio.dorotech.service;

import br.com.desafio.dorotech.entidade.Product;
import br.com.desafio.dorotech.entidade.request.ProductRecordRequest;
import br.com.desafio.dorotech.entidade.response.ProductRecordResponse;
import br.com.desafio.dorotech.mapper.ProductMapper;
import br.com.desafio.dorotech.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    @Mock
    private ProductRepository repository;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createProduct() {
        // Given
        ProductRecordRequest request = buildProduct();
        Product product = ProductMapper.toEntity(request);
        when(repository.save(product)).thenReturn(product);

        // When
        ProductRecordResponse response = productService.createProduct(request);

        // Then
        assertNotNull(response);
        assertEquals(request.name(), response.name());
        assertEquals(request.description(), response.description());
        assertEquals(request.price(), response.price());
        assertEquals(request.amount(), response.amount());
        verify(repository, times(1)).save(product);
    }

    @Test
    void createProduct_NullRequest_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> productService.createProduct(null));
    }

    @Test
    void createProduct_InvalidRequest_ThrowsException() {
        ProductRecordRequest request = new ProductRecordRequest("", "Description 1", 100.0, 10);
        assertThrows(IllegalArgumentException.class, () -> productService.createProduct(request));
    }

    @Test
    void delete_product(){
        ProductRecordRequest request = buildProduct();
        var product = ProductMapper.toEntity(request);
        when(repository.findById(product.getId())).thenReturn(Optional.of(product));

        productService.delete(product.getId());

        verify(repository, times(1)).delete(product);

    }
    @Test
    void delete_product_an_error(){
        ProductRecordRequest request = buildProduct();
        var product = ProductMapper.toEntity(request);
        assertThrows(IllegalArgumentException.class, () -> productService.delete(null));
    }

    @Test
    void list_get_all_products(){
        List<Product> mockList = Collections.singletonList(mock(Product.class));
        when(repository.findAll()).thenReturn(mockList);

        List<ProductRecordResponse> response = productService.getAllProduct();
        // Assert
        assertThat(response).hasSize(1);
    }

    @Test
    void find_By_id(){
        var produtRequest = buildProduct();
        var product = ProductMapper.toEntity(produtRequest);

        //GIVEN
        when(repository.findById(anyString())).thenReturn(Optional.of(product));

        //WHEN
        var response = productService.getProductById(Mockito.anyString());

        //THEN
        assertNotNull(response);
        assertEquals(produtRequest.name(), response.name());
        assertEquals(produtRequest.description(), response.description());
        assertEquals(produtRequest.price(), response.price());
        assertEquals(produtRequest.amount(), response.amount());

    }

    @Test
    void getById_InvalidRequest_ThrowsException() {
        ProductRecordRequest request = new ProductRecordRequest("", "Description 1", 100.0, 10);
        assertThrows(RuntimeException.class, () -> productService.getProductById(null));
    }

    @Test
    public void testUpdate() {
        var productRequest = buildProduct();
        var existingProduct = ProductMapper.toEntity(productRequest);
        existingProduct.setId("existingId");

        var newRequest = new ProductRecordRequest("teste", "teste 1", 10.0, 1);
        var updatedProduct = ProductMapper.toEntity(newRequest);
        updatedProduct.setId(existingProduct.getId());

        // GIVEN
        when(repository.findById("existingId")).thenReturn(Optional.of(existingProduct));
        when(repository.save(any(Product.class))).thenReturn(updatedProduct);

        // WHEN
        var productResponse = productService.updateProduct(newRequest, "existingId");

        // THEN
        assertNotNull(productResponse);
        assertEquals(newRequest.name(), productResponse.name());
        assertEquals(newRequest.description(), productResponse.description());
        assertEquals(newRequest.price(), productResponse.price());
        assertEquals(newRequest.amount(), productResponse.amount());
    }


    public ProductRecordRequest buildProduct(){
        var product = new ProductRecordRequest("Product 1", "Description 1", 100.0, 10);
        return product;
    }
}

