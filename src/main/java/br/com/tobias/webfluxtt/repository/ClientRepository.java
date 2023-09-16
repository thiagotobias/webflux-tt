package br.com.tobias.webfluxtt.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import br.com.tobias.webfluxtt.model.Client;
import reactor.core.publisher.Mono;

@Repository
public interface ClientRepository extends ReactiveMongoRepository<Client, String>{

    Mono<Void> deleteByEmail(String email);

	Mono<Client> findByEmail(String email);
}
