/*
 * Author: jianqing
 * Date: May 1, 2020
 * Description: This document is created for
 */
package canvas.model;

import java.time.LocalDateTime;

/**
 *
 * @author jianqing
 */
public class URLProxy extends Object
{
    private int id;
    private String name;
    private LocalDateTime generatedDate;
    private String redirectTo;
    private String userEmail;
    private int visits;
    
    

   

    public URLProxy(int id, String name, LocalDateTime generatedDate, String redirectTo, String userEmail)
    {
        this.id = id;
        this.name = name;
        this.generatedDate = generatedDate;
        this.redirectTo = redirectTo;
        this.userEmail = userEmail;
    }

    public URLProxy(int id, String name, LocalDateTime generatedDate, String redirectTo, String userEmail, int visits)
    {
        this.id = id;
        this.name = name;
        this.generatedDate = generatedDate;
        this.redirectTo = redirectTo;
        this.userEmail = userEmail;
        this.visits = visits;
    }
    
    

    public URLProxy()
    {
    }

//    public URLProxy(int id, LocalDateTime generatedDate, String redirectTo, String userEmail)
//    {
//        this.id = id;
//        this.generatedDate = generatedDate;
//        this.redirectTo = redirectTo;
//        this.userEmail = userEmail;
//    }

    public int getVisits()
    {
        return visits;
    }

    public void setVisits(int visits)
    {
        this.visits = visits;
    }

    
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getUserEmail()
    {
        return userEmail;
    }

    public void setUserEmail(String userEmail)
    {
        this.userEmail = userEmail;
    }
    
    

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public LocalDateTime getGeneratedDate()
    {
        return generatedDate;
    }

    public void setGeneratedDate(LocalDateTime generatedDate)
    {
        this.generatedDate = generatedDate;
    }

    public String getRedirectTo()
    {
        return redirectTo;
    }

    public void setRedirectTo(String redirectTo)
    {
        this.redirectTo = redirectTo;
    }
    
    public String getProxyURL()
    {
        return "https://luckycanvas.cn/Redirect?id="+id;
    }
    
    public String getEmbedCode()
    {
        return "<iframe src=\"" + getProxyURL() + "\" style=\"width:100%; height: 500px;\">Your browser does not support iframe</iframe>";
    }

    @Override
    public String toString()
    {
        return "URLProxy{" + "id=" + id + ", generatedDate=" + generatedDate + ", redirectTo=" + redirectTo + '}';
    }
    
    
    
    
}
