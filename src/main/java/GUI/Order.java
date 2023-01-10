package GUI;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

public class Order extends JPanel {
    String[] columnNames = { "ID", "Tên sách" };
    String[] _columnNames = { "ID", "Code", "Mô tả", "Số lượng", "Ngày bắt đầu", "Ngày kết thúc", "Trạng thái" };
    void buildUI() {
        JPanel header = new JPanel();
        header.setLayout(new GridLayout(1, 2));
        JTable jTable = new JTable(new DefaultTableModel(getTableData(), columnNames)) {
            public boolean isCellEditable(int row, int column) {
                return false;
            };
        };
        JTable _jTable = new JTable(new DefaultTableModel(getTableData(), _columnNames)) {
            public boolean isCellEditable(int row, int column) {
                return false;
            };
        };
        JPanel body = new JPanel();
        JTextField name = new JTextField(20);
        JTextField phone = new JTextField(20);
        JTextField email = new JTextField(20);
        JTextField address = new JTextField(20);
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
        datePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        datePanel.add(labelDate);
        datePanel.add(txtDate);
        body.add(namePanel);
        body.add(emailPanel);
        body.add(phonePanel);
        body.add(addressPanel);
        body.add(datePanel);

        JPanel actionPanel = new JPanel();
        actionPanel.setLayout(new GridLayout(2,3));
        JTextField idBook = new JTextField(10);
        JButton addBookBtn = new JButton("Thêm sách");
        JButton addButton = new JButton("Thêm");
        JButton updateButton = new JButton("Cập nhật");
        JButton disableButton = new JButton("Disable");
        JButton enableButton = new JButton("Enable");
        actionPanel.add(idBook);
        actionPanel.add(addBookBtn);
        actionPanel.add(addButton);
        actionPanel.add(updateButton);
        actionPanel.add(disableButton);
        actionPanel.add(enableButton);
        actionPanel.setBorder( new EmptyBorder(0, 20, 0, 0) );
        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new BoxLayout(tablePanel, BoxLayout.Y_AXIS));
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.X_AXIS));
        JTextField searchInput = new JTextField(20);
        JButton searchSubmit = new JButton("Tìm kiếm");
        String sortTypeArr[] = { "Tất cả", "Đã hết hạn", "Sắp đến" };
        JComboBox cb1 = new JComboBox(sortTypeArr);
        String sortArr[] = { "Tăng dần", "Giảm dần" };
        JComboBox cb2 = new JComboBox(sortArr);
        menuPanel.add(searchInput);
        menuPanel.add(searchSubmit);
        menuPanel.add(cb1);
        menuPanel.add(cb2);
        menuPanel.setBorder(BorderFactory.createTitledBorder("Tìm kiếm và sắp xếp"));
        tablePanel.add(menuPanel);

        _jTable.setBounds(30, 40, 800, 300);

        JScrollPane sp = new JScrollPane(_jTable);
        tablePanel.add(sp);
        tablePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        tablePanel.setPreferredSize(new Dimension(700, 300));
        tablePanel.setMaximumSize(new Dimension(700, 300));

        header.add(body);
        header.add(jTable);
        header.setBorder( new EmptyBorder(20, 50, 20, 10) );
        add(header);
        add(actionPanel);
        add(tablePanel);
    }

    public Order() {
        refresh();
    }

    public void refresh() {
        removeAll();
        buildUI();
    }
    public String[][] getTableData() {
        String[][] data = {
                { "1", "Book A"},
                { "1", "Book B"},
                { "1", "Book C"},
                { "1", "Book D"}
        };
        return data;
    }
}
