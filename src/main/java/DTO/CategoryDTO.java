package DTO;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "CATEGORY")
public class CategoryDTO {
    public CategoryDTO(){

    }

    @Id
    @Column
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column
    private Timestamp createdAt;

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createAt) {
        this.createdAt = createAt;
    }

    @Column
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
        return new String(id + ", " + name + ", " + ", " + createdAt + ", " + updatedAt);
    }
}
