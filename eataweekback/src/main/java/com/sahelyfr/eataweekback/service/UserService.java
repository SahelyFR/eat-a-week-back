package com.sahelyfr.eataweekback.service;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import com.sahelyfr.eataweekback.model.User;
import com.sahelyfr.eataweekback.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Data;

@Data
@Service
public class UserService {

    @Autowired
    UserRepository ur;

    EntityManager em;

    public UserService(EntityManager entityManager) {
        this.em = entityManager;
    }

    public void deleteUser(final Long id) {
        ur.deleteById(id);
    }

    @Transactional
    public User saveUser(User u) {
        em.persist(u);
        return u;
    }
}
