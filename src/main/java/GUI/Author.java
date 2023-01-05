package GUI;

import DAO.AuthorDAO;
import DTO.AuthorDTO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class Author extends JPanel {
    List<AuthorDTO> authorList;
    AuthorDTO authorActive;
    public Author() {
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.X_AXIS));
        JPanel actionPanel = new JPanel();
        actionPanel.setLayout(new GridLayout(4,1));
        JButton disableButton = new JButton("Disable");
        JButton enableButton = new JButton("Enable");
        JButton addButton = new JButton("Add");
        JButton updateButton = new JButton("Update");
        actionPanel.add(disableButton);
        actionPanel.add(enableButton);
        actionPanel.add(addButton);
        actionPanel.add(updateButton);
        controlPanel.setBorder(new EmptyBorder(20, 0, 20, 0));

        JPanel formPanel = new JPanel();
        JTextField name = new JTextField(20);
        JTextField phone = new JTextField(20);
        JTextField email = new JTextField(20);
        JTextField address = new JTextField(20);
        JTextField txtDate = new JTextField(20);
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
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
        formPanel.add(namePanel);
        formPanel.add(emailPanel);
        formPanel.add(phonePanel);
        formPanel.add(addressPanel);
        formPanel.add(datePanel);

        ImageIcon icon = new ImageIcon("src/main/resources/images/loginbg.png");
        Image scaleImage = icon.getImage().getScaledInstance(150, 150,Image.SCALE_DEFAULT);
        JLabel picLabel = new JLabel(new ImageIcon(scaleImage));
        JButton changeImg = new JButton("Chọn ảnh đại diện");
        JPanel image = new JPanel();
        image.add(picLabel);
        image.add(changeImg);
        image.setLayout(new BoxLayout(image, BoxLayout.Y_AXIS));

        controlPanel.add(image);
        controlPanel.add(formPanel);
        controlPanel.add(actionPanel);
        add(controlPanel);

        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new BoxLayout(tablePanel, BoxLayout.Y_AXIS));
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.X_AXIS));
        JTextField searchInput = new JTextField(20);
        JButton searchSubmit = new JButton("Tìm kiếm");
        String sortType[] = { "Tên", "Ngày than gia", "ID" };
        JComboBox cb1 = new JComboBox(sortType);
        String sort[] = { "Tăng dần", "Giảm dần" };
        JComboBox cb2 = new JComboBox(sort);
        menuPanel.add(searchInput);
        menuPanel.add(searchSubmit);
        menuPanel.add(cb1);
        menuPanel.add(cb2);
        menuPanel.setBorder(BorderFactory.createTitledBorder("Tìm kiếm và sắp xếp"));
        tablePanel.add(menuPanel);

        String[][] data = {
                { "Kundan Kumar Jha", "4031", "CSE" },
                { "Anand Jha", "6014", "IT" }
        };
        String[] columnNames = { "Name", "Roll Number", "Department" };
        JTable jTable = new JTable(data, columnNames);
        jTable.setBounds(30, 40, 800, 300);
        JScrollPane sp = new JScrollPane(jTable);
        tablePanel.add(sp);
        tablePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        tablePanel.setPreferredSize(new Dimension(700, 300));
        tablePanel.setMaximumSize(new Dimension(700, 300));
        add(tablePanel);
    }
}