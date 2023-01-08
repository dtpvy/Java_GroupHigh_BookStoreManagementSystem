package DTO;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "CATEGORIES")
public class CategoryDTO {
    public CategoryDTO(){

    }

    public CategoryDTO(int id, String name, Timestamp createdAt, Timestamp updatedAt, boolean disable){
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.disable = disable;
    }

    public CategoryDTO(String name, Timestamp createdAt, Timestamp updatedAt, boolean disable){
        this.name = name;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.disable = disable;
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
        return  name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column (name = "disable")
    private boolean disable;

    public boolean getDisable(){
        return disable;
    }

    public void setDisable(boolean disable){
        this.disable = disable;
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


    public CategoryDTO(String name, Timestamp createdAt, Timestamp updatedAt){
        this.name = name;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
    public String toString(){
        return new String("Category:{id: " + id + ", name: " + name + ", " + ", createdAt: " + createdAt + ", updatedAt:" + updatedAt + "}");
    }
}
