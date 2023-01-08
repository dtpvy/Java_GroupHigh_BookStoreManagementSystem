package GUI;

import DTO.EmployeeDTO;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class App {
    EmployeeDTO account;
    LoginFrame loginFrame = new LoginFrame();
    MainFrame mainFrame = new MainFrame();;
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
            mainFrame.setAccount(account);
            mainFrame.showFrame();
            mainFrame.addComponentListener(new ComponentAdapter() {
                public void componentHidden(ComponentEvent e) {
                    /* code run when component hidden*/
                    account = null;
                    loadApp();
                }
            });
        }
    }
}
