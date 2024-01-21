package dataaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import models.Role;
import models.User;
import services.RoleService;

/**
 *
 * @author Kihyun Kim
 */
public class UserDB {

    RoleService roleService = new RoleService();

    public List<User> getAll() throws Exception {
        List<User> users = new ArrayList<>();
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection con = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM user";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                String email = rs.getString(1);
                boolean status = rs.getBoolean(2);
                String firstName = rs.getString(3);
                String lastName = rs.getString(4);
                String password = rs.getString(5);
                int roleId = rs.getInt(6);
                String roleName = roleService.getRoleById(roleId).getRoleName();
                Role role = new Role(roleId, roleName);
                User user = new User(email, status, firstName, lastName, password, role);
                users.add(user);
            }
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(con);
        }

        return users;
    }

    public User get(String inputEmail) throws Exception {
        User userItem = null;
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection con = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM user WHERE email=? ";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, inputEmail);

            rs = ps.executeQuery();
            while (rs.next()) {

                boolean status = rs.getBoolean(2);
                String firstName = rs.getString(3);
                String lastName = rs.getString(4);
                String password = rs.getString(5);

                int roleId = rs.getInt(6);
                String roleName = roleService.getRoleById(roleId).getRoleName();
                Role role = new Role(roleId, roleName);
                userItem = new User(inputEmail, status, firstName, lastName, password, role);
            }
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(con);
        }
        return userItem;
    }

    public User get(String inputEmail, String inputPassword) throws Exception {
        User user = null;
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection con = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM user WHERE email=? AND password=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, inputEmail);
            ps.setString(2, inputPassword);
            rs = ps.executeQuery();
            while (rs.next()) {

                boolean status = rs.getBoolean(2);
                String firstName = rs.getString(3);
                String lastName = rs.getString(4);

                int roleId = rs.getInt(6);
                String roleName = roleService.getRoleById(roleId).getRoleName();
                Role role = new Role(roleId, roleName);
                user = new User(inputEmail, status, firstName, lastName, inputPassword, role);
            }
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(con);
        }

        if (user != null && inputEmail.equals(user.getEmail()) && inputPassword.equals(user.getPassword())) {
            return user;
        } else {
            return null;
        }

    }

    public void insert(User user) throws Exception {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection con = pool.getConnection();
        PreparedStatement ps = null;
        String sql = "INSERT INTO user(email,active,first_name, last_name, password, role) VALUES(?,?,?,?,?,?)";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, user.getEmail());
            ps.setBoolean(2, user.isActive());
            ps.setString(3, user.getFirstName());
            ps.setString(4, user.getLastName());
            ps.setString(5, user.getPassword());
            ps.setInt(6, roleService.getRoleId(user.getRole()));
            ps.executeUpdate();
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(con);
        }
    }

    public void update(User user) throws Exception {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection con = pool.getConnection();
        PreparedStatement ps = null;
        String sql = "UPDATE user SET email=?,active=?,first_name=?, last_name=?, password =?, role=? WHERE email=?";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, user.getEmail());
            ps.setBoolean(2, user.isActive());
            ps.setString(3, user.getFirstName());
            ps.setString(4, user.getLastName());
            ps.setString(5, user.getPassword());
            ps.setInt(6, roleService.getRoleId(user.getRole()));
            ps.setString(7, user.getEmail());
            ps.executeUpdate();
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(con);
        }
    }

    public void updateWithoutRole(User user) throws Exception {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection con = pool.getConnection();
        PreparedStatement ps = null;
        String sql = "UPDATE user SET email=?,active=?,first_name=?, last_name=?, password =? WHERE email=?";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, user.getEmail());
            ps.setBoolean(2, user.isActive());
            ps.setString(3, user.getFirstName());
            ps.setString(4, user.getLastName());
            ps.setString(5, user.getPassword());
            ps.setString(6, user.getEmail());
            ps.executeUpdate();
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(con);
        }
    }

    public void delete(User user) throws Exception {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection con = pool.getConnection();
        PreparedStatement ps = null;

        try {
            // 1. First, delete all items associated with the user
            String deleteItemsSql = "DELETE FROM item WHERE owner=?";
            ps = con.prepareStatement(deleteItemsSql);
            ps.setString(1, user.getEmail());
            ps.executeUpdate();

            // 2. Then, delete the user
            String deleteUserSql = "DELETE FROM user WHERE email=?";
            ps = con.prepareStatement(deleteUserSql);
            ps.setString(1, user.getEmail());
            ps.executeUpdate();
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(con);
        }
    }
}