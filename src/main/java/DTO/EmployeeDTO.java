package DTO;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity 
@Table (name = "employees")
public class EmployeeDTO {
    EmployeeDTO(){
    }

    public EmployeeDTO(int id, boolean accessType, Timestamp dob, String fullname, String email, String address, String phone, Timestamp createdAt, Timestamp updatedAt, Boolean disable) {
        this.id = id;
        this.address = address;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.disable = disable;
        this.phone = phone;
        this.email = email;
        this.fullname = fullname;
        this.dob = dob;
        this.accessType = accessType;
    }

    @Id
    @GeneratedValue (strategy=GenerationType.IDENTITY)
    @Column (name = "id")
    private int id;

    public int getId(){
        return this.id;
    }

    public void setId(int id){
        this.id = id;
    }

    @Column (name = "username")
    private String username;

    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    @Column (name = "password")
    private String password;
    
    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public boolean comparePassword(String password){
        return this.password.equals(password);
    }

    @Column (name = "access_type")
    private boolean accessType;

    public boolean getAccessType(){
        return accessType;
    }

    public void setAccessType(boolean accessType){
        this.accessType = accessType;
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

    public String getPhone(){
        return phone;
    }

    public void setPhone(String phone){
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

    @Column (name = "image")
    private String image;

    public String getImage(){
        return image;
    }

    public void setImage(String image){
        this.image = image;
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
    
    public EmployeeDTO(String username, String password, boolean accessType , String fullname, String phone, String email, String image, String address, Timestamp dob , Timestamp createdAt, Timestamp updatedAt, boolean disable){
        this.username = username;
        this.password = password;
        this.accessType = accessType;
        this.fullname = fullname;
        this.phone = phone;
        this.email = email;
        this.image = image;
        this.address = address;
        this.dob = dob;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.disable = disable;
    }

    public String toString(){
        return "Employee{ id:" + id + ", username: " + username  + ", role: " + (accessType ? "ADMIN" : "EMPLOYEE") + ", fullname: " + fullname + "...}"; 
    }
}
