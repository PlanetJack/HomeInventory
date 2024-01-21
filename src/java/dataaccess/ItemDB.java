package dataaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.Category;
import models.Item;
import models.User;

public class ItemDB {

    public List<Item> getAll(User user) throws Exception {
        if (user == null || user.getEmail() == null) {
            throw new IllegalArgumentException("User or user email is null.");
        }

        List<Item> items = new ArrayList<>();
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection con = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM item WHERE owner = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, user.getEmail());
            rs = ps.executeQuery();

            while (rs.next()) {
                int itemID = rs.getInt(1);
                int categoryID = rs.getInt(2);
                String itemName = rs.getString(3);
                double price = rs.getDouble(4);
                String ownerEmail = rs.getString(5);

                CategoryDB categoryDB = new CategoryDB();
                Category category = categoryDB.get(categoryID);
                UserDB userDB = new UserDB();
                User owner = userDB.get(ownerEmail);

                Item item = new Item(itemID, category, itemName, price, owner);
                items.add(item);
            }
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(con);
        }

        return items;
    }

    public Item get(int inputItemID) throws Exception {
        Item item = null;
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection con = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM item WHERE item_id=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, inputItemID);
            rs = ps.executeQuery();
            while (rs.next()) {
                int itemID = rs.getInt("item_id");
                int categoryID = rs.getInt("category");
                String ownerEmail = rs.getString("owner");

                CategoryDB categoryDB = new CategoryDB();
                Category category = categoryDB.get(categoryID);
                UserDB userDB = new UserDB();
                User owner = userDB.get(ownerEmail);

                String itemName = rs.getString("item_name");
                double price = rs.getDouble("price");
                item = new Item(itemID, category, itemName, price, owner);
            }
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(con);
        }

        return item;
    }

    public void insert(Item item) throws Exception {
        if (item == null || item.getCategory() == null || item.getOwner() == null) {
            throw new IllegalArgumentException("Item, category or owner is null.");
        }

        ConnectionPool pool = ConnectionPool.getInstance();
        Connection con = pool.getConnection();
        PreparedStatement ps = null;
        String sql = "INSERT INTO item(category,item_name,price,owner) VALUES(?,?,?,?)";

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, item.getCategory().getId());
            ps.setString(2, item.getItemName());
            ps.setDouble(3, item.getPrice());
            ps.setString(4, item.getOwner().getEmail());

            ps.executeUpdate();
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(con);
        }
    }

    public void update(Item item) throws Exception {
        if (item == null || item.getCategory() == null || item.getOwner() == null) {
            throw new IllegalArgumentException("Item, category or owner is null.");
        }

        ConnectionPool pool = ConnectionPool.getInstance();
        Connection con = pool.getConnection();
        PreparedStatement ps = null;
        String sql = "UPDATE item SET category=?,item_name=?, price=? WHERE item_id=?";

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, item.getCategory().getId());
            ps.setString(2, item.getItemName());
            ps.setDouble(3, item.getPrice());
            ps.setInt(4, item.getItemId());

            ps.executeUpdate();
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(con);
        }
    }

    public void delete(Item item) throws Exception {
        if (item == null) {
            throw new IllegalArgumentException("Item is null.");
        }

        ConnectionPool pool = ConnectionPool.getInstance();
        Connection con = pool.getConnection();
        PreparedStatement ps = null;
        String sql = "DELETE FROM item WHERE item_id=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, item.getItemId());
            ps.executeUpdate();
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(con);
        }
    }

    public static int updateItem(Item item) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = "UPDATE inventory SET category = ?, name = ?, price = ? WHERE id = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, item.getCategory().getId());
            ps.setString(2, item.getItemName());
            ps.setDouble(3, item.getPrice());
            ps.setInt(4, item.getItemId());

            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
}
