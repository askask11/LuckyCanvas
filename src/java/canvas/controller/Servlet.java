/*Editor: Johnson Gao 
 * Date This File Created: 2020-4-12 3:46:41
 * Description Of This Class: This is a servet class. This is the controller of this application.
 */
package canvas.controller;

import canvas.connector.CanvasReader;
import canvas.connector.LuckyStorage;
import canvas.connector.Mailer;
import canvas.model.Assignment;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import canvas.model.Course;
import canvas.model.RedirectHistory;
import canvas.model.URLProxy;
import canvas.model.User;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import javax.imageio.ImageIO;
import org.json.simple.parser.ParseException;
import canvas.utils.Alerts;
import canvas.utils.Captcha;
import canvas.utils.Randomizer;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 *
 * @author app
 */
@WebServlet(name = "Servlet", urlPatterns
        =
        {
            "/index", "/SignUpEmbedded", "/SignUp", "/Verify", "/Userhome", "/ChooseAssignment", "/Logout", "/LuckyPage", "/UserSettingEmbedded",
            "/Captcha", "/AgentFile", "/AgentFileEmbedded", "/AgentFileDownload", "/MyRedirectsEmbedded", "/Redirect","/RedirectHistory",
            "/login_success"
        }, loadOnStartup = 1
)
public class Servlet extends HttpServlet
{

    /**
     * For getting the ip of client, this puts all conditions.
     */
    private static final String[] HEADERS_TO_TRY =
    {
        "X-Forwarded-For",
        "Proxy-Client-IP",
        "WL-Proxy-Client-IP",
        "HTTP_X_FORWARDED_FOR",
        "HTTP_X_FORWARDED",
        "HTTP_X_CLUSTER_CLIENT_IP",
        "HTTP_CLIENT_IP",
        "HTTP_FORWARDED_FOR",
        "HTTP_FORWARDED",
        "HTTP_VIA",
        "REMOTE_ADDR"
    };

