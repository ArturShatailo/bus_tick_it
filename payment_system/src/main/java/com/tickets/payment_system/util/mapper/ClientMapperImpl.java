package com.tickets.payment_system.util.mapper;

import com.tickets.payment_system.domain.Client;
import com.tickets.payment_system.domain.ClientDTO;

public class ClientMapperImpl implements ClientMapper{

    @Override
    public ClientDTO toAreaViewDTO(Client object) {

        if (object == null) return null;

        ClientDTO clientDTO = new ClientDTO();
        clientDTO.email = object.getEmail();
        clientDTO.name = object.getName();
        clientDTO.surname = object.getSurname();
        clientDTO.father_name = object.getFather_name();

        return clientDTO;

    }

    @Override
    public Client toObject(ClientDTO dto) {

        if (dto == null) return null;

        Client client = new Client();
        client.setEmail(dto.email);
        client.setName(dto.name);
        client.setSurname(dto.surname);
        client.setFather_name(dto.father_name);

        return client;
    }
}
