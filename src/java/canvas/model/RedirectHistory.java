/*
 * Author: jianqing
 * Date: Jun 6, 2020
 * Description: This document is created for one record of user visiting the link.
 */
package canvas.model;

import canvas.connector.LuckyStorage;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This represent one record of user visiting the link.
 *
 * @author jianqing
 */
public class RedirectHistory
{

    private int redirectId;
    private String ip;
    private LocalDateTime redirectDateTime;

    public static String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public RedirectHistory()
    {
        redirectId = 0;
        ip = null;
        redirectDateTime = null;
    }

    public RedirectHistory(int redirectId, String ip, LocalDateTime redirectDateTime)
    {
        this.redirectId = redirectId;
        this.ip = ip;
        this.redirectDateTime = redirectDateTime;
    }

    public RedirectHistory(int redirectId, String ip, CharSequence redirectDateTime)
    {
        this.redirectId = redirectId;
        this.ip = ip;
        this.redirectDateTime = formatDateTime(redirectDateTime);
    }

    public int getRedirectId()
    {
        return redirectId;
    }

    public void setRedirectId(int redirectId)
    {
        this.redirectId = redirectId;
    }

    public String getIp()
    {
        return ip;
    }

    public void setIp(String ip)
    {
        this.ip = ip;
    }

    public LocalDateTime getRedirectDateTime()
    {
        return redirectDateTime;
    }

    public void setRedirectDateTime(LocalDateTime redirectDateTime)
    {
        this.redirectDateTime = redirectDateTime;
    }

    /**
     * Parse the redirectdatetime to the string format using the constant.
     *
     * @return
     */
    public String getFormattedDateTime()
    {
        return formatDateTime(this.redirectDateTime);
    }

    public void setRedirectDateTime(CharSequence redirectDateTime)
    {
        this.redirectDateTime = formatDateTime(redirectDateTime);
    }

    //parse String <---> LocalDateTime
    public static String formatDateTime(LocalDateTime dateTime)
    {
        return DateTimeFormatter.ofPattern(DATETIME_FORMAT).format(dateTime);
    }

    public static LocalDateTime formatDateTime(CharSequence dateTime)
    {
        return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern(DATETIME_FORMAT));
    }

    
    
    ////////////////////DATABASE METHODS////////////////
    public void deleteFromDB() throws Exception
    {
        try (LuckyStorage storage = LuckyStorage.getDefaultInstance())
        {
            storage.deleteFromRedirectHistory(redirectId, formatDateTime(redirectDateTime));
        }
    }

    public void insertIntoDB() throws Exception
    {
        try (LuckyStorage storage = LuckyStorage.getDefaultInstance())
        {
            storage.insertIntoRedirectHistory(this);
        }
    }

}
