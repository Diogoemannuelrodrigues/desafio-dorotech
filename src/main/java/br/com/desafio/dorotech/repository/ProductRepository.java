package br.com.desafio.dorotech.repository;

import br.com.desafio.dorotech.entidade.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
}
