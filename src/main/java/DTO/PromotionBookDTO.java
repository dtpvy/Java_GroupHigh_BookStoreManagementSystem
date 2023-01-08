package DTO;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "promotion_book_apply")
public class PromotionBookDTO {
    public PromotionBookDTO(){

    }
    @Id
    @GeneratedValue (strategy=GenerationType.IDENTITY)
    @Column (name = "id")
    private int id;

    int getId(){
        return id;
    }

    void setId(int id){
        this.id = id;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "promotion_id")
    private PromotionDTO promotion;

    public PromotionDTO getPromotion() {
        return promotion;
    }

    public void setPromotion(PromotionDTO promotion) {
        this.promotion = promotion;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "book_id")
    private BookDTO book;

    BookDTO getBook(){
        return book;
    }

    void setBook(BookDTO book){
        this.book = book;
    }

    public PromotionBookDTO(PromotionDTO promotion, BookDTO book) {
        this.promotion = promotion;
        this.book = book;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PromotionBookDTO that = (PromotionBookDTO) o;
        return  promotion.equals(that.promotion) && book.equals(that.book);
    }

//    @Override
//    public int hashCode() {
//        return Objects.hash(id, promotion, book);
//    }

    public String toString(){
        return "{book: " + book.toString() + "}";
    }
}
