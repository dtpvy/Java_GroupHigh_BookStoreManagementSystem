package DAO;

import DTO.BookDTO;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;

public class BookDAO {
    private static List<BookDTO> bookList;

    public static List<BookDTO> getBookList(){
        Session session = SessionGet.getSessionFactory().openSession();
        try {
            String hql = "FROM BookDTO ORDER BY id ASC";
            Query query = session.createQuery(hql);
            bookList = query.list();
        } catch (HibernateException ex) {
            //Log the exception
            System.err.println(ex);
        } finally {
            session.close();
        }
        return bookList;
    }
}
