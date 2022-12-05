package com.tickets.payment_system.util.mapper;

import com.tickets.payment_system.domain.Client;
import com.tickets.payment_system.domain.dto.ClientDTO;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = ClientMapper.class, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ClientMapper {

    ClientDTO toDTO(Client object);

    Client toObject(ClientDTO dto);

}
