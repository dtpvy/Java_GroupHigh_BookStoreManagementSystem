package GUI;

import BLO.EmployeeBLO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangePassword {
    private JDialog dialog;

    public ChangePassword(int id) {
        JFrame frame = new JFrame();
        dialog = new JDialog(frame, "Đổi mật khẩu", true);

        JPanel body = new JPanel();
        JPasswordField password = new JPasswordField(40);
        JPasswordField newpassword = new JPasswordField(40);
        JPasswordField confirmpassword = new JPasswordField(40);

        body.setLayout(new BoxLayout(body, BoxLayout.Y_AXIS));
        JLabel label1 = new JLabel("Mật khẩu cũ:");
        JPanel panel1 = new JPanel();
        panel1.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel1.add(label1);
        panel1.add(password);
        JLabel label2 = new JLabel("Mật khẩu mới:");
        JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel2.add(label2);
        panel2.add(newpassword);
        JLabel label3 = new JLabel("Xác nhận mật khẩu:");
        JPanel panel3 = new JPanel();
        panel3.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel3.add(label3);
        panel3.add(confirmpassword);
        JLabel status = new JLabel("");
        body.add(panel1);
        body.add(panel2);
        body.add(panel3);
        body.add(status);
        dialog.add(body);

        Button button = new Button("Đổi mật khẩu");
        button.setBackground(Color.orange);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String p = new String(password.getPassword());
                String np = new String(newpassword.getPassword());
                String cp = new String(confirmpassword.getPassword());
                if (np.equals(cp)) {
                    if (EmployeeBLO.changePassword(id, p, np)) {
                        status.setText("Đổi mật khẩu thành công!");
                    } else {
                        status.setText("Mật khẩu sai. Vui lòng thử lại.");
                    }
                } else {
                    status.setText("Xác nhận mật khẩu chưa chính xác");
                }
            }
        });

        body.setBorder(new EmptyBorder(20, 50, 20, 50));
        dialog.add(button);
        dialog.setSize(600, 300);
        dialog.setLocationRelativeTo(null);
        dialog.setLayout(new BoxLayout(dialog.getContentPane(), BoxLayout.Y_AXIS));
        dialog.setVisible(true);
    }

}
