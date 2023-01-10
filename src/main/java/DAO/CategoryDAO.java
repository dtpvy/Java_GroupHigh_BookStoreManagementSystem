package DAO;

import DTO.CategoryDTO;

import DTO.EmployeeDTO;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class CategoryDAO {
    private static List<CategoryDTO> categoryList;

    public static List<CategoryDTO> getCategoryList(){
        Session session = SessionGet.getSessionFactory().openSession();
        try {
            String hql = "FROM CategoryDTO ORDER BY id ASC";
            Query query = session.createQuery(hql);
            categoryList = query.list();
        } catch (HibernateException ex) {
            //Log the exception
            System.err.println(ex);
        } finally {
            session.close();
        }
        return categoryList;
    }

    public static void addCategory(CategoryDTO category){
        Session session = SessionGet.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(category);
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

    public static CategoryDTO getCategoryById(int id){
        CategoryDTO result = null;
        Session session = SessionGet.getSessionFactory().openSession();
        try {
            result = (CategoryDTO) session.get(CategoryDTO.class, id);
        } catch (HibernateException ex) {
            //Log the exception
            System.err.println(ex);
            return null;
        } finally {
            session.close();
        }
        return result;
    }

    public static boolean removeCategory(int id){
        Session session = SessionGet.getSessionFactory().openSession();
        CategoryDTO item = getCategoryById(id);
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

    public static List<CategoryDTO> getCategoryList(String search, String sortType, String sort){
        Session session = SessionGet.getSessionFactory().openSession();
        try {
            String hql = "FROM CategoryDTO WHERE name LIKE :search ORDER BY " + sortType + " " + sort;
            Query query = session.createQuery(hql);
            query.setParameter("search", "%" + search + "%");
            System.out.println(query.list());
            categoryList = query.list();
        } catch (HibernateException ex) {
            //Log the exception
            System.err.println(ex);
        } finally {
            session.close();
        }
        return categoryList;
    }

    public static boolean updateCategory(CategoryDTO category){
        Session session = SessionGet.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(category);
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