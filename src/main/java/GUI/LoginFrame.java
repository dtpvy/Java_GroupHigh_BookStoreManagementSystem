package GUI;

import BLO.EmployeeBLO;
import DTO.EmployeeDTO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class LoginFrame extends JFrame {
    EmployeeDTO account;
    private String username;
    private String password;
    private JLabel status = new JLabel("", JLabel.RIGHT);
    public LoginFrame() {
        setTitle("Đăng nhập");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));

        JPanel bg = new JPanel();
        bg.setAlignmentX(Component.CENTER_ALIGNMENT);
        bg.setPreferredSize(new Dimension(300, 300));
        bg.setMaximumSize(new Dimension(300, 300)); // set max = pref
        ImageIcon icon = new ImageIcon("src/main/resources/images/loginbg.png");
        Image scaleImage = icon.getImage().getScaledInstance(300, 300,Image.SCALE_DEFAULT);
        JLabel picLabel = new JLabel(new ImageIcon(scaleImage));
        bg.add(picLabel);

        JPanel form = new JPanel();
        form.setAlignmentX(Component.CENTER_ALIGNMENT);
        form.setPreferredSize(new Dimension(300, 300));
        form.setMaximumSize(new Dimension(300, 300)); // set max = pref
        form.setBorder(new EmptyBorder(20, 20, 20,20));

        JPanel panelLogin = new JPanel();
        JPanel panelPassword = new JPanel();
        JPanel panelSubmit = new JPanel();
        JPanel panelStatus = new JPanel();
        JLabel headerLabel = new JLabel("ĐĂNG NHẬP HỆ THỐNG", JLabel.CENTER);

        panelLogin.setLayout(new GridLayout(2, 1));
        panelPassword.setLayout(new GridLayout(2,1));
        panelSubmit.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelStatus.setLayout(new FlowLayout(FlowLayout.CENTER));

        JLabel nameLabel = new JLabel("Tài khoản: ", JLabel.LEFT);
        JLabel pwLabel = new JLabel("Mật khẩu: ", JLabel.LEFT);

        JTextField userText = new JTextField(6);
        JPasswordField passwordText = new JPasswordField(6);

        JButton loginButton = new JButton("Đăng nhập");
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                username = userText.getText();
                password = new String(passwordText.getPassword());
                loginButton.setEnabled(false);
                userText.setEnabled(false);
                passwordText.setEnabled(false);
                status.setText("Đang đăng nhập...");
                Thread t = new Thread() {
                    public void run() {
                        onSubmit();
                        loginButton.setEnabled(true);
                        userText.setEnabled(true);
                        passwordText.setEnabled(true);
                    }
                };
                t.start();
            }
        });

        panelLogin.add(nameLabel);
        panelLogin.add(userText);

        panelPassword.add(pwLabel);
        panelPassword.add(passwordText);

        panelSubmit.add(loginButton);

        panelStatus.add(status);

        form.add(headerLabel);
        form.add(panelLogin);
        form.add(panelPassword);
        form.add(panelSubmit);
        form.add(panelStatus);
        form.setLayout(new GridLayout(5, 1));

        setSize(600, 300);
        setLocationRelativeTo(null);
        getContentPane().add(bg);
        getContentPane().add(form);
        pack();
    }
    public void showFrame() {
        setVisible(true);
    }
    private void onSubmit() {
        account = EmployeeBLO.checkUserLogin(username, password);
        if (account == null) {
            status.setText("Tài khoản hoặc mật khẩu không hợp lệ");
            status.setForeground(Color.red);
        } else {
            status.setText("");
            status.setForeground(Color.BLACK);
            setVisible(false);
        }
    }
    public EmployeeDTO getEmployee() {
        return account;
    }
}
