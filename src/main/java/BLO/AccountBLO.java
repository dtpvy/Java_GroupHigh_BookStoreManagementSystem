package BLO;

import DAO.AccountDAO;
import DTO.AccountDTO;

public class AccountBLO {
    public static AccountDTO checkUserLogin(String username, String password){
        AccountDTO acc = AccountDAO.getAccountByUsername(username);
        if(acc != null){
            if (acc.comparePassword(password)){
                return acc;
            }
        }
        return null;
    }
}
