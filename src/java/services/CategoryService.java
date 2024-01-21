package services;

import dataaccess.CategoryDB;
import java.util.List;
import models.Category;

/**
 *
 * Note: In the current implementation, category names are hard-coded based on category IDs.

 * @author Kihyun Kim
 */
public class CategoryService {

    // Retrieves all categories from the database.
    public List<Category> getAll() throws Exception {
        CategoryDB categoryDB = new CategoryDB();
        List<Category> categories = categoryDB.getAll();
        return categories;
    }

    public String get(int categoryID) throws Exception {
        CategoryDB categoryDB = new CategoryDB();
        Category category = categoryDB.get(categoryID);
        return category.getName();
    }
    
    public void addCategory(String categoryName) throws Exception {
        CategoryDB categoryDB = new CategoryDB();
        Category category = new Category(0, categoryName);
        categoryDB.insert(category);
    }

    public void editCategory(int categoryId, String categoryName) throws Exception {
        CategoryDB categoryDB = new CategoryDB();
        Category category = categoryDB.get(categoryId);
        category.setName(categoryName);
        categoryDB.update(category);
    }
}
