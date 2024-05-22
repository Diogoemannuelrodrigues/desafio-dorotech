package br.com.desafio.dorotech.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class ProductNotFoundException extends GlobalExceptionHandler{

    private String productId;

    public ProductNotFoundException(String productId){
        this.productId = productId;
    }

    @Override
    public ProblemDetail toProblemDetail() {
        var pb = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);

        pb.setTitle("Product not found");
        pb.setDetail("There is no product with id " + productId + ".");

        return pb;
    }
}
