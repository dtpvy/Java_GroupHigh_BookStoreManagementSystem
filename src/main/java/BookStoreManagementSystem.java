import DTO.*;
import DAO.*;
import GUI.App;

import java.sql.Timestamp;
import java.util.List;

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
//
//        List<BookDTO>  list = BookDAO.getBookList();
//        System.out.println(cat + "\n" + aut + "\n" + pub);
//        System.out.println(list);
    }
}
