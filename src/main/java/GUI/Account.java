package GUI;

import BLO.EmployeeBLO;
import DAO.EmployeeDAO;
import DTO.EmployeeDTO;
import UTILS.TimeUtil;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DateFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

public class Account extends JPanel {
    EmployeeDTO account;
    void buildUI() {
        JPanel header = new JPanel();
        header.setLayout(new GridLayout(1, 2));
        ImageIcon icon = new ImageIcon("src/main/resources/images/loginbg.png");
        Image scaleImage = icon.getImage().getScaledInstance(150, 150,Image.SCALE_DEFAULT);
        JLabel picLabel = new JLabel(new ImageIcon(scaleImage));
        JButton changeImg = new JButton("Chọn ảnh đại diện");
        picLabel.setBorder(new EmptyBorder(0, 0, 10, 0));
        JPanel image = new JPanel();
        image.add(picLabel);
        image.add(changeImg);
        image.setAlignmentX(Component.CENTER_ALIGNMENT);
        image.setLayout(new BoxLayout(image, BoxLayout.Y_AXIS));
        header.add(image);

        JPanel info = new JPanel();
        info.setLayout(new GridLayout(4, 2));
        JLabel label = new JLabel("Mã nhân viên:");
        JLabel label1 = new JLabel("Chức vụ:");
        JLabel label2 = new JLabel("Ngày tham gia:");
        JLabel label3 = new JLabel("Cập nhật lần cuối:");
        JLabel value = new JLabel(account.getUsername());
        JLabel value1 = new JLabel(account.getAccessType() ? "Quản lý" : "Nhân viên");
        JLabel value2 = new JLabel(TimeUtil.formatDate(account.getCreatedAt()));
        JLabel value3 = new JLabel(TimeUtil.formatDate(account.getUpdatedAt()));
        info.add(label);
        info.add(value);
        info.add(label1);
        info.add(value1);
        info.add(label2);
        info.add(value2);
        info.add(label3);
        info.add(value3);
        header.add(info);

        JPanel actionPanel = new JPanel();
        actionPanel.setLayout(new GridLayout(4,1));

        JButton logoutButton = new JButton("Đăng xuất");
        logoutButton.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                JFrame topFrame = (JFrame) SwingUtilities.getRoot(logoutButton);
                setAccount(null);
                topFrame.setVisible(false);
            }
        });
        JButton cpButton = new JButton("Đổi mật khẩu");
        cpButton.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                new ChangePassword(account.getId());
            }
        });
        actionPanel.add(new JPanel());
        actionPanel.add(logoutButton);
        actionPanel.add(cpButton);
        actionPanel.add(new JPanel());
        actionPanel.setBorder( new EmptyBorder(0, 20, 0, 0) );
        header.add(actionPanel);

        header.setBorder( new EmptyBorder(20, 50, 20, 50) );
        add(header);

        JPanel body = new JPanel();
        JTextField name = new JTextField(account.getFullname(), 40);
        JTextField phone = new JTextField(account.getPhone(), 40);
        JTextField email = new JTextField(account.getEmail(), 40);
        JTextField address = new JTextField(account.getAddress(), 40);
        UtilDateModel model = new UtilDateModel();
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(account.getDob().getTime()));
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

        JButton updateButton = new JButton("Cập nhật");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date date = (Date) txtDate.getModel().getValue();
                Timestamp selectedDate = new Timestamp(date.getTime());
                EmployeeBLO.updateAccInfo(account.getId(), name.getText(), phone.getText(), email.getText(), "", address.getText(), selectedDate);
                setAccount(EmployeeDAO.getEmployeeById(account.getId()));
                refresh();
            }
        });
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
        setPreferredSize(new Dimension(800, 700));
        setMaximumSize(new Dimension(800, 700));
    }
    public Account(EmployeeDTO account) {
        setAccount(account);
        removeAll();
        buildUI();
    }

    public Account() {
        if (account == null) return;
        removeAll();
        buildUI();
    }

    public void setAccount(EmployeeDTO account) {
        this.account = account;
    }

    public EmployeeDTO getAccount() {
        return account;
    }

    public void refresh() {
        removeAll();
        buildUI();
    }
}
