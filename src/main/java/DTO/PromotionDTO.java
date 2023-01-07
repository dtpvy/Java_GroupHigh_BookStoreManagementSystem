package DTO;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "promotions")
public class PromotionDTO {
    public PromotionDTO(){

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

    @Column(name = "code")
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Column(name = "description")
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "discount_percent")
    private double discountPercent;

    public double getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(double discountPercent) {
        this.discountPercent = discountPercent;
    }

    @Column(name = "available")
    private int available;

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    @Column(name = "customer_applied")
    private boolean customerApplied;

    public boolean isCustomerApplied() {
        return customerApplied;
    }

    public void setCustomerApplied(boolean customerApplied) {
        this.customerApplied = customerApplied;
    }

    @Column(name = "anonymous_applied")
    private boolean anonymousApplied;

    public boolean isAnonymousApplied() {
        return anonymousApplied;
    }

    public void setAnonymousApplied(boolean anonymousApplied) {
        this.anonymousApplied = anonymousApplied;
    }

    @OneToMany(mappedBy = "promotion", fetch = FetchType.EAGER)
    Set<PromotionBookDTO> bookApplied = new HashSet<>();

    public Set<PromotionBookDTO> getBookApplied() {
        return bookApplied;
    }

    public void setBookApplied(Set<PromotionBookDTO> bookApplied) {
        this.bookApplied = bookApplied;
    }

    public void addBookApplied(PromotionBookDTO book){
        this.bookApplied.add(book);
    }

    public void removeBookApplied(PromotionBookDTO book){
        this.bookApplied.remove(book);
    }

    @Column(name = "start_date")
    private Timestamp startDate;

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    @Column(name = "end_date")
    private Timestamp endDate;

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
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

    public PromotionDTO(String code, String description, double discountPercent, int available, boolean customerApplied, boolean anonymousApplied, Timestamp startDate, Timestamp endDate, Timestamp createdAt, Timestamp updatedAt) {
        this.code = code;
        this.description = description;
        this.discountPercent = discountPercent;
        this.available = available;
        this.customerApplied = customerApplied;
        this.anonymousApplied = anonymousApplied;
        this.startDate = startDate;
        this.endDate = endDate;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String toString(){
        return "PROMOTION{code: " + code + ", description: " + description + ", discount percent: " + discountPercent + ", applied for customer: " + customerApplied + ", applied for anonymous: " + anonymousApplied + "}";
    }
}
