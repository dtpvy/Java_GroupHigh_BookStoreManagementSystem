package DTO;

import javax.persistence.*;

@Entity
@Table(name = "order_promotion_apply")
public class OrderPromotionDTO {
    public OrderPromotionDTO() {

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
    @JoinColumn(name = "order_id")
    private OrderDTO order;

    OrderDTO getOrder(){
        return order;
    }

    void setOrder(OrderDTO order){
        this.order = order;
    }

    public OrderPromotionDTO(PromotionDTO promotion, OrderDTO order) {
        this.promotion = promotion;
        this.order = order;
    }

    public String toString(){
        return "{promotion: " + promotion.toString() + "}";
    }

}
