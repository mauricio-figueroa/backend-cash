package com.cash.examen.dao;

import com.cash.examen.domain.Client;
import com.google.common.collect.ImmutableList;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;


@Transactional
@Repository
public class ClientDAOImpl implements ClientDAO {

    @PersistenceContext
    private EntityManager entityManager;


    //TODO revisar esta query, tratar de hacer con criteria
    @Override
    public List<Client> getAllClients() {
      /*  entityManager.getCriteriaBuilder()
        return getAll(Client.class);
        */
      return ImmutableList.of();
    }

    @Override
    public Client getClient(int clientId) {
        return entityManager.find(Client.class, clientId);
    }

    @Override
    public void addClient(Client client) {

    }

    @Override
    public void updateClient(Client client) {
        Client clientDb = getClient(client.getId());
        clientDb.setEmail(clientDb.getEmail());
        clientDb.setFirst_name(clientDb.getFirst_name());
        clientDb.setLast_name(clientDb.getLast_name());
        entityManager.flush();
    }

    @Override
    public void deleteClient(Client client) {
        entityManager.remove(client);
    }


}
