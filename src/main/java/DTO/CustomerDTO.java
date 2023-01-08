package DTO;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity 
@Table (name = "customers")
public class CustomerDTO {
    public CustomerDTO(){

    }
    @Id 
    @GeneratedValue (strategy=GenerationType.IDENTITY)
    @Column (name="id")
    private int id;

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    @Column (name = "name")
    private String name;

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    @Column (name = "phone")
    private String phone;

    public String getPhone(){
        return phone;
    }

    public void setPhone(String phone){
        this.phone = phone;
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

    public CustomerDTO(String name, String phone, Timestamp createdAt, Timestamp updatedAt){
        this.name = name;
        this.phone = phone;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerDTO that = (CustomerDTO) o;
        return id == that.id;
    }

    public String toString(){
        return "Customer{ id:" + id + ", name: " + name + ", phone: " + phone + ", created at: " + createdAt + ", updated at: " + updatedAt + "}"; 
    }
}
