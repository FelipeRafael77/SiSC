package util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {

    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public static Date stringToData(String str) {
        try {
            return sdf.parse(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static String dataToString(Date data) {
        return sdf.format(data);
    }
}
