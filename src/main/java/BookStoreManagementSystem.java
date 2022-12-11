import DAO.CategoryDAO;
import DTO.CategoryDTO;
import DTO.BookDTO;
import DAO.BookDAO;
import java.util.List;

public class BookStoreManagementSystem {
    public static void main(String[] args){
        List<BookDTO>  list = BookDAO.getBookList();
        for (BookDTO i : list){
            System.out.println(i.toString());
        }
    }
}
