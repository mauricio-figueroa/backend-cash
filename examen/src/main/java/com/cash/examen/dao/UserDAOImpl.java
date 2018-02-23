package com.cash.examen.dao;

import com.cash.examen.domain.User;
import com.google.common.collect.ImmutableList;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;


@Transactional
@Repository
public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    private EntityManager entityManager;


    //TODO revisar esta query, tratar de hacer con criteria
    @Override
    public List<User> getAllUsers() {
      /*  entityManager.getCriteriaBuilder()
        return getAll(Client.class);
        */
      return ImmutableList.of();
    }

    @Override
    public User getUser(long userId) {
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
    public void deleteUser(User userId) {
        entityManager.remove(userId);
    }


}
