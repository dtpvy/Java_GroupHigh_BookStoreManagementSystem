package BLO;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import DTO.BookDTO;
import DTO.PromotionDTO;
import DAO.PromotionDAO;
import DTO.PromotionBookDTO;
public class PromotionBLO {
    public static void updatePromotionInfo(PromotionDTO promotion){
        Date date = new Date();
        Timestamp cur_date = new Timestamp(date.getTime());
        promotion.setUpdatedAt(cur_date);
        PromotionDAO.updatePromotion(promotion);
    }
    public static void disablePromotion(PromotionDTO promotion){
        promotion.setDisable(false);
    }
    public static void enablePromotion(PromotionDTO promotion){
        promotion.setDisable(true);
    }

    public static void addBookForPromotion(PromotionDTO promotion, BookDTO book){
        PromotionBookDTO p = new PromotionBookDTO(promotion, book);
        promotion.addBookApplied(p);
    }

    public static void removeBookForPromotion(PromotionDTO promotion, BookDTO book){
        PromotionBookDTO p = new PromotionBookDTO(promotion, book);
        promotion.removeBookApplied(p);
    }

    public static void enableAvailableForCustomer(PromotionDTO promotion){
        promotion.setCustomerApplied(true);
    }

    public static void disableAvailableForCustomer(PromotionDTO promotion){
        promotion.setCustomerApplied(false);
    }

    public static void disableAvailableForAnonymous(PromotionDTO promotion){
        promotion.setAnonymousApplied(false);
    }

    public static void enableAvailableForAnonymous(PromotionDTO promotion){
        promotion.setAnonymousApplied(true);
    }

    public static PromotionDTO addPromotion(String code, String description, double discountPercent, int available, boolean customerApplied, boolean anonymousApplied, Timestamp startDate, Timestamp endDate, List<BookDTO> bookApplied, boolean disable){
        Date date = new Date();
        Timestamp cur_date = new Timestamp(date.getTime());
        PromotionDTO promotion = new PromotionDTO(code, description, discountPercent, available, customerApplied, anonymousApplied, startDate, endDate, cur_date, cur_date, disable);
        for (BookDTO b : bookApplied){
            PromotionBookDTO pb = new PromotionBookDTO(promotion,b);
            promotion.addBookApplied(pb);
        }
        PromotionDAO.addPromotion(promotion);
        return promotion;
    }
    public static List<PromotionDTO> getPromotionList(String search, String sortType, String sort){
        return PromotionDAO.getPromotionList(search, sortType.toLowerCase(), sort);
    }
}
