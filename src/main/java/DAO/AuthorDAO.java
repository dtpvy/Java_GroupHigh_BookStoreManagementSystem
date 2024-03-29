package DAO;

import DTO.AuthorDTO;

import DTO.PublisherDTO;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;

public class AuthorDAO {
    private static List<AuthorDTO> authorList;

    public static List<AuthorDTO> getAuthorList(){
        Session session = SessionGet.getSessionFactory().openSession();
        try {
            String hql = "FROM AuthorDTO ORDER BY id ASC";
            Query query = session.createQuery(hql);
            authorList = query.list();
        } catch (HibernateException ex) {
            //Log the exception
            System.err.println(ex);
        } finally {
            session.close();
        }
        return authorList;
    }
    public static void addAuthor(AuthorDTO author){
        Session session = SessionGet.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(author);
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

    public static AuthorDTO getAuthorById(int id){
        AuthorDTO result = null;
        Session session = SessionGet.getSessionFactory().openSession();
        try {
            result = (AuthorDTO) session.get(AuthorDTO.class, id);
        } catch (HibernateException ex) {
            //Log the exception
            System.err.println(ex);
            return null;
        } finally {
            session.close();
        }
        return result;
    }

    public static boolean removeAuthor(int id){
        Session session = SessionGet.getSessionFactory().openSession();
        AuthorDTO item = getAuthorById(id);
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

    public static boolean updateAuthor(AuthorDTO author){
        Session session = SessionGet.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(author);
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

    public static List<AuthorDTO> getAuthorList(String search, String sortType, String sort) {
        AuthorDTO result = null;
        Session session = SessionGet.getSessionFactory().openSession();
        try {
            String hql = "FROM AuthorDTO WHERE name LIKE :search ORDER BY " + sortType + " " + sort;
            Query query = session.createQuery(hql);
            query.setParameter("search", "%" + search + "%");
            System.out.println(query.list());
            authorList = query.list();
        } catch (HibernateException ex) {
            //Log the exception
            System.err.println(ex);
        } finally {
            session.close();
        }
        return authorList;
    }
}
