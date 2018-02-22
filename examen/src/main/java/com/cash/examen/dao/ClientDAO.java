package com.cash.examen.dao;


import java.util.List;

import com.cash.examen.domain.Client;

public interface ClientDAO {

    List<Client> getAllClients();

    Client getClient(int client);

    void addClient(Client client);

    void updateClient(Client client);

    void deleteClient(Client client);


}
