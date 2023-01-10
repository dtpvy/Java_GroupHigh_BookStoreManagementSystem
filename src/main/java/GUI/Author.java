package GUI;

import BLO.CategoryBLO;
import BLO.AuthorBLO;
import BLO.EmployeeBLO;
import DAO.AuthorDAO;
import DAO.EmployeeDAO;
import DTO.AuthorDTO;
import DTO.CategoryDTO;
import DTO.EmployeeDTO;
import UTILS.TimeUtil;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DateFormatter;
import java.awt.*;
import java.awt.event.*;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.List;

public class Author extends JPanel {
    AuthorDTO author;
    List<AuthorDTO> authorList;
    AuthorDTO authorActive;
    String search = "";
    String sortType = "id";
    String sort = "ASC";
    String[] columnNames = { "ID", "Tên", "Mô tả", "Ngày sinh", "Ngày tạo",
            "Cập nhật lần cuối", "Trạng thái" };

    void buildUI() {
        JPanel controlPanel = new JPanel();
        JTable jTable = new JTable(new DefaultTableModel(getTableData(), columnNames)) {
            public boolean isCellEditable(int row, int column) {
                return false;
            };
        };
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.X_AXIS));
        controlPanel.setBorder(new EmptyBorder(20, 0, 20, 0));

        JPanel info = new JPanel();
        JTextField idTF = new JTextField(20);
        JTextField nameTF = new JTextField(20);
        JTextField descriptionTF = new JTextField(20);
        JTextField statusTF = new JTextField(20);
        UtilDateModel model = new UtilDateModel();
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        model.setDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        model.setSelected(true);
        JDatePanelImpl dPanel = new JDatePanelImpl(model, p);
        JDatePickerImpl txtDate = new JDatePickerImpl(dPanel, new UTILS.DateLabelFormatter());

        info.setLayout(new BoxLayout(info, BoxLayout.Y_AXIS));
        idTF.setEnabled(false);

        JLabel labelName = new JLabel("Họ và tên:");
        JPanel namePanel = new JPanel();
        namePanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        namePanel.add(labelName);
        namePanel.add(nameTF);
        JLabel labelDescription = new JLabel("Mô tả:");
        JPanel descriptionPanel = new JPanel();
        descriptionPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        descriptionPanel.add(labelDescription);
        descriptionPanel.add(descriptionTF);
        JLabel labelDate = new JLabel("Ngày sinh:");
        JPanel datePanel = new JPanel();
        datePanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        datePanel.add(labelDate);
        datePanel.add(txtDate);
        JLabel labelStatus = new JLabel("Trạng thái:");
        JPanel statusPanel = new JPanel();
        statusPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        statusPanel.add(labelStatus);
        statusPanel.add(statusTF);

        info.add(namePanel);
        info.add(descriptionPanel);
        info.add(datePanel);
        info.add(statusPanel);

        controlPanel.add(info);

        JPanel actionPanel = new JPanel();
        actionPanel.setLayout(new GridLayout(5,1));

        JButton disableButton = new JButton("Disable");
        disableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (authorActive == null || !authorActive.isDisable()) return;
                AuthorBLO.disableAuthor(authorActive);
                statusTF.setText("Disable");
            }
        });

        JButton enableButton = new JButton("Enable");
        enableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (authorActive == null || authorActive.isDisable()) return;
                AuthorBLO.enableAuthor(authorActive);
                statusTF.setText("Enable");
            }
        });

        JButton addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameTF.getText();
                String description = descriptionTF.getText();
                Date date = (Date) txtDate.getModel().getValue();
                Timestamp dateOfBirth = new Timestamp(date.getTime());
                boolean disable = statusTF.getText().equals("Enable");
                AuthorDTO newAu = AuthorBLO.addAuthor(name, description, dateOfBirth, disable);
                DefaultTableModel model = (DefaultTableModel) jTable.getModel();
                model.addRow(new String[]{String.valueOf(newAu.getId()), newAu.getName(), newAu.getDescription(),
                TimeUtil.formatDate(newAu.getDob()), TimeUtil.formatDate(newAu.getCreatedAt()),
                TimeUtil.formatDate(newAu.getUpdatedAt()), newAu.isDisable() ? "Disable" : "Enable"});
            }
        });

        JButton updateButton = new JButton("Update");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                Date date = (Date) txtDate.getModel().getValue();
