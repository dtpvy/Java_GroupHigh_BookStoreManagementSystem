package GUI;

import BLO.CategoryBLO;
import BLO.EmployeeBLO;
import DTO.CategoryDTO;
import UTILS.ImageUpload;
import UTILS.TimeUtil;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class Promotion extends JPanel {
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
        UtilDateModel emodel = new UtilDateModel();
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
        emodel.setDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        emodel.setSelected(true);
        JDatePanelImpl edPanel = new JDatePanelImpl(emodel, p);
        JDatePickerImpl etxtDate = new JDatePickerImpl(edPanel, new UTILS.DateLabelFormatter());
        body.setLayout(new BoxLayout(body, BoxLayout.Y_AXIS));
        JLabel labelName = new JLabel("Mã giảm giá:");
        JPanel namePanel = new JPanel();
        namePanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        namePanel.add(labelName);
        namePanel.add(name);
        JLabel labelEmail = new JLabel("Mô tả:");
        JPanel emailPanel = new JPanel();
        emailPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        emailPanel.add(labelEmail);
        emailPanel.add(email);
        JLabel labelPhone = new JLabel("Số lượng:");
        JPanel phonePanel = new JPanel();
        phonePanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        phonePanel.add(labelPhone);
        phonePanel.add(phone);
        JLabel labelAddress = new JLabel("Đối tượng áp dụng:");
        JPanel addressPanel = new JPanel();
        addressPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        addressPanel.add(labelAddress);
        addressPanel.add(address);
        JLabel labelDate = new JLabel("Ngày bắt đầu:");
        JPanel datePanel = new JPanel();
        datePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        datePanel.add(labelDate);
        datePanel.add(txtDate);
        JLabel labelEDate = new JLabel("Ngày kết thúc:");
        JPanel edatePanel = new JPanel();
        edatePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        edatePanel.add(labelEDate);
        edatePanel.add(etxtDate);
        body.add(namePanel);
        body.add(emailPanel);
        body.add(phonePanel);
        body.add(addressPanel);
        body.add(datePanel);
        body.add(edatePanel);

        JPanel actionPanel = new JPanel();
        actionPanel.setLayout(new GridLayout(1,4));
        JButton addButton = new JButton("Thêm");
        JButton updateButton = new JButton("Cập nhật");
        JButton disableButton = new JButton("Disable");
        JButton enableButton = new JButton("Enable");
        actionPanel.add(addButton);
        actionPanel.add(updateButton);
        actionPanel.add(disableButton);
        actionPanel.add(enableButton);
        actionPanel.setBorder( new EmptyBorder(0, 20, 0, 0) );

        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new BoxLayout(tablePanel, BoxLayout.Y_AXIS));
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.X_AXIS));
        JTextField idInput = new JTextField(20);
        JButton idSubmit = new JButton("Thêm sách");
        JButton idDelete = new JButton("Xóa sách");
        menuPanel.add(idInput);
        menuPanel.add(idSubmit);
        menuPanel.add(idDelete);
        menuPanel.setBorder(BorderFactory.createTitledBorder("Thêm sách"));
        tablePanel.add(menuPanel);

        jTable.setBounds(30, 40, 300, 200);

        JScrollPane sp = new JScrollPane(jTable);
        tablePanel.add(sp);
        tablePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        tablePanel.setPreferredSize(new Dimension(300, 200));
        tablePanel.setMaximumSize(new Dimension(300, 200));

        JPanel _tablePanel = new JPanel();
        _tablePanel.setLayout(new BoxLayout(_tablePanel, BoxLayout.Y_AXIS));
        JPanel _menuPanel = new JPanel();
        _menuPanel.setLayout(new BoxLayout(_menuPanel, BoxLayout.X_AXIS));
        JTextField searchInput = new JTextField(20);
        JButton searchSubmit = new JButton("Tìm kiếm");
        String sortTypeArr[] = { "Tất cả", "Đã hết hạn", "Sắp đến" };
        JComboBox cb1 = new JComboBox(sortTypeArr);
        String sortArr[] = { "Tăng dần", "Giảm dần" };
        JComboBox cb2 = new JComboBox(sortArr);
        _menuPanel.add(searchInput);
        _menuPanel.add(searchSubmit);
        _menuPanel.add(cb1);
        _menuPanel.add(cb2);
        _menuPanel.setBorder(BorderFactory.createTitledBorder("Tìm kiếm và sắp xếp"));
        _tablePanel.add(_menuPanel);

        _jTable.setBounds(30, 40, 800, 300);

        JScrollPane _sp = new JScrollPane(_jTable);
        _tablePanel.add(_sp);
        _tablePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        _tablePanel.setPreferredSize(new Dimension(700, 300));
        _tablePanel.setMaximumSize(new Dimension(700, 300));


        header.add(body);
        header.add(tablePanel);
        header.setBorder( new EmptyBorder(20, 50, 20, 10) );
        add(header);
        add(actionPanel);
        add(_tablePanel);
    }

    public Promotion() {
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
