package br.com.desafio.dorotech.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class ProductNotCanBeNullException extends GlobalExceptionHandler {

    private final String detail;
    public ProductNotCanBeNullException(String detail) {
        this.detail = detail;
    }

    @Override
    public ProblemDetail toProblemDetail() {
        var pb = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);

        pb.setTitle("Product cannot be null");
        pb.setDetail(detail);

        return pb;
    }
}
