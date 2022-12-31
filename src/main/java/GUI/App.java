package GUI;

import DTO.AccountDTO;
import DTO.EmployeeDTO;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class App {
    AccountDTO account;
    LoginFrame loginFrame = new LoginFrame();
    MainFrame mainFrame;
    public void loadApp() {
        if (account == null) {
            loginFrame.showFrame();
            loginFrame.addComponentListener(new ComponentAdapter() {
                public void componentHidden(ComponentEvent e) {
                    /* code run when component hidden*/
                    account = loginFrame.getEmployee();
                    loadApp();
                }
            });
        } else {
            mainFrame = new MainFrame(account);
            mainFrame.showFrame();
        }
    }
}
