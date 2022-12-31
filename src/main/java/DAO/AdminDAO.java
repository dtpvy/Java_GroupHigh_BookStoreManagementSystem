package DAO;

import DTO.AdminDTO;
import DTO.AccountDTO;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;

public class AdminDAO {
    private static List<AdminDTO> adminList;
    public static List<AdminDTO> getAdminList(){
        Session session = SessionGet.getSessionFactory().openSession();
        try {
            String hql = "FROM AdminDTO ORDER BY id ASC";
            Query query = session.createQuery(hql);
            adminList = query.list();
        } catch (HibernateException ex) {
            //Log the exception
            System.err.println(ex);
        } finally {
            session.close();
        }
        return adminList;
    }

    public static void addAdmin(AdminDTO admin){
        Session session = SessionGet.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(admin);
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

    public static AdminDTO getAdminById(int id){
        AdminDTO result = null;
        Session session = SessionGet.getSessionFactory().openSession();
        try {
            result = (AdminDTO) session.get(AdminDTO.class, id);
        } catch (HibernateException ex) {
            //Log the exception
            System.err.println(ex);
            return null;
        } finally {
            session.close();
        }
        return result;
    }

    public static boolean removeAdmin(int id){
        Session session = SessionGet.getSessionFactory().openSession();
        AdminDTO item = getAdminById(id);
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

    public static boolean updateAdmin(AdminDTO admin){
        Session session = SessionGet.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(admin);
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
