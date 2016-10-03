package app.daos;


import app.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDAO
{

    Optional<User> getUserByUsername(String username);
    boolean contains(User user);
    boolean addUserIfNotExists(User user);
    List<String> getAllUsernames();
    void updateUser(User user);
    boolean removeUserByUsername(String user);
}
