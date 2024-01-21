package services;

import java.util.List;
import models.Role;
import dataaccess.RoleDB;

/**
 *
 * @author Kihyun Kim
 */
public class RoleService {

    public List<Role> getAll() throws Exception {
        RoleDB roleDB = new RoleDB();
        List<Role> roles = roleDB.getAll();
        return roles;
    }

    public Role getRoleById(int id) throws Exception {
        RoleDB roleDB = new RoleDB();
        return roleDB.get(id);
    }

    public int getRoleId(Role role) throws Exception {
        return role.getRoleId();
    }

    public int get(Role role) throws Exception {
        String roleName = role.getRoleName();
        if (roleName.equals("system admin")) {
            return 1;
        } else {
            return 2;
        }
    }
}