//                Timestamp dateOfBirth = new Timestamp(date.getTime());
//                AuthorBLO.updateAuthorInfo(author.getId(), nameTF.getText(), descriptionTF.getText(), dateOfBirth);
//                setAuthorActive(AuthorDAO.getAuthorById(author.getId()));
//                refresh();

                authorActive.setName(nameTF.getText());
                authorActive.setDescription(descriptionTF.getText());

                AuthorBLO.updateAuthorInfo(authorActive);
                jTable.getModel().setValueAt(authorActive.getId(), jTable.getSelectedRow(), 0);
                jTable.getModel().setValueAt(authorActive.getName(), jTable.getSelectedRow(), 1);
                jTable.getModel().setValueAt(authorActive.getDescription(), jTable.getSelectedRow(), 2);
                jTable.getModel().setValueAt(TimeUtil.formatDate(authorActive.getCreatedAt()), jTable.getSelectedRow(), 3);
                jTable.getModel().setValueAt(TimeUtil.formatDate(authorActive.getCreatedAt()), jTable.getSelectedRow(), 4);
                jTable.getModel().setValueAt(TimeUtil.formatDate(authorActive.getUpdatedAt()), jTable.getSelectedRow(), 5);
                jTable.getModel().setValueAt(authorActive.isDisable() ? "Enable" : "Disable", jTable.getSelectedRow(), 6);
            }
        });

        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                idTF.setText("");
                statusTF.setText("Enable");
                nameTF.setText("");
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
                try {
                int id = Integer.valueOf(jTable.getValueAt(jTable.getSelectedRow(), 0).toString());
                String name = jTable.getValueAt(jTable.getSelectedRow(), 1).toString();
                    Object descriptionOb = jTable.getValueAt(jTable.getSelectedRow(), 2);
                    String description = descriptionOb == null ? "" : descriptionOb.toString();

                Timestamp dob;
                Timestamp createdAt;
                Timestamp updatedAt;
                String status = jTable.getValueAt(jTable.getSelectedRow(), 6).toString();

                idTF.setText(String.valueOf(id));
                idTF.setEnabled(false);
                statusTF.setText(status);
                nameTF.setText(name);

                    String dobStr = jTable.getValueAt(jTable.getSelectedRow(), 3).toString();
                    String ca = jTable.getValueAt(jTable.getSelectedRow(), 4).toString();
                    String ua = jTable.getValueAt(jTable.getSelectedRow(), 5).toString();
                    dob = new Timestamp((new SimpleDateFormat("dd/MM/yyyy")).parse(dobStr).getTime());
                    createdAt = new Timestamp((new SimpleDateFormat("dd/MM/yyyy").parse(ca).getTime()));
                    updatedAt = new Timestamp((new SimpleDateFormat("dd/MM/yyyy").parse(ua).getTime()));

                    setAuthorActive(new AuthorDTO(id, name, description, dob, createdAt, updatedAt, status.equals("Enable") ? true : false));

                }
                catch (ParseException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }

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
            data[i] = new String[]{
                    String.valueOf(authorList.get(i).getId()),
                    authorList.get(i).getName(),
                    authorList.get(i).getDescription(),
                    TimeUtil.formatDate(authorList.get(i).getDob()),
                    TimeUtil.formatDate(authorList.get(i).getCreatedAt()),
                    TimeUtil.formatDate(authorList.get(i).getUpdatedAt()),
                    authorList.get(i).isDisable() ? "Enable" : "Disable"};
        }
        return data;
    }
}
