package GUI;

import BLO.AuthorBLO;
import DAO.AuthorDAO;
import DAO.CategoryDAO;
import DAO.PublisherDAO;
import DTO.*;
import BLO.BookBLO;
import BLO.PublisherBLO;
import DTO.BookDTO;
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
import java.util.Date;
import java.util.List;

public class Book extends JPanel {
    List<BookDTO> bookList;
    BookDTO BookActive;

    String search = "";
    String sortType = "id";
    String sort = "ASC";
    String[] columnNames = { "ID", "Thể loại", "Nhà xuất bản", "Tác giả", "Tên", "Mô tả", "Giá", "Số lượng", "Ngày tạo", "Cập nhật lần cuối", "Trạng thái" };


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
        JTextField category = new JTextField(20);
        JTextField publisher = new JTextField(20);
        JTextField author = new JTextField(20);
        JTextField name = new JTextField(20);
        JTextField description = new JTextField(100);
        JTextField price = new JTextField(20);
        JTextField quantity = new JTextField(20);
        JTextField status = new JTextField(20);

        id.setEnabled(false);

        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));

        JLabel labelId = new JLabel("ID:");
        JPanel idPanel = new JPanel();
        idPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        idPanel.add(labelId);
        idPanel.add(id);

        JLabel labelCategory = new JLabel("Thể loại: ");
        JPanel categoryPanel = new JPanel();
        categoryPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        categoryPanel.add(labelCategory);
        categoryPanel.add(category);

        JLabel labelPublisher = new JLabel("Nhà xuất bản: ");
        JPanel publisherPanel = new JPanel();
        publisherPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        publisherPanel.add(labelPublisher);
        publisherPanel.add(publisher);

        JLabel labelAuthor = new JLabel("Tác giả: ");
        JPanel authorPanel = new JPanel();
        authorPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        authorPanel.add(labelAuthor);
        authorPanel.add(author);

        JLabel labelName = new JLabel("Tên: ");
        JPanel namePanel = new JPanel();
        namePanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        namePanel.add(labelName);
        namePanel.add(category);

        JLabel labelDescription = new JLabel("Giới thiệu: ");
        JPanel descriptionPanel = new JPanel();
        descriptionPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        descriptionPanel.add(labelDescription);
        descriptionPanel.add(description);

        JLabel labelPrice = new JLabel("Giá tiền: ");
        JPanel pricePanel = new JPanel();
        pricePanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        pricePanel.add(labelPrice);
        pricePanel.add(price);

        JLabel labelQuantity = new JLabel("Số lượng: ");
        JPanel quantityPanel = new JPanel();
        quantityPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        quantityPanel.add(labelQuantity);
        quantityPanel.add(quantity);

        JLabel labelStatus = new JLabel("Trạng thái:");
        JPanel statusPanel = new JPanel();
        statusPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        statusPanel.add(labelStatus);
        statusPanel.add(status);
        status.setEnabled(false);
        status.setText("Enable");

        formPanel.add(idPanel);
        formPanel.add(categoryPanel);
        formPanel.add(publisherPanel);
        formPanel.add(authorPanel);
        formPanel.add(namePanel);
        formPanel.add(descriptionPanel);
        formPanel.add(pricePanel);
        formPanel.add(quantityPanel);
        formPanel.add(statusPanel);

        JPanel actionPanel = new JPanel();
        actionPanel.setLayout(new GridLayout(5,1));


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

        JButton disableButton = new JButton("Disable");
        disableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (BookActive == null || !BookActive.isDisable()) return;
                BookBLO.disableBook(BookActive);
                status.setText("Disable");
            }
        });
        JButton enableButton = new JButton("Enable");
        enableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (BookActive == null || !BookActive.isDisable()) return;
                BookBLO.enableBook(BookActive);
                status.setText("Enable");
            }
        });
        JButton addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int catAdd = Integer.parseInt(category.getText());
                int pubAdd = Integer.parseInt(publisher.getText());
                int auAdd = Integer.parseInt(author.getText());
                String nameAdd = name.getText();
                String desAdd = description.getText();
                double priceAdd = Double.parseDouble(price.getText());
                int quantityAdd = Integer.parseInt(quantity.getText());
                boolean disable = status.getText().equals("Enable");

                BookDTO newBook = BookBLO.addBook(catAdd, pubAdd, auAdd, nameAdd, desAdd, priceAdd, quantityAdd, disable);
                DefaultTableModel model = (DefaultTableModel) jTable.getModel();
                model.addRow(new String[]{String.valueOf(newBook.getCategory()), String.valueOf(newBook.getPublisher()), String.valueOf(newBook.getAuthor()), newBook.getName(), newBook.getDescription(), String.valueOf(newBook.getPrice()), String.valueOf(newBook.getQuantity()), TimeUtil.formatDate(newBook.getCreatedAt()), TimeUtil.formatDate(newBook.getUpdatedAt()), newBook.isDisable() ? "Enable" : "Disable"});
            }
        });

        JButton updateButton = new JButton("Update");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BookActive.setName(name.getText());
                BookBLO.updateBookInfo(BookActive);
                jTable.getModel().setValueAt(BookActive.getCategory(), jTable.getSelectedRow(), 0);
                jTable.getModel().setValueAt(BookActive.getPublisher(), jTable.getSelectedRow(), 1);
                jTable.getModel().setValueAt(BookActive.getAuthor(), jTable.getSelectedRow(), 2);
                jTable.getModel().setValueAt(BookActive.getName(), jTable.getSelectedRow(), 3);
                jTable.getModel().setValueAt(BookActive.getDescription(), jTable.getSelectedRow(), 4);
                jTable.getModel().setValueAt(BookActive.getPrice(), jTable.getSelectedRow(), 5);
                jTable.getModel().setValueAt(BookActive.getQuantity(), jTable.getSelectedRow(), 6);
                jTable.getModel().setValueAt(TimeUtil.formatDate(BookActive.getCreatedAt()), jTable.getSelectedRow(), 7);
                jTable.getModel().setValueAt(TimeUtil.formatDate(BookActive.getUpdatedAt()), jTable.getSelectedRow(), 8);
                jTable.getModel().setValueAt(BookActive.isDisable() ? "Enable" : "Disable", jTable.getSelectedRow(), 9);

            }
        });

        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                id.setText("");
                status.setText("Enable");
                name.setText("");
                setBookActive(null);
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
                setBookList();
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
                setBookList();
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
                setBookList();
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
                int idGet = Integer.parseInt(jTable.getValueAt(jTable.getSelectedRow(), 0).toString());
                int catIDGet = Integer.parseInt(jTable.getValueAt(jTable.getSelectedRow(), 1).toString());
                int pubIDGet = Integer.parseInt(jTable.getValueAt(jTable.getSelectedRow(), 2).toString());
                int auIDGet = Integer.parseInt(jTable.getValueAt(jTable.getSelectedRow(), 3).toString());

                String nameGet = jTable.getValueAt(jTable.getSelectedRow(), 4).toString();
                String desGet = jTable.getValueAt(jTable.getSelectedRow(), 5).toString();
                double priceGet = Double.parseDouble(jTable.getValueAt(jTable.getSelectedRow(), 6).toString());
                int quantityGet = Integer.parseInt(jTable.getValueAt(jTable.getSelectedRow(), 7).toString());

                String statusGet = jTable.getValueAt(jTable.getSelectedRow(), 10).toString();

                id.setText(String.valueOf(idGet));
                id.setEnabled(false);
                category.setText(String.valueOf(catIDGet));
                publisher.setText(String.valueOf(pubIDGet));
                author.setText(String.valueOf(auIDGet));

                description.setText(desGet);
                name.setText(nameGet);
                price.setText(String.valueOf(priceGet));
                quantity.setText(String.valueOf(quantityGet));
                status.setText(statusGet);

                Timestamp createdAt = null;
                Timestamp updatedAt = null;
                try {
                    String ca = jTable.getValueAt(jTable.getSelectedRow(), 8).toString();
                    String ua = jTable.getValueAt(jTable.getSelectedRow(), 9).toString();
                    createdAt = new Timestamp((new SimpleDateFormat("dd/MM/yyyy").parse(ca).getTime()));
                    updatedAt = new Timestamp((new SimpleDateFormat("dd/MM/yyyy").parse(ua).getTime()));
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                setBookActive(new BookDTO(idGet, CategoryDAO.getCategoryById(catIDGet), PublisherDAO.getPublisherById(pubIDGet), AuthorDAO.getAuthorById(pubIDGet), nameGet, desGet, priceGet, quantityGet, createdAt, updatedAt, statusGet.equals("Enable") ? true : false));
            }
        });
        JScrollPane sp = new JScrollPane(jTable);
        tablePanel.add(sp);
        tablePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        tablePanel.setPreferredSize(new Dimension(700, 450));
        tablePanel.setMaximumSize(new Dimension(700, 450));
        add(tablePanel);

    }

    public Book() {
        refresh();
    }

    public void refresh() {
        setBookList();
        removeAll();
        buildUI();
    }
    public void setBookActive(BookDTO bookActive) {
        this.BookActive = bookActive;
    }
    public void setBookList() {
        this.bookList = BookBLO.getBookList(search, sortType, sort);
    }
    public String[][] getTableData() {
        String[][] data = new String[bookList.size()][10];
        for (int i = 0; i < bookList.size(); i++) {
            data[i] = new String[]{String.valueOf(bookList.get(i).getId()), String.valueOf(bookList.get(i).getCategory()), String.valueOf(bookList.get(i).getPublisher()), String.valueOf(bookList.get(i).getAuthor()), bookList.get(i).getName(), bookList.get(i).getDescription(), String.valueOf(bookList.get(i).getPrice()), String.valueOf(bookList.get(i).getQuantity()), TimeUtil.formatDate(bookList.get(i).getCreatedAt()), TimeUtil.formatDate(bookList.get(i).getUpdatedAt()), bookList.get(i).isDisable() ? "Enable" : "Disable"};
        }
        return data;
    }
}
