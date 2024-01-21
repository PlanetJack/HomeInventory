package services;

import dataaccess.ItemDB;
import java.util.List;
import models.Category;
import models.Item;
import models.User;

/**
 *
 * @author Kihyun Kim
 */

public class ItemService {

    public List<Item> getAll(User user) throws Exception {
        ItemDB itemDB = new ItemDB();
        List<Item> items = itemDB.getAll(user);
        return items;
    }

    public Item get(int itemID) throws Exception {
        ItemDB itemDB = new ItemDB();
        Item item = itemDB.get(itemID);
        return item;
    }

    public void insert(int itemID, Category category, String itemName, double price, User user) throws Exception {
        Item item = new Item(itemID, category, itemName, price, user);
        ItemDB itemDB = new ItemDB();
        itemDB.insert(item);
    }

    public void update(int itemID, Category category, String itemName, double price, User user) throws Exception {
        Item item = new Item(itemID, category, itemName, price, user);
        ItemDB itemDB = new ItemDB();
        itemDB.update(item);
    }

    public void delete(int itemID) throws Exception {
        ItemDB itemDB = new ItemDB();
        Item item = itemDB.get(itemID);
        itemDB.delete(item);
    }
}