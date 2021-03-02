/*Editor: Johnson Gao 
 * Date This File Created: 2020-4-12 15:28:08
 * Description Of This Class:This is where will be connected to the SQL.
 */
package canvas.connector;

import canvas.model.RedirectHistory;
import canvas.model.URLProxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import canvas.model.User;
import canvas.utils.TimeConverter;
import cn.hutool.setting.Setting;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;

/**
 * This is where the website will connect to SQL.
 *
 * @author Jianqing Gao
 */
public class LuckyStorage implements AutoCloseable
{

    private Connection dbConn;
    private String dbName, host, username, password;
    private boolean useSSL;

    public LuckyStorage()
    {
        dbConn = null;
    }

    public LuckyStorage(String dbName, String host, String username, String password, boolean useSSL) throws SQLException, ClassNotFoundException
    {
        establishConnection(dbName, host, username, password, useSSL);
        this.dbName = dbName;
        this.host = host;
        this.username = username;
        this.password = password;
        this.useSSL = useSSL;
    }

    /**
     * ****************************
     ************************************
     *************USERS **********************************
     * ****************************************************
     */
    //////////SELECTION/////////////
    /**
     *
     * @param email
     * @return
     * @throws java.sql.SQLException
     */
    public User selectUserByEmail(String email) throws SQLException
    {
        PreparedStatement ps = dbConn.prepareStatement("SELECT * FROM users WHERE email=?");
        ps.setString(1, email);
        ResultSet rs = ps.executeQuery();

        if (rs.next())
        {
            return readAUserFromRS(rs);
        } else
        {
            return null;
        }
    }

    /**
     * This method is used to check user's credential.
     *
     * @param email
     * @param password
     * @return
     * @throws SQLException
     */
    public User selectUserByEmailPassword(String email, String password) throws SQLException
    {
        String dbQuary = "SELECT * FROM users WHERE email=? AND password=?";
        PreparedStatement ps = dbConn.prepareStatement(dbQuary);
        ResultSet rs;
        ps.setString(1, email);
        ps.setString(2, password);
        rs = ps.executeQuery();
        if (rs.next())
        {
            return readAUserFromRS(rs);
        } else
        {
            return null;
        }
    }

    public boolean isUserExistsByEmail(String email) throws SQLException
    {
        return selectUserByEmail(email) != null;
    }

