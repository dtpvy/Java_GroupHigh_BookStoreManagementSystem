package DTO;

public class EmployeeDTO {
    private String id, name, role;
    public EmployeeDTO(String id){
        this.id = id;
    }
    public EmployeeDTO(String id, String name, String role){
        this.id = id;
        this.name = name;
        this.role = role;
    }


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public String getTitleRole() {
        return role == "admin" ? "Quản trị viên" : "Nhân viên";
    }
}
