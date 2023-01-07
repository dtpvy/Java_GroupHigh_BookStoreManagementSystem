package DTO;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "orders")
public class OrderDTO {
    public OrderDTO(){

    }

    @Id
    @GeneratedValue (strategy=GenerationType.IDENTITY)
    @Column (name = "id")
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @OneToOne
    @JoinColumn(name = "customer_id")
    private CustomerDTO customer;

    public CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }

    @OneToOne
    @JoinColumn(name = "employee_id")
    private EmployeeDTO employee;

    public EmployeeDTO getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeDTO employee) {
        this.employee = employee;
    }

    @Column(name = "message")
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
    private Set<OrderBookDTO> items = new HashSet<>();

    public Set<OrderBookDTO> getItems() {
        return items;
    }

    public void setItems(Set<OrderBookDTO> items) {
        this.items = items;
    }

    public void addItem(OrderBookDTO book){
        this.items.add(book);
    }

    public void removeItem(OrderBookDTO book){
        this.items.remove(book);
    }

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
    private Set<OrderPromotionDTO> promotionApplied = new HashSet<>();

    public Set<OrderPromotionDTO> getPromotionApplied() {
        return promotionApplied;
    }

    public void setPromotionApplied(Set<OrderPromotionDTO> promotionApplied) {
        this.promotionApplied = promotionApplied;
    }

    public void addPromotionApplied(OrderPromotionDTO promotion){
        this.promotionApplied.add(promotion);
    }

    public void removePromotionApplied(OrderPromotionDTO promotion){
        this.promotionApplied.remove(promotion);
    }

    @Column (name = "created_at")
    private Timestamp createdAt;

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createAt) {
        this.createdAt = createAt;
    }

    @Column (name = "updated_at")
    private Timestamp updatedAt;

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updateAt) {
        this.updatedAt = updateAt;
    }

    public OrderDTO(CustomerDTO customer, EmployeeDTO employee, String message, Timestamp createdAt, Timestamp updatedAt) {
        this.customer = customer;
        this.employee = employee;
        this.message = message;
        this.items = items;
        this.promotionApplied = promotionApplied;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
