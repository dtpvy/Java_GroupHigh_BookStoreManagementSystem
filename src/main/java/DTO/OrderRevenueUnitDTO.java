package DTO;

import java.util.List;

public class OrderRevenueUnitDTO {

    public OrderRevenueUnitDTO(){

    }
    OrderBookDTO book;

    public OrderBookDTO getBook() {
        return book;
    }

    public void setBook(OrderBookDTO book) {
        this.book = book;
    }

    List<PromotionDTO> promos;

    public List<PromotionDTO> getPromos() {
        return promos;
    }

    public void setPromos(List<PromotionDTO> promos) {
        this.promos = promos;
    }

    public OrderRevenueUnitDTO(OrderBookDTO book, List<PromotionDTO> promos){
        this.book = book;
        this.promos = promos;
    }

    double getPrice(){
        double curPrice = book.getPrice();
        for (PromotionDTO p : promos){
            List<BookDTO> bookApplied = p.getBookAppliedList();
            double discountPercent = p.getDiscountPercent();
            if (bookApplied.contains(book)){
                curPrice = curPrice - curPrice * discountPercent / 100;
            }
        }
        return curPrice;
    }
}
