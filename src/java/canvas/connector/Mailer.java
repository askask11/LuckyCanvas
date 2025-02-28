/*Editor: Johnson Gao 
 * Date This File Created: 2020-3-11 21:03:57
 * Description Of This Class:This class is responsible for sending email to the user.
 */
package canvas.connector;

import cn.hutool.setting.Setting;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
//import javax.websocket.Session;

/**
 * This class is responsible for sending email to the user. Need to import
 * activation.jar and mail.jar
 *
 * @author Jianqing Gao
 */
public class Mailer
{

    /**
     * Send an email use the given information.
     *
     * @param host This hosting address of the email
     * @param port The mail port.
     * @param userName The email accounts user name
     * @param password The password of the email account.
     * @param toAddress Theaddress send to
     * @param subject The subject
     * @param message The HTML message to be sent to client.
     * @throws AddressException
     * @throws MessagingException
     */
    public static void sendEmail(String host, String port,
            final String userName, final String password, String toAddress,
            String subject, String message) throws AddressException,
            MessagingException
    {
        //set mail session
        Session session = setUpMail(host, port, userName, password, false);
        // creates a new e-mail message
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(userName));
        InternetAddress[] toAddresses =
        {
            new InternetAddress(toAddress)
        };
        System.err.println(" ---> Message created, now writing content in message! <----");
        msg.setRecipients(Message.RecipientType.TO, toAddresses);
        msg.setSubject(subject);
        msg.setSentDate(new Date());
        // msg.setText(message + "\n ***Generated By Johnson Mail*** ");
        //msg.setSender(new InternetAddress("noreply@villagechemcats.com"));
        msg.setContent("" + message + "", "text/html");
        // sends the e-mail
        System.out.println(" -->Sending Email><--");
        Transport.send(msg);
        System.out.println("Done!! Thank you for using smart email!");
    }

    /**
     * Set up the mailing setting.
     *
     * @param host
     * @param port
     * @param userName
     * @param password
     * @param debug
     * @return
     */
    private static Session setUpMail(String host, String port,
            String userName, String password, boolean debug)
    {
        // sets SMTP server properties
        System.out.println(" ---->Sets SMTP server properties<----- ");
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.isSSL", "true");
        properties.put("mail.debug", Boolean.toString(debug));
        properties.put("mail.imap.ssl.enable", "true");
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        properties.put("mail.imap.auth.mechanisms", "XOAUTH2");
        // creates a new session with an authenticator
        System.out.println(" --->Logging In<---  ");
        Authenticator auth = new Authenticator()
        {
            @Override
            public PasswordAuthentication getPasswordAuthentication()
            {
                return new PasswordAuthentication(userName, password);
            }
        };

        System.out.println(" -->Setting Mail Instance<-- ");
        return Session.getInstance(properties, auth);
    }

    /**
     * Send an email using the server's default configuration.
     *
     * @param recipient The receiver of the email.
     * @param subject The subject of the email
     * @param content The HTML content of the email!
     * @throws javax.mail.MessagingException
     */
    public static void sendDefaultMail(String recipient, String subject, String content) throws MessagingException
    {
        
        Setting setting = new Setting("mail.setting");
        sendEmail(setting.get("host"), setting.get("port"), setting.get("user"), setting.get("pass"), recipient, subject, content);
    }

    public static String getMailFromDefaultMailServer(URL url) throws IOException
    {
        URLConnection webConnection;
        InputStream mailStream;
        String line;
        String content = "";
        //connection to email server
        webConnection = url.openConnection();
        //request confirm to send the server
        webConnection.connect();
        //download the mail content
        mailStream = webConnection.getInputStream();

        //write everything in a string
        try (BufferedReader read = new BufferedReader(new InputStreamReader(mailStream)))
        {
            while ((line = read.readLine()) != null)
            {
                content += line;
            }
        }
        //return the whole String
        return content;
    }

    public static void main(String[] args)
    {
        try
        {
            sendDefaultMail("edu@jianqinggao.com", "miao", "this is a miao mail.");
        } catch (Exception ex)
        {
            Logger.getLogger(Mailer.class.getName()).log(Level.SEVERE, "failed to send mail", ex);
        }
    }
}
