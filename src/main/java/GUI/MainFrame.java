package GUI;

import DTO.EmployeeDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class MainFrame extends JFrame {
    EmployeeDTO account;
    JPanel bodyPanel;
    List<TabMenu> tabMenus = new ArrayList<>();
    Account accountPanel = new Account();
    Employee employeePanel = new Employee();
    Promotion promotionPanel = new Promotion();
    Order orderPanel = new Order();
    Category categoryPanel = new Category();
    Book bookPanel = new Book();
    void buildUI() {
        setTitle("Hệ thống quản lý cửa hàng sách");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));

        JPanel menu = new JPanel();
        menu.setAlignmentX(Component.CENTER_ALIGNMENT);
        menu.setPreferredSize(new Dimension(200, 700));
        menu.setMaximumSize(new Dimension(200, 700));

        ImageIcon icon = new ImageIcon("src/main/resources/images/loginbg.png");
        Image scaleImage = icon.getImage().getScaledInstance(150, 150,Image.SCALE_DEFAULT);
        JLabel picLabel = new JLabel(new ImageIcon(scaleImage), JLabel.CENTER);
        JPanel image = new JPanel();
        image.setBackground(Color.orange);
        image.setLayout(new GridLayout(1,1));
        image.add(picLabel);
        TabMenu name = new TabMenu("Tên: " + account.getUsername(), false);
        menu.setBackground(Color.orange);
        menu.add(image);
        menu.add(name);

        TabMenu accountButton = new TabMenu("Tài khoản", true);
        tabMenus.add(accountButton);
        accountButton.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                inactiveTabs();
                onAccountTab();
                accountButton.setIsActive(true);
            }
        });

        TabMenu employeeButton = new TabMenu("Nhân viên", false);
        tabMenus.add(employeeButton);
        employeeButton.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                inactiveTabs();
                onEmployeeTab();
                employeeButton.setIsActive(true);
            }
        });

        TabMenu authorButton = new TabMenu("Tác giả", false);
        tabMenus.add(authorButton);
        authorButton.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                inactiveTabs();
                onAuthorTab();
                authorButton.setIsActive(true);
            }
        });

        TabMenu publisherButton = new TabMenu("Nhà sản xuất", false);
        tabMenus.add(publisherButton);
        publisherButton.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                inactiveTabs();
                onPublisherTab();
                publisherButton.setIsActive(true);
            }
        });

        TabMenu categoryButton = new TabMenu("Thể loại sách", false);
        tabMenus.add(categoryButton);
        categoryButton.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                inactiveTabs();
                onCategoryTab();
                categoryButton.setIsActive(true);
            }
        });

        TabMenu bookButton = new TabMenu("Sách", false);
        tabMenus.add(bookButton);
        bookButton.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                inactiveTabs();
                onBookTab();
                bookButton.setIsActive(true);
            }
        });

        TabMenu analysisButton = new TabMenu("Thống kê", false);
        tabMenus.add(analysisButton);
        analysisButton.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                inactiveTabs();
                onAuthorTab();
                analysisButton.setIsActive(true);
            }
        });

        TabMenu promotionButton = new TabMenu("Mã giảm giá", false);
        tabMenus.add(promotionButton);
        promotionButton.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                inactiveTabs();
                onPromotionTab();
                promotionButton.setIsActive(true);
            }
        });

        TabMenu orderButton = new TabMenu("Đơn hàng", false);
        tabMenus.add(orderButton);
        orderButton.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                inactiveTabs();
                onOrderTab();
                orderButton.setIsActive(true);
            }
        });


        menu.add(accountButton);
        menu.add(employeeButton);
        menu.add(authorButton);
        menu.add(publisherButton);
        menu.add(categoryButton);
        menu.add(bookButton);
        menu.add(analysisButton);
        menu.add(promotionButton);
        menu.add(orderButton);
        menu.setLayout(new BoxLayout(menu, BoxLayout.Y_AXIS));

        if (accountPanel.getAccount() == null) {
            accountPanel.setAccount(account);
            employeePanel.setAccount(account);
            employeePanel.refreshUI();
            accountPanel.refresh();
        }
        bodyPanel = accountPanel;
        bodyPanel.setPreferredSize(new Dimension(800, 700));
        bodyPanel.setMaximumSize(new Dimension(800, 700)); // set max = pref
        bodyPanel.setBorder(BorderFactory.createTitledBorder(""));

        getContentPane().add(menu);
        getContentPane().add(bodyPanel);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        pack();
    }
    public MainFrame() {
        if (account == null) return;
        buildUI();
    }

    public void setAccount(EmployeeDTO account) {
        this.account = account;
        getContentPane().removeAll();
        buildUI();
        repaint();
    }
    public void showFrame() {
        setVisible(true);
    }
    private void inactiveTabs() {
        for (TabMenu tabMenu : tabMenus) {
            tabMenu.setIsActive(false);
        }
    }
    public void onAccountTab() {
        remove(bodyPanel);
        accountPanel.refreshData();
        accountPanel.refresh();
        bodyPanel = accountPanel;
        bodyPanel.setPreferredSize(new Dimension(800, 800));
        bodyPanel.setMaximumSize(new Dimension(800, 800)); // set max = pref
        bodyPanel.setBorder(BorderFactory.createTitledBorder(""));
        add(bodyPanel);

        revalidate();
        bodyPanel.repaint();
    }
    public void onEmployeeTab() {
        if (!account.getAccessType()) return;
        remove(bodyPanel);
        employeePanel.refresh();
        bodyPanel = employeePanel;
        bodyPanel.setPreferredSize(new Dimension(800, 800));
        bodyPanel.setMaximumSize(new Dimension(800, 800)); // set max = pref
        bodyPanel.setBorder(BorderFactory.createTitledBorder(""));

        add(bodyPanel);
        revalidate();
        bodyPanel.repaint();
    }
    public void onPromotionTab() {
        remove(bodyPanel);
        promotionPanel.refresh();
        bodyPanel = promotionPanel;
        bodyPanel.setPreferredSize(new Dimension(800, 800));
        bodyPanel.setMaximumSize(new Dimension(800, 800)); // set max = pref
        bodyPanel.setBorder(BorderFactory.createTitledBorder(""));

        add(bodyPanel);
        revalidate();
        bodyPanel.repaint();
    }
    public void onOrderTab() {
        remove(bodyPanel);
        orderPanel.refresh();
        bodyPanel = orderPanel;
        bodyPanel.setPreferredSize(new Dimension(800, 800));
        bodyPanel.setMaximumSize(new Dimension(800, 800)); // set max = pref
        bodyPanel.setBorder(BorderFactory.createTitledBorder(""));

        add(bodyPanel);
        revalidate();
        bodyPanel.repaint();
    }
    public void onAuthorTab() {
        remove(bodyPanel);
        bodyPanel = new Author();
        bodyPanel.setPreferredSize(new Dimension(800, 800));
        bodyPanel.setMaximumSize(new Dimension(800, 800)); // set max = pref
        bodyPanel.setBorder(BorderFactory.createTitledBorder(""));

        add(bodyPanel);
        revalidate();
        bodyPanel.repaint();
    }
    public void onPublisherTab() {
        remove(bodyPanel);
        bodyPanel = new Publisher();
        bodyPanel.setPreferredSize(new Dimension(800, 800));
        bodyPanel.setMaximumSize(new Dimension(800, 800)); // set max = pref
        bodyPanel.setBorder(BorderFactory.createTitledBorder(""));

        add(bodyPanel);
        revalidate();
        bodyPanel.repaint();

    }
    public void onCategoryTab() {
        remove(bodyPanel);
        categoryPanel.refresh();
        bodyPanel = categoryPanel;
        bodyPanel.setPreferredSize(new Dimension(800, 800));
        bodyPanel.setMaximumSize(new Dimension(800, 800)); // set max = pref
        bodyPanel.setBorder(BorderFactory.createTitledBorder(""));

        add(bodyPanel);
        revalidate();
        bodyPanel.repaint();
    }
    public void onBookTab() {
        remove(bodyPanel);
        bookPanel.refresh();
        bodyPanel = bookPanel;
        bodyPanel.setPreferredSize(new Dimension(800, 800));
        bodyPanel.setMaximumSize(new Dimension(800, 800)); // set max = pref
        bodyPanel.setBorder(BorderFactory.createTitledBorder(""));

        add(bodyPanel);
        revalidate();
        bodyPanel.repaint();
    }
    public void onAnalysisTab() {
        setVisible(true);
    }
}
