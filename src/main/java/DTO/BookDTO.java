package DTO;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "BOOKS")
public class BookDTO {
    public BookDTO(){

    }

    @Id 
    @GeneratedValue (strategy=GenerationType.IDENTITY)
    @Column (name="id")
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @OneToOne
    @JoinColumn(name = "category_id")
    private CategoryDTO category;
    public CategoryDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryDTO category) {
        this.category = category;
    }

    @OneToOne
    @JoinColumn(name = "publisher_id")
    private PublisherDTO publisher;
    public PublisherDTO getPublisher() {
        return publisher;
    }

    public void setPublisher(PublisherDTO publisher) {
        this.publisher = publisher;
    }

    @OneToOne
    @JoinColumn(name = "author_id")
    private AuthorDTO author;
    public AuthorDTO getAuthor() {
        return author;
    }

    public void setAuthor(AuthorDTO author) {
        this.author = author;
    }

    @Column (name = "name")
    private String name;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column (name = "description")
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column (name = "price")
    private double price;
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Column (name = "quantity")
    private int quantity;
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Column (name = "created_at")
    private Timestamp createdAt;

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Column (name = "updated_at")
    private Timestamp updatedAt;

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public BookDTO(CategoryDTO category, PublisherDTO publisher, AuthorDTO author, String name, String description, double price, int quantity, Timestamp createdAt, Timestamp updatedAt) {
        this.category = category;
        this.publisher = publisher;
        this.author = author;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String toString(){
        return new String(
                "Book:{id: " +id + ", name: " + name + ", description: " + description + ", price: " + price + ", quantity: " + quantity
                        + ", createdAt: " + createdAt + ", updatedAt: " + updatedAt + ",\n\t"
                        + category.toString() + ",\n\t"
                        + publisher.toString() + ",\n\t"
                        + author.toString() +"}");
    }


}
