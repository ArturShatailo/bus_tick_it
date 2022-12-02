package com.tickets.tickets_managemet.util.mapper;


import com.tickets.tickets_managemet.domain.Client;
import com.tickets.tickets_managemet.domain.ClientDTO;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = ClientMapper.class, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ClientMapper {

    ClientDTO toAreaViewDTO(Client object);

    Client toObject(ClientDTO dto);

}
