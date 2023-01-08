package BLO;

import DAO.AuthorDAO;
import DTO.AuthorDTO;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class AuthorBLO {
    public static void updateAuthorInfo(AuthorDTO author) {
        Date date = new Date();
        Timestamp current_date = new Timestamp(date.getTime());
        author.setUpdatedAt(current_date);
        AuthorDAO.updateAuthor(author);
    }

    public static void disableAuthor(AuthorDTO author) {
        author.setDisable(false);
    }

    public static void enableAuthor(AuthorDTO author) {
        author.setDisable(true);
    }

    public static AuthorDTO addAuthor(String name, String description, Timestamp dob, Timestamp createdAt, Timestamp updatedAt, boolean disable) {
        Date date = new Date();
        Timestamp current_date = new Timestamp(date.getTime());
        AuthorDTO author = new AuthorDTO(name, description, dob, current_date, current_date, disable);
        AuthorDAO.addAuthor(author);
        return author;
    }

    public static List<AuthorDTO> getAuthorList(String search, String sortType, String sort) {
        return AuthorDAO.getAuthorList(search, sortType.toLowerCase(), sort);
    }
}


