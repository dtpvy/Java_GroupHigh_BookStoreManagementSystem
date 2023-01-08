package BLO;

import DAO.PublisherDAO;
import DTO.PublisherDTO;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class PublisherBLO {
    public static void updatePublisherInfo(PublisherDTO publisher) {
        Date date = new Date();
        Timestamp current_date = new Timestamp(date.getTime());
        publisher.setUpdatedAt(current_date);
        PublisherDAO.updatePublisher(publisher);
    }

    public static void disablePublisher(PublisherDTO publisher) {
        publisher.setDisable(false);
    }

    public static void enablePublisher(PublisherDTO publisher) {
        publisher.setDisable(true);
    }

    public static PublisherDTO addPublisher(String name, String description, boolean disable) {
        Date date = new Date();
        Timestamp current_date = new Timestamp(date.getTime());
        PublisherDTO publisher = new PublisherDTO(name, description, current_date, current_date, disable);
        PublisherDAO.addPublisher(publisher);
        return publisher;
    }

    public static List<PublisherDTO> getPublisherList(String search, String sortType, String sort) {
        return PublisherDAO.getPublisherList(search, sortType.toLowerCase(), sort);
    }
}


