import DTO.*;
import DAO.*;
import GUI.App;

import java.sql.Timestamp;
import java.util.List;
import java.util.Date;

public class BookStoreManagementSystem {
    public static void main(String[] args){

        App app = new App();
        app.loadApp();
//        BookDTO book = BookDAO.getBookById("001");
//        CategoryDTO cat = CategoryDAO.getCategoryById("001");
//        AuthorDTO aut = AuthorDAO.getAuthorById("001");
//        PublisherDTO pub = PublisherDAO.getPublisherById("001");

//        System.out.println(cat);

//        Timestamp ti = new Timestamp(2022, 02, 11, 00, 00, 00, 00);
//        BookDTO book1 = new BookDTO("002", cat, pub, aut, "testbook2", "random description", 50.0, 10, ti, ti);
//        BookDTO book2 = new BookDTO("003", cat, pub, aut, "testbook3", "random description", 50.0, 10, ti, ti);
//        book.setDescription("random description");
//
//        BookDAO.updateBook(book);
//        BookDAO.addBook(book1);
//        BookDAO.addBook(book2);

//        BookDAO.removeBook("003");

        
        // BookDTO book = BookDAO.getBookById(1);
        // System.out.println(book);
        Date date = new Date();
        Timestamp cur_date = new Timestamp(date.getTime());
        // BookDTO book = new BookDTO(cat, pub, aut, "test book 1", "this is a test book", 25.5, 100, cur_date, cur_date);

        // BookDAO.addBook(book);

        // List<BookDTO>  list = BookDAO.getBookList();
        // System.out.println(cat + "\n" + aut + "\n" + pub);
        // System.out.println(list);

        // List<AdminDTO> admins = AdminDAO.getAdminList();
        // System.out.println(admins);


        // System.out.println(BLO.AccountBLO.checkUserLogin("khoildm", "25asdf02"));
        // CustomerDTO c = new CustomerDTO("khoi", "0778661298", cur_date, cur_date);
        // CustomerDAO.addCustomer(c);

        //BLO.EmployeeBLO.addAdmin("khoil", "25082002", "khoi le dang minh", "123456", "khoi@gmail.com", null, "ho chi minh", cur_date);
    }
}
