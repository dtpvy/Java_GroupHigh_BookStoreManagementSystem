package GUI;

import BLO.CategoryBLO;
import BLO.EmployeeBLO;
import DAO.EmployeeDAO;
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
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

public class Employee extends JPanel {
    List<EmployeeDTO> employeeList = new ArrayList<>();
    EmployeeDTO account;
    EmployeeDTO employeeActive;
    String search = "";
    String sortType = "id";
    String sort = "ASC";
    String[] columnNames = {"ID","Tên", "Chức vụ", "Số điện thoại",
            "Email", "Địa chỉ", "Ngày sinh", "Trạng thái" };
    void buildUI() {
        JPanel controlPanel = new JPanel();
        JTable jTable = new JTable(new DefaultTableModel(getTableData(), columnNames)) {
            public boolean isCellEditable(int row, int column) {
                return false;
            };
        };

        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.X_AXIS));
        controlPanel.setBorder(new EmptyBorder(20, 0, 20, 0));

        JPanel body = new JPanel();
        JTextField id = new JTextField(20);
        id.setEnabled(false);
        JTextField name = new JTextField(20);
        JTextField role = new JTextField(20);
        role.setText("Nhân viên");
        role.setEnabled(false);
        JTextField phone = new JTextField(20);
        JTextField email = new JTextField(20);
        JTextField address = new JTextField(20);
        JTextField status = new JTextField(20);
        status.setEnabled(false);
        UtilDateModel model = new UtilDateModel();
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(account.getDob().getTime()));
        JDatePanelImpl dPanel = new JDatePanelImpl(model, p);
        JDatePickerImpl txtDate = new JDatePickerImpl(dPanel, new UTILS.DateLabelFormatter());
        body.setLayout(new BoxLayout(body, BoxLayout.Y_AXIS));
        JLabel labelId = new JLabel("Mã nhân viên:");
        JPanel idPanel = new JPanel();
        idPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        idPanel.add(labelId);
        idPanel.add(id);
        JLabel labelName = new JLabel("Họ và tên:");
        JPanel namePanel = new JPanel();
        namePanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        namePanel.add(labelName);
        namePanel.add(name);
        JLabel labelRole = new JLabel("Chức vụ:");
        JPanel rolePanel = new JPanel();
        rolePanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        rolePanel.add(labelRole);
        rolePanel.add(role);
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
        datePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        datePanel.add(labelDate);
        datePanel.add(txtDate);
        JLabel labelStatus = new JLabel("Trạng thái:");
        JPanel statusPanel = new JPanel();
        statusPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        statusPanel.add(labelStatus);
        statusPanel.add(status);
        body.add(idPanel);
        body.add(namePanel);
        body.add(rolePanel);
        body.add(emailPanel);
        body.add(phonePanel);
        body.add(addressPanel);
        body.add(datePanel);
        body.add(statusPanel);

        controlPanel.add(body);

        JPanel actionPanel = new JPanel();
        actionPanel.setLayout(new GridLayout(4,2));
        JButton disableButton = new JButton("Disable");
        disableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (employeeActive == null || !account.getAccessType() || employeeActive.getDisable()) return;
                EmployeeBLO.disableEmployee(employeeActive);
                status.setText("Disable");
                jTable.getModel().setValueAt(!employeeActive.getDisable() ? "Enable" : "Disable", jTable.getSelectedRow(), 7);
            }
        });
        JButton enableButton = new JButton("Enable");
        enableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (employeeActive == null || !account.getAccessType() || !employeeActive.getDisable()) return;
                EmployeeBLO.enableEmployee(employeeActive);
                status.setText("Enable");
                jTable.getModel().setValueAt(!employeeActive.getDisable() ? "Enable" : "Disable", jTable.getSelectedRow(), 7);
            }
        });
        JButton adminButton = new JButton("Tăng chức");
        adminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (employeeActive == null || !account.getAccessType() || employeeActive.getAccessType()) return;
                EmployeeBLO.levelUpEmployee(employeeActive, true);
                role.setText("Quản lý");
                jTable.getModel().setValueAt(!employeeActive.getAccessType() ? "Quản lý" : "Nhân viên", jTable.getSelectedRow(), 2);
            }
        });
        JButton employeeButton = new JButton("Giảm chức");
        employeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (employeeActive == null || !account.getAccessType() || !employeeActive.getAccessType()) return;
                EmployeeBLO.levelUpEmployee(employeeActive, false);
                role.setText("Nhân viên");
                jTable.getModel().setValueAt(!employeeActive.getAccessType() ? "Quản lý" : "Nhân viên", jTable.getSelectedRow(), 2);
            }
        });
        JButton addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date date = (Date) txtDate.getModel().getValue();
                Timestamp selectedDate = new Timestamp(date.getTime());
                EmployeeDTO newE = EmployeeBLO.addAcc(EmployeeBLO.createUsername(name.getText()), EmployeeBLO.RandomPasswordGen(), role.equals("Quản lý") ,name.getText(), phone.getText(), email.getText(), "https://ik.imagekit.io/0o9nfg6a3/loginbg_M39x117vT.png?ik-sdk-version=javascript-1.4.3&updatedAt=1673189879987", address.getText(), selectedDate);
                DefaultTableModel model = (DefaultTableModel) jTable.getModel();
                model.addRow(new String[]{String.valueOf(newE.getId()), newE.getFullname(), newE.getAccessType() ? "Quản lý" : "Nhân viên", newE.getPhone(), newE.getEmail(), newE.getAddress(), TimeUtil.formatDate(newE.getDob()), !newE.getDisable() ? "Enable" : "Disable"});
            }
        });
        JButton updateButton = new JButton("Update");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date date = (Date) txtDate.getModel().getValue();
                Timestamp selectedDate = new Timestamp(date.getTime());
                employeeActive.setFullname(name.getText());
                employeeActive.setPhone(phone.getText());
                employeeActive.setEmail(email.getText());
                employeeActive.setAddress(address.getText());
                employeeActive.setDob(selectedDate);
                EmployeeDAO.updateEmployee(employeeActive);
                jTable.getModel().setValueAt(employeeActive.getFullname(), jTable.getSelectedRow(), 1);
                jTable.getModel().setValueAt(employeeActive.getAddress(), jTable.getSelectedRow(), 5);
                jTable.getModel().setValueAt(employeeActive.getEmail(), jTable.getSelectedRow(), 4);
                jTable.getModel().setValueAt(employeeActive.getPhone(), jTable.getSelectedRow(), 3);
                jTable.getModel().setValueAt(TimeUtil.formatDate(employeeActive.getDob()), jTable.getSelectedRow(), 6);}
        });
        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                id.setText("");
                name.setText("");
                role.setText("");
                phone.setText("");
                email.setText("");
                address.setText("");
                status.setText("");
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(new Date());
                model.setDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                model.setSelected(true);
                setEmployeeActive(null);
                role.setText("Nhân viên");
                jTable.getSelectionModel().clearSelection();
            }
        });
        JButton resetPassButton = new JButton("Reset Password");
        resetPassButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EmployeeBLO.resetPassword(employeeActive.getId());
            }
        });
        actionPanel.add(resetButton);
        actionPanel.add(resetPassButton);
        actionPanel.add(adminButton);
        actionPanel.add(employeeButton);
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
                setEmployeeList();
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
                setEmployeeList();
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
                setEmployeeList();
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
                int _id = Integer.valueOf(jTable.getValueAt(jTable.getSelectedRow(), 0).toString());
                if (_id == account.getId()) return;
                String _name = jTable.getValueAt(jTable.getSelectedRow(), 1).toString();
                String _role = jTable.getValueAt(jTable.getSelectedRow(), 2).toString();
                String _phone = jTable.getValueAt(jTable.getSelectedRow(), 3).toString();
                String _email = jTable.getValueAt(jTable.getSelectedRow(), 4).toString();
                String _address = jTable.getValueAt(jTable.getSelectedRow(), 5).toString();
                String _dob = jTable.getValueAt(jTable.getSelectedRow(), 6).toString();
                String _status = jTable.getValueAt(jTable.getSelectedRow(), 7).toString();
                id.setText(String.valueOf(_id));
                name.setText(_name);
                role.setText(_role);
                phone.setText(_phone);
                email.setText(_email);
                address.setText(_address);
                status.setText(_status);
                Date date = null;
                try {
                    date = new SimpleDateFormat("dd/MM/yyyy").parse(_dob);
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(date);
                    model.setDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                    model.setSelected(true);
                } catch (ParseException e) {}

                EmployeeDTO e = EmployeeDAO.getEmployeeById(Integer.valueOf(_id));
                Timestamp dateOfBirth = new Timestamp(date.getTime());
                setEmployeeActive(new EmployeeDTO(Integer.valueOf(_id), _role.equals("Quản lý"), dateOfBirth, _name, _email, _address, _phone, e.getCreatedAt(), e.getUpdatedAt(), _status.equals("Disable")));
            }
        });
        JScrollPane sp = new JScrollPane(jTable);
        tablePanel.add(sp);
        tablePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        tablePanel.setPreferredSize(new Dimension(700, 450));
        tablePanel.setMaximumSize(new Dimension(700, 450));
        add(tablePanel);
    }

    public Employee() {
        if (account == null) return;
        refresh();
    }

    public void setAccount(EmployeeDTO account) {
        this.account = account;
    }
    public void refreshUI() {
        removeAll();
        buildUI();
    }

    public void refresh() {
        setEmployeeList();
        removeAll();
        buildUI();
    }
    public void setEmployeeActive(EmployeeDTO employeeActive) {
        this.employeeActive = employeeActive;
    }
    public void setEmployeeList() {
        this.employeeList = EmployeeBLO.getEmployeeList(search, sortType, sort);
    }
    public String[][] getTableData() {
        String[][] data = new String[employeeList.size()][columnNames.length];
        for (int i = 0; i < employeeList.size(); i++) {
            data[i] = new String[]{String.valueOf(employeeList.get(i).getId()), employeeList.get(i).getFullname(), employeeList.get(i).getAccessType() ? "Quản lý" : "Nhân viên", employeeList.get(i).getPhone(), employeeList.get(i).getEmail(), employeeList.get(i).getAddress(), TimeUtil.formatDate(employeeList.get(i).getDob()), !employeeList.get(i).getDisable() ? "Enable" : "Disable"};
        }
        return data;
    }
}
