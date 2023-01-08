package UTILS;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class TimeUtil {
    public static String formatDate(Timestamp time) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return formatter.format(time);
    }
}
