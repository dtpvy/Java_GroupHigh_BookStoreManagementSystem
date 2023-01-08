package BLO;

import DAO.BookDAO;
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

    public static BookDTO addBook(CategoryDTO category, PublisherDTO publisher, AuthorDTO author, String name, String description, double price, int quantity, Timestamp createdAt, Timestamp updatedAt, boolean disable) {
        Date date = new Date();
        Timestamp current_date = new Timestamp(date.getTime());
        BookDTO book = new BookDTO(category, publisher, author, name, description, price, quantity, current_date, current_date, disable);
        BookDAO.addBook(book);
        return book;
    }

    public static List<BookDTO> getBookList(String search, String sortType, String sort) {
        return BookDAO.getBookList(search, sortType.toLowerCase(), sort);
    }
}


