package servlets;

import dataaccess.CategoryDB;
import dataaccess.ItemDB;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Category;
import models.Item;
import models.User;

/**
 *
 * @author Kihyun Kim
 */
public class InventoryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CategoryDB categoryDB = new CategoryDB();
        ItemDB itemDB = new ItemDB();
        String action = request.getParameter("action");

        try {
            List<Category> categories = categoryDB.getAll();
            request.setAttribute("categories", categories);

            User user = (User) request.getSession().getAttribute("user");
            List<Item> items = itemDB.getAll(user);
            request.setAttribute("items", items);

            if ("add".equals(action)) {
                getServletContext().getRequestDispatcher("/WEB-INF/addItem.jsp").forward(request, response);
                return;
            }

            if ("edit".equals(action)) {
                int itemId = Integer.parseInt(request.getParameter("itemId"));
                Item item = itemDB.get(itemId);
                request.setAttribute("selectedItem", item);
                getServletContext().getRequestDispatcher("/WEB-INF/editItem.jsp").forward(request, response);
                return;
            }

            getServletContext().getRequestDispatcher("/WEB-INF/inventory.jsp").forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute("errorMessage", "An error occurred while processing the request. Details: " + ex.getMessage());
            getServletContext().getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        String action = request.getParameter("action");
        ItemDB itemDB = new ItemDB();
        CategoryDB categoryDB = new CategoryDB();

        try {
            if ("add".equals(action)) {
                int categoryId = Integer.parseInt(request.getParameter("category"));
                Category category = categoryDB.get(categoryId);
                String name = request.getParameter("name");

                // Item price must not be 0 or below
                double price = Double.parseDouble(request.getParameter("price"));
                if (price <= 0) {
                    List<Category> categories = categoryDB.getAll();
                    request.setAttribute("categories", categories);

                    request.setAttribute("errorMessage", "Price cannot be 0 or negative.");
                    getServletContext().getRequestDispatcher("/WEB-INF/addItem.jsp").forward(request, response);
                    return;
                }
                Item item = new Item(0, category, name, price, user);
                itemDB.insert(item);
            } else if ("edit".equals(action)) {
                int itemId = Integer.parseInt(request.getParameter("itemId"));
                int categoryId = Integer.parseInt(request.getParameter("category"));
                Category category = categoryDB.get(categoryId);
                String name = request.getParameter("name");

                double price = Double.parseDouble(request.getParameter("price"));
                // Item price must not be 0 or below
                if (price <= 0) {
                    List<Category> categories = categoryDB.getAll();
                    request.setAttribute("categories", categories);

                    request.setAttribute("errorMessage", "Price cannot be 0 or negative");
                    request.setAttribute("selectedItem", new Item(itemId, category, name, price, user));
                    getServletContext().getRequestDispatcher("/WEB-INF/editItem.jsp").forward(request, response);
                    return;
                }
                Item item = new Item(itemId, category, name, price, user);
                itemDB.update(item);
            } else if ("delete".equals(action)) {
                int itemId = Integer.parseInt(request.getParameter("itemId"));
                Item item = itemDB.get(itemId);
                if (item.getOwner().getEmail().equals(user.getEmail())) {
                    itemDB.delete(item);
                }
            }
            response.sendRedirect("inventory");
        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute("errorMessage", "An error occurred while processing the request. Details: " + ex.getMessage());
            getServletContext().getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
        }
    }
}
