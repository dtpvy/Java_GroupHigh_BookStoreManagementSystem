package BLO;

import DTO.EmployeeDTO;
import DAO.EmployeeDAO;

import java.util.Date;
import java.sql.Timestamp;

public class EmployeeBLO {
    static String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
         + "0123456789"
         + "abcdefghijklmnopqrstuvxyz";
    private static String RandomPasswordGen(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++){
            int index = (int)(AlphaNumericString.length() * Math.random());
            sb.append(AlphaNumericString.charAt(index));
        }
        return sb.toString();
        //original: https://www.geeksforgeeks.org/generate-random-string-of-given-size-in-java/
    }

    public static EmployeeDTO checkUserLogin(String username, String password){
        EmployeeDTO acc = EmployeeDAO.getEmployeeByUsername(username);
        if(acc != null){
            if (acc.comparePassword(password)){
                return acc;
            }
        }
        return null;
    }

    public static EmployeeDTO getAccInfo(int id){
        EmployeeDTO result = null;
        result = EmployeeDAO.getEmployeeById(id);
        return result;
    }

    public static void updateAccInfo(int id, String fullname, String phone, String email, String image, String address, Timestamp dob){
        EmployeeDTO acc = EmployeeDAO.getEmployeeById(id);
        acc.setFullname(fullname);
        acc.setPhone(phone);
        acc.setEmail(email);
        acc.setImage(image);
        acc.setAddress(address);
        acc.setDob(dob);
        Date date = new Date();
        Timestamp cur_date = new Timestamp(date.getTime());
        acc.setUpdatedAt(cur_date);
        EmployeeDAO.updateEmployee(acc);
    }

    public static void addAcc(String username, String password, boolean accessType ,String fullname, String phone, String email, String image, String address, Timestamp dob){
        Date date = new Date();
        Timestamp cur_date = new Timestamp(date.getTime());
        EmployeeDTO acc = new EmployeeDTO(username, password, accessType, fullname, phone, email, image, address, dob, cur_date, cur_date, false);
        EmployeeDAO.addEmployee(acc);
    }

    public static void addEmployee(String username, String password, String fullname, String phone, String email, String image, String address, Timestamp dob){
        addAcc(username, password, false, fullname, phone, email, image, address, dob);
    }

    public static void addAdmin(String username, String password, String fullname, String phone, String email, String image, String address, Timestamp dob){
        addAcc(username, password, true, fullname, phone, email, image, address, dob);
    }

    public static String resetPassword(int id){
        String npwd = RandomPasswordGen();
        EmployeeDTO acc = EmployeeDAO.getEmployeeById(id);
        acc.setPassword(npwd);
        EmployeeDAO.updateEmployee(acc);
        return npwd;
    }

    public static String resetPassword(String username){
        String npwd = RandomPasswordGen();
        EmployeeDTO acc = EmployeeDAO.getEmployeeByUsername(username);
        acc.setPassword(npwd);
        EmployeeDAO.updateEmployee(acc);
        return npwd;
    }

    public static Boolean changePassword(int id, String op, String np){
        EmployeeDTO acc = EmployeeDAO.getEmployeeById(id);
        if (!op.equals(acc.getPassword())) return false;
        acc.setPassword(np);
        EmployeeDAO.updateEmployee(acc);
        return true;
    }
}
