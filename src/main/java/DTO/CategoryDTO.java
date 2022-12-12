package DTO;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "CATEGORIES")
public class CategoryDTO {
    public CategoryDTO(){

    }

    @Id
    @Column (name = "id")
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column (name = "name")
    private String name;

    public String getName() {
        return  name;
    }

    public void setName(String name) {
        this.name = name;
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


    public CategoryDTO(String id, String name, Timestamp createdAt, Timestamp updatedAt){
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
    public String toString(){
        return new String("Category:{id: " + id + ", name: " + name + ", " + ", createdAt: " + createdAt + ", updatedAt:" + updatedAt + "}");
    }
}