    public static String host = "luckycanvas.cn";
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter())
        {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Servlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Servlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    protected void writeContent(HttpServletRequest request, HttpServletResponse response, String title, String content) throws IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter())
        {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>" + title + "</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1> " + title + "</h1>");
            out.print(content);
            out.println("</body>");
            out.println("</html>");
        }
    }

    protected void timeoutContent(HttpServletRequest request, HttpServletResponse response, boolean embedContent) throws IOException
    {
        writeContent(request, response, "Time out", " You are currently visiting restricted area."
                + "But it looks like you did not sign in or you have time out."
                + "Please <a href='index'" + (embedContent ? " target='_parent' " : "") + ">go back to index </a> and sign in again.");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String userPath = request.getServletPath();
        host = request.getServerName();
        switch (userPath)
        {
            case "/index":
            case "/SignUpEmbedded":
            case "/SignUp":
            case "/Verify":
                request.getRequestDispatcher(userPath + ".jsp").forward(request, response);
                break;
            case "/Userhome":
                processUserhomeGET(request, response);
                break;
            case "/UserSettingEmbedded":
            case "/AgentFile":
            case "/login_success":
                request.getRequestDispatcher("/WEB-INF" + userPath + ".jsp").forward(request, response);
                break;
            case "/ChooseAssignment":
                processChooseAssignmentGET(request, response);
                break;
            case "/Logout":
                processLogoutGET(request, response);
                break;
            case "/LuckyPage":
                processLuckyPageGET(request, response);
                break;
            case "/Captcha":
                processCaptchaGET(request, response);
                break;
            case "/MyRedirectsEmbedded":
                processMyRedirectsEmbeddedGET(request, response);
                break;
            case "/Redirect":
                processRedirectsGET(request, response);
                break;
            case "/RedirectHistory":
                processRedirectHistoryGET(request, response);
                break;
            default:
                processRequest(request, response);
                break;
        }

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        //processRequest(request, response);
        String userPath = request.getServletPath();
        switch (userPath)
        {
            case "/SignUp":
                processSignUpPOST(request, response);
                break;
            case "/index":
                processIndexPOST(request, response);
                break;
            case "/UserSettingEmbedded":
                processUserSettingEmbeddedPOST(request, response);
                break;
            case "/AgentFileEmbedded":
                processAgentFileEmbeddedPOST(request, response);
                break;
            case "/AgentFileDownload":
                processAgentFileDownload(request, response);
                break;
            
            default:
                processRequest(request, response);
                break;
        }
    }
    
    protected void processRedirectHistoryGET(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        String idString = request.getParameter("id");
        String action = request.getParameter("action");
        String redirectHistoryMessage = null;
        int id;
        if(user != null)
        {
            ///user logged in
            try(LuckyStorage storage = LuckyStorage.getDefaultInstance())
            {
                //parse the id into a number
                id = Integer.parseInt(idString);
                
                //determine the action
                
                if(action!=null)
                {
                    if("delete".equals(action))
                    {
                        String datetime = request.getParameter("datetime");
                        int rowsDeleted=storage.deleteFromRedirectHistory(id, datetime);
                        if(rowsDeleted == 1)
                        {
                            redirectHistoryMessage = Alerts.successMessage("Deleted", "A record is deleted.");
                        }else
                        {
                            redirectHistoryMessage = Alerts.infoMessage("Maybe deleted", (rowsDeleted >1 ?"More than a row is deleted " : " Deletion may be successful.") );
                        }
                    }
                }
                
                
                //get the link accordingly
                
                
                
                URLProxy px = storage.selectFromRedirectsById(id);
                
                ArrayList<RedirectHistory> records = storage.selectFromRedirectHistoryByRedirectId(id);
                
                //pass the object to the webpage
                session.setAttribute("redirectHistoryMessage", redirectHistoryMessage);
                session.setAttribute("proxy", px);
                
                session.setAttribute("dbObj", records);
                
                //forward
                request.getRequestDispatcher("/WEB-INF" + request.getServletPath() + ".jsp").forward(request, response);
                
            }catch(Exception e)
            {
                e.printStackTrace();
                session.setAttribute("myRedirectEmbeddedMessage", Alerts.dangerMessage("Exception", "Sorry, we failed to load your history record"));
                response.sendRedirect(request.getContextPath() + "/AgentFile");
            }
            
            
            
            
        }else
        {
            //user not logged in
            timeoutContent(request, response, false);
        }
    }

    protected void processRedirectsGET(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        String idString = request.getParameter("id");//get the ?id= value
        String redirectTag = null;
        String redirectMessage = null;
        System.out.println("Objects created");
        //no id
        if (idString != null)
        {
            //connect to db
            try (LuckyStorage storage = LuckyStorage.getDefaultInstance())
            {
                System.out.println("Connected to db");
                int id = Integer.parseInt(idString);
                ////try to get the link according to the id
                String toUrl = storage.selectUrlFromRedirectsByIdAndPlusOneOnVisit(id);
                if (toUrl != null)
                {
                    //////////VALID REDIRECT//////////
                    System.out.println("canvas.controller.Servlet.processRedirectsGET()");
                    
                    
                    redirectTag = "<meta http-equiv=\"Refresh\" content=\"0; url=" + (toUrl.isEmpty() ? "about:blank" : toUrl) + "\" />";
                    redirectMessage = "Redirecting... If not responding, click <a href=\"" + (toUrl.isEmpty() ? "about:blank" : toUrl) + "\">here</a><br>"
                            + "System by Lucky Canvas";

                    String ip = getClientIpAddress(request);
                    RedirectHistory history = new RedirectHistory(id, ip, LocalDateTime.now(ZoneId.of("UTC")));
                    storage.insertIntoRedirectHistory(history);
                    //System.out.println("canvas.controller.Servlet.processRedirectsGET()");
                } else
                {

                    /////////LINK NOT FOUND///////////
                    redirectTag = "<meta http-equiv=\"Refresh\" content=\"3; url=https://"+host+"\" />";
                    redirectMessage = "This link does not exist. The id does not point to other websites. You will be redirected to our main site shortly.<br>"
                            + "If you think this is an error, please contact support@gaogato.com!<br>"
                            + "Lucky Canvas System";
                }
            } catch (NumberFormatException nfe)
            {

                ///////////INVALID ID FORMAT//////////////
                redirectTag = "<meta http-equiv=\"Refresh\" content=\"3; url=https://"+host+"\" />";
                redirectMessage = "This link is invalid. You will be redirected to our main site shortly.<br>"
                        + "If you think this is an error, please contact support@gaogato.com!";
            } catch (Exception e)
            {
                /////////////UNKNOWN WORLD///////////////
                e.printStackTrace();
                redirectMessage = "It looks like you just encountered a glitch in our website. Please contact support@gaogato.com or try later." + e;
            }
        } else
        {
            redirectTag = "<meta http-equiv=\"Refresh\" content=\"3; url=https://"+host+" />";
            redirectMessage = "This link is invalid. You will be redirected to our main site shortly.<br>"
                    + "If you think this is an error, please contact support@gaogato.com!";
        }
        session.setAttribute("redirectTag", redirectTag);
        session.setAttribute("redirectMessage", redirectMessage);
        //ystem.out.println("wow, forwarding");
        request.getRequestDispatcher("/WEB-INF" + request.getServletPath() + ".jsp").forward(request, response);
    }

    protected void processMyRedirectsEmbeddedGET(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        //get the default session of the user
        HttpSession session = request.getSession();
        //get login info
        User user = (User) session.getAttribute("user");
        String action = request.getParameter("action");
        if (user == null)
        {
            timeoutContent(request, response, true);
        } else
        {
            //get what should this table be arragnged by
            String sort = request.getParameter("sort");//parameter pass down

            //split the received order par
            String orderBY;
            String order;
            String sortSessionValue = (String) session.getAttribute("tableSort");
            //if there is no parameter passed down, use name by default.
            if (sort == null)
            {
                if (sortSessionValue == null)
                {
                    sort = "name.DESC";
                } else
                {
                    sort = sortSessionValue;
                }

            }
            System.out.println(sort + " sort");
            //split the param into a.b
            String[] sortarray = sort.split(Pattern.quote("."));
            //System.out.println(Arrays.toString(sortarray));
            orderBY = sortarray[0];
            order = sortarray[1];

            //check for illegal inputs
            switch (orderBY)
            {
                case "name":
                case "generatedTime":
                case "visited":
                case "id":
                    break;
                default:
                    orderBY = "name";
                    break;
            }

            switch (order)
            {
                case "ASC":
                case "DESC":
                    break;
                default:
                    order = "DESC";
                    break;
            }

            //try to connect to database.
            try (LuckyStorage storage = LuckyStorage.getDefaultInstance())
            {
                //on the page, use a javascript to get the offset of the user timezone
                long offset;
                try
                {

                    //check if the offset is a real number
                    offset = Long.parseLong(request.getParameter("offset"));
                } catch (NumberFormatException nfe)
                {
                    //if unable to detect any offset, then use UTC time!
                    offset = 0;
                }

                //do insertion here
                switch (action)
                {
                    case "insert":
                        insertIntoRedirects(request, response, session, offset, user, storage);
                        break;
                    case "update-name":
                        updateRedirects(request, storage);
                        break;
                    case "delete":
                        deleteFromRedirects(request, storage, session);
                        break;
                    case "update-url":
                        updateRedirectsUrl(request, storage, session);
                    default:
                        break;
                }

                //pass the offset 
                session.setAttribute("tableSort", sort);
                session.setAttribute("offset", offset);
                session.setAttribute("dbObj", storage.selectFromRedirectsByUserEmail(user.getEmail(), orderBY, order));
                //session.setAttribute("myRedirectEmbeddedMessage", user);

            } catch (Exception e)
            {
                e.printStackTrace();
                session.setAttribute("myRedirectEmbeddedMessage", Alerts.dangerMessage("Failed to connect", "Please try later."));
            }
            request.getRequestDispatcher("/WEB-INF" + request.getServletPath() + ".jsp").forward(request, response);

        }

    }

    private String getClientIpAddress(HttpServletRequest request)
    {
        for (String header : HEADERS_TO_TRY)
        {
            String ip = request.getHeader(header);
            if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip))
            {
                return ip;
            }
        }

        return request.getRemoteAddr();
    }

    private void updateRedirectsUrl(HttpServletRequest request, LuckyStorage storage, HttpSession session) throws SQLException
    {
        int id = Integer.parseInt(request.getParameter("id"));
        storage.updateRedirectsToUrl(request.getParameter("url"), id);
        session.setAttribute("myRedirectEmbeddedMessage", Alerts.successMessage("Success", "The target url of this link has been updated"));
    }

    private void deleteFromRedirects(HttpServletRequest request, LuckyStorage storage, HttpSession session) throws SQLException
    {
        int id = Integer.parseInt(request.getParameter("id"));
        storage.deleteFromRedirectsById(id);
        int recordsDeleted = storage.deleteFromRedirectHistoryById(id);
        session.setAttribute("myRedirectEmbeddedMessage", Alerts.successMessage("Success", "One redirect link has been deleted, " + (recordsDeleted==0?"": recordsDeleted+ " line" + (recordsDeleted==1?"" :"s" ) + " of visit history " + (recordsDeleted==1?"is":"are") + " deleted as well.")));
    }

    private void updateRedirects(HttpServletRequest request, LuckyStorage storage) throws SQLException
    {
        HttpSession session = request.getSession();
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        storage.updateNameByIdRedirects(name, id);
        session.setAttribute("myRedirectEmbeddedMessage", Alerts.successMessage("Updated", "You have successfully changed the name of an redirect!"));
    }

    private void insertIntoRedirects(HttpServletRequest request, HttpServletResponse response, HttpSession session, long offset, User user, LuckyStorage storage) throws SQLException
    {
        String name = request.getParameter("name");
        String url = request.getParameter("url");
        String appendGoogleDoc = request.getParameter("appendGoogleDoc");
        boolean doAppendGoogleDoc;
        //see if google doc appending is on
        if (appendGoogleDoc != null)
        {
            doAppendGoogleDoc = appendGoogleDoc.equals("on");
        } else
        {
            doAppendGoogleDoc = false;
        }
        //read user email
        String email = user.getEmail();

        //append my stuff
        url += doAppendGoogleDoc ? "?embedded=true" : "";

        URLProxy proxy = new URLProxy(Randomizer.randomInt(0, 9999999), name, LocalDateTime.now(ZoneId.of("UTC")), url, email, 0);

        storage.insertIntoRedirects(proxy);
        session.setAttribute("myRedirectEmbeddedMessage", Alerts.successMessage("Success", "Your link has been saved."));

    }

    protected void processAgentFileDownload(HttpServletRequest request, HttpServletResponse response)
    {
        HttpSession session = request.getSession();
        int type;
        int urltype;

        type = Integer.parseInt(request.getParameter("type"));//type of file

        urltype = Integer.parseInt((String) session.getAttribute("urltype") == null ? request.getParameter("urltype") : session.getAttribute("urltype").toString());

        String url = request.getParameter("url");
        User user = (User) session.getAttribute("user");
        if (user != null)
        {

            String title = request.getParameter("title");
            URL fileURL = getClass().getResource(type == 1 ? "embeddedFile.html" : "redirectFile.html");
            String line;
            String content = "";
            try (BufferedReader br = new BufferedReader(new InputStreamReader(fileURL.openStream())))
            {
                while ((line = br.readLine()) != null)
                {
                    content += line;
                }

                //content input complete
                content = content.replace("${title}", title).replace("${url}", url + (urltype == 0 ? "?embedded=true" : ""));

                response.setContentType("application/octet-stream");
                response.setHeader("Content-Disposition", "attachment; filename=" + title + ".html ");
                try (PrintWriter pw = response.getWriter())
                {
                    pw.write(content);
                } catch (Exception e)
                {
                    e.printStackTrace();
                }

            } catch (Exception e)
            {
                e.printStackTrace();
            }

        }
    }

    private void agentFileDownload1()
    {

    }

    protected void processAgentFileEmbeddedPOST(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String type = request.getParameter("type");
        String url = request.getParameter("url");
        String url_type = request.getParameter("urltype");
        final int TYPE_HTML_CODE = 0, TYPE_IFRAME_FILE = 1, TYPE_REDIRECT_FILE = 2,
                URLTYPE_GOOGLE_DOC = 0, URL_TYPE_OTHERS = 1;
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Captcha captcha = (Captcha) session.getAttribute("captcha");
        String userinputcapt = request.getParameter("captcha");
        boolean passAgentFile = false;

        String agentFileEmbedded = "";
        //determine null user
        if (user != null && captcha != null)
        {
            if (user.isAgentFileUser())
            {
                if (captcha.getBody().equalsIgnoreCase(userinputcapt))
                {
                    if (LocalDateTime.now(ZoneId.of("UTC")).isBefore(captcha.getExpireTime()))
                    {
                        int typeInt = Integer.parseInt(type);
                        switch (typeInt)
                        {
                            //servlet handle iframe generation
                            case TYPE_HTML_CODE:
                                passAgentFile = true;
                                agentFileHtmlCode(request, response);
                                break;

                            //let jstl handle these two
                            case TYPE_IFRAME_FILE:
                            case TYPE_REDIRECT_FILE:
                                session.setAttribute("urltype", url_type);
                                passAgentFile = true;
                                //request.getRequestDispatcher("/WEB-INF" + request.getServletPath() + ".jsp").forward(request, response);
                                break;
                        }

                    } else
                    {
                        //captcha is expired
                        agentFileEmbedded = Alerts.dangerMessage("Verification code is null", " Please re-enter your verification code.");
                    }
                } else
                {
                    //captcha input wrong
                    agentFileEmbedded = Alerts.warningMessage("Verification code is wrong", " Please retry to put your verification code!");
                }

            } else
            {
                //user is not authorized to use this function
                agentFileEmbedded = Alerts.dangerMessage("You are not invited yet.", "This function is for internal testing only. contact support@gaogato.com if you are interested in.");
            }
            session.setAttribute("agentFileEmbedded", agentFileEmbedded);
            session.setAttribute("passAgentFile", passAgentFile);
            request.getRequestDispatcher("/WEB-INF" + request.getServletPath() + ".jsp").forward(request, response);

        } else
        {
            //user is null
            timeoutContent(request, response, true);
        }

    }

    private void agentFileHtmlCode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        int url_type = Integer.parseInt(request.getParameter("urltype"));
        HttpSession session = request.getSession();
        String url = request.getParameter("url");
        String agentFileOutputCode;

        //this is a google doc
        agentFileOutputCode = "<iframe src=\"" + url + (url_type == 0 ? "?embedded=true" : "") + "\" style=\"width: 100%; height: 500px;\" >Your browser does not support iframe</iframe>";

        session.setAttribute("agentFileEmbedded", Alerts.successMessage("Successful!", "Please copy the generated code below to an HTML editor."));
        session.setAttribute("agentFileOutputCode", agentFileOutputCode);
        session.setAttribute("passAgentFile", true);

        request.getRequestDispatcher("/WEB-INF" + request.getServletPath() + ".jsp").forward(request, response);
    }

    protected void processCaptchaGET(HttpServletRequest request, HttpServletResponse response)
    {
        HttpSession session = request.getSession();
        //String userPath = request.getServletPath();

        //decide the returning datatype and avoid potential security issues.
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");

        Captcha captcha = Captcha.generateCaptcha(100/*width*/, 35 /*height*/, 4);

        try (OutputStream os = response.getOutputStream())
        {
            captcha.setExpireTime(LocalDateTime.now(ZoneId.of("UTC")).plusMinutes(5));//set expire 5 mins later.
            session.setAttribute("captcha", captcha);//user may only hold one captcha at a time. The captcha will be used app wise.
            //no forward required.
            //write the image onto the page
            ImageIO.write(captcha.getImage(), "jpg", os);

        } catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    protected void processUserSettingEmbeddedPOST(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        //HttpSession session = request.getSession();
        String action = request.getParameter("action");
        switch (action)
        {
            case "update-username":
                updateUsername(request, response);
                break;
            case "update-password":
                updatePassword(request, response);
                break;
            case "update-api":
                updateApi(request, response);
                break;
            default:
                System.err.println("error " + action);
                break;
        }

    }

    /**
     * Receive information from the webpage and update the username with db
     * connection.
     *
     * @param request
     * @param response
     * @throws IOException
     */
    private void updateApi(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        //get cridentials
        String api = request.getParameter("apikey");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String path = request.getServletPath();
        //check bean status
        if (user != null)
        {
            String email = user.getEmail();
            try (LuckyStorage storage = LuckyStorage.getDefaultInstance())
            {
                //submit to datanase
                storage.updateUserCanvasAPIByEmail(api, email);
                user.setCanvasAuth(api);
                session.setAttribute("userSettingMessage", Alerts.successMessage("Successful!", " Your account has been updated."));
            } catch (Exception e)
            {
                e.printStackTrace();
                session.setAttribute("userSettingMessage", Alerts.dangerMessage("Exception!", " There is a exception happening!"));
            }
            request.getRequestDispatcher("/WEB-INF" + path + ".jsp").forward(request, response);
        } else
        {
            timeoutContent(request, response, true);
        }
    }

    /**
     * Receive information from the webpage and update the username with db
     * connection.
     *
     * @param request
     * @param response
     * @throws IOException
     */
    private void updatePassword(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        //get cridentials
        String password = request.getParameter("password");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String path = request.getServletPath();
        //check bean status
        if (user != null)
        {
            String email = user.getEmail();
            try (LuckyStorage storage = LuckyStorage.getDefaultInstance())
            {
                //submit to datanase
                storage.updateUserPasswordByEmail(password, email);
                user.setPassword(password);
                session.setAttribute("userSettingMessage", Alerts.successMessage("Successful!", " Your account has been updated."));
            } catch (Exception e)
            {
                e.printStackTrace();
                session.setAttribute("userSettingMessage", Alerts.dangerMessage("Exception!", " There is a exception happening!"));
            }
            request.getRequestDispatcher("/WEB-INF" + path + ".jsp").forward(request, response);
        } else
        {
            timeoutContent(request, response, true);
        }
    }

    /**
     * Receive information from the webpage and update the username with db
     * connection.
     *
     * @param request
     * @param response
     * @throws IOException
     */
    private void updateUsername(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        //get cridentials
        String username = request.getParameter("username");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String path = request.getServletPath();
        //check bean status
        if (user != null)
        {
            String email = user.getEmail();
            try (LuckyStorage storage = LuckyStorage.getDefaultInstance())
            {
                //submit to datanase
                storage.updateUserNameByEmail(username, email);
                user.setUsername(username);
                session.setAttribute("userSettingMessage", Alerts.successMessage("Successful!", " Your account has been updated."));
            } catch (Exception e)
            {
                e.printStackTrace();
                session.setAttribute("userSettingMessage", Alerts.dangerMessage("Exception!", " There is a exception happening!"));
            }
            request.getRequestDispatcher("/WEB-INF" + path + ".jsp").forward(request, response);
        } else
        {
            timeoutContent(request, response, true);
        }
    }

    protected void processLuckyPageGET(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        //get attributes.
//        String userPath = request.getServletPath();
        String action = request.getParameter("action");//default is confirm
//        String goodGradeString = request.getParameter("goodGrade");
//        String okGradeString = request.getParameter("okGrade");
        int index = Integer.parseInt(request.getParameter("index"));
        String chooseAssignmentMessage;
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        //read the arrayl

        //check if user is timed out
        if (user != null)
        {
            ArrayList<Assignment> assignments = (ArrayList<Assignment>) session.getAttribute("assignments");

            if (assignments == null || assignments.isEmpty())
            {
                timeoutContent(request, response, false);
            } else
            {
                //run get assignments and other main logic
                Assignment assignment = assignments.get(index);

                try
                {
                    //determine grading type
                    // GradingType type = assignment.getType();

                    ///TODO: write a canvas reader to retrive user's real grade and put it here.
                    luckyPagePercentGrade(request, response, session, assignment);
                } catch (InterruptedException | ParseException ex)
                {
                    Logger.getLogger(Servlet.class.getName()).log(Level.SEVERE, null, ex);
                    chooseAssignmentMessage = Alerts.dangerMessage("Exception happened", "There is an unexptcted ex. happened in our end. Please try again later.");
                    session.setAttribute("chooseAssignmentMessage", chooseAssignmentMessage + ex);
                    response.sendRedirect(request.getContextPath() + "/ChooseAssignment");
                }

            }

        } else
        {
            timeoutContent(request, response, false);
        }

    }

    private void luckyPagePercentGrade(HttpServletRequest request, HttpServletResponse response, HttpSession session, Assignment assignment) throws IOException, InterruptedException, ParseException, ServletException
    {
        //check if user has entered "numbers" instead of "NUMBAS".
        try
        {
//            double goodGrade = Double.parseDouble(request.getParameter("goodGrade"));
//            double okGrade = Double.parseDouble(request.getParameter("okGrade"));
//            String delay = request.getParameter("delay");
            Course course = (Course) session.getAttribute("on_course");
            User user = (User) session.getAttribute("user");

            session.setAttribute("assignment", assignment);
            HashMap<String, String> infoMap = CanvasReader.getGrade(course.getCourse_id() + "", assignment.getAssignment_id() + "", course.getUser_id() + "", user.getCanvasAuth());
//                session.setAttribute("goodGrade", goodGrade);
//                session.setAttribute("okGrade", okGrade);
            session.setAttribute("grade", infoMap.get("grade").replace('%', ' '));
            if (infoMap.get("workflow_state").equals("graded"))
            {
                request.getRequestDispatcher("/WEB-INF" + request.getServletPath() + ".jsp").forward(request, response);
            } else
            {
                session.setAttribute("chooseAssignmentMessage", Alerts.infoMessage("Not graded", " This assignment haven't graded yet."));
                response.sendRedirect(request.getContextPath() + "/ChooseAssignment");
            }
//                session.setAttribute("delay", delay);

        } catch (NumberFormatException nfe)
        {
            //return them to back to previous page
            session.setAttribute("chooseAssignmentMessage", Alerts.dangerMessage("Input mismatch", "Please only input numbers in provided field."));
            response.sendRedirect(request.getContextPath() + "/ChooseAssignment");
        } catch (NullPointerException npe)
        {
            //the user did not fill the infomation
            session.setAttribute("chooseAssignmentMessage", Alerts.dangerMessage("Info Missing", "Please fill in required information."));
            response.sendRedirect(request.getContextPath() + "/ChooseAssignment");
            npe.printStackTrace();
        }
    }

    protected void processLogoutGET(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        request.getSession().invalidate();
        response.sendRedirect(request.getContextPath() + "/index");
    }

    /**
     * Use get method to handle the request of choosing an assignment.This
     * usually happens during work-flow
     *
     * @param request
     * @param response
     * @throws java.io.IOException
     * @throws javax.servlet.ServletException
     */
    protected void processChooseAssignmentGET(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        //get required attributes
        HttpSession session = request.getSession();
        //get attribute user and "courses"(an arraylist of course), and user_id
        User user = (User) session.getAttribute("user");
        ArrayList<Course> courses = (ArrayList<Course>) session.getAttribute("courses");
        Integer id = (Integer) session.getAttribute("user_id");

        //get parameter:action and course index
        String action = request.getParameter("action");
        String order = request.getParameter("order");
        //check for null user 
        if (user == null)
        {
            timeoutContent(request, response, false);
        } else
        {
            Course course;
            //check for action
            if (action == null)
            {
                action = "refresh";
            }
            switch (action)
            {
                //if the action is confirm-flow or refresh, then retrive the ith course(course index) from the arraylist

                case "confirm-flow":
                    int index = Integer.parseInt(request.getParameter("course_index"));
                    course = courses.get(index);
                    session.setAttribute("on_course", course);//        store the current course  into the session use "on_course"
                    session.setAttribute("chooseAssignmentMessage", "");
                    break;
                case "refresh":
                case "confirm-return":
                default:
                    course = (Course) session.getAttribute("on_course");
                    break;

            }

            //loadAssignmentList(course.getCourse_id(), course.getUser_id(), session);
            ArrayList<Assignment> readUserAssignments;
            try
            {
                readUserAssignments = CanvasReader.readUserAssignments(id, course.getCourse_id(), user.getCanvasAuth() + "&order_by=" + order);

                session.setAttribute("assignments", readUserAssignments);
            } catch (ParseException | InterruptedException ex)
            {
                session.setAttribute("chooseAssignmentMessage", Alerts.dangerMessage("Error reading assignment", " There is currently an error reading your assignments."));
                Logger.getLogger(Servlet.class.getName()).log(Level.SEVERE, null, ex);
            }

//        //get the id of the course from the object, along with user_id, retrive the specific
//        //information about this course from the canvas API by calling CanvasReader
            //if the action is confirm-return, then just forward
            request.getRequestDispatcher("/WEB-INF" + request.getServletPath() + ".jsp").forward(request, response);
        }

    }

    /**
     * Use get method to process userhome.
     *
     * @param request The request being sent.
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    protected void processUserhomeGET(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String filter = request.getParameter("filter");
        String authCode;
        String userPath = request.getServletPath();
        String userhomeMessage = null;
        ArrayList<Course> courseList, finalCourseList;
        if (user == null)
        {
            timeoutContent(request, response, false);
        } else
        {
            authCode = user.getCanvasAuth();
            if (authCode == null)
            {
                //nah, no auth code, bye bye.
                request.getRequestDispatcher("/WEB-INF" + userPath + ".jsp").forward(request, response);
            } else
            {
                try
                {
                    int filterInt;
                    //read the list from API
                    courseList = CanvasReader.readUserCourses(authCode);

                    //load table in session
                    //test null filter(user first click)
                    if (filter == null || filter.isEmpty())
                    {
                        filterInt = LocalDate.now().getYear() - 1;
                        System.out.println("present year " + filterInt);
                    } else
                    {
                        if (filter.equals("all"))
                        {
                            filterInt = -1;
                        } else
                        {
                            filterInt = Integer.parseInt(filter);
                        }

                    }

                    //filter the array
                    if (filterInt != -1)
                    {
                        finalCourseList = new ArrayList<>();
                        for (int i = 0; i < courseList.size(); i++)
                        {
                            if (courseList.get(i).getStart_at().getYear() == filterInt)
                            {
                                //courseList.remove(courseList.get(i));
                                finalCourseList.add(courseList.get(i));
                            }
                        }

                        //store the user_id for later use.
                        if (!finalCourseList.isEmpty())
                        {
                            session.setAttribute("user_id", finalCourseList.get(0).getUser_id());
                        }

                        session.setAttribute("courses", finalCourseList);
                        //if the year is different then remove from list
                    } else
                    {
                        //store the user_id
                        if (!courseList.isEmpty())
                        {
                            session.setAttribute("user_id", courseList.get(0).getUser_id());
                        }
                        session.setAttribute("courses", courseList);
                    }

                    //return the list to the page
                } catch (Exception ex)
                {
                    //exception handling
                    Logger.getLogger(Servlet.class.getName()).log(Level.SEVERE, null, ex);
                    userhomeMessage = Alerts.dangerMessage("Reading failed!", "Please make sure you entered a correct, effective token."
                            + "If this continuously happends, please contact support@gaogato.com ");
                }
            }
            //set the attr in the session.
            session.setAttribute("userhomeMessage", userhomeMessage);
            request.getRequestDispatcher("/WEB-INF" + userPath + ".jsp").forward(request, response);
        }

    }

    /**
     * This is the post method for processing index page.
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    protected void processIndexPOST(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        String userPath = request.getServletPath();
        //HttpSession session = request.getSession();
        String action = request.getParameter("action");

        if (action == null)
        {
            request.getRequestDispatcher(userPath + ".jsp").forward(request, response);
        } else
        {
            if (action.equals("sign-in"))
            {
                //sign in into the index page
                indexSignIn(request, response);
            } else
            {
                request.getRequestDispatcher(userPath + ".jsp").forward(request, response);
            }
        }

    }

    /**
     * Sign in into the index page.
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void indexSignIn(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        //get params
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        User user;
        String signInMessage = null;
        HttpSession session = request.getSession();
        boolean backToIndex;
        //connect to db
        Captcha captcha = (Captcha) session.getAttribute("captcha");
        String userInputCode = request.getParameter("captcha");
        if (captcha != null)
        {
            if (userInputCode.equalsIgnoreCase(captcha.getBody()))
            {

                try (LuckyStorage storage = LuckyStorage.getDefaultInstance())
                {
                    //check
                    user = storage.selectUserByEmailPassword(email, password);
                    if (user == null)
                    {
                        //fail
                        signInMessage = Alerts.warningMessage("password/email wrong. ", "Please check your login info and try again!" + (email.isEmpty() ? " Email is empty" : "") + (password.isEmpty() ? " Password is empty" : ""));
                        backToIndex = true;
                    } else
                    {
                        //success
                        ///request.login(email, password);
                        session.setAttribute("user", user);
                        backToIndex = false;
                        //processUserhomeGET(request, response);
                        
                        //response.sendRedirect(request.getContextPath() + "/Userhome");
                        request.getRequestDispatcher("/WEB-INF/login_success.jsp").forward(request, response);
                    }
                } catch (Exception ex)
                {
                    //error
                    ex.printStackTrace();
                    signInMessage = Alerts.dangerMessage("Error connecting to database", " Please refresh the page and try again later.");
                    backToIndex = true;
                }
            } else
            {
                backToIndex = true;
                signInMessage = Alerts.dangerMessage("Incorrect code", " Please enter your verification code again!");
            }
        } else
        {

            backToIndex = true;
            signInMessage = Alerts.infoMessage("Something is wrong", " Please refresh this page.");
        }

        //show message
        session.setAttribute("signInMessage", signInMessage);
        if (backToIndex)
        {
            //back
            request.getRequestDispatcher(request.getServletPath() + ".jsp").forward(request, response);
        }

    }

    /**
     * Process the sign up page with post method. It means to sign user up.
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void processSignUpPOST(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        //get required attribute.
        String userPath = request.getServletPath();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String canvasAuth = request.getParameter("canvasauth");
        String email = request.getParameter("email");
        String timezone = request.getParameter("timezone");
        //read generated captcha

        HttpSession session = request.getSession();
        String signUpMessage;
        String bash;
        Captcha captcha = (Captcha) session.getAttribute("captcha");
        String userCaptchaInput = request.getParameter("captcha");
        if (!username.isEmpty() && !password.isEmpty() && !email.isEmpty())
        {
            //connect to database
            if (captcha != null)
            {
                //compare the user entered value and the body
                String code = captcha.getBody();
                if (userCaptchaInput.equals(code))
                {
                    //if now is before the expire time.
                    if (LocalDateTime.now(ZoneId.of("UTC")).isBefore(captcha.getExpireTime()))
                    {
                        try (LuckyStorage storage = LuckyStorage.getDefaultInstance())
                        {
                            if (storage.isUserExistsByEmail(email))
                            {
                                signUpMessage = Alerts.warningMessage("User exists", "The email is alredy registered! Please change another one.");
                            } else
                            {

                                ///////////COMPLETE REGISTERATION PROCESS////////////
                                //store in the db
                                signUpMessage = storage.insertIntoUsers(new User(email, username, password, canvasAuth, null, User.STATUS_INACTIVE, timezone)) == 1 ? Alerts.successMessage("Success", "Your account was successfully registered! Please check your email for verification.") : Alerts.infoMessage("Might be success", "There are some problems with our end. Would you please try again?");

                                //generate a bash
                                bash = Randomizer.generateBash();

                                //send an email to the user
                                Mailer.sendDefaultMail(email, "💯 Welcome Verification ❤", ""
                                        + "Dear " + username + " , \n"
                                        + "Welcome to use the lucky canvas! ✨\n"
                                        + "<br>Now, you just need to click on,"
                                        + "or copy-paste the following URL to your browser"
                                        + "to verify your account.\n"
                                        + "http://"+host+"/Verify?bash=" + bash
                                        + "\n Thank you!<br>"
                                        + "Sincerely,\n"
                                        + "<br>Jianqing Gao"
                                        + "Support email : support@gaogato.com ");

                                //register the bash into the database
                                storage.smartInsertVerifyBash(email, bash);

                                //clean up.
                            }

                        } catch (Exception e)
                        {
                            signUpMessage = Alerts.dangerMessage("Insertion Failed", "Please try again later.");
                            e.printStackTrace();
                        }
                    } else//code expired
                    {
                        signUpMessage = Alerts.dangerMessage("Timed Out", " Your verification code was timed out. Please enter again.");
                    }

                } else//code wrong
                {
                    signUpMessage = Alerts.dangerMessage("Verification code not right", " Wrong verification code.");
                }

            } else
            {
                //timeoutContent(request, response, false);
                signUpMessage = Alerts.dangerMessage("Content time out", " There is something wrong with our end, please try again later.");
            }

        } else
        {
            signUpMessage = Alerts.dangerMessage("Missing cridentials", " required information is missing. Please check your form.");
        }

        //clean up, output and forward
        session.setAttribute("signUpMessage", signUpMessage);

        request.getRequestDispatcher(userPath + ".jsp").forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo()
    {
        return "Short description";
    }// </editor-fold>

    public static void main(String[] args)
    {
        System.out.println(Arrays.toString("a.b".split(Pattern.quote("."))));
    }
}
