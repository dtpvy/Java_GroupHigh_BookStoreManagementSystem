package DAO;

import DTO.AccountDTO;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;


public class AccountDAO {
    private static List<AccountDTO> accountList;
    public static List<AccountDTO> AccountList(){
        Session session = SessionGet.getSessionFactory().openSession();
        try {
            String hql = "FROM AccountDTO ORDER BY id ASC";
            Query query = session.createQuery(hql);
            accountList = query.list();
        } catch (HibernateException ex) {
            //Log the exception
            System.err.println(ex);
        } finally {
            session.close();
        }
        return accountList;
    }

    public static AccountDTO getAccountByUsername(String username){
        AccountDTO result = null;
        Session session = SessionGet.getSessionFactory().openSession();
        try {
            String hql = "FROM AccountDTO WHERE username=:username";
            Query query = session.createQuery(hql);
            query.setParameter("username", username);
            result = (AccountDTO) query.uniqueResult();
        } catch (HibernateException ex) {
            //Log the exception
            System.err.println(ex);
        } finally {
            session.close();
        }
        return result;
    }
}

