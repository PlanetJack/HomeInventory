package models;

/**
 *
 * @author Kihyun Kim
 */
public class Item {

    private int itemId;
    private Category category;
    private String itemName;
    private double price;
    private User owner;

    public Item(int itemId, Category category, String itemName, double price, User owner) {
        this.itemId = itemId;
        this.category = category;
        this.itemName = itemName;
        this.price = price;
        this.owner = owner;
    }

    // Getter and Setter methods
    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
