package DAO;

import DTO.EmployeeDTO;
import DTO.EmployeeDTO;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;

public class EmployeeDAO {
    private static List<EmployeeDTO> employeeList;
    public static List<EmployeeDTO> getEmployeeList(){
        Session session = SessionGet.getSessionFactory().openSession();
        try {
            String hql = "FROM EmployeeDTO ORDER BY id ASC";
            Query query = session.createQuery(hql);
            employeeList = query.list();
        } catch (HibernateException ex) {
            //Log the exception
            System.err.println(ex);
        } finally {
            session.close();
        }
        return employeeList;
    }

    public static void addEmployee(EmployeeDTO admin){
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

    public static EmployeeDTO getEmployeeById(int id){
        EmployeeDTO result = null;
        Session session = SessionGet.getSessionFactory().openSession();
        try {
            result = (EmployeeDTO) session.get(EmployeeDTO.class, id);
        } catch (HibernateException ex) {
            //Log the exception
            System.err.println(ex);
            return null;
        } finally {
            session.close();
        }
        return result;
    }

    public static EmployeeDTO getEmployeeByUsername(String username){
        EmployeeDTO result = null;
        Session session = SessionGet.getSessionFactory().openSession();
        try {
            String hql = "FROM EmployeeDTO WHERE username=:username";
            Query query = session.createQuery(hql);
            query.setParameter("username", username);
            result = (EmployeeDTO) query.uniqueResult();
        } catch (HibernateException ex) {
            //Log the exception
            System.err.println(ex);
        } finally {
            session.close();
        }
        return result;
    }

    public static boolean removeEmployee(int id){
        Session session = SessionGet.getSessionFactory().openSession();
        EmployeeDTO item = getEmployeeById(id);
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

    public static boolean updateEmployee(EmployeeDTO employee){
        Session session = SessionGet.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(employee);
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
