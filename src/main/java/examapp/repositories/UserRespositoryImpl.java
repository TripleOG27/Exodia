package examapp.repositories;

import examapp.domain.entities.User;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class UserRespositoryImpl implements UserRepository {
    private final EntityManager entityManager;
    private final ModelMapper mapper;

    @Inject
    public UserRespositoryImpl(EntityManager entityManager,ModelMapper mapper) {
        this.entityManager = entityManager;
        this.mapper = mapper;
    }


    @Override
    public User save(User user) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(user);
        this.entityManager.getTransaction().commit();
        return user;

    }

    @Override
    public List findAll() {
        this.entityManager.getTransaction().begin();
        List<User> users =  this.entityManager.createQuery("select u from User u",User.class).getResultList();
        this.entityManager.getTransaction().commit();
        return users;

    }

    @Override
    public User findById(String id) {
        this.entityManager.getTransaction().begin();
        User user =  this.entityManager.createQuery("select u from User u where u.id= :id",User.class)
                .setParameter("id",id).getSingleResult();
        this.entityManager.getTransaction().commit();
        return user;
    }

    @Override
    public User findByUsername(String username) {
        this.entityManager.getTransaction().begin();
        try {


            User user = this.entityManager.createQuery("select u from User u where u.username= :username", User.class)
                    .setParameter("username", username).getSingleResult();
            this.entityManager.getTransaction().commit();
            return user;
        }catch (Exception e){
            this.entityManager.getTransaction().commit();
            return null;
        }
    }
}
