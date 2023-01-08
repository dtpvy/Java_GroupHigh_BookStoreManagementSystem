import BLO.AuthorBLO;
import BLO.BookBLO;
import BLO.PublisherBLO;
import DTO.*;
import DAO.*;
import GUI.App;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Date;
import java.util.Set;
import java.util.HashSet;

public class BookStoreManagementSystem {
        public static void main(String[] args){

//        App app = new App();
//        app.loadApp();

//        BookDTO book = BookDAO.getBookById("001");
//        CategoryDTO cat = CategoryDAO.getCategoryById("001");
//        AuthorDTO aut = AuthorDAO.getAuthorById("001");
//        PublisherDTO pub = PublisherDAO.getPublisherById("001");

//        System.out.println(cat);

//        Timestamp ti = new Timestamp(2022, 02, 11, 00, 00, 00, 00);
//        BookDTO book1 = new BookDTO("002", cat, pub, aut, "testbook2", "random description", 50.0, 10, ti, ti);
//        BookDTO book2 = new BookDTO("003", cat, pub, aut, "testbook3", "random description", 50.0, 10, ti, ti);
//        book.setDescription("random description");
//
//        BookDAO.updateBook(book);
//        BookDAO.addBook(book1);
//        BookDAO.addBook(book2);

//        BookDAO.removeBook("003");


                // BookDTO book = BookDAO.getBookById(1);
                // System.out.println(book);
                Date date = new Date();
                Timestamp cur_date = new Timestamp(date.getTime());
                // BookDTO book = new BookDTO(cat, pub, aut, "test book 1", "this is a test book", 25.5, 100, cur_date, cur_date);

                // BookDAO.addBook(book);

                // List<BookDTO>  list = BookDAO.getBookList();
                // System.out.println(cat + "\n" + aut + "\n" + pub);
                // System.out.println(list);

                // List<AdminDTO> admins = AdminDAO.getAdminList();
                // System.out.println(admins);


                // System.out.println(BLO.AccountBLO.checkUserLogin("khoildm", "25asdf02"));
                // CustomerDTO c = new CustomerDTO("khoi", "0778661298", cur_date, cur_date);
                // CustomerDAO.addCustomer(c);


                //BLO.EmployeeBLO.addAdmin("khoil", "25082002", "khoi le dang minh", "123456", "khoi@gmail.com", null, "ho chi minh", cur_date);

//                BookDTO b1 = BookDAO.getBookById(2);
//                BookDTO b2 = BookDAO.getBookById(4);
//                System.out.println(b1);

//                PublisherDTO p1 = PublisherDAO.getPublisherById(1);
//                System.out.print(p1);

                List<BookDTO> getBookList = BookBLO.getBookList("", "id", "asc");
                for(BookDTO i:getBookList) {
                        System.out.println(i);
                }
                List<AuthorDTO> getAuthorList = AuthorBLO.getAuthorList("", "id", "asc");
                for(AuthorDTO i:getAuthorList) {
                        System.out.println(i);
                }
                List<PublisherDTO> getPublisherList = PublisherBLO.getPublisherList("", "id", "asc");
                for(PublisherDTO i:getPublisherList) {
                        System.out.println(i);
                }

//
//        PromotionDTO p = new PromotionDTO("123", "this is a code", 0.7, 100, true, true, cur_date, cur_date, cur_date, cur_date);
//
//        PromotionBookDTO book1 = new PromotionBookDTO(p, b1);
//        PromotionBookDTO book2 = new PromotionBookDTO(p, b2);
//        Set<PromotionBookDTO> books = new HashSet<>();
//        books.add(book1);
//        books.add(book2);
//
//        p.setBookApplied(books);
//
//        DAO.PromotionDAO.addPromotion(p);


//        CustomerDTO c = DAO.CustomerDAO.getCustomerById(1);
//        EmployeeDTO e = DAO.EmployeeDAO.getEmployeeById(1);
//        PromotionDTO p = DAO.PromotionDAO.getPromotionById(4);
//
//        OrderDTO o = new OrderDTO(c, e, "this is a order", cur_date, cur_date);
//        OrderBookDTO books1 = new OrderBookDTO(o, b1, 2);
//        OrderBookDTO books2 = new OrderBookDTO(o, b2, 3);
//        Set<OrderBookDTO> b = new HashSet<>();
//        b.add(books1);
//        b.add(books2);
//
//        OrderPromotionDTO op = new OrderPromotionDTO(p, o);
//        Set<OrderPromotionDTO> ops = new HashSet<>();
//        ops.add(op);
//
//        o.setPromotionApplied(ops);
//        o.setItems(b);
//
//        DAO.OrderDAO.addOrder(o);

//        OrderDTO o = DAO.OrderDAO.getOrderById(12);
//        OrderBookDTO books1 = new OrderBookDTO(o, b1, 2);
//        OrderBookDTO books4 = new OrderBookDTO(o, DAO.BookDAO.getBookById(4), 3);
//        o.addItem(books4);
//        o.removeItem(books1);
//        DAO.OrderDAO.updateOrder(o);


//        PromotionDTO p = DAO.PromotionDAO.getPromotionById(3);
//        BookDTO book3 = DAO.BookDAO.getBookById(3);
//        BookDTO book2 = DAO.BookDAO.getBookById(2);
//        PromotionBookDTO b = new PromotionBookDTO(p, book3);
//        PromotionBookDTO b2 = new PromotionBookDTO(p, book2);
////        p.addBookApplied(b);
//        p.removeBookApplied(b2);
//        for (PromotionBookDTO pb : p.getBookApplied()){
//            System.out.println(pb);
//        }
//        DAO.PromotionDAO.updatePromotion(p);
//        DAO.PromotionDAO.removePromotion(3);

//        BLO.EmployeeBLO.addAdmin("khoil", "25082002", "khoi le dang minh", "123456", "khoi@gmail.com", null, "ho chi minh", cur_date);

//                CategoryDTO c = CategoryDAO.getCategoryById(4);
//                EmployeeDTO e = EmployeeDAO.getEmployeeById(1);
//                List<OrderDTO> o = DAO.OrderDAO.getOrderList("id", "asc");
//                RevenueDTO r = new EmployeeRevenueDTO(e);
//                r.addBookByOrderList(o);
//                System.out.print(r.getTotalRevenue());
        }
}