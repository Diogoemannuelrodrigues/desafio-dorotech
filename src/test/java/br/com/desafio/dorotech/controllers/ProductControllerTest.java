package br.com.desafio.dorotech.controllers;

import br.com.desafio.dorotech.entidade.request.ProductRecordRequest;
import br.com.desafio.dorotech.entidade.response.ProductRecordResponse;
import br.com.desafio.dorotech.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ProductService productService;

    private ProductRecordRequest productRecordRequest;
    private ProductRecordResponse productRecordResponse;

    @Test
    void shouldCreateNewProductSuccessfully() throws Exception {
        productRecordRequest = buildProduct();

        mockMvc.perform(post("/product")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(productRecordRequest)))
                .andExpect(status().isCreated());

        productRecordResponse = productService.createProduct(productRecordRequest);

        assertNotNull(productRecordResponse);
        assertEquals(productRecordResponse.name(), productRecordRequest.name());
        assertEquals(productRecordResponse.description(), productRecordRequest.description());
        assertNotNull(productRecordResponse.id());
    }

    public ProductRecordRequest buildProduct(){
        var product = new ProductRecordRequest("Product 1", "Description 1", 100.0, 10);
        return product;
    }

}
