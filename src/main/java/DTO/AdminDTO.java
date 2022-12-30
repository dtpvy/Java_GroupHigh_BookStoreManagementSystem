package DTO;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "admins")
public class AdminDTO extends AccountDTO{
    public AdminDTO(){
        super();
    }

    @Column (name = "full_name")
    private String fullname;

    String getFullname(){
        return fullname;
    }

    void setFullname(String fullname){
        this.fullname = fullname;
    }

    @Column (name = "phone")
    private String phone;

    String getPhone(){
        return phone;
    }

    void setPhone(String phone){
        this.phone = phone;
    }

    @Column (name = "email")
    private String email;

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
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
    
    public AdminDTO(String username, String password, String fullname, String phone, String email, Timestamp createdAt, Timestamp updatedAt){
        super(username, password, true);
        this.fullname = fullname;
        this.phone = phone;
        this.email = email;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String toString(){
        return "Admin{ id:" + super.getId() + ", password: " + super.getPassword() + ", fullname: " + fullname + "...}"; 
    }
}
