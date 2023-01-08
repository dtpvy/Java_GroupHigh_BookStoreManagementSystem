package BLO;

import DAO.OrderDAO;
import DTO.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class  OrderBLO {
    public static void updateOrderInfo(OrderDTO order){
        Date date = new Date();
        Timestamp cur_date = new Timestamp(date.getTime());
        order.setUpdatedAt(cur_date);
        OrderDAO.updateOrder(order);
    }

    public static void addBookForOrder(OrderDTO order, BookDTO book, int quantity){
        OrderBookDTO p = new OrderBookDTO(order, book, quantity);
        order.addItem(p);
    }

    public static void removeBookForOrder(OrderDTO order, BookDTO book){
        OrderBookDTO p = new OrderBookDTO(order, book, 0);
        order.removeItem(p);
    }

    public static void addPromotionForOrder(OrderDTO order, PromotionDTO promotion){
        OrderPromotionDTO p = new OrderPromotionDTO(promotion, order);
        order.addPromotionApplied(p);
    }

    public static void removePromotionForOrder(OrderDTO order, PromotionDTO promotion){
        OrderPromotionDTO p = new OrderPromotionDTO(promotion, order);
        order.removePromotionApplied(p);
    }

    //add book to order manually via addBookForOrder
    public static OrderDTO addOrder( CustomerDTO customer, EmployeeDTO employee, String message, List<PromotionDTO> promotion){
        Date date = new Date();
        Timestamp cur_date = new Timestamp(date.getTime());
        OrderDTO order = new OrderDTO(customer, employee, message, cur_date, cur_date);
        for (PromotionDTO p : promotion){
            OrderPromotionDTO op = new OrderPromotionDTO(p, order);
            order.addPromotionApplied(op);
        }
        OrderDAO.addOrder(order);
        return order;
    }
    public static List<OrderDTO> getOrderList(String sortType, String sort){
        return OrderDAO.getOrderList(sortType.toLowerCase(), sort);
    }
}
