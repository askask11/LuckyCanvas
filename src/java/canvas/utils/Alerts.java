/*Editor: Johnson Gao 
 * Date This File Created: 2020-4-12 3:32:08
 * Description Of This Class:
 */
package canvas.utils;

/**
 *
 * @author Jianqing Gao
 */
public class Alerts
{

    public static final String IFRAME_NOT_SUPPORTED = "Your browser does not support iframe."
            + "Please look here for solution! <a href=\"https://windowsreport.com/browser-does-not-support-iframes/\">"
            + "Click Me!</a>. Thank you!";

    public static String successMessage(String title, String message)
    {
        return "<div class=\"alert alert-success alert-dismissible\">\n"
                + "  <button type=\"button\" class=\"close\" data-dismiss=\"alert\">&times;</button>\n"
                + "  <strong>" + title + "</strong> " + message + "\n"
                + "</div>";
    }
    
    public static String infoMessage(String title, String message)
    {
        return "<div class=\"alert alert-info alert-dismissible\">\n"
                + "  <button type=\"button\" class=\"close\" data-dismiss=\"alert\">&times;</button>\n"
                + "  <strong>" + title + "</strong> " + message + "\n"
                + "</div>";
    }
    
    public static String warningMessage(String title, String message)
    {
        return "<div class=\"alert alert-warning alert-dismissible\">\n"
                + "  <button type=\"button\" class=\"close\" data-dismiss=\"alert\">&times;</button>\n"
                + "  <strong>" + title + "</strong> " + message + "\n"
                + "</div>";
    }
    
    public static String dangerMessage(String title, String message)
    {
        return "<div class=\"alert alert-danger alert-dismissible\">\n"
                + "  <button type=\"button\" class=\"close\" data-dismiss=\"alert\">&times;</button>\n"
                + "  <strong>" + title + "</strong> " + message + "\n"
                + "</div>";
    }
    
    public static String primaryMessage(String title, String message)
    {
        return "<div class=\"alert alert-primary alert-dismissible\">\n"
                + "  <button type=\"button\" class=\"close\" data-dismiss=\"alert\">&times;</button>\n"
                + "  <strong>" + title + "</strong> " + message + "\n"
                + "</div>";
    }
    
    public static String secondaryMessage(String title, String message)
    {
        return "<div class=\"alert alert-secondary alert-dismissible\">\n"
                + "  <button type=\"button\" class=\"close\" data-dismiss=\"alert\">&times;</button>\n"
                + "  <strong>" + title + "</strong> " + message + "\n"
                + "</div>";
    }
    
     public static String lightMessage(String title, String message)
    {
        return "<div class=\"alert alert-light alert-dismissible\">\n"
                + "  <button type=\"button\" class=\"close\" data-dismiss=\"alert\">&times;</button>\n"
                + "  <strong>" + title + "</strong> " + message + "\n"
                + "</div>";
    }
     
      public static String darkMessage(String title, String message)
    {
        return "<div class=\"alert alert-dark alert-dismissible\">\n"
                + "  <button type=\"button\" class=\"close\" data-dismiss=\"alert\">&times;</button>\n"
                + "  <strong>" + title + "</strong> " + message + "\n"
                + "</div>";
    }

}
