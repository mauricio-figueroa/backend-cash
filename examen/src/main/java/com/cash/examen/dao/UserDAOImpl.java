package com.cash.examen.dao;


import com.cash.examen.domain.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

@Transactional
@Repository
public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public User getUser(Integer userId) {
        return entityManager.find(User.class, userId);
    }

    @Override
    public void save(User user) {
        entityManager.persist(user);
    }

    @Override
    public void updateUser(User user) {
        User userToUpdate = getUser(user.getId());
        userToUpdate.setEmail(userToUpdate.getEmail());
        userToUpdate.setFirst_name(userToUpdate.getFirst_name());
        userToUpdate.setLast_name(userToUpdate.getLast_name());
        entityManager.flush();
    }

    @Override
    public void deleteUser(User user) {
        entityManager.remove(user);
    }


    @Override
    public boolean emailAlreadyRegistred(String email) {
        Query query = entityManager.createQuery("select first_name FROM user WHERE email=:email ")
                .setParameter("email", email);

        return !query.getResultList().isEmpty();
    }


}
