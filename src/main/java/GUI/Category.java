package GUI;

import BLO.CategoryBLO;
import DAO.CategoryDAO;
import DTO.AuthorDTO;
import DTO.CategoryDTO;
import DTO.EmployeeDTO;
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

public class Category extends JPanel {
    List<CategoryDTO> catList;
    CategoryDTO catActive;
    String search = "";
    String sortType = "id";
    String sort = "ASC";
    String[] columnNames = { "ID", "Tên thể loại", "Trạng thái", "Ngày tạo",
            "Cập nhật lần cuối" };
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
        JTextField nameTF = new JTextField(20);
        JTextField idTF = new JTextField(20);
        idTF.setEnabled(false);
        JTextField statusTF = new JTextField(20);
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        JLabel labelId = new JLabel("ID:");
        JPanel idPanel = new JPanel();
        idPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        idPanel.add(labelId);
        idPanel.add(idTF);
        JLabel labelName = new JLabel("Tên thể loại:");
        JPanel namePanel = new JPanel();
        namePanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        namePanel.add(labelName);
        namePanel.add(nameTF);
        JLabel labelStatus = new JLabel("Trạng thái:");
        JPanel statusPanel = new JPanel();
        statusPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        statusPanel.add(labelStatus);
        statusPanel.add(statusTF);
        statusTF.setEnabled(false);
        statusTF.setText("Enable");

        formPanel.add(idPanel);
        formPanel.add(namePanel);
        formPanel.add(statusPanel);

        controlPanel.add(formPanel);

        JPanel actionPanel = new JPanel();
        actionPanel.setLayout(new GridLayout(5,1));
        JButton disableButton = new JButton("Disable");
        disableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (catActive == null || !catActive.getDisable()) return;
                CategoryBLO.disableCategory(catActive);
                statusTF.setText("Disable");
            }
        });
        JButton enableButton = new JButton("Enable");
        enableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (catActive == null || catActive.getDisable()) return;
                CategoryBLO.enableCategory(catActive);
                statusTF.setText("Enable");
            }
        });
        JButton addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameTF.getText();
                boolean disable = statusTF.getText().equals("Enable");
                CategoryDTO newCat = CategoryBLO.addCategory(name, disable);
                DefaultTableModel model = (DefaultTableModel) jTable.getModel();
                model.addRow(new String[]{String.valueOf(newCat.getId()), newCat.getName(), newCat.getDisable() ? "Enable" : "Disable", TimeUtil.formatDate(newCat.getCreatedAt()), TimeUtil.formatDate(newCat.getUpdatedAt())});
            }
        });
        JButton updateButton = new JButton("Update");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                catActive.setName(nameTF.getText());
                CategoryBLO.updateCategoryInfo(catActive);
                jTable.getModel().setValueAt(catActive.getId(), jTable.getSelectedRow(), 0);
                jTable.getModel().setValueAt(catActive.getName(), jTable.getSelectedRow(), 1);
                jTable.getModel().setValueAt(catActive.getDisable() ? "Enable" : "Disable", jTable.getSelectedRow(), 2);
                jTable.getModel().setValueAt(TimeUtil.formatDate(catActive.getCreatedAt()), jTable.getSelectedRow(), 3);
                jTable.getModel().setValueAt(TimeUtil.formatDate(catActive.getUpdatedAt()), jTable.getSelectedRow(), 4);
            }
        });
        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                idTF.setText("");
                statusTF.setText("Enable");
                nameTF.setText("");
                setCatActive(null);
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
                setCatList();
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
                setCatList();
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
                setCatList();
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
                int id = Integer.valueOf(jTable.getValueAt(jTable.getSelectedRow(), 0).toString());
                String name = jTable.getValueAt(jTable.getSelectedRow(), 1).toString();
                String status = jTable.getValueAt(jTable.getSelectedRow(), 2).toString();
                idTF.setText(String.valueOf(id));
                idTF.setEnabled(false);
                statusTF.setText(status);
                nameTF.setText(name);
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
                setCatActive(new CategoryDTO(id, name, createdAt, updatedAt, status.equals("Enable") ? true : false));
            }
        });
        JScrollPane sp = new JScrollPane(jTable);
        tablePanel.add(sp);
        tablePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        tablePanel.setPreferredSize(new Dimension(700, 450));
        tablePanel.setMaximumSize(new Dimension(700, 450));
        add(tablePanel);
    }

    public Category() {
        refresh();
    }

    public void refresh() {
        setCatList();
        removeAll();
        buildUI();
    }
    public void setCatActive(CategoryDTO catActive) {
        this.catActive = catActive;
    }
    public void setCatList() {
        this.catList = CategoryBLO.getCategoryList(search, sortType, sort);
    }
    public String[][] getTableData() {
        String[][] data = new String[catList.size()][5];
        for (int i = 0; i < catList.size(); i++) {
            data[i] = new String[]{String.valueOf(catList.get(i).getId()), catList.get(i).getName(), catList.get(i).getDisable() ? "Enable" : "Disable", TimeUtil.formatDate(catList.get(i).getCreatedAt()), TimeUtil.formatDate(catList.get(i).getUpdatedAt())};
        }
        return data;
    }
}
