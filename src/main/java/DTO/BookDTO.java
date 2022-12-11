package DTO;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "BOOKS")
public class BookDTO {
    public BookDTO(){

    }

    @Id
    @Column (name="id")
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private CategoryDTO category;
    public CategoryDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryDTO category) {
        this.category = category;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "publisher_id")
    private PublisherDTO publisher;
    public PublisherDTO getPublisher() {
        return publisher;
    }

    public void setPublisher(PublisherDTO publisher) {
        this.publisher = publisher;
    }

    @OneToOne(cascade = CascadeType.ALL)
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
    private float price;
    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
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

    public Timestamp getCreatedtAt() {
        return createdAt;
    }

    public void setCreatedtAt(Timestamp createdtAt) {
        this.createdAt = createdtAt;
    }

    @Column (name = "updated_at")
    private Timestamp updatedAt;

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String toString(){
        return new String(
                "Book:{\n" +id + ", " + name + ", " + description + ", " + price + ", " + quantity + ", " + createdAt + ", " + updatedAt + ",\n"
                + category.toString() + ",\n"
                + publisher.toString() + ",\n"
                + author.toString() +"\n}"
        );
    }
}
