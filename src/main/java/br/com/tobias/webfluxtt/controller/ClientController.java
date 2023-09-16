package br.com.tobias.webfluxtt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.tobias.webfluxtt.dto.ClientDTO;
import br.com.tobias.webfluxtt.dto.ResponseDTO;
import br.com.tobias.webfluxtt.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/clients")
public class ClientController {
	
	@Autowired
    private ClientService clientService;
	
	@PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    @Operation(description = "Create a client",
               requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody())
    public Mono<ResponseDTO> create(@RequestBody ClientDTO clientDTO) {
        return this.clientService.create(clientDTO);
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(description = "Find all clients")
    public Flux<ResponseDTO<ClientDTO>> getAll() {
        return this.clientService.getAll();
    }

    @GetMapping("{email}")
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(description = "Find by email of client")
    public Mono<ResponseDTO<ClientDTO>> findByEmail(@PathVariable("email") String email) {
        return this.clientService.findByEmail(email);
    }

    @PutMapping
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(description = "Update a client")
    public Mono<ResponseDTO> update(@RequestBody ClientDTO clientDTO){
        return this.clientService.update(clientDTO);
    }

    @DeleteMapping("{email}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public Mono<ResponseDTO> delete(@PathVariable("email") String email) {
        return this.clientService.delete(email);
    }

}
