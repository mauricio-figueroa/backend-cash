package com.cash.examen.dao;

import com.cash.examen.domain.Loan;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public class LoansDAOImpl {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Loan> getLoans(Integer limit, Integer offset){
        Query query = entityManager.createQuery("FROM loan")
                .setMaxResults(limit)
                .setFirstResult(offset);
        return query.getResultList();
    }

    public Integer getTotal(Integer limit, Integer offset){
        Query query = entityManager.createQuery("select count(*) FROM loan");
        return new Integer(query.getFirstResult());
    }

}
