package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Category;
import services.CategoryService;
import java.util.List;

/**
 *
 * @author Kihyun Kim
 */
public class CategoryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CategoryService categoryService = new CategoryService();
        try {
            List<Category> categories = categoryService.getAll();
            request.setAttribute("categories", categories);
            getServletContext().getRequestDispatcher("/WEB-INF/categories.jsp").forward(request, response);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        CategoryService categoryService = new CategoryService();

        try {
            if ("add".equals(action)) {
                String categoryName = request.getParameter("categoryName");
                categoryService.addCategory(categoryName);
              // Edit category list
            } else if ("setEditMode".equals(action)) {
                int categoryId = Integer.parseInt(request.getParameter("categoryId"));
                request.setAttribute("editCategoryId", categoryId);
                doGet(request, response);
                return;
            } else if ("edit".equals(action)) {
                int categoryId = Integer.parseInt(request.getParameter("categoryId"));
                String categoryName = request.getParameter("categoryName");
                categoryService.editCategory(categoryId, categoryName);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        response.sendRedirect("category");
    }
}
