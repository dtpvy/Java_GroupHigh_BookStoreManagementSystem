package DAO;

import DTO.CustomerDTO;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;

public class CustomerDAO {
    private static List<CustomerDTO> customerList;

    public static List<CustomerDTO> getCustomerList(){
        Session session = SessionGet.getSessionFactory().openSession();
        try {
            String hql = "FROM CustomerDTO ORDER BY id ASC";
            Query query = session.createQuery(hql);
            customerList = query.list();
        } catch (HibernateException ex) {
            //Log the exception
            System.err.println(ex);
        } finally {
            session.close();
        }
        return customerList;
    }
    public static void addCustomer(CustomerDTO customer){
        Session session = SessionGet.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(customer);
            transaction.commit();
            //System.out.println("New item id: " + item.getId());
        } catch (HibernateException ex) {
            //Log the exception
            assert transaction != null;
            transaction.rollback();
            System.err.println(ex);
        } finally {
            session.close();
        }
    }

    public static CustomerDTO getCustomerById(int id){
        CustomerDTO result;
        Session session = SessionGet.getSessionFactory().openSession();
        try {
            result = (CustomerDTO) session.get(CustomerDTO.class, id);
        } catch (HibernateException ex) {
            //Log the exception
            System.err.println(ex);
            return null;
        } finally {
            session.close();
        }
        return result;
    }

    public static boolean removeCustomer(int id){
        Session session = SessionGet.getSessionFactory().openSession();
        CustomerDTO item = getCustomerById(id);
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
            assert transaction != null;
            transaction.rollback();
            System.err.println(ex);
            return false;
        } finally {
            session.close();
        }
        return true;
    }

    public static boolean updateCustomer(CustomerDTO customer){
        Session session = SessionGet.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(customer);
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