    private User readAUserFromRS(ResultSet rs) throws SQLException
    {
        return new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getString(7), rs.getBoolean(8));
    }

    //////////////////INSERTION////////////////////////
    /**
     * This is to insert a user into the designated database.
     *
     * @param user
     * @return The user to be inserted.
     * @throws java.sql.SQLException
     */
    public int insertIntoUsers(User user) throws SQLException
    {
        String sql = "INSERT INTO users VALUES(?,?,?,?,?,?,?,?)";
        PreparedStatement ps = dbConn.prepareStatement(sql);
        ps.setString(1, user.getEmail());
        ps.setString(2, user.getUsername());
        ps.setString(3, user.getPassword());
        ps.setString(4, user.getCanvasAuth());
        ps.setString(5, user.getGoogleId());
        ps.setInt(6, user.getStatus());
        ps.setString(7, user.getTimeZone());
        ps.setBoolean(8, Boolean.FALSE);
        return ps.executeUpdate();
    }

    ///////////////UPDATE///////////////////
    public int updateUserStatusByEmail(String email, int status) throws SQLException
    {
        PreparedStatement ps = dbConn.prepareStatement("UPDATE users SET status=? WHERE email=?");
        ps.setInt(1, status);
        ps.setString(2, email);
        return ps.executeUpdate();
    }

    /**
     * Update the email of the user stored in database identified by its old
     * email.
     *
     * @param oldEmail
     * @param newEmail
     * @return
     * @throws SQLException
     */
    public int updateUserEmailByEmail(String oldEmail, String newEmail) throws SQLException
    {
        PreparedStatement ps = dbConn.prepareStatement("UPDATE users SET email=? WHERE email=?");
        ps.setString(1, newEmail);
        ps.setString(2, oldEmail);
        return ps.executeUpdate();
    }

    public int updateUserNameByEmail(String username, String email) throws SQLException
    {
        PreparedStatement ps = dbConn.prepareStatement("UPDATE users SET username=? WHERE email=?");
        ps.setString(1, username);
        ps.setString(2, email);
        return ps.executeUpdate();
    }

    public int updateUserPasswordByEmail(String password, String email) throws SQLException
    {
        PreparedStatement ps = dbConn.prepareStatement("UPDATE users SET password=? WHERE email=?");
        ps.setString(1, password);
        ps.setString(2, email);
        return ps.executeUpdate();
    }

    public int updateUserCanvasAPIByEmail(String api, String email) throws SQLException
    {
        PreparedStatement ps = dbConn.prepareStatement("UPDATE users SET canvasAuth=? WHERE email=?");
        ps.setString(1, api);
        ps.setString(2, email);
        return ps.executeUpdate();
    }

    //////////////////////////////////////////////////////////
    //////////////////VERIFY BASH/////////////////////////
    ///////////////////////////////////////
    ////////////////////////////////////////////////////////
    /////////////////////SELECTION///////////////////
    /**
     * Select the bashcode registered with a email.
     *
     * @param email The email to be in the query.
     * @return The bashcode registered with the email. Will return
     * <code>null</code> if no such condition exists.
     * @throws SQLException
     */
    public String selectBashFromVerifyBashByEmail(String email) throws SQLException
    {
        PreparedStatement ps = dbConn.prepareStatement("SELECT bash FROM verifyBash WHERE email=?");
        ResultSet rs;
        ps.setString(1, email);
        rs = ps.executeQuery();
        if (rs.next())
        {
            return rs.getString("bash");
        } else
        {
            return null;
        }
    }

    public String selectEmailFromVerifyBashByBash(String bash) throws SQLException
    {
        PreparedStatement ps = dbConn.prepareStatement("SELECT email FROM verifyBash WHERE bash=?");
        ResultSet rs;
        ps.setString(1, bash);
        rs = ps.executeQuery();
        if (rs.next())
        {
            return rs.getString(1);
        } else
        {
            return null;
        }
    }

    /**
     * See if an email is registered within the bashtable.
     *
     * @param email The email to be checked.
     * @return <code>true</code> if the email exists. <code>false</code>
     * otherwise.
     * @throws SQLException
     */
    public boolean isEmailRegisteredInVerifyBash(String email) throws SQLException
    {
        return selectBashFromVerifyBashByEmail(email) != null;
    }

    //////////////////INSERTION//////////////////
    public int insertIntoVerifyBash(String email, String bash) throws SQLException
    {
        PreparedStatement ps = dbConn.prepareStatement("INSERT INTO verifyBash VALUES(?,?)");
        ps.setString(1, email);
        ps.setString(2, bash);
        return ps.executeUpdate();
    }

    //////////////DELETION///////////////
    public int deleteFromVerifyBash(String bash) throws SQLException
    {
        PreparedStatement ps = dbConn.prepareStatement("DELETE FROM verifyBash WHERE bash=?");
        ps.setString(1, bash);
        return ps.executeUpdate();
    }

    ////////////UPDATE////////////////////
    public int updateVerifyBash(String email, String bash) throws SQLException
    {
        PreparedStatement ps = dbConn.prepareStatement("UPDATE verifyBash SET bash=? WHERE email=?");
        ps.setString(1, bash);
        ps.setString(2, email);
        return ps.executeUpdate();
    }

    ////////////////CONDITIONAL//////////////
    /**
     * Conditionally update the verify bash table. If the user exists in the
     * bashtable, UPDATE method will be used. Otherwise, INSERT method will be
     * used.
     *
     * @param email The email of the account to be checked.
     * @param bash The bachcode to be checked.
     * @return Rows affected in the table.
     * @throws SQLException
     */
    public int smartInsertVerifyBash(String email, String bash) throws SQLException
    {

        return insertIntoVerifyBash(email, bash);

    }

    public boolean verifyUser_VerifyBash(String bash) throws SQLException
    {
        String email = selectEmailFromVerifyBashByBash(bash);
        if (email == null)
        {
            return Boolean.FALSE;
        } else
        {
            deleteFromVerifyBash(bash);
            //pass verification, update to active.
            updateUserStatusByEmail(email, User.STATUS_ACTIVE);
            return Boolean.TRUE;
        }
    }

    ////////////////////////
    //////////////
    //////////////REDIRECTS////////////////
    //////////////
    //////////////////
    /////////////////////////
    /**
     * Insert a redirect link into record.
     *
     * @param proxy
     * @return
     * @throws SQLException
     */
    public int insertIntoRedirects(URLProxy proxy) throws SQLException
    {
        PreparedStatement ps = dbConn.prepareStatement("INSERT INTO redirects VALUES(?,?,?,?,?,?)");
        ps.setInt(1, proxy.getId());
        ps.setString(2, proxy.getName());
        ps.setString(3, TimeConverter.formatDateTime(proxy.getGeneratedDate()));
        ps.setString(4, proxy.getRedirectTo());
        ps.setString(5, proxy.getUserEmail());
        ps.setInt(6, proxy.getVisits());
        return ps.executeUpdate();
    }

    ///////////////UPDATE///////////
    public int updateNameByIdRedirects(String name, int id) throws SQLException
    {
        String sql = "UPDATE redirects SET name=? WHERE id=?";
        PreparedStatement ps = dbConn.prepareStatement(sql);
        ps.setString(1, name);
        ps.setInt(2, id);
        return ps.executeUpdate();
    }

    public int deleteFromRedirectsById(int id) throws SQLException
    {
        Statement s = dbConn.createStatement();
        return s.executeUpdate("DELETE FROM redirects WHERE id=" + id);
    }

    public int updateRedirectsToUrl(String url, int id) throws SQLException
    {
        PreparedStatement ps = dbConn.prepareStatement("UPDATE redirects SET tourl=? WHERE id=?");
        ps.setString(1, url);
        ps.setInt(2, id);
        return ps.executeUpdate();
    }

    public ArrayList<URLProxy> selectFromRedirectsByUserEmail(String email, String orderBY, String order) throws SQLException
    {
        String sql = "SELECT * FROM redirects WHERE useremail=? ORDER BY " + orderBY + " " + order;
        PreparedStatement ps = dbConn.prepareStatement(sql);
        ResultSet rs;
        ArrayList<URLProxy> list = new ArrayList<>();
        ps.setString(1, email);
        rs = ps.executeQuery();
        while (rs.next())
        {
            list.add(readProxyFromResultSet(rs));
        }
        return list;
    }

    public String selectUrlFromRedirectsByIdAndPlusOneOnVisit(int id) throws SQLException
    {
        Statement s = dbConn.createStatement();
        ResultSet rs = s.executeQuery("SELECT tourl,visited FROM redirects WHERE id=" + id);
        if (rs.next())
        {
            int visited = rs.getInt("visited");
            String url = rs.getString("tourl");
            visited++;
            s.executeUpdate("UPDATE redirects SET visited=" + visited + " WHERE id=" + id);
            return url;
        } else
        {
            return null;
        }
    }

    public int redirectUrlVisitsMinusOne(int id) throws SQLException
    {
        Statement s = dbConn.createStatement();
        ResultSet rs = s.executeQuery("SELECT visited FROM redirects WHERE id=" + id);
        //determine if there is any result
        if (rs.next())
        {
            int visits = rs.getInt("visited");
            visits--;
            return s.executeUpdate("UPDATE redirects SET visited=" + visits + " WHERE id=" + id);
        } else
        {
            return 0;
        }

    }

    /**
     * Select from redirects by its id.
     *
     * @param id The id of the redirect link
     * @return The Object of redirect
     * @throws SQLException
     */
    public URLProxy selectFromRedirectsById(int id) throws SQLException
    {
        Statement s = dbConn.createStatement();
        ResultSet rs = s.executeQuery("SELECT * FROM redirects WHERE id=" + id);
        //read result set
        if (rs.next())
        {
            return readProxyFromResultSet(rs);
        } else
        {
            return null;
        }
    }

    private URLProxy readProxyFromResultSet(ResultSet rs) throws SQLException
    {
        return new URLProxy(rs.getInt(1), rs.getString(2), TimeConverter.formatDateTime(rs.getString(3)), rs.getString(4), rs.getString(5), rs.getInt(6));
    }

    /////////////////////
    ////////////////
    /////////////REDIRECT HISTORY//////////////
    ///////////////
    ////////////////
    public int insertIntoRedirectHistory(RedirectHistory record) throws SQLException
    {
        PreparedStatement ps = dbConn.prepareStatement("INSERT INTO redirectHistory VALUES(?,?,?)");
        ps.setInt(1, record.getRedirectId());
        ps.setString(2, record.getIp());
        ps.setString(3, record.getFormattedDateTime());
        return ps.executeUpdate();
    }

    public RedirectHistory extractRedirectHistoryFromRS(ResultSet rs) throws SQLException
    {
        return new RedirectHistory(rs.getInt(1), rs.getString(2), rs.getString(3));
    }

    /**
     *
     * @param id
     * @return
     * @throws SQLException
     */
    public ArrayList<RedirectHistory> selectFromRedirectHistoryByRedirectId(int id) throws SQLException
    {
        ResultSet rs;
        Statement s = dbConn.createStatement();
        ArrayList<RedirectHistory> result = new ArrayList<>();

        rs = s.executeQuery("SELECT * FROM redirectHistory WHERE redirectId=" + id);

        while (rs.next())
        {
            result.add(extractRedirectHistoryFromRS(rs));
        }

        return result;
    }

    public int deleteFromRedirectHistory(int redirectId, String dateTime) throws SQLException
    {
        PreparedStatement ps = dbConn.prepareStatement("DELETE FROM redirectHistory WHERE redirectId=? AND redirectTime=?");
        ps.setInt(1, redirectId);
        ps.setString(2, dateTime);
        return ps.executeUpdate();
    }

    public int deleteFromRedirectHistoryById(int id) throws SQLException
    {
        return dbConn.createStatement().executeUpdate("DELETE FROM redirectHistory WHERE id=" + id);
    }

    ////////////////////////////////////////////////////
    //////////////////////////CONSTRUCTIONAL MEHTODS/////////////
    /////////////////////////////////////////
    /////////////////////////////////////////////
    public void establishConnection(String dbName, String host, String username, String password, boolean useSSL) throws SQLException, ClassNotFoundException
    {
        //NO this.dbConn = dbConn;
        String connectionURL = "jdbc:mysql://" + host + "/" + dbName;
        this.dbConn = null;
        //Find the driver and make connection;

        Class.forName("com.mysql.cj.jdbc.Driver"); //URL for new version jdbc connector.
        Properties properties = new Properties(); //connection system property
        properties.setProperty("user", username);
        properties.setProperty("password", password);
        properties.setProperty("useSSL", Boolean.toString(useSSL));//set this true if domain suppotes SSL
        //"-u root -p mysql1 -useSSL false"
        this.dbConn = DriverManager.getConnection(connectionURL, properties);
        //System.out.println("yah");
    }

    public static LuckyStorage getDefaultInstance() throws SQLException, ClassNotFoundException
    {
        Setting setting = new Setting("db.setting");
        return new LuckyStorage(setting.get("dbName"), setting.get("host"), setting.get("user"), setting.get("pass"), false);
    }

    /**
     * {@inheritDoc }
     *
     * @throws Exception
     */
    @Override
    public void close() throws Exception
    {
        this.dbConn.close();
    }

    public Connection getDbConn()
    {
        return dbConn;
    }

    public void setDbConn(Connection dbConn)
    {
        this.dbConn = dbConn;
    }

    public String getDbName()
    {
        return dbName;
    }

    public void setDbName(String dbName)
    {
        this.dbName = dbName;
    }

    public String getHost()
    {
        return host;
    }

    public void setHost(String host)
    {
        this.host = host;
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

    public boolean isUseSSL()
    {
        return useSSL;
    }

    public void setUseSSL(boolean useSSL)
    {
        this.useSSL = useSSL;
    }

    public static void main(String[] args)
    {
        try (LuckyStorage storage = LuckyStorage.getDefaultInstance())
        {
        } catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

}
