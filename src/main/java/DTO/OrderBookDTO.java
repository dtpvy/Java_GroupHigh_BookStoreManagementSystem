package DTO;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "order_item")
public class OrderBookDTO {
    public OrderBookDTO() {

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
    @JoinColumn(name = "order_id")
    private OrderDTO order;

    OrderDTO getOrder(){
        return order;
    }

    void setOrder(OrderDTO order){
        this.order = order;
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

    @Column(name = "quantity")
    private int quantity;

    int getQuantity(){
        return quantity;
    }

    void setQuantity(int quantity){
        this.quantity = quantity;
    }

    public OrderBookDTO(OrderDTO order, BookDTO book, int quantity) {
        this.order = order;
        this.book = book;
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderBookDTO that = (OrderBookDTO) o;
        return quantity == that.quantity && order.equals(that.order) && book.equals(that.book);
    }

//    @Override
//    public int hashCode() {
//        return Objects.hash(id, order, book, quantity);
//    }


    public String toString(){
        return "{book: " + book.toString() + ",\nquantity: " + quantity + "}";
    }
}
