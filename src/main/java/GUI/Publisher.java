package GUI;

import DTO.AuthorDTO;
import DTO.PublisherDTO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.List;

public class Publisher extends JPanel {
    List<PublisherDTO> PublisherList;
    PublisherDTO publisherActive;
    public Publisher() {
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
        JTextField description = new JTextField(20);
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));

        JLabel labelName = new JLabel("Họ và tên:");
        JPanel namePanel = new JPanel();
        namePanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        namePanel.add(labelName);
        namePanel.add(name);
        JLabel labelDescription = new JLabel("Giới thiệu:");
        JPanel DescriptionPanel = new JPanel();
        DescriptionPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        DescriptionPanel.add(labelDescription);
        DescriptionPanel.add(description);

        formPanel.add(namePanel);
        formPanel.add(DescriptionPanel);

        ImageIcon icon = new ImageIcon("src/main/resources/images/loginbg.png");
        Image scaleImage = icon.getImage().getScaledInstance(150, 150,Image.SCALE_DEFAULT);
        JLabel picLabel = new JLabel(new ImageIcon(scaleImage));
        JPanel image = new JPanel();
        image.add(picLabel);
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
        String[] sortType = { "Tên", "Ngày than gia", "ID" };
        JComboBox cb1 = new JComboBox(sortType);
        String[] sort = { "Tăng dần", "Giảm dần" };
        JComboBox cb2 = new JComboBox(sort);
        menuPanel.add(searchInput);
        menuPanel.add(searchSubmit);
        menuPanel.add(cb1);
        menuPanel.add(cb2);
        menuPanel.setBorder(BorderFactory.createTitledBorder("Tìm kiếm và sắp xếp"));
        tablePanel.add(menuPanel);

        String[][] data = {
                { "Kundan Kumar Jha", "4031", "CSE" },
                { "Anand Jha", "6014", "IT" },
        };
        String[] columnNames = { "Name", "Roll Number", "D" +
                "epartment" };
        JTable jTable = new JTable(data, columnNames) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        jTable.setBounds(30, 40, 800, 450);
        jTable.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {

                System.out.println(jTable.getValueAt(jTable.getSelectedRow(), 0).toString());
            }
        });
        JScrollPane sp = new JScrollPane(jTable);
        tablePanel.add(sp);
        tablePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        tablePanel.setPreferredSize(new Dimension(700, 450));
        tablePanel.setMaximumSize(new Dimension(700, 450));
        add(tablePanel);
    }
}
