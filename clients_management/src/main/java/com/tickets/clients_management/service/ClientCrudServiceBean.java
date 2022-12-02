package com.tickets.clients_management.service;

import com.tickets.clients_management.domain.Client;
import com.tickets.clients_management.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class ClientCrudServiceBean implements CrudService<Client> {

    private final ClientRepository clientRepository;

    @Override
    public Client getById(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Can't find Client with id: " + id));
    }

    @Override
    public void update(Long id, Client client) {
        clientRepository.findById(id).map(
                p -> {
                    p.setName(client.getName());
                    p.setSurname(client.getSurname());
                    p.setFather_name(client.getFather_name());
                    p.setTickets(client.getTickets());
                    p.setPayments(client.getPayments());
                    p.setDeleted(client.getDeleted());
                    return clientRepository.save(p);
                }
        ).orElseThrow(() -> new NoSuchElementException("Can't find an Client with id = " + id));
    }

    @Override
    public Client create(Client client) {
        return clientRepository.save(client);
    }

    public List<Client> getAll() {
        return clientRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        Client client = clientRepository.findById(id)
                        .orElseThrow(() -> new NoSuchElementException("Can't find Client with id: " + id));
        client.setDeleted(true);
        clientRepository.save(client);
    }



}
