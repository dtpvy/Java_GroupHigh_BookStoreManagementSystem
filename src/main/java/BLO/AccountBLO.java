package BLO;

import DAO.AccountDAO;
import DTO.AccountDTO;

public class AccountBLO {
    public static boolean checkUserLogin(String username, String password){
        AccountDTO acc = AccountDAO.getAccountByUsername(username);
        if(acc != null){
            if (acc.comparePassword(password)){
                return true;
            }
        }
        return false;
    }
}
