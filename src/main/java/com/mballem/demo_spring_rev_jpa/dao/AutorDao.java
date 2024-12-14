package com.mballem.demo_spring_rev_jpa.dao;

import com.mballem.demo_spring_rev_jpa.model.Autor;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class AutorDao {

    @PersistenceContext
    private EntityManager manager;

    @Transactional(readOnly = false)
    public void save(Autor autor){
        this.manager.persist(autor);
    }
}
