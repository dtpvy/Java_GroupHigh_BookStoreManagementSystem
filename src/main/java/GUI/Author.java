package GUI;

import BLO.AuthorBLO;
import BLO.PublisherBLO;
import DTO.AuthorDTO;
import DTO.PublisherDTO;
import UTILS.TimeUtil;
import UTILS.DateLabelFormatter;

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
import java.util.List;

public class Author extends JPanel {
    List<AuthorDTO> authorList;
    AuthorDTO authorActive;

    String search = "";
    String sortType = "id";
    String sort = "ASC";
    String[] columnNames = { "ID", "Họ tên", "Mô tả","Ngày sinh", "Ngày tạo", "Cập nhật lần cuối", "Trạng thái" };

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
        JTextField dob = new JTextField(20);

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
        JPanel descriptionPanel = new JPanel();
        descriptionPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        descriptionPanel.add(labelDescription);
        descriptionPanel.add(description);
        JLabel labelDob = new JLabel("Ngày sinh:");
        JPanel dobPanel = new JPanel();
        dobPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        dobPanel.add(labelDob);
        dobPanel.add(dob);
        JLabel labelStatus = new JLabel("Trạng thái:");
        JPanel statusPanel = new JPanel();
        statusPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        statusPanel.add(labelStatus);
        statusPanel.add(status);
        status.setEnabled(false);
        status.setText("Enable");

        formPanel.add(namePanel);
        formPanel.add(descriptionPanel);
        formPanel.add(dobPanel);
        formPanel.add(statusPanel);


        controlPanel.add(formPanel);

        JPanel actionPanel = new JPanel();
        actionPanel.setLayout(new GridLayout(5,1));
        JButton disableButton = new JButton("Disable");

        disableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (authorActive == null || !authorActive.isDisable()) return;
                AuthorBLO.disableAuthor(authorActive);
                status.setText("Disable");
            }
        });
        JButton enableButton = new JButton("Enable");
        enableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (authorActive == null || !authorActive.isDisable()) return;
                AuthorBLO.enableAuthor(authorActive);
                status.setText("Enable");
            }
        });
        JButton addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nameAdd = name.getText();
                String desAdd = description.getText();
                String dobAdd = dob.getText().toString();
                boolean disable = status.getText().equals("Enable");
                Timestamp dob = null;

                try {
                    dob = new Timestamp(new SimpleDateFormat("dd/MM/yyyy").parse(dobAdd).getTime());
                    AuthorDTO newAu = AuthorBLO.addAuthor(nameAdd, desAdd, dob, disable);
                    DefaultTableModel model = (DefaultTableModel) jTable.getModel();
                    model.addRow(new String[]{String.valueOf(newAu.getId()), newAu.getName(), newAu.getDescription(), TimeUtil.formatDate(newAu.getCreatedAt()), TimeUtil.formatDate(newAu.getUpdatedAt()), newAu.isDisable() ? "Enable" : "Disable"});
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
        JButton updateButton = new JButton("Update");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                authorActive.setName(name.getText());
                AuthorBLO.updateAuthorInfo(authorActive);
                jTable.getModel().setValueAt(authorActive.getId(), jTable.getSelectedRow(), 0);
                jTable.getModel().setValueAt(authorActive.getName(), jTable.getSelectedRow(), 1);
                jTable.getModel().setValueAt(authorActive.getDescription(), jTable.getSelectedRow(), 2);
                jTable.getModel().setValueAt(authorActive.getDob(), jTable.getSelectedRow(), 3);
                jTable.getModel().setValueAt(TimeUtil.formatDate(authorActive.getCreatedAt()), jTable.getSelectedRow(), 4);
                jTable.getModel().setValueAt(TimeUtil.formatDate(authorActive.getUpdatedAt()), jTable.getSelectedRow(), 5);
                jTable.getModel().setValueAt(authorActive.isDisable() ? "Enable" : "Disable", jTable.getSelectedRow(), 6);

            }
        });
        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                id.setText("");
                status.setText("Enable");
                name.setText("");
                setAuthorActive(null);
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
                setAuthorList();
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
                setAuthorList();
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
                setAuthorList();
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
                String dobGet = jTable.getValueAt(jTable.getSelectedRow(), 3).toString();
                String statusGet = jTable.getValueAt(jTable.getSelectedRow(), 6).toString();

                id.setText(String.valueOf(idGet));
                id.setEnabled(false);
                description.setText(desGet);
                name.setText(nameGet);
                status.setText(statusGet);
                dob.setText(dobGet);

                Timestamp dob_temp = null;
                Timestamp createdAt = null;
                Timestamp updatedAt = null;
                try {
                    dob_temp = new Timestamp(new SimpleDateFormat("dd/MM/yyyy").parse(dobGet).getTime());
                    String ca = jTable.getValueAt(jTable.getSelectedRow(), 4).toString();
                    String ua = jTable.getValueAt(jTable.getSelectedRow(), 5).toString();
                    createdAt = new Timestamp((new SimpleDateFormat("dd/MM/yyyy").parse(ca).getTime()));
                    updatedAt = new Timestamp((new SimpleDateFormat("dd/MM/yyyy").parse(ua).getTime()));
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                setAuthorActive(new AuthorDTO(idGet, nameGet, desGet, dob_temp, createdAt, updatedAt, statusGet.equals("Enable") ? true : false));
            }
        });
        JScrollPane sp = new JScrollPane(jTable);
        tablePanel.add(sp);
        tablePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        tablePanel.setPreferredSize(new Dimension(700, 450));
        tablePanel.setMaximumSize(new Dimension(700, 450));
        add(tablePanel);
    }

    public Author() {
        refresh();
    }

    public void refresh() {
        setAuthorList();
        removeAll();
        buildUI();
    }
    public void setAuthorActive(AuthorDTO authorActive) {
        this.authorActive = authorActive;
    }
    public void setAuthorList() {
        this.authorList = AuthorBLO.getAuthorList(search, sortType, sort);
    }
    public String[][] getTableData() {
        String[][] data = new String[authorList.size()][7];
        for (int i = 0; i < authorList.size(); i++) {
            data[i] = new String[]{String.valueOf(authorList.get(i).getId()), authorList.get(i).getName(), authorList.get(i).getDescription(), String.valueOf(authorList.get(i).getDob()), TimeUtil.formatDate(authorList.get(i).getCreatedAt()), TimeUtil.formatDate(authorList.get(i).getUpdatedAt()), authorList.get(i).isDisable() ? "Enable" : "Disable"};
        }
        return data;
    }
}
