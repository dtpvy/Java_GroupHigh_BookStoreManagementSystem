import DAO.CategoryDAO;
import DTO.CategoryDTO;
import java.util.List;

public class BookStoreManagementSystem {
    public static void main(String[] args){
        List<CategoryDTO>  list = CategoryDAO.getCategoryList();
        for (CategoryDTO i : list){
            System.out.println(i.toString());
        }
    }
}
