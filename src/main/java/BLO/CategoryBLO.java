package BLO;

import DAO.CategoryDAO;
import DAO.EmployeeDAO;
import DTO.CategoryDTO;
import DTO.EmployeeDTO;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CategoryBLO {
    public static void updateCategoryInfo(CategoryDTO category){
        Date date = new Date();
        Timestamp cur_date = new Timestamp(date.getTime());
        category.setUpdatedAt(cur_date);
        CategoryDAO.updateCategory(category);
    }

    public static void disableCategory(CategoryDTO category){
        category.setDisable(false);
    }

    public static void enableCategory(CategoryDTO category){
        category.setDisable(true);
    }

    public static CategoryDTO addCategory(String name, boolean disable){
        Date date = new Date();
        Timestamp cur_date = new Timestamp(date.getTime());
        CategoryDTO category = new CategoryDTO(name, cur_date, cur_date, disable);
        CategoryDAO.addCategory(category);
        return category;
    }

    public static List<CategoryDTO> getCategoryList(String search, String sortType, String sort){
        return CategoryDAO.getCategoryList(search, sortType.toLowerCase(), sort);
    }
}
