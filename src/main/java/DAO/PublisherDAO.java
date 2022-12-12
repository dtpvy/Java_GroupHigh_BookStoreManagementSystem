package DAO;

import DTO.PublisherDTO;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;

public class PublisherDAO {
    private static List<PublisherDTO> publisherList;

    public static List<PublisherDTO> getPublisherList(){
        Session session = SessionGet.getSessionFactory().openSession();
        try {
            String hql = "FROM PublisherDTO ORDER BY id ASC";
            Query query = session.createQuery(hql);
            publisherList = query.list();
        } catch (HibernateException ex) {
            //Log the exception
            System.err.println(ex);
        } finally {
            session.close();
        }
        return publisherList;
    }
    public static void addPublisher(PublisherDTO publisher){
        Session session = SessionGet.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(publisher);
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

    public static PublisherDTO getPublisherById(String id){
        PublisherDTO result = null;
        Session session = SessionGet.getSessionFactory().openSession();
        try {
            result = (PublisherDTO) session.get(PublisherDTO.class, id);
        } catch (HibernateException ex) {
            //Log the exception
            System.err.println(ex);
            return null;
        } finally {
            session.close();
        }
        return result;
    }

    public static boolean removePublisher(String id){
        Session session = SessionGet.getSessionFactory().openSession();
        PublisherDTO item = getPublisherById(id);
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

    public static boolean updatePublisher(PublisherDTO publisher){
        Session session = SessionGet.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(publisher);
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
