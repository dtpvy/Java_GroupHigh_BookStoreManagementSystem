package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class TabMenu extends JPanel {
    Boolean isActive = false;
    TabMenu(String title, Boolean isActive) {
        JLabel label = new JLabel(title, JLabel.CENTER);
        add(label);
        setLayout(new GridLayout(1,1));
        setIsActive(isActive);
    }
    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
        if (isActive) {
            setBackground(Color.YELLOW);
        } else {
            setBackground(Color.orange);
        }
    }
}
