package GUI;

import DTO.EmployeeDTO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Account extends JPanel {
    EmployeeDTO employee;
    public Account(EmployeeDTO employee) {
        this.employee = employee;

        JPanel header = new JPanel();
        header.setLayout(new GridLayout(1, 2));
        ImageIcon icon = new ImageIcon("src/main/resources/images/loginbg.png");
        Image scaleImage = icon.getImage().getScaledInstance(150, 150,Image.SCALE_DEFAULT);
        JLabel picLabel = new JLabel(new ImageIcon(scaleImage));
        JButton changeImg = new JButton("Chọn ảnh đại diện");
        JPanel image = new JPanel();
        image.add(picLabel);
        image.add(changeImg);
        image.setLayout(new BoxLayout(image, BoxLayout.Y_AXIS));
        header.add(image);

        JPanel info = new JPanel();
        info.setLayout(new GridLayout(4, 2));
        JLabel label = new JLabel("Mã nhân viên:");
        JLabel label1 = new JLabel("Chức vụ:");
        JLabel label2 = new JLabel("Ngày tham gia:");
        JLabel label3 = new JLabel("Cập nhật lần cuối:");
        JLabel value = new JLabel(employee.getId());
        JLabel value1 = new JLabel(employee.getTitleRole());
        JLabel value2 = new JLabel("11:11:11 26/12/2022");
        JLabel value3 = new JLabel("11:11:11 26/12/2022");
        info.add(label);
        info.add(value);
        info.add(label1);
        info.add(value1);
        info.add(label2);
        info.add(value2);
        info.add(label3);
        info.add(value3);
        header.add(info);
        header.setBorder( new EmptyBorder(20, 50, 20, 50) );
        add(header);

        JPanel body = new JPanel();
        JTextField name = new JTextField(40);
        JTextField phone = new JTextField(40);
        JTextField email = new JTextField(40);
        JTextField address = new JTextField(40);
        JTextField txtDate = new JTextField(40);
        body.setLayout(new BoxLayout(body, BoxLayout.Y_AXIS));
        JLabel labelName = new JLabel("Họ và tên:");
        JPanel namePanel = new JPanel();
        namePanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        namePanel.add(labelName);
        namePanel.add(name);
        JLabel labelEmail = new JLabel("Email:");
        JPanel emailPanel = new JPanel();
        emailPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        emailPanel.add(labelEmail);
        emailPanel.add(email);
        JLabel labelPhone = new JLabel("Số điện thoại:");
        JPanel phonePanel = new JPanel();
        phonePanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        phonePanel.add(labelPhone);
        phonePanel.add(phone);
        JLabel labelAddress = new JLabel("Địa chỉ:");
        JPanel addressPanel = new JPanel();
        addressPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        addressPanel.add(labelAddress);
        addressPanel.add(address);
        JLabel labelDate = new JLabel("Ngày sinh:");
        JPanel datePanel = new JPanel();
        datePanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        datePanel.add(labelDate);
        datePanel.add(txtDate);
        body.add(namePanel);
        body.add(emailPanel);
        body.add(phonePanel);
        body.add(addressPanel);
        body.add(datePanel);

        JButton updateButton = new JButton("Cập nhật");
        JPanel submitPanel = new JPanel();
        submitPanel.setLayout(new GridLayout(1, 1));
        submitPanel.add(updateButton);
        body.add(submitPanel);

        add(body);
        body.setAlignmentX(Component.CENTER_ALIGNMENT);
        body.setPreferredSize(new Dimension(600, 500));
        body.setMaximumSize(new Dimension(600, 500));
        body.setBorder( new EmptyBorder(50, 0, 50, 0) );
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }
}
