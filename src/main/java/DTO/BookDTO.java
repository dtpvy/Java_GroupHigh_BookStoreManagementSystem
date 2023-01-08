package DTO;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

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

    @Column (name = "disable")
    private boolean disable;
    public boolean isDisable() {
        return disable;
    }

    public void setDisable(boolean disable) {
        this.disable = disable;
    }

    public BookDTO(CategoryDTO category, PublisherDTO publisher, AuthorDTO author, String name, String description, double price, int quantity, Timestamp createdAt, Timestamp updatedAt, boolean disable) {
        this.category = category;
        this.publisher = publisher;
        this.author = author;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.disable = disable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookDTO bookDTO = (BookDTO) o;
        return id == bookDTO.id;// && Double.compare(bookDTO.price, price) == 0 && quantity == bookDTO.quantity && Objects.equals(category, bookDTO.category) && Objects.equals(publisher, bookDTO.publisher) && Objects.equals(author, bookDTO.author) && Objects.equals(name, bookDTO.name) && Objects.equals(description, bookDTO.description) && Objects.equals(createdAt, bookDTO.createdAt) && Objects.equals(updatedAt, bookDTO.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, category, publisher, author, name, description, price, quantity, createdAt, updatedAt);
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
