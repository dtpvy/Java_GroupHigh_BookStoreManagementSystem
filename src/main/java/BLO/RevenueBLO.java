package BLO;

import DAO.*;
import DTO.*;

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

//    public static DefaultCategoryDataset getCustomerRevenueByDate(){
//
//    }
}
