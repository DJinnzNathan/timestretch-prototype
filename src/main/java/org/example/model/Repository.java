package org.example.model;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Repository {
    private EntityManagerFactory emf;


    public Repository(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public List<Task> findAll() throws SQLException {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Task> q = em.createQuery("select e from Task e", Task.class);
        List<Task> emps = q.getResultList();
        em.close();
        return emps;
    }

}
