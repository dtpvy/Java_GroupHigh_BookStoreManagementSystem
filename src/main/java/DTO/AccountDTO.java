package DTO;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table (name = "accounts")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class AccountDTO {
    public AccountDTO() {

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

    @Column (name = "access_type")
    private boolean accessType;

    public boolean getAccessType(){
        return accessType;
    }

    public void setAccessType(boolean accessType){
        this.accessType = accessType;
    }

    public AccountDTO(String username, String password, boolean accessType) {
        this.username = username;
        this.password = password;
        this.accessType = accessType;
    }
}
