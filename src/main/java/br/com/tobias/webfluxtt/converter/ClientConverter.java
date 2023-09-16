package br.com.tobias.webfluxtt.converter;

import br.com.tobias.webfluxtt.dto.ClientDTO;
import br.com.tobias.webfluxtt.model.Client;

public class ClientConverter {

	public Client toClient(ClientDTO client) {
		return new Client(client.getName(), client.getAge(), client.getEmail());
	}

	public ClientDTO toClientDTO(Client client) {
		return new ClientDTO(client.getName(), client.getAge(), client.getEmail());
	}

}
