package DTO;

import java.sql.Timestamp;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;


public class BookRevenueDTO implements RevenueDTO{

    Map<Timestamp, List<OrderRevenueUnitDTO>> dateMap;
    public BookRevenueDTO(){
        dateMap = new HashMap<>();
    }

    public void addBookByOrder(OrderDTO order){
        Timestamp date = order.getCreatedAt();
        List<OrderRevenueUnitDTO> bookList = null;
        List<PromotionDTO> promo = new ArrayList<>();
        for (OrderPromotionDTO op : order.getPromotionApplied()){
            promo.add(op.getPromotion());
        }

        if (dateMap.containsKey(date)){
           bookList = dateMap.get(date);
        }
        else {
            bookList = new ArrayList<>();
        }
        for (OrderBookDTO b : order.getItems()){
            OrderRevenueUnitDTO nitem = new OrderRevenueUnitDTO(b, promo);
            bookList.add(nitem);
        }

        dateMap.put(date, bookList);
    }

    public void addBookByOrderList(List<OrderDTO> orderList){
        for (OrderDTO o : orderList) addBookByOrder(o);
    }

    public double getRevenueByDay(Timestamp date){
        List<OrderRevenueUnitDTO> bookList = dateMap.get(date);
        double total = 0;
        for (OrderRevenueUnitDTO b: bookList){
            total += b.getPrice();
        }
        return total;
    }

    public double getRevenueByRangeOfDay(Timestamp startDate, Timestamp endDate){
        double total =0;
        for (Timestamp t : dateMap.keySet()){
            if (t.compareTo(startDate) >= 0 && t.compareTo(endDate) <= 0)
                total += getRevenueByDay(t);
        }
        return total;
    }

    public double getTotalRevenue(){
        double total = 0;
        for (Timestamp t : dateMap.keySet()){
            total += getRevenueByDay(t);
        }
        return total;
    }
}
