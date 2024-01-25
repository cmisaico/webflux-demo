package com.sermaluc.helpers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    private DateUtil() {}

    public static String formatearFecha(LocalDateTime fecha){
        if(fecha != null)
            return fecha.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
        else
            return "";
    }
}
