package GUI;

import BLO.CategoryBLO;
import BLO.PublisherBLO;
import DTO.AuthorDTO;
import DTO.CategoryDTO;
import DTO.PublisherDTO;
import UTILS.TimeUtil;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Publisher extends JPanel {
    List<PublisherDTO> publisherList;
    PublisherDTO publisherActive;
    String search = "";
    String sortType = "id";
    String sort = "ASC";
    String[] columnNames = { "ID", "Tên", "Mô tả", "Ngày tạo", "Cập nhật lần cuối", "Trạng thái" };

    void buildUI() {
        JPanel controlPanel = new JPanel();
        JTable jTable = new JTable(new DefaultTableModel(getTableData(), columnNames)) {
            public boolean isCellEditable(int row, int column) {
                return false;
            };
        };

        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.X_AXIS));
        controlPanel.setBorder(new EmptyBorder(20, 0, 20, 0));

        JPanel formPanel = new JPanel();
        JTextField id = new JTextField(20);
        JTextField name = new JTextField(20);
        JTextField description = new JTextField(20);
        id.setEnabled(false);
        JTextField status = new JTextField(20);
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));

        JLabel labelId = new JLabel("ID:");
        JPanel idPanel = new JPanel();
        idPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        idPanel.add(labelId);
        idPanel.add(id);
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
        JLabel labelStatus = new JLabel("Trạng thái:");
        JPanel statusPanel = new JPanel();
        statusPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        statusPanel.add(labelStatus);
        statusPanel.add(status);
        status.setEnabled(false);
        status.setText("Enable");

        formPanel.add(namePanel);
        formPanel.add(DescriptionPanel);
        formPanel.add(statusPanel);

        controlPanel.add(formPanel);

        JPanel actionPanel = new JPanel();
        actionPanel.setLayout(new GridLayout(5,1));

        JButton disableButton = new JButton("Disable");
        disableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (publisherActive == null || !publisherActive.isDisable()) return;
                PublisherBLO.disablePublisher(publisherActive);
                status.setText("Disable");
            }
        });
        JButton enableButton = new JButton("Enable");
        enableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (publisherActive == null || !publisherActive.isDisable()) return;
                PublisherBLO.enablePublisher(publisherActive);
                status.setText("Enable");
            }
        });
        JButton addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nameAdd = name.getText();
                String desAdd = description.getText();
                boolean disable = status.getText().equals("Enable");
                PublisherDTO newPub = PublisherBLO.addPublisher(nameAdd, desAdd, disable);
                DefaultTableModel model = (DefaultTableModel) jTable.getModel();
                model.addRow(new String[]{String.valueOf(newPub.getId()), newPub.getName(), newPub.getDescription(), TimeUtil.formatDate(newPub.getCreatedAt()), TimeUtil.formatDate(newPub.getUpdatedAt()), newPub.isDisable() ? "Enable" : "Disable"});
            }
        });
        JButton updateButton = new JButton("Update");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                publisherActive.setName(name.getText());
                PublisherBLO.updatePublisherInfo(publisherActive);
                jTable.getModel().setValueAt(publisherActive.getId(), jTable.getSelectedRow(), 0);
                jTable.getModel().setValueAt(publisherActive.getName(), jTable.getSelectedRow(), 1);
                jTable.getModel().setValueAt(publisherActive.getDescription(), jTable.getSelectedRow(), 2);
                jTable.getModel().setValueAt(TimeUtil.formatDate(publisherActive.getCreatedAt()), jTable.getSelectedRow(), 3);
                jTable.getModel().setValueAt(TimeUtil.formatDate(publisherActive.getUpdatedAt()), jTable.getSelectedRow(), 4);
                jTable.getModel().setValueAt(publisherActive.isDisable() ? "Enable" : "Disable", jTable.getSelectedRow(), 5);

            }
        });
        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                id.setText("");
                status.setText("Enable");
                name.setText("");
                setPublisherActive(null);
            }
        });
        actionPanel.add(resetButton);
        actionPanel.add(disableButton);
        actionPanel.add(enableButton);
        actionPanel.add(addButton);
        actionPanel.add(updateButton);

        controlPanel.add(actionPanel);
        add(controlPanel);

        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new BoxLayout(tablePanel, BoxLayout.Y_AXIS));
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.X_AXIS));
        JTextField searchInput = new JTextField(20);
        JButton searchSubmit = new JButton("Tìm kiếm");

        searchSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                search = searchInput.getText();
                setPublisherList();
                DefaultTableModel dtm = (DefaultTableModel) jTable.getModel();
                dtm.setDataVector(getTableData(), columnNames);
            }
        });
        String sortTypeArr[] = { "ID", "Tên" };
        JComboBox cb1 = new JComboBox(sortTypeArr);
        cb1.addItemListener(new ItemListener()
        {
            @Override
            public void itemStateChanged(ItemEvent e)
            {
                sortType = ((String) cb1.getSelectedItem()).equals("Tên") ? "name" : "id";
                setPublisherList();
                DefaultTableModel dtm = (DefaultTableModel) jTable.getModel();
                dtm.setDataVector(getTableData(), columnNames);
            }
        });

        String sortArr[] = { "Tăng dần", "Giảm dần" };
        JComboBox cb2 = new JComboBox(sortArr);
        cb2.addItemListener(new ItemListener()
        {
            @Override
            public void itemStateChanged(ItemEvent e)
            {
                sort = ((String) cb2.getSelectedItem()).equals("Tăng dần") ? "ASC" : "DESC";
                System.out.println(sort);
                setPublisherList();
                DefaultTableModel dtm = (DefaultTableModel) jTable.getModel();
                dtm.setDataVector(getTableData(), columnNames);
            }
        });

        menuPanel.add(searchInput);
        menuPanel.add(searchSubmit);
        menuPanel.add(cb1);
        menuPanel.add(cb2);
        menuPanel.setBorder(BorderFactory.createTitledBorder("Tìm kiếm và sắp xếp"));
        tablePanel.add(menuPanel);

        jTable.setBounds(30, 40, 800, 450);
        jTable.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
                int idGet = Integer.valueOf(jTable.getValueAt(jTable.getSelectedRow(), 0).toString());
                String nameGet = jTable.getValueAt(jTable.getSelectedRow(), 1).toString();
                String desGet = jTable.getValueAt(jTable.getSelectedRow(), 2).toString();
                String statusGet = jTable.getValueAt(jTable.getSelectedRow(), 5).toString();

                id.setText(String.valueOf(idGet));
                id.setEnabled(false);
                description.setText(desGet);
                name.setText(nameGet);
                status.setText(statusGet);
                Timestamp createdAt = null;
                Timestamp updatedAt = null;
                try {
                    String ca = jTable.getValueAt(jTable.getSelectedRow(), 3).toString();
                    String ua = jTable.getValueAt(jTable.getSelectedRow(), 4).toString();
                    createdAt = new Timestamp((new SimpleDateFormat("dd/MM/yyyy").parse(ca).getTime()));
                    updatedAt = new Timestamp((new SimpleDateFormat("dd/MM/yyyy").parse(ua).getTime()));
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                setPublisherActive(new PublisherDTO(nameGet, desGet, createdAt, updatedAt, statusGet.equals("Enable") ? true : false));
            }
        });
        JScrollPane sp = new JScrollPane(jTable);
        tablePanel.add(sp);
        tablePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        tablePanel.setPreferredSize(new Dimension(700, 450));
        tablePanel.setMaximumSize(new Dimension(700, 450));
        add(tablePanel);
    }

    public Publisher() {
        refresh();
    }

    public void refresh() {
        setPublisherList();
        removeAll();
        buildUI();
    }
    public void setPublisherActive(PublisherDTO publisherActive) {
        this.publisherActive = publisherActive;
    }
    public void setPublisherList() {
        this.publisherList = PublisherBLO.getPublisherList(search, sortType, sort);
    }
    public String[][] getTableData() {
        String[][] data = new String[publisherList.size()][6];
        for (int i = 0; i < publisherList.size(); i++) {
            data[i] = new String[]{String.valueOf(publisherList.get(i).getId()), publisherList.get(i).getName(), publisherList.get(i).getDescription(), TimeUtil.formatDate(publisherList.get(i).getCreatedAt()), TimeUtil.formatDate(publisherList.get(i).getUpdatedAt()), publisherList.get(i).isDisable() ? "Enable" : "Disable"};
        }
        return data;
    }
}