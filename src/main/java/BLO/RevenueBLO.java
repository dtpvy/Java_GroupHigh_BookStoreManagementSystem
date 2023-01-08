package BLO;

import DAO.*;
import DTO.*;

import GUI.Category;
import org.jfree.data.category.DefaultCategoryDataset;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Calendar;

public class RevenueBLO {
    public static DefaultCategoryDataset getBookRevenueByDate(){
        DefaultCategoryDataset res = new DefaultCategoryDataset();
        List<BookDTO> books = BookDAO.getBookList();
        for(BookDTO b : books){
            RevenueDTO r = new BookRevenueDTO(b);
            Date date = new Date();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Calendar c = Calendar.getInstance();
            Timestamp cur_date = new Timestamp(date.getTime());
            Timestamp start_date = new Timestamp(2022, 12, 1, 0, 0, 0, 0);
            while (start_date.compareTo(cur_date) <= 0){
                double rev = r.getRevenueByDay(start_date);
                Date d = new Date(start_date.getTime());
                res.addValue(rev, b.getName(), df.format(d));

                c.setTime(start_date);
                c.add(Calendar.DAY_OF_WEEK, 1);
                start_date.setTime(c.getTime().getTime());
            }
        }
        return res;
    }

    public static DefaultCategoryDataset getCustomerRevenueByDate(){
        DefaultCategoryDataset res = new DefaultCategoryDataset();
        List<CustomerDTO> customers = CustomerDAO.getCustomerList();
        for(CustomerDTO b : customers){
            RevenueDTO r = new CustomerRevenueDTO(b);
            Date date = new Date();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Calendar c = Calendar.getInstance();
            Timestamp cur_date = new Timestamp(date.getTime());
            Timestamp start_date = new Timestamp(2022, 12, 1, 0, 0, 0, 0);
            while (start_date.compareTo(cur_date) <= 0){
                double rev = r.getRevenueByDay(start_date);
                Date d = new Date(start_date.getTime());
                res.addValue(rev, b.getName(), df.format(d));

                c.setTime(start_date);
                c.add(Calendar.DAY_OF_WEEK, 1);
                start_date.setTime(c.getTime().getTime());
            }
        }
        return res;
    }

    public static DefaultCategoryDataset getEmployeeRevenueByDate(){
        DefaultCategoryDataset res = new DefaultCategoryDataset();
        List<EmployeeDTO> employees = EmployeeDAO.getEmployeeList();
        for(EmployeeDTO b : employees){
            RevenueDTO r = new EmployeeRevenueDTO(b);
            Date date = new Date();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Calendar c = Calendar.getInstance();
            Timestamp cur_date = new Timestamp(date.getTime());
            Timestamp start_date = new Timestamp(2022, 12, 1, 0, 0, 0, 0);
            while (start_date.compareTo(cur_date) <= 0){
                double rev = r.getRevenueByDay(start_date);
                Date d = new Date(start_date.getTime());
                res.addValue(rev, b.getFullname(), df.format(d));

                c.setTime(start_date);
                c.add(Calendar.DAY_OF_WEEK, 1);
                start_date.setTime(c.getTime().getTime());
            }
        }
        return res;
    }

    public static DefaultCategoryDataset getCategoryRevenueByDate(){
        DefaultCategoryDataset res = new DefaultCategoryDataset();
        List<CategoryDTO> categories = CategoryDAO.getCategoryList();
        for(CategoryDTO b : categories){
            RevenueDTO r = new CategoryRevenueDTO(b);
            Date date = new Date();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Calendar c = Calendar.getInstance();
            Timestamp cur_date = new Timestamp(date.getTime());
            Timestamp start_date = new Timestamp(2022, 12, 1, 0, 0, 0, 0);
            while (start_date.compareTo(cur_date) <= 0){
                double rev = r.getRevenueByDay(start_date);
                Date d = new Date(start_date.getTime());
                res.addValue(rev, b.getName(), df.format(d));

                c.setTime(start_date);
                c.add(Calendar.DAY_OF_WEEK, 1);
                start_date.setTime(c.getTime().getTime());
            }
        }
        return res;
    }

