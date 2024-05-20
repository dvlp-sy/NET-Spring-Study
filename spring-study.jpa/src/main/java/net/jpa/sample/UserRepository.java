package net.jpa.sample;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository
{
    @PersistenceContext
    private EntityManager em;

    public Long save(Users users)
    {
        em.persist(users);
        return users.getId();
    }

    public Users find(Long id)
    {
        return em.find(Users.class, id);
    }

}
