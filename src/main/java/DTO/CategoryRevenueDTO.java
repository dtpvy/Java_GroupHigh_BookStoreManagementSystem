package DTO;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class CategoryRevenueDTO extends TotalRevenueDTO{
    CategoryDTO category;

    public CategoryRevenueDTO(CategoryDTO category){
        this.category = category;
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
            if (b.getBook().getCategory().equals(this.category)) {
                OrderRevenueUnitDTO nitem = new OrderRevenueUnitDTO(b, promo);
                bookList.add(nitem);
            }
        }

        dateMap.put(date, bookList);
    }
}
