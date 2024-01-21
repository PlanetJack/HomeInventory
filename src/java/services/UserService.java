package services;

import dataaccess.RoleDB;
import dataaccess.UserDB;
import java.util.List;
import models.Role;
import models.User;

public class UserService {

    private UserDB userDB = new UserDB();
    private RoleDB roleDB = new RoleDB();

    public List<User> getAll() throws Exception {
        return userDB.getAll();
    }

    public User get(String email) throws Exception {
        return userDB.get(email);
    }

    public Role getRoleById(int roleId) throws Exception {
        return roleDB.get(roleId);
    }

    public User authenticate(String email, String password) throws Exception {
        User user = userDB.get(email);

        if (user != null && user.getPassword().equals(password)) {
            return user;
        } else {
            return null;
        }
    }

    public int insert(User user) throws Exception {
        Role role = roleDB.get(user.getRole().getRoleId());
        userDB.insert(user);
        return 1;
    }

    public void update(String email, boolean active, String firstName, String lastName, String password, int roleId) throws Exception {
        Role currentRole = roleDB.get(roleId);
        User user = new User(email, active, firstName, lastName, password, currentRole);
        userDB.update(user);
    }

    public void delete(String email) throws Exception {
        userDB.delete(userDB.get(email));
    }

    public void activate(String email) throws Exception {
        User user = userDB.get(email);
        user.setActive(true);
        userDB.update(user);
    }

    public void deactivate(String email) throws Exception {
        User user = userDB.get(email);
        user.setActive(false);
        userDB.update(user);
    }

}
