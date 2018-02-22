package com.cash.examen.dao;

import com.cash.examen.domain.Client;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;


@Transactional
@Repository
public class ClientDAOImpl extends BaseDAO implements ClientDAO  {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<Client> getAllClients() {
        return getAll(Client.class);
    }

    @Override
    public Client getClient(int clientId) {
        return get(Client.class, (long) clientId);
    }

    @Override
    public void addClient(Client client) {
        save(client);
    }

    @Override
    public void updateClient(Client client) {
        saveOrUpdate(client);
    }

    @Override
    public void deleteClient(Client client) {
        delete(client);
    }
}
