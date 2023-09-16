package br.com.tobias.webfluxtt.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tobias.webfluxtt.converter.ClientConverter;
import br.com.tobias.webfluxtt.dto.ClientDTO;
import br.com.tobias.webfluxtt.dto.ResponseDTO;
import br.com.tobias.webfluxtt.model.Client;
import br.com.tobias.webfluxtt.repository.ClientRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ClientService {
	
	@Autowired
    private ClientConverter clientConverter;

    @Autowired
    private ClientRepository clientRepository;

	public Mono<ResponseDTO> create(ClientDTO clientDTO) {
        Client client = this.clientConverter.toClient(clientDTO);
        Mono<Client> clientMono = this.clientRepository.save(client);
        return clientMono
                .map((clientDocument) -> new ResponseDTO("Cliente cadastrado com sucesso!",
                        this.clientConverter.toClientDTO(clientDocument),
                        LocalDateTime.now()))
                .onErrorReturn(new ResponseDTO("Erro ao cadastrar cliente!",
                        new ClientDTO(),
                        LocalDateTime.now()));
	}

	public Flux<ResponseDTO<ClientDTO>> getAll() {
		Flux<Client> clientFlux = this.clientRepository.findAll();
        return clientFlux
                .map(client -> new ResponseDTO("Listagem de clientes retornada com sucesso!",
                                              this.clientConverter.toClientDTO(client),
                                              LocalDateTime.now()
        ));
	}

	public Mono<ResponseDTO<ClientDTO>> findByEmail(String email) {
		Mono<Client> clientMono = this.clientRepository.findByEmail(email);
		return clientMono.map(client -> new ResponseDTO("Busca por codigo retornada com sucesso!",
				this.clientConverter.toClientDTO(client), LocalDateTime.now()));
	}

	public Mono<ResponseDTO> update(ClientDTO clientDTO) {
		Mono<Client> clientMono = this.clientRepository.findByEmail(clientDTO.getEmail());

		return clientMono.flatMap((existingClient) -> {
			existingClient.setName(clientDTO.getName());
			existingClient.setAge(clientDTO.getAge());
			existingClient.setEmail(clientDTO.getEmail());
			return this.clientRepository.save(existingClient);
		}).map(client -> new ResponseDTO<>("Cliente alterado com sucesso!", this.clientConverter.toClientDTO(client),
				LocalDateTime.now()));
	}

	public Mono<ResponseDTO> delete(String email) {
		return this.clientRepository.deleteByEmail(email)
				.map((client) -> new ResponseDTO<>("Produto removido com sucesso!", null, LocalDateTime.now()));
	}

}
