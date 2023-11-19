package com.amsoft.appsalon.repositories;

import java.util.List;
import java.util.UUID;

import com.amsoft.appsalon.models.User;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@RequestScoped
public class UserRepositoryImpl implements UserRepository {
    @Inject
    private EntityManager em;

    @Override
    @Transactional
    public User save(User obj) {
        try {
            if (obj.getUid() != null) {
                em.merge(obj);
            } else {
                obj.setUid(UUID.randomUUID().toString());
                em.persist(obj);
            }
            return obj;
        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    public User findById(String id) {
        return em.find(User.class, id);
    }

    @Override
    public List<User> findAll() {
        return em.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    public void deleteById(String id) {
        User user = em.find(User.class, id);
        em.remove(user);
    }

    @Override
    public User findByEmail(String email) {
        try {
            TypedQuery<User> query = em.createQuery("select u from User u where u.email = :email", User.class);
            User user = query.setParameter("email", email).getSingleResult();
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

}
