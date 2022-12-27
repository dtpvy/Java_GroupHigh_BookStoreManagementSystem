package GUI;

import DTO.EmployeeDTO;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class App {
    EmployeeDTO employee;
    LoginFrame loginFrame = new LoginFrame();
    MainFrame mainFrame;
    public void loadApp() {
        if (employee == null) {
            loginFrame.showFrame();
            loginFrame.addComponentListener(new ComponentAdapter() {
                public void componentHidden(ComponentEvent e) {
                    /* code run when component hidden*/
                    employee = loginFrame.getEmployee();
                    loadApp();
                }
            });
        } else {
            mainFrame = new MainFrame(employee);
            mainFrame.showFrame();
        }
    }
}