    public static DefaultCategoryDataset getBookRevenueByWeek(){
        DefaultCategoryDataset res = new DefaultCategoryDataset();
        List<BookDTO> books = BookDAO.getBookList();
        for(BookDTO b : books){
            RevenueDTO r = new BookRevenueDTO(b);
            Date date = new Date();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Calendar c = Calendar.getInstance();
            Timestamp cur_date = new Timestamp(date.getTime());
            Timestamp start_date = new Timestamp(2022, 12, 1, 0, 0, 0, 0);
            while (start_date.compareTo(cur_date) <= 0){
                c.setTime(start_date);
                c.add(Calendar.DAY_OF_WEEK, 7);
                Timestamp next_date = new Timestamp(c.getTime().getTime());

                double rev = r.getRevenueByRangeOfDay(start_date, next_date);
                Date d = new Date(start_date.getTime());
                res.addValue(rev, b.getName(), df.format(d));

                start_date = next_date;
            }
        }
        return res;
    }

    public static DefaultCategoryDataset getCustomerRevenueByWeek(){
        DefaultCategoryDataset res = new DefaultCategoryDataset();
        List<CustomerDTO> customers = CustomerDAO.getCustomerList();
        for(CustomerDTO b : customers){
            RevenueDTO r = new CustomerRevenueDTO(b);
            Date date = new Date();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Calendar c = Calendar.getInstance();
            Timestamp cur_date = new Timestamp(date.getTime());
            Timestamp start_date = new Timestamp(2022, 12, 1, 0, 0, 0, 0);
            while (start_date.compareTo(cur_date) <= 0){
                c.setTime(start_date);
                c.add(Calendar.DAY_OF_WEEK, 7);
                Timestamp next_date = new Timestamp(c.getTime().getTime());

                double rev = r.getRevenueByRangeOfDay(start_date, next_date);
                Date d = new Date(start_date.getTime());
                res.addValue(rev, b.getName(), df.format(d));

                start_date = next_date;
            }
        }
        return res;
    }

    public static DefaultCategoryDataset getEmployeeRevenueByWeek(){
        DefaultCategoryDataset res = new DefaultCategoryDataset();
        List<EmployeeDTO> employees = EmployeeDAO.getEmployeeList();
        for(EmployeeDTO b : employees){
            RevenueDTO r = new EmployeeRevenueDTO(b);
            Date date = new Date();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Calendar c = Calendar.getInstance();
            Timestamp cur_date = new Timestamp(date.getTime());
            Timestamp start_date = new Timestamp(2022, 12, 1, 0, 0, 0, 0);
            while (start_date.compareTo(cur_date) <= 0){
                c.setTime(start_date);
                c.add(Calendar.DAY_OF_WEEK, 7);
                Timestamp next_date = new Timestamp(c.getTime().getTime());

                double rev = r.getRevenueByRangeOfDay(start_date, next_date);
                Date d = new Date(start_date.getTime());
                res.addValue(rev, b.getFullname(), df.format(d));

                start_date = next_date;
            }
        }
        return res;
    }

    public static DefaultCategoryDataset getCategoryRevenueByWeek(){
        DefaultCategoryDataset res = new DefaultCategoryDataset();
        List<CategoryDTO> categories = CategoryDAO.getCategoryList();
        for(CategoryDTO b : categories){
            RevenueDTO r = new CategoryRevenueDTO(b);
            Date date = new Date();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Calendar c = Calendar.getInstance();
            Timestamp cur_date = new Timestamp(date.getTime());
            Timestamp start_date = new Timestamp(2022, 12, 1, 0, 0, 0, 0);
            while (start_date.compareTo(cur_date) <= 0){
                c.setTime(start_date);
                c.add(Calendar.DAY_OF_WEEK, 7);
                Timestamp next_date = new Timestamp(c.getTime().getTime());

                double rev = r.getRevenueByRangeOfDay(start_date, next_date);
                Date d = new Date(start_date.getTime());
                res.addValue(rev, b.getName(), df.format(d));

                start_date = next_date;
            }
        }
        return res;
    }

