package DTO;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "PUBLISHERS")
public class PublisherDTO {
    public PublisherDTO(){

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


    public PublisherDTO(String name, String description, Timestamp createdAt, Timestamp updatedAt, boolean disable){
        this.name = name;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.disable = disable;
    }

    public String toString(){
        return new String("Publisher:{id: " + id + ", name:" + name + ", description:" + description
                + ", createdAt: " + createdAt + ", updatedAt: " + updatedAt + "}");
    }
}
