package BLO;

import DAO.AuthorDAO;
import DAO.BookDAO;
import DAO.CategoryDAO;
import DAO.PublisherDAO;
import DTO.AuthorDTO;
import DTO.BookDTO;
import DTO.CategoryDTO;
import DTO.PublisherDTO;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class BookBLO {
    public static void updateBookInfo(BookDTO book) {
        Date date = new Date();
        Timestamp current_date = new Timestamp(date.getTime());
        book.setUpdatedAt(current_date);
        BookDAO.updateBook(book);
    }

    public static void disableBook(BookDTO book) {
        book.setDisable(false);
    }

    public static void enableBook(BookDTO book) {
        book.setDisable(true);
    }

    public static BookDTO addBook(int categoryID, int publisherID, int authorID, String name, String description, double price, int quantity, boolean disable) {
        Date date = new Date();
        Timestamp current_date = new Timestamp(date.getTime());

        BookDTO book = new BookDTO(CategoryDAO.getCategoryById(categoryID), PublisherDAO.getPublisherById(publisherID), AuthorDAO.getAuthorById(authorID), name, description, price, quantity, current_date, current_date, disable);
        BookDAO.addBook(book);
        return book;
    }

    public static List<BookDTO> getBookList(String search, String sortType, String sort) {
        return BookDAO.getBookList(search, sortType.toLowerCase(), sort);
    }
}


