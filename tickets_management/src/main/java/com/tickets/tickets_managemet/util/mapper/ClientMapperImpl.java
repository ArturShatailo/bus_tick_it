package com.tickets.tickets_managemet.util.mapper;

import com.tickets.tickets_managemet.domain.Client;
import com.tickets.tickets_managemet.domain.dto.ClientDTO;
import org.springframework.stereotype.Component;

@Component
public class ClientMapperImpl implements ClientMapper{

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
