package br.com.desafio.dorotech.entidade.response;

import lombok.Builder;

@Builder
public record ProductRecordResponse(String id,
                                    String name,
                                    String description,
                                    Double price,
                                    Integer amount) {

}
