package DTO;

import java.sql.Timestamp;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;


public class BookRevenueDTO extends TotalRevenueDTO{

    BookDTO book;
    public BookRevenueDTO(BookDTO book){
        this.book = book;
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
            if (b.getBook().equals(book)) {
                OrderRevenueUnitDTO nitem = new OrderRevenueUnitDTO(b, promo);
                bookList.add(nitem);
            }
        }

        dateMap.put(date, bookList);
    }
}
