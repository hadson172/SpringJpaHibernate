package app.daos;

import app.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.persistence.*;
import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository implements UserDAO
{

    @PersistenceContext
    private EntityManager em;





    @Transactional
    public boolean contains(User user)
    {
        return getUserByUsername(user.getUsername()).isPresent();
    }


    @Override
    @Transactional
    public Optional<User> getUserByUsername(String username)
    {
        TypedQuery <User> query = em.createQuery("SELECT u FROM User u WHERE u.username = :username",User.class)
                .setParameter("username",username);

        List<User> resultList = query.getResultList();



        return resultList.size() == 1 ? Optional.of(resultList.get(0)) : Optional.empty();
    }

    @Transactional
    @Override
    public boolean addUserIfNotExists(User user)
    {
        if(contains(user)) return false;
        else
        {
            em.persist(user);
            return true;
        }
    }

    @Override
    @Transactional
    public List<String> getAllUsernames()
    {
        return em.createQuery("SELECT u.username FROM User u",String.class).getResultList();
    }

    @Override
    @Transactional
    public void updateUser(User user)
    {

        em.merge(user);
    }

    @Override
    @Transactional
    public boolean removeUserByUsername(String username)
    {
        Optional<User> optUser = getUserByUsername(username);
        if(optUser.isPresent())
        {
            em.remove(optUser.get());
            return true;
        }

        return false;

    }
}
