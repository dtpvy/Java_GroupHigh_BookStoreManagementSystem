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
    public static void addBook(BookDTO book){
        Session session = SessionGet.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(book);
            transaction.commit();
            //System.out.println("New item id: " + item.getId());
        } catch (HibernateException ex) {
            //Log the exception
            transaction.rollback();
            System.err.println(ex);
        } finally {
            session.close();
        }
    }

    public static BookDTO getBookById(String id){
        BookDTO result = null;
        Session session = SessionGet.getSessionFactory().openSession();
        try {
            result = (BookDTO) session.get(BookDTO.class, id);
        } catch (HibernateException ex) {
            //Log the exception
            System.err.println(ex);
            return null;
        } finally {
            session.close();
        }
        return result;
    }

    public static boolean removeBook(String id){
        Session session = SessionGet.getSessionFactory().openSession();
        BookDTO item = getBookById(id);
        if (item == null) {
            return false;
        }
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(item);
            transaction.commit();
        } catch (HibernateException ex) {
            //Log the exception
            transaction.rollback();
            System.err.println(ex);
            return false;
        } finally {
            session.close();
        }
        return true;
    }

    public static boolean updateBook(BookDTO book){
        Session session = SessionGet.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(book);
            transaction.commit();
        } catch (HibernateException ex) {
            //Log the exception
            transaction.rollback();
            System.err.println(ex);
            return false;
        } finally {
            session.close();
        }
        return true;
    }
}
