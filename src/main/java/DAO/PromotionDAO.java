package DAO;

import DTO.PromotionDTO;
import DTO.PromotionBookDTO;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.Set;

import java.util.List;

public class PromotionDAO {
    private static List<PromotionDTO> promotionList;

    public static List<PromotionDTO> getPromotionList(String search, String sortType, String sort){
        Session session = SessionGet.getSessionFactory().openSession();
        try {
            String hql = "FROM PromotionDTO Where code LIKE :code ORDER BY " + sortType + " " + sort;
            Query query = session.createQuery(hql);
            query.setParameter("code", "%" + search + "%");
            promotionList = query.list();
        } catch (HibernateException ex) {
            //Log the exception
            System.err.println(ex);
        } finally {
            session.close();
        }
        return promotionList;
    }

    public static void addPromotion(PromotionDTO promotion){
        Session session = SessionGet.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Set<PromotionBookDTO> books = promotion.getBookApplied();
            for (PromotionBookDTO b : books) session.save(b);
            session.save(promotion);
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

    public static PromotionDTO getPromotionById(int id){
        PromotionDTO result = null;
        Session session = SessionGet.getSessionFactory().openSession();
        try {
            result = (PromotionDTO) session.get(PromotionDTO.class, id);
        } catch (HibernateException ex) {
            //Log the exception
            System.err.println(ex);
            return null;
        } finally {
            session.close();
        }
        return result;
    }

    public static boolean removePromotion(int id){
        Session session = SessionGet.getSessionFactory().openSession();
        PromotionDTO item = getPromotionById(id);
        if (item == null) {
            return false;
        }
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            String hql = "DELETE FROM PromotionBookDTO P WHERE P.promotion.id = :promotion_id";
            Query q = session.createQuery(hql);
            q.setParameter("promotion_id", item.getId());
            q.executeUpdate();
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

    public static boolean updatePromotion(PromotionDTO promotion){
        Session session = SessionGet.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            String hql = "DELETE FROM PromotionBookDTO P WHERE P.promotion.id = :promotion_id";
            Query q = session.createQuery(hql);
            q.setParameter("promotion_id", promotion.getId());
            q.executeUpdate();
            Set<PromotionBookDTO> books = promotion.getBookApplied();
            for (PromotionBookDTO b : books) session.save(b);
            session.update(promotion);
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
