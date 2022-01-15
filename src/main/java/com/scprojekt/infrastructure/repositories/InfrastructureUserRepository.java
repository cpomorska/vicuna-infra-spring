package com.scprojekt.infrastructure.repositories;

import com.scprojekt.domain.interfaces.UserRepository;
import com.scprojekt.domain.entities.User;
import com.scprojekt.domain.entities.UserType;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Repository
@Transactional
public class InfrastructureUserRepository implements UserRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<User> findAll() {
        Query query = em.createQuery(" SELECT u from User u");
        return query.getResultList();
    }

    @Override
    public User findById(long id) {
        return em.find(User.class,id);
    }

    @Override
    public void createEntity(User entity) {
        em.merge(entity);
        em.flush();
    }

    @Override
    public void removeEntity(User entity) {
        em.remove(entity);
    }

    @Override
    public void updateEntity(User entity) {
        em.merge(entity);
    }

    @Override
    public User findByUUID(UUID uuid) {
        Query query = em.createQuery(" SELECT u from User u WHERE u.userNumber := usernumber");
        query.setParameter("usernumber", uuid);
        return (User) query.getSingleResult();
    }

    @Override
    public List<User> findByType(UserType type) {
        Query query = em.createQuery(" SELECT u from User u WHERE :type MEMBER of u.userType");
        query.setParameter("type", type);
        return query.getResultList();
    }

    @Override
    public List<User> findByName(String name) {
        Query query = em.createQuery(" SELECT u from User u WHERE u.userName := username");
        query.setParameter("username", name);
        return query.getResultList();
    }

    @Override
    public List<User> findByDesription(String description) {
        Query query = em.createQuery(" SELECT u from User u WHERE u.userDescription := userdescription");
        query.setParameter("userdescription", description);
        return query.getResultList();
    }
}
