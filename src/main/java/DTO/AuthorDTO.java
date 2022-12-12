package DTO;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "AUTHORS")
public class AuthorDTO {
    public AuthorDTO(){

    }

    @Id
    @Column (name ="id")
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

    @Column (name = "date_of_birth")
    private Timestamp dob;

    public Timestamp getDob() {
        return dob;
    }

    public void setDob(Timestamp dob) {
        this.dob = dob;
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

    public AuthorDTO(String id, String name, String description, Timestamp dob, Timestamp createdAt, Timestamp updatedAt){
        this.id = id;
        this.name = name;
        this.description = description;
        this.dob = dob;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String toString(){
        return new String("Author:{id: " + id + ", name: " + name + ", description: " + description + ", dob: " + dob
                + ", createdAt: " + createdAt + ", updatedAt:" + updatedAt + "}");
    }
}
