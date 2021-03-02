/*
 * Author: jianqing
 * Date: May 6, 2020
 * Description: This document is created for
 */
package canvas.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author jianqing
 */
public class TimeConverter
{
    public static final DateTimeFormatter STANDARD_DATETIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static String formatDateTime(LocalDateTime dateTime)
    {
        return STANDARD_DATETIME_FORMATTER.format(dateTime);
    }
    
    public static LocalDateTime formatDateTime(String datetime)
    {
        return LocalDateTime.from(STANDARD_DATETIME_FORMATTER.parse(datetime));
    }
    
    public static void main(String[] args)
    {
        System.out.println(formatDateTime(LocalDateTime.now(ZoneId.of("UTC")).plusMinutes(480)));
    }
}