    public static DefaultCategoryDataset getBookRevenueByMonth(){
        DefaultCategoryDataset res = new DefaultCategoryDataset();
        List<BookDTO> books = BookDAO.getBookList();
        for(BookDTO b : books){
            RevenueDTO r = new BookRevenueDTO(b);
            Date date = new Date();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Calendar c = Calendar.getInstance();
            Timestamp cur_date = new Timestamp(date.getTime());
            Timestamp start_date = new Timestamp(2022, 12, 1, 0, 0, 0, 0);
            while (start_date.compareTo(cur_date) <= 0){
                c.setTime(start_date);
                c.add(Calendar.DAY_OF_WEEK, 31);
                Timestamp next_date = new Timestamp(c.getTime().getTime());

                double rev = r.getRevenueByRangeOfDay(start_date, next_date);
                Date d = new Date(start_date.getTime());
                res.addValue(rev, b.getName(), df.format(d));

                start_date = next_date;
            }
        }
        return res;
    }

    public static DefaultCategoryDataset getCustomerRevenueByMonth(){
        DefaultCategoryDataset res = new DefaultCategoryDataset();
        List<CustomerDTO> customers = CustomerDAO.getCustomerList();
        for(CustomerDTO b : customers){
            RevenueDTO r = new CustomerRevenueDTO(b);
            Date date = new Date();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Calendar c = Calendar.getInstance();
            Timestamp cur_date = new Timestamp(date.getTime());
            Timestamp start_date = new Timestamp(2022, 12, 1, 0, 0, 0, 0);
            while (start_date.compareTo(cur_date) <= 0){
                c.setTime(start_date);
                c.add(Calendar.DAY_OF_WEEK, 31);
                Timestamp next_date = new Timestamp(c.getTime().getTime());

                double rev = r.getRevenueByRangeOfDay(start_date, next_date);
                Date d = new Date(start_date.getTime());
                res.addValue(rev, b.getName(), df.format(d));

                start_date = next_date;
            }
        }
        return res;
    }

    public static DefaultCategoryDataset getEmployeeRevenueByMonth(){
        DefaultCategoryDataset res = new DefaultCategoryDataset();
        List<EmployeeDTO> employees = EmployeeDAO.getEmployeeList();
        for(EmployeeDTO b : employees){
            RevenueDTO r = new EmployeeRevenueDTO(b);
            Date date = new Date();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Calendar c = Calendar.getInstance();
            Timestamp cur_date = new Timestamp(date.getTime());
            Timestamp start_date = new Timestamp(2022, 12, 1, 0, 0, 0, 0);
            while (start_date.compareTo(cur_date) <= 0){
                c.setTime(start_date);
                c.add(Calendar.DAY_OF_WEEK, 31);
                Timestamp next_date = new Timestamp(c.getTime().getTime());

                double rev = r.getRevenueByRangeOfDay(start_date, next_date);
                Date d = new Date(start_date.getTime());
                res.addValue(rev, b.getFullname(), df.format(d));

                start_date = next_date;
            }
        }
        return res;
    }

    public static DefaultCategoryDataset getCategoryRevenueByMonth(){
        DefaultCategoryDataset res = new DefaultCategoryDataset();
        List<CategoryDTO> categories = CategoryDAO.getCategoryList();
        for(CategoryDTO b : categories){
            RevenueDTO r = new CategoryRevenueDTO(b);
            Date date = new Date();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Calendar c = Calendar.getInstance();
            Timestamp cur_date = new Timestamp(date.getTime());
            Timestamp start_date = new Timestamp(2022, 12, 1, 0, 0, 0, 0);
            while (start_date.compareTo(cur_date) <= 0){
                c.setTime(start_date);
                c.add(Calendar.DAY_OF_WEEK, 31);
                Timestamp next_date = new Timestamp(c.getTime().getTime());

                double rev = r.getRevenueByRangeOfDay(start_date, next_date);
                Date d = new Date(start_date.getTime());
                res.addValue(rev, b.getName(), df.format(d));

                start_date = next_date;
            }
        }
        return res;
    }
}
