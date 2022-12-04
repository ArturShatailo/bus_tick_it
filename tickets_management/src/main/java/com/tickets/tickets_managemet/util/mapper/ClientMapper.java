package com.tickets.tickets_managemet.util.mapper;


import com.tickets.tickets_managemet.domain.Client;
import com.tickets.tickets_managemet.domain.dto.ClientDTO;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = ClientMapper.class, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ClientMapper {

    Client toObject(ClientDTO dto);

}
