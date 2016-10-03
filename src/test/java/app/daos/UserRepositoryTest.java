package app.daos;

import app.config.RootConfig;
import app.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.Profile;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.Collections;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootConfig.class)
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UserRepositoryTest
{
    @Inject
    UserDAO dao;

    @PersistenceContext
    EntityManager em;

    @Test
    public void injectionDAOTest()
    {
        assertThat(dao,not(nullValue()));
    }


    @Test
    public void addUserTest()
    {
        User user = new User("John","Doe","johndoe123","johdoe123","john@gmail.com");
        dao.addUserIfNotExists(user);

        assertThat(em.find(User.class,user.getId()),not(nullValue()));
    }

    @Test
    public void containsIfExists() throws Exception
    {
         User user = new User("John","Doe","johndoe123","johdoe123","john@gmail.com");
         dao.addUserIfNotExists(user);

         assertThat(dao.contains(user),equalTo(true));

    }

    @Test
    public void containsIfNotExists()
    {
        User user = new User("Carolyn","Darley","Reated1954","wahQueeZ0it8","CarolynPDarley@teleworm.us");
        assertThat(dao.contains(user),equalTo(false));
    }



    @Test
    public void getUserByUsernameIfExists() throws Exception
    {
        User user = new User("Carolyn","Darley","Reated1954","wahQueeZ0it8","CarolynPDarley@teleworm.us");
        dao.addUserIfNotExists(user);
        assertThat(dao.getUserByUsername(user.getUsername()),equalTo(Optional.of(user)));

    }

    @Test
    public void getUserByUsernameIfNotExists()
    {
        User user = new User("Carolyn","Darley","Reated1954","wahQueeZ0it8","CarolynPDarley@teleworm.us");
        assertThat(dao.getUserByUsername(user.getUsername()),equalTo(Optional.empty()));
    }

    @Test
    public void addUserIfNotExists() throws Exception
    {
        User user = new User("Carolyn","Darley","Reated1954","wahQueeZ0it8","CarolynPDarley@teleworm.us");
        assertThat(dao.addUserIfNotExists(user),equalTo(true));
    }


    @Test
    public void addUserIfExistsTest()
    {
        User user = new User("Carolyn","Darley","Reated1954","wahQueeZ0it8","CarolynPDarley@teleworm.us");
        dao.addUserIfNotExists(user);
        assertThat(dao.addUserIfNotExists(user),equalTo(false));
    }


    @Test
    public void getUsernamesFromEmptyDB()
    {
        assertThat(dao.getAllUsernames(),equalTo(Collections.EMPTY_LIST));
    }

    @Test
    public void getUsernamesFromDatabase()
    {
        User user = new User("Carolyn","Darley","Reated1954","wahQueeZ0it8","CarolynPDarley@teleworm.us");
        User user1 = new User("Jane","Doe","janedoe123","janeeec","janedoe@gmail.com");

        dao.addUserIfNotExists(user);
        dao.addUserIfNotExists(user1);

        assertThat(dao.getAllUsernames().size(),equalTo(2));

    }

    @Test
    public void updateUserTest()
    {
        User user = new User("Carolyn","Darley","Reated1954","wahQueeZ0it8","CarolynPDarley@teleworm.us");
        dao.addUserIfNotExists(user);
        user.getDetails().setAge(22);
        dao.updateUser(user);
        assertThat(dao.getUserByUsername(user.getUsername()).get(),equalTo(user));
    }

    @Test
    public void removeByUsernameIfNotExistsTest()
    {
        User user = new User("Carolyn","Darley","Reated1954","wahQueeZ0it8","CarolynPDarley@teleworm.us");
        assertThat(dao.removeUserByUsername(user.getUsername()),equalTo(false));
    }

    @Test
    public void removeByUsernameIfExistsTest()
    {
        User user = new User("Carolyn","Darley","Reated1954","wahQueeZ0it8","CarolynPDarley@teleworm.us");
        dao.addUserIfNotExists(user);
        assertThat(dao.contains(user),equalTo(true));
        assertThat(dao.removeUserByUsername(user.getUsername()),equalTo(true));
        assertThat(dao.contains(user),equalTo(false));
    }

}