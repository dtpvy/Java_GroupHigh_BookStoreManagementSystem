package DAO;

import DTO.OrderBookDTO;
import DTO.OrderDTO;
import DTO.OrderPromotionDTO;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.Set;

import java.util.List;

public class OrderDAO {
    private static List<OrderDTO> orderList;

    public static List<OrderDTO> getOrderList(){
        Session session = SessionGet.getSessionFactory().openSession();
        try {
            String hql = "FROM OrderDTO ORDER BY id ASC";
            Query query = session.createQuery(hql);
            orderList = query.list();
        } catch (HibernateException ex) {
            //Log the exception
            System.err.println(ex);
        } finally {
            session.close();
        }
        return orderList;
    }

    public static void addOrder(OrderDTO order){
        Session session = SessionGet.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Set<OrderBookDTO> items = order.getItems();
            Set<OrderPromotionDTO> promos = order.getPromotionApplied();
            for (OrderBookDTO i : items) session.save(i);
            for (OrderPromotionDTO p : promos) session.save(p);
            session.save(order);
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

    public static OrderDTO getOrderById(int id){
        OrderDTO result = null;
        Session session = SessionGet.getSessionFactory().openSession();
        try {
            result = (OrderDTO) session.get(OrderDTO.class, id);
        } catch (HibernateException ex) {
            //Log the exception
            System.err.println(ex);
            return null;
        } finally {
            session.close();
        }
        return result;
    }

    public static boolean removeOrder(int id){
        Session session = SessionGet.getSessionFactory().openSession();
        OrderDTO item = getOrderById(id);
        if (item == null) {
            return false;
        }
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Set<OrderBookDTO> items = item.getItems();
            Set<OrderPromotionDTO> promos = item.getPromotionApplied();
            for (OrderBookDTO i : items) session.update(i);
            for (OrderPromotionDTO p : promos) session.update(p);
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

    public static boolean updateOrder(OrderDTO order){
        Session session = SessionGet.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Set<OrderBookDTO> items = order.getItems();
            Set<OrderPromotionDTO> promos = order.getPromotionApplied();
            for (OrderBookDTO i : items) session.update(i);
            for (OrderPromotionDTO p : promos) session.update(p);
            session.update(order);
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
