/*Editor: Johnson Gao 
 * Date This File Created: 2020-4-12 1:40:21
 * Description Of This Class: This is a place where user 's information will be stored.
 */
package canvas.model;

/**
 * This is a bean class where user's information will be stored.
 * @author Jianqing Gao
 */
public class User
{
    private String email,username, password,canvasAuth,googleId;
    private int status;
    private String timeZone;
    private boolean agentFileUser;
    public static final int STATUS_INACTIVE = 0, STATUS_ACTIVE = 1, STATUS_SUSPENDED = -1;

    /**
     * Creates a user with given cridentials.
     * @param email
     * @param username
     * @param password
     * @param canvasAuth
     * @param googleId 
     * @param status 
     */
    public User(String email, String username, String password, String canvasAuth, String googleId,int status)
    {
        this.email = email;
        this.username = username;
        this.password = password;
        this.canvasAuth = canvasAuth;
        this.googleId = googleId;
        this.status = status;
    }

    public User(String email, String username, String password, String canvasAuth, String googleId, int status, String timeZone) {
        this(email, username, password, canvasAuth, googleId, status);
        this.timeZone = timeZone;
    }

    public User(String email, String username, String password, String canvasAuth, String googleId, int status, String timeZone, boolean agentFileUser)
    {
        this.email = email;
        this.username = username;
        this.password = password;
        this.canvasAuth = canvasAuth;
        this.googleId = googleId;
        this.status = status;
        this.timeZone = timeZone;
        this.agentFileUser = agentFileUser;
    }
    
    
    
    /**
     * Creates an empty user.
     */
    public User()
    {
        this.email = null;
        this.username = null;
        this.password = null;
        this.canvasAuth = null;
        this.googleId = null;
    }
    
    

    //////////////////////SETS AND GETS///////////////////
    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getCanvasAuth()
    {
        return canvasAuth;
    }

    public void setCanvasAuth(String canvasAuth)
    {
        this.canvasAuth = canvasAuth;
    }

    public String getGoogleId()
    {
        return googleId;
    }

    public void setGoogleId(String googleId)
    {
        this.googleId = googleId;
    }

    public int getStatus()
    {
        return status;
    }

    public void setStatus(int status)
    {
        this.status = status;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public boolean isAgentFileUser()
    {
        return agentFileUser;
    }

    public void setAgentFileUser(boolean agentFileUser)
    {
        this.agentFileUser = agentFileUser;
    }
    
    
    
}
