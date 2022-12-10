package DAO;

import DTO.CategoryDTO;
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
}