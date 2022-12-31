package DTO;

import javax.persistence.*;

import java.sql.Time;
import java.sql.Timestamp;

@Entity 
@Table (name = "employees")
public class EmployeeDTO extends AccountDTO{
    EmployeeDTO(){
        super();
    }

    @Column(name = "full_name")
    private String fullname;
    
    public String getFullname(){
        return fullname;
    }

    public void setFullname(String fullname){
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

    @Column (name = "address")
    private String address;

    public String getAddress(){
        return address;
    }

    public void setAddress(String address){
        this.address = address;
    }

    @Column (name = "date_of_birth")
    private Timestamp dob;

    public Timestamp getDob(){
        return dob;
    }

    public void setDob(Timestamp dob){
        this.dob = dob;
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

    @Column (name = "disable")
    private boolean disable;

    public boolean getDisable(){
        return disable;
    }

    public void setDisable(boolean disable){
        this.disable = disable;
    }
    
    public EmployeeDTO(String username, String password, String fullname, String phone, String email, String address, Timestamp dob , Timestamp createdAt, Timestamp updatedAt, boolean disable){
        super(username, password, false);
        this.fullname = fullname;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.dob = dob;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.disable = disable;
    }

    public String toString(){
        return "Employee{ id:" + super.getId() + ", password: " + super.getPassword() + ", fullname: " + fullname + "...}"; 
    }
}
