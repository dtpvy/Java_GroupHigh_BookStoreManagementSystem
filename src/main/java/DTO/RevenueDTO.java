package DTO;

import java.sql.Timestamp;
import java.util.List;

public interface RevenueDTO {
    public void addBookByOrder(OrderDTO order);
    public void addBookByOrderList(List<OrderDTO> orderList);
    public double getRevenueByDay(Timestamp date);
    public double getRevenueByRangeOfDay(Timestamp startDate, Timestamp endDate);
    public double getTotalRevenue();
}
