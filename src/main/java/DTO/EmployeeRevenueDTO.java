package DTO;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRevenueDTO extends BookRevenueDTO{
    EmployeeDTO employee;

    public EmployeeRevenueDTO(EmployeeDTO employee){
        this.employee = employee;
    }

    public void addBookByOrder(OrderDTO order){
        if(!order.getEmployee().equals(employee)) return;
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
}